package com.example.test.service;

import java.util.List;

import com.example.test.model.dao.AssetRepository;
import com.example.test.model.entity.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepos;

    /**すべての資産情報を取得するメソッド
     * @param page ページ番号
     * @param size 1ページに表示するレコード数
     * @return SELECT * FROM mst_asset;
     */
    @Transactional
    public Page<Asset> findAllAssets(Integer page, Integer size) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Asset> assetList = assetRepos.findAll(pageable);
        return assetList;
    }

    /**資産IDを条件に資産詳細を取得するメソッド
     * @param assetId 資産ID
     * @return SELECT * FROM mst_asset WHERE asset_id = #{assetId};
     */
    public List<Asset> findById(int assetId) {
        return assetRepos.findById(assetId);

    }

}
