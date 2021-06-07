package com.java.wine.lucas.winestores.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFaixaCepRequest
{
    private int fromFaixa_inicio;
    private int fromFaixa_fim;
    private int toFaixa_inicio;
    private int toFaixa_fim;
}
