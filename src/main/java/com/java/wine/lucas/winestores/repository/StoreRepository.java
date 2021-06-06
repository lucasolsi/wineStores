package com.java.wine.lucas.winestores.repository;

import com.java.wine.lucas.winestores.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>
{
}
