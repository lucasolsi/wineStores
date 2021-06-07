package com.java.wine.lucas.winestores.service.impl;

import com.java.wine.lucas.winestores.entity.StoreEntity;
import com.java.wine.lucas.winestores.exceptions.FaixaAlreadyExistsException;
import com.java.wine.lucas.winestores.exceptions.StoreAlreadyExistsException;
import com.java.wine.lucas.winestores.exceptions.StoreNotFoundException;
import com.java.wine.lucas.winestores.repository.StoreRepository;
import com.java.wine.lucas.winestores.service.StoreService;
import com.java.wine.lucas.winestores.shared.dto.StoreDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService
{

    @Autowired
    StoreRepository storeRepository;

    @Override
    public StoreDto createStore(StoreDto storeDto)
    {
        if (storeRepository.findByCodigoLoja(storeDto.getCodigoLoja()) != null)
        {
            throw new StoreAlreadyExistsException("Loja com código " + storeDto.getCodigoLoja() + " já existe!");
        }

        checkFaixaInicioAndFaixaFim(storeDto);

        ModelMapper modelMapper = new ModelMapper();
        StoreEntity storeEntity = modelMapper.map(storeDto,StoreEntity.class);

        StoreEntity storeFromDb = storeRepository.save(storeEntity);
        return modelMapper.map(storeFromDb, StoreDto.class);
    }

    @Override
    public void deleteStoreByCodigoLoja(String codigoLoja)
    {
        StoreEntity storeFromDb = storeRepository.findByCodigoLoja(codigoLoja);
        if (storeFromDb == null) throw new StoreNotFoundException("Loja com id " + codigoLoja + " não existe!");
        storeRepository.delete(storeFromDb);
    }

    @Override
    public StoreDto updateStore(String codigoLoja, StoreDto storeDto)
    {
        StoreEntity storeFromDbToUpdate = storeRepository.findByCodigoLoja(codigoLoja);
        if (storeFromDbToUpdate == null) throw new StoreNotFoundException("Loja com id " + codigoLoja + " não existe!");

        checkFaixaInicioAndFaixaFim(storeDto);

        storeFromDbToUpdate.setFaixaInicio(storeDto.getFaixaInicio());
        storeFromDbToUpdate.setFaixaFim(storeDto.getFaixaFim());

        StoreEntity updateStore = storeRepository.save(storeFromDbToUpdate);
        return new ModelMapper().map(updateStore, StoreDto.class);
    }

    @Override
    public List<StoreDto> getAllStores(int page, int limit)
    {
        List<StoreDto> storeDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        Page<StoreEntity> storeEntityPage = storeRepository.findAll(pageable);

        List<StoreEntity> storeList = storeEntityPage.getContent();
        ModelMapper modelMapper = new ModelMapper();
        storeList.forEach(store -> storeDtoList.add(modelMapper.map(store, StoreDto.class)));
        return storeDtoList;
    }

    @Override
    public StoreDto getStoreByCodigoLoja(String codigoLoja)
    {
        StoreEntity storeFromDb = storeRepository.findByCodigoLoja(codigoLoja);
        if (storeFromDb == null) throw new StoreNotFoundException("Loja com id " + codigoLoja + " não existe!");

        return new ModelMapper().map(storeFromDb, StoreDto.class);
    }


    private void checkFaixaInicioAndFaixaFim(StoreDto storeDto)
    {
        List<StoreEntity> checkFaixaInicio = storeRepository.findAllByFaixaInicioBetween(storeDto.getFaixaInicio(), storeDto.getFaixaFim());
        List<StoreEntity> checkFaixaFim = storeRepository.findAllByFaixaFimBetween(storeDto.getFaixaInicio(), storeDto.getFaixaFim());

        if (!checkFaixaInicio.isEmpty() || !checkFaixaFim.isEmpty()) throw new FaixaAlreadyExistsException("Faixa de CEP preenchida!");
    }

    @Override
    public StoreDto findStoreByCep(int cep)
    {
        List<StoreEntity> storeEntityList = storeRepository.findAll();
        StoreEntity storeFound = storeEntityList.stream()
                .filter(storeEntity -> cep >= storeEntity.getFaixaInicio() && cep <= storeEntity.getFaixaFim())
                .findFirst().orElseThrow(() -> new StoreNotFoundException("Nenhuma loja envia para o CEP: " + cep));

        return new ModelMapper().map(storeFound, StoreDto.class);
    }
}
