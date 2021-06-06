package com.java.wine.lucas.winestores.service.impl;

import com.java.wine.lucas.winestores.entity.StoreEntity;
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
            throw new RuntimeException("Store with código " + storeDto.getCodigoLoja() + " already exists!");
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
        if (storeFromDb == null) throw new RuntimeException();
        storeRepository.delete(storeFromDb);
    }

    @Override
    public StoreDto updateStore(String codigoLoja, StoreDto storeDto)
    {
        StoreEntity storeFromDbToUpdate = storeRepository.findByCodigoLoja(codigoLoja);
        if (storeFromDbToUpdate == null) throw new RuntimeException();

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
        if (storeFromDb == null) throw new RuntimeException();

        return new ModelMapper().map(storeFromDb, StoreDto.class);
    }


    private void checkFaixaInicioAndFaixaFim(StoreDto storeDto)
    {
        StoreEntity checkFaixaInicio = storeRepository.findAllByFaixaInicioBetween(storeDto.getFaixaInicio(), storeDto.getFaixaFim());
        StoreEntity checkFaixaFim = storeRepository.findAllByFaixaFimBetween(storeDto.getFaixaInicio(), storeDto.getFaixaFim());

        if (checkFaixaInicio != null || checkFaixaFim != null) throw new RuntimeException();
    }
}
