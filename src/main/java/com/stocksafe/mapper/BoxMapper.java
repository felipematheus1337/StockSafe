package com.stocksafe.mapper;


import com.stocksafe.dto.BoxDTO;
import com.stocksafe.dto.ClientDTO;
import com.stocksafe.model.Box;
import com.stocksafe.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class BoxMapper {

    private final ModelMapper mapper;

    public Box toModel(BoxDTO dto) {
        return mapper.map(dto, Box.class);
    }

    public BoxDTO toDTO(Box box) {
        return mapper.map(box, BoxDTO.class);
    }

    public List<BoxDTO> toDTOList(List<Box> boxList) {

        return boxList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Box> toEntityList(List<BoxDTO> dtoList) {

        return dtoList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
