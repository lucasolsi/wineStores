package com.java.wine.lucas.winestores.repository;

import com.java.wine.lucas.winestores.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>
{
    StoreEntity findByCodigoLoja(String codigoLoja);
    List<StoreEntity> findAllByFaixaInicioBetween(int faixaInicio, int faixaFim);
    List<StoreEntity> findAllByFaixaFimBetween(int faixaInicio, int faixaFim);
    StoreEntity findStoreEntityByFaixaInicioIsGreaterThanEqual(int cep);
    StoreEntity findStoreEntityByFaixaFimIsLessThan(int cep);
}
