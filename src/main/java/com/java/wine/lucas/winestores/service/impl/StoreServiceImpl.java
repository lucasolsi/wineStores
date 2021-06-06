package com.java.wine.lucas.winestores.service.impl;

import com.java.wine.lucas.winestores.repository.StoreRepository;
import com.java.wine.lucas.winestores.service.StoreService;
import com.java.wine.lucas.winestores.shared.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

    @Autowired
    StoreRepository storeRepository;

    @Override
    public StoreDto createStore(StoreDto storeDto)
    {
        return null;
    }
}
