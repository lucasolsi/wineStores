package com.java.wine.lucas.winestores.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreResponse
{
    private Long id;
    private String codigo_loja;
    private int faixa_inicio;
    private int faixa_fim;
}
