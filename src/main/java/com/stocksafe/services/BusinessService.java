package com.stocksafe.services;

import com.stocksafe.dto.ItemDTO;
import com.stocksafe.mapper.ItemMapper;
import com.stocksafe.model.Box;
import com.stocksafe.model.Client;
import com.stocksafe.model.Item;
import com.stocksafe.model.enums.BoxCapacityStatus;
import com.stocksafe.model.enums.BoxStatus;
import com.stocksafe.repositories.BoxRepository;
import com.stocksafe.repositories.ClientRepository;
import com.stocksafe.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final ClientRepository clientRepository;
    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public Client assignItemsToClientAndBox(Long clientId, Long boxId, List<ItemDTO> itemDTOs) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Box box = boxRepository.findById(boxId)
                .orElseThrow(() -> new RuntimeException("Box not found"));

        if (box.getClient() != null) {
            throw new RuntimeException("Box is already occupied by another client");
        }

        box.setClient(client);
        boxRepository.save(box);

        int totalWeightOfItens = itemDTOs
                .stream()
                .map(i -> i.getWeight())
                .reduce(0, (subtotal, weight) -> subtotal + weight);

        var status =  this.verifyWeight(box, totalWeightOfItens);

        if (status != BoxCapacityStatus.VALID)
            throw new IllegalStateException(status.getMessage());


        List<Item> items = itemDTOs.stream()
                .map(itemDTO -> {
                    Item item = this.itemMapper.toModel(itemDTO);
                    item.setClient(client);
                    return item;
                })
                .collect(Collectors.toList());

        itemRepository.saveAll(items);

        client.setItens(items);

        clientRepository.save(client);

        return client;
    }

    private BoxCapacityStatus verifyWeight(Box box, int weightOfItens) {

        int boxMaximumCapacity = box.getMaxWeight();
        int actualWeight = box.getWeight();

        if (actualWeight >= boxMaximumCapacity) {
            box.setStatus(BoxStatus.FULL);
            return BoxCapacityStatus.OVER_CAPACITY;
        }

        int remainingWeight = boxMaximumCapacity - actualWeight;

        if (weightOfItens > remainingWeight) {
            return BoxCapacityStatus.EXCEEDS_REMAINING;
        }

        box.setStatus(BoxStatus.ENABLED);
        return BoxCapacityStatus.VALID;



    }

    @Async
    @Transactional
    public void verify() {

        Pageable pageable = PageRequest.of(0, 1000);
        Page<Box> page;

        do {

            page = this.boxRepository.findAll(pageable);
            page.getContent().forEach(b -> {

                LocalDateTime dataFinal = b.getPeriodoFinal();
                LocalDateTime dataAtual = LocalDateTime.now();

                if (dataFinal != null && dataAtual.isAfter(dataFinal)) {
                    b.setStatus(BoxStatus.DISABLED);
                }
            });
            this.boxRepository.saveAll(page.getContent());
            pageable = pageable.next();

        } while(page.hasNext());
    }


}
