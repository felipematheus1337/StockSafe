package com.stocksafe.model;


import com.stocksafe.model.enums.TypeItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int size;

    private int weight;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeItem typeItem;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
