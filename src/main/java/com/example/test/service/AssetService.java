package com.example.test.service;

import java.util.Collections;
import java.util.List;

import com.example.test.model.dao.AssetRepository;
import com.example.test.model.entity.Asset;

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

    /** すべての資産情報を取得するメソッド
     * @param null
     * @return SELECT * FROM mst_asset;
     */
    // final public List<Asset> assets = assetRepos.findAll();

    /**リクエストされたページの資産情報を取得するメソッド
     * @param page ページ番号
     * @param size 1ページに表示するレコード数
     * @return リクエストされたページのレコード
     */
    public Page<Asset> findPaginatedPage(Pageable pageable) {

        int pageSize = pageable.getPageSize(); // 1ページあたりの表示するレコード数
        System.out.println("ページサイズ：" + pageSize);
        int currentPage = pageable.getPageNumber(); // 現在のページ
        System.out.println("現在ページ：" + currentPage);
        int startItem = pageSize * currentPage; // 現在表示しているページの1番上のレコード
        System.out.println("現在ページの最後の資産ID：" + startItem);
        List<Asset> list = null; // Asset型の変数をnullで初期化して保持
        List<Asset> assets = assetRepos.findAll();
        for (Asset asset : assets) {
            System.out.println("取得した全資産ID：" + asset.getId());
        }
        if (assets.size() < startItem) { // ???
            list = Collections.emptyList(); // 変数listを空のまま不変にする
        } else {
            int toIndex = Math.min(startItem + pageSize, assets.size()); //「現在表示しているページの1番上のレコード」＋「10」と「全レコード数」の小さい方をtoIndexとする
            list = assets.subList(startItem, toIndex); // 「現在表示しているページの1番上のレコード」からtoIndexまでのレコード数
        }

        Page<Asset> assetList = new PageImpl<Asset>(list, pageable, assets.size());

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
