package com.stocksafe.services;


import com.stocksafe.dto.BoxDTO;
import com.stocksafe.dto.ClientDTO;
import com.stocksafe.mapper.BoxMapper;
import com.stocksafe.model.Box;
import com.stocksafe.model.Client;
import com.stocksafe.repositories.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper mapper;

    public void cadastrar(BoxDTO boxDTO) {

        Box box = this.mapper.toModel(boxDTO);
        this.boxRepository.save(box);
    }

    @Cacheable(value = "boxes", key = "#id")
    public BoxDTO buscar(Long id) {

        Optional<Box> optBox = this.boxRepository.findById(id);

        if (optBox.isEmpty())
            throw new RuntimeException("Box not found");

        return this.mapper.toDTO(optBox.get());

    }

    public List<BoxDTO> list() {

        return this.mapper.toDTOList(this.boxRepository.findAll());
    }
}
