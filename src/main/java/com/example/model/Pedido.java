package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Pedido {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataDoPedido;

    @ManyToMany
    private List<Produto> produto;

    private float quantidade;
    private float valorTotal;

}
