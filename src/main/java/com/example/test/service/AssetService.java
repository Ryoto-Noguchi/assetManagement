package com.example.test.service;

import com.example.test.model.dao.AssetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {
    
    @Autowired
    AssetRepository assetRespository;

    /**資産IDを条件に資産詳細を取得する
     * @param assetId 資産ID
     * @return
     */ 

}