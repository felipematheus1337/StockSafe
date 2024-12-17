package com.stocksafe.controller;


import com.stocksafe.dto.BoxDTO;
import com.stocksafe.dto.ClientDTO;
import com.stocksafe.services.BoxService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoxController {


    private  BoxService boxService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCliente(@RequestBody BoxDTO dto) {

        this.boxService.cadastrar(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BoxDTO>> listar() {
        return ResponseEntity.ok(boxService.list());
    }
}
