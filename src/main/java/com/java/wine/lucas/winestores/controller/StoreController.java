package com.java.wine.lucas.winestores.controller;

import com.java.wine.lucas.winestores.model.request.StoreRequest;
import com.java.wine.lucas.winestores.model.request.UpdateFaixaCepRequest;
import com.java.wine.lucas.winestores.model.response.StoreResponse;
import com.java.wine.lucas.winestores.service.StoreService;
import com.java.wine.lucas.winestores.shared.dto.StoreDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController
{

    @Autowired
    StoreService storeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResponse> createStore(@RequestBody StoreRequest storeRequest) throws Exception
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(mapperStoreRequestStoreDtoPropertyMap);
        StoreDto storeDto = modelMapper.map(storeRequest, StoreDto.class);

        StoreDto createdStore = storeService.createStore(storeDto);
        modelMapper.addMappings(mapperStoreDtoStoreResponsePropertyMap);
        return new ResponseEntity<>(modelMapper.map(createdStore, StoreResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(path = "/codigo/{codigoLoja}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StoreResponse getStoreByCodigo(@PathVariable String codigoLoja)
    {
        ModelMapper modelMapper = new ModelMapper();
        StoreDto storeDto = storeService.getStoreByCodigoLoja(codigoLoja);
        modelMapper.addMappings(mapperStoreDtoStoreResponsePropertyMap);
        return modelMapper.map(storeDto, StoreResponse.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StoreResponse> getAllStores(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "5") int limit)
    {
        List<StoreResponse> responseList = new ArrayList<>();
        List<StoreDto> storeDtoList = storeService.getAllStores(page, limit);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(mapperStoreDtoStoreResponsePropertyMap);

        storeDtoList.forEach(storeDto -> responseList.add(modelMapper.map(storeDto, StoreResponse.class)));

        return responseList;
    }

    @DeleteMapping(path = "/codigo/{codigoLoja}")
    public void deleteStore(@PathVariable String codigoLoja)
    {
        storeService.deleteStoreByCodigoLoja(codigoLoja);
    }


    @PutMapping(path = "/codigo/{codigoLoja}/atualizaFaixa", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResponse> updateFaixaCep(@PathVariable String codigoLoja, @RequestBody UpdateFaixaCepRequest fromRequest)
    {
        StoreDto storeToUpdate = storeService.findStoreByFaixaCepAndCodigoLoja( codigoLoja, fromRequest.getFromFaixa_inicio(), fromRequest.getFromFaixa_fim());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(mapperStoreRequestStoreDtoPropertyMap);
        modelMapper.addMappings(mapperStoreDtoStoreResponsePropertyMap);

        StoreDto storeDto = new StoreDto();
        storeDto.setFaixaInicio(fromRequest.getToFaixa_inicio());
        storeDto.setFaixaFim(fromRequest.getToFaixa_fim());

        StoreDto updatedStore = storeService.updateFaixaCep(storeDto);

        return new ResponseEntity<>(modelMapper.map(updatedStore, StoreResponse.class), HttpStatus.OK);
    }

    @GetMapping(path = "/buscaCep", produces = MediaType.APPLICATION_JSON_VALUE)
    public StoreResponse findStoreByCep(@RequestParam(value = "cep") int cep)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(mapperStoreDtoStoreResponsePropertyMap);
        StoreDto storeDto = storeService.findStoreByCep(cep);
        return modelMapper.map(storeDto,StoreResponse.class);
    }

    PropertyMap<StoreRequest, StoreDto> mapperStoreRequestStoreDtoPropertyMap = new PropertyMap<StoreRequest, StoreDto>()
    {
        @Override
        protected void configure()
        {
            map().setCodigoLoja(source.getCodigo_loja());
            map().setFaixaFim(source.getFaixa_fim());
            map().setFaixaInicio(source.getFaixa_inicio());
        }
    };

    PropertyMap<StoreDto, StoreResponse> mapperStoreDtoStoreResponsePropertyMap = new PropertyMap<StoreDto, StoreResponse>()
    {
        @Override
        protected void configure()
        {
            map().setCodigo_loja(source.getCodigoLoja());
            map().setFaixa_fim(source.getFaixaFim());
            map().setFaixa_inicio(source.getFaixaInicio());
        }
    };
}
