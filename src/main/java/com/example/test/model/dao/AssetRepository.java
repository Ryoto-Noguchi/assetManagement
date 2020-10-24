package com.example.test.model.dao;

import java.util.List;

import com.example.test.model.entity.Asset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    Page<Asset> findAll(Pageable pageable);
    List<Asset> findById(int assetId);


}
