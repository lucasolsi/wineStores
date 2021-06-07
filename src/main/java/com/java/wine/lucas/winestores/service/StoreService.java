package com.java.wine.lucas.winestores.service;

import com.java.wine.lucas.winestores.shared.dto.StoreDto;

import java.util.List;

public interface StoreService
{
    StoreDto createStore(StoreDto storeDto) throws Exception;
    void deleteStoreByCodigoLoja(String codigoLoja);
    StoreDto updateStore(String codigoLoja, StoreDto storeDto);
    StoreDto updateFaixaCep(StoreDto storeDto);
    List<StoreDto> getAllStores(int page, int limit);
    StoreDto getStoreByCodigoLoja(String codigoLoja);
    StoreDto findStoreByCep(int cep);
    StoreDto findStoreByFaixaCepAndCodigoLoja(String codigoLoja, int fromFaixaInicio, int fromFaixaFim);
}
