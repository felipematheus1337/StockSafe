package com.stocksafe.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.stocksafe.model.enums.BoxStatus;
import com.stocksafe.model.enums.BoxType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxWeight;

    private int weight;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime periodoInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime periodoFinal;

    @Enumerated(EnumType.STRING)
    private BoxType boxType;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private BoxStatus status = BoxStatus.ENABLED;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
