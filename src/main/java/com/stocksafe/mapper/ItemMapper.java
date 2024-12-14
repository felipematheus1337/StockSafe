package com.stocksafe.mapper;


import com.stocksafe.dto.BoxDTO;
import com.stocksafe.dto.ItemDTO;
import com.stocksafe.model.Box;
import com.stocksafe.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ItemMapper {

    private final ModelMapper mapper;

    public Item toModel(ItemDTO dto) {
        return mapper.map(dto, Item.class);
    }

    public ItemDTO toDTO(Item box) {
        return mapper.map(box, ItemDTO.class);
    }

    public List<ItemDTO> toDTOList(List<Item> itemList) {

        return itemList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Item> toEntityList(List<ItemDTO> dtoList) {

        return dtoList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
