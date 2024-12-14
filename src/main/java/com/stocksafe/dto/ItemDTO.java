package com.stocksafe.dto;


import com.stocksafe.model.enums.TypeItem;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private int size;

    private int weight;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeItem typeItem;
}
