package com.stocksafe.controller;

import com.stocksafe.dto.ClientDTO;
import com.stocksafe.dto.ItemDTO;
import com.stocksafe.mapper.ClientMapper;
import com.stocksafe.model.Client;
import com.stocksafe.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;
    private final ClientMapper clientMapper;

    @PostMapping("/assign-items-to-client/{clientId}/box/{boxId}")
    public ResponseEntity<ClientDTO> assignItemsToClientAndBox(@PathVariable Long clientId,
                                                               @PathVariable Long boxId,
                                                               @RequestBody List<ItemDTO> itemDTOs) {
        Client client = businessService.assignItemsToClientAndBox(clientId, boxId, itemDTOs);

        return ResponseEntity.ok(clientMapper.toDTO(client));
    }



}
