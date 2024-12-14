package com.stocksafe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stocksafe.model.enums.DocumentFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO {

    private String document;

    private String name;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private DocumentFormat documentFormat;
}
