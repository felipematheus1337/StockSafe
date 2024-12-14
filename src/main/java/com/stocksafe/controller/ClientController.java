package com.stocksafe.controller;


import com.stocksafe.dto.ClientDTO;
import com.stocksafe.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> cadastrarCliente(@RequestBody ClientDTO clientDTO) {
        return clientService.cadastrar(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> buscar(@PathVariable Long id) {

        return clientService.buscar(id);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> listar() {
        return clientService.list();
    }
}
