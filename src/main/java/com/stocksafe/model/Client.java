package com.stocksafe.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.stocksafe.model.enums.DocumentFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String document;

    @Enumerated(EnumType.STRING)
    private DocumentFormat documentFormat;

    private String name;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime birthDate;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;

    @OneToOne(mappedBy = "client")
    private Box box;



}
