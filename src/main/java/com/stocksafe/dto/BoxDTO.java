package com.stocksafe.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.stocksafe.model.enums.BoxType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxDTO {

    private int maxWeight;

    private int weight;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime periodoInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime periodoFinal;

    @Enumerated(EnumType.STRING)
    private BoxType boxType;
}
