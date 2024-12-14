package com.stocksafe.services;


import com.stocksafe.dto.BoxDTO;
import com.stocksafe.mapper.BoxMapper;
import com.stocksafe.model.Box;
import com.stocksafe.repositories.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper mapper;

    public void cadastrar(BoxDTO boxDTO) {

        Box box = this.mapper.toModel(boxDTO);
        this.boxRepository.save(box);
    }

    public List<BoxDTO> list() {

        return this.mapper.toDTOList(this.boxRepository.findAll());
    }
}
