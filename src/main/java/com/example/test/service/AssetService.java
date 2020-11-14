package com.example.test.service;

import java.util.Collections;
import java.util.List;

import com.example.test.model.dao.AssetRepository;
import com.example.test.model.entity.Asset;
import com.example.test.model.form.SearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepos;

    /**
     * リクエストされたページの資産情報を取得するメソッド
     *
     * @param page ページ番号
     * @param size 1ページに表示するレコード数
     * @return リクエストされたページのレコード
     */
    public Page<Asset> findPaginatedPage(Pageable pageable) {

        int pageSize = pageable.getPageSize(); // 1ページあたりの表示するレコード数
        int currentPage = pageable.getPageNumber(); // 現在のページ
        int startItem = pageSize * currentPage; // 現在表示しているページの1番上のレコード
        List<Asset> list = null; // Asset型の変数をnullで初期化して保持

        List<Asset> assets = assetRepos.findAll();
        if (assets.size() < startItem) { // ???
            list = Collections.emptyList(); // 変数listを空のまま不変にする
        } else {
            int toIndex = Math.min(startItem + pageSize, assets.size()); // 「現在表示しているページの1番上のレコード」＋「10」と「全レコード数」の小さい方をtoIndexとする
            list = assets.subList(startItem, toIndex); // 「現在表示しているページの1番上のレコード」からtoIndexまでのレコード数 = リクエストされたページで表示したいレコード数
        }

        Page<Asset> assetList = new PageImpl<Asset>(list, pageable, assets.size()); // リクエストされたページに合致するレコード情報
        return assetList;
    }

    /**
     * 資産IDを条件に資産詳細を取得するメソッド
     *
     * @param assetId 資産ID
     * @return SELECT * FROM mst_asset WHERE asset_id = #{assetId};
     */
    public List<Asset> findById(int assetId) {
        return assetRepos.findById(assetId);

    }

    public Page<Asset> findSearchedAndPaginatedPage(SearchForm f, Pageable pageable) {
        Integer id = f.getId();
        Integer categoryId = f.getCategoryId();
        String adminName = f.getAdminName().replaceAll("　", " ").replaceAll("\\s+", " ").trim();
        String assetName = f.getAssetName().replaceAll("　", " ").replaceAll("\\s+", " ").trim();
        if (id == null) {f.setId(null);}
        if (categoryId == null) {f.setCategoryId(null);}
        if (adminName == null) {f.setAdminName(null);}
        if (assetName == null) {f.setAssetName(null);}
        List<Asset> assets = assetRepos.findByIdAndCategoryIdAndAdminNameAndAssetName(id, categoryId, adminName, assetName);

        int pageSize = pageable.getPageSize(); // 1ページあたりの表示するレコード数
        int currentPage = pageable.getPageNumber(); // 現在のページ
        int startItem = pageSize * currentPage; // 現在表示しているページの1番上のレコード
        List<Asset> list = null; // Asset型の変数をnullで初期化して保持
        if (assets.size() < startItem) { // ???
            list = Collections.emptyList(); // 変数listを空のまま不変にする
        } else {
            int toIndex = Math.min(startItem + pageSize, assets.size()); // 「現在表示しているページの1番上のレコード」＋「10」と「全レコード数」の小さい方をtoIndexとする
            list = assets.subList(startItem, toIndex); // 「現在表示しているページの1番上のレコード」からtoIndexまでのレコード数 = リクエストされたページで表示したいレコード数
        }

        Page<Asset> assetList = new PageImpl<Asset>(list, pageable, assets.size()); // リクエストされたページに合致するレコード情報
        return assetList;

    }

}
