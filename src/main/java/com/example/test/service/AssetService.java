package com.example.test.service;

import java.util.List;

import com.example.test.model.dao.AssetRepository;
import com.example.test.model.entity.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {
    
    @Autowired
    AssetRepository assetRepos;

    /**すべての資産情報を取得するメソッド
     * @param null 初期表示のためな無し
     * @return SELECT * FROM mst_asset;
     */ 
    public List<Asset> findAllAssets() {
        return assetRepos.findAll();
    }

    /**資産IDを条件に資産詳細を取得するメソッド
     * @param assetId 資産ID
     * @return SELECT * FROM mst_asset WHERE asset_id = #{assetId};
     */ 
    public List<Asset> findById(int assetId) {
        return assetRepos.findById(assetId);
        
    }

}