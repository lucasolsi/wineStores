package com.java.wine.lucas.winestores.repository;

import com.java.wine.lucas.winestores.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>
{
    StoreEntity findByCodigoLoja(String codigoLoja);
    StoreEntity findAllByFaixaInicioBetween(int faixaInicio, int faixaFim);
    StoreEntity findAllByFaixaFimBetween(int faixaInicio, int faixaFim);
}
