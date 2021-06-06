package com.java.wine.lucas.winestores.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequest
{
    private String codigo_loja;
    private int faixa_inicio;
    private int faixa_fim;

    public StoreRequest withCodigoLoja(String codigoLoja)
    {
        this.codigo_loja = codigoLoja;
        return this;
    }

    public StoreRequest withFaixaInicio(int faixaInicio)
    {
        this.faixa_inicio = faixaInicio;
        return this;
    }

    public StoreRequest withFaixaFim(int faixaFim)
    {
        this.faixa_fim = faixaFim;
        return this;
    }
}
