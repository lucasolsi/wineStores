package com.java.wine.lucas.winestores.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StoreDto implements Serializable
{
    private Long id;
    private String codigoLoja;
    private int faixaInicio;
    private int faixaFim;
}
