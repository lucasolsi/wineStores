package com.java.wine.lucas.winestores.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class StoreEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo_loja;

    @Column(nullable = false, unique = true)
    private int faixa_inicio;

    @Column(nullable = false, unique = true)
    private int faixa_fim;
}
