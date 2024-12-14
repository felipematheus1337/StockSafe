package com.stocksafe.mapper;


import com.stocksafe.dto.ClientDTO;
import com.stocksafe.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper mapper;

    public Client toModel(ClientDTO dto) {
        return mapper.map(dto, Client.class);
    }

    public ClientDTO toDTO(Client client) {
        return mapper.map(client, ClientDTO.class);
    }

    public List<ClientDTO> toDTOList(List<Client> clientList) {

        return clientList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Client> toEntityList(List<ClientDTO> dtoList) {

        return dtoList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
