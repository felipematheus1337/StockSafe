package com.stocksafe.services;


import com.stocksafe.dto.ClientDTO;
import com.stocksafe.mapper.ClientMapper;
import com.stocksafe.model.Client;
import com.stocksafe.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;



    public ClientDTO cadastrar(ClientDTO dto) {

        Client clientToRegister = this.mapper.toModel(dto);

        Client clientSaved =  this.clientRepository.save(clientToRegister);

        return this.mapper.toDTO(clientSaved);


    }

    @Cacheable(value = "clients", key = "#id")
    public ClientDTO buscar(Long id) {

        Optional<Client> optClient = this.clientRepository.findById(id);

       if (optClient.isEmpty())
           throw new RuntimeException("Client not found");

       return this.mapper.toDTO(optClient.get());

    }

    public List<ClientDTO> list() {

        return this.mapper.toDTOList(this.clientRepository.findAll());


    }



}
