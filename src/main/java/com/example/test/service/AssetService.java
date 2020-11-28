package com.example.test.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.test.model.dao.AssetRepository;
import com.example.test.model.entity.Asset;
import com.example.test.model.form.SearchForm;
import com.example.test.model.session.KeywordSession;
import com.example.test.model.session.SearchSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @Autowired
    SearchSession searchSession;

    @Autowired
    KeywordSession session;

    /**
     * 資産IDを条件に資産詳細を取得するメソッド
     * @param id 資産ID
     * @return SELECT * FROM mst_asset WHERE asset_id = #{assetId};
     */
    public Asset findById(int id) {
        return assetRepos.findById(id);
    }

    /**
     * 検索時に入力された「管理者名」キーワードを整形
     * @param adminName
     * @return adminName
     */
    public String adminNameShape(String adminName) {
        return adminName = adminName.replaceAll("　", " ").replaceAll("\\s+", " ").trim();
    }

    /**
     * 検索時に入力された「資産名」キーワードを整形
     * @param adminName
     * @return adminName
     */
    public String assetNameShape(String assetName) {
        return assetName = assetName.replaceAll("　", " ").replaceAll("\\s+", " ").trim();
    }

    /**
     * トップページ初期表示と検索時の資産レコード取得メソッド
     * @param f
     * @param pageable
     * @return
     */
    public Page<Asset> findSearchedAndPaginatedPage(Optional<SearchForm> f, Pageable pageable) {

        Integer id = f.get().getId();
        Integer categoryId = f.get().getCategoryId();
        String adminName = f.get().getAdminName();
        String assetName = f.get().getAssetName();

        if (searchSession.getId() == null) {
            if (id == null) {
                searchSession.setId(0);
            } else {
                searchSession.setId(id);
            }
        } else {
            if (id != null) {
                searchSession.setId(id);
            }
        }

        if (searchSession.getCategoryId() == null) {
            if (categoryId == null) {
                searchSession.setCategoryId(0);
            } else {
                searchSession.setCategoryId(categoryId);
            }
        } else {
            if (categoryId != null) {
                searchSession.setCategoryId(categoryId);
            }
        }

        if (searchSession.getAdminName() == null) {
            if (adminName == null) {
                searchSession.setAdminName("");
            } else {
                searchSession.setAdminName(adminNameShape(f.get().getAdminName()));
            }
        } else {
            if (adminName != null) {
                searchSession.setAdminName(adminName);
            }
        }

        if (searchSession.getAssetName() == null) {
            if (assetName == null) {
                searchSession.setAssetName("");
            } else {
                searchSession.setAssetName(assetNameShape(f.get().getAssetName()));
            }
        } else {
            if (assetName != null) {
                searchSession.setAssetName(assetName);
            }
        }

        List<Asset> assets = assetRepos.findByIdAndCategoryIdAndAdminNameAndAssetName(searchSession.getId(), searchSession.getCategoryId(), searchSession.getAdminName(), searchSession.getAssetName()); // 検索条件を元に検索(空欄はSQLで無視するようにしてある)

        int pageSize = pageable.getPageSize(); // 1ページあたりの表示するレコード数
        int currentPage = pageable.getPageNumber(); // リクエストされたページ
        int startItem = pageSize * currentPage; // リクエストされたページの最大値(1ページあたりに表示するレコード数が10なら、2ページ目最大値は20)
        List<Asset> list = null; // Asset型の変数をnullで初期化して保持
        if (assets.size() < startItem) { // リクエストされたページの最大値よりも取得してきたレコードの数が少なかった時
            list = Collections.emptyList(); // 空のlistを作成する
        } else {
            int toIndex = Math.min(startItem + pageSize, assets.size()); // 「現在表示しているページの1番上のレコード」＋「10」と「全レコード数」の小さい方をtoIndexとする
            list = assets.subList(startItem, toIndex); // 「現在表示しているページの1番上のレコード」からtoIndexまでのレコード数 = リクエストされたページで表示したいレコード数
        }

        Page<Asset> assetList = new PageImpl<Asset>(list, pageable, assets.size()); // リクエストされたページに合致するレコード情報(pageableで表示するページ番号を決め、asset.size()で表される検索された全レコードからlistの数ぶん引いたレコードがassetListということみたい)
        return assetList;
    }

    /**
     * レコード数取得をするメソッド
     * @param なし
     * @return レコード数
     */
    public int getNewAssetId() {
        return assetRepos.findAllCnt();
    }

    /**
     * 登録をするメソッド
     * @param newAsset
     * @return 更新件数
     */
    public int insert(Asset newAsset) {
        return assetRepos.register(newAsset);
    }

    /**
     * 更新するメソッド
     * @param newAsset
     * @return 更新件数
     */
    public int update(Asset newAsset) {
        return assetRepos.update(newAsset);
    }

    /**
     * 論理削除メソッド
     * @param id
     * @return
     */
    public int logicalDeleteById(int id) {
        return assetRepos.logicalDeleteById(id);
    }

	public Page<Asset> search(Asset assetForm, Pageable pageable) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                    .withMatcher("adminName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("assetName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("remarks", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("makerName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("serialId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("purchaseDate", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("accessory", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("storingPlace", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
            Asset sample = new Asset();
            if (assetForm.equals(sample)) {
                System.out.println("検索空欄");
            } else {
                session.setId(assetForm.getId());
                session.setCategoryId(assetForm.getCategoryId());
                session.setAdminName(assetForm.getAdminName());
                session.setAssetName(assetForm.getAssetName());
                session.setRemarks(assetForm.getRemarks());
                session.setMakerName(assetForm.getMakerName());
                session.setSerialId(assetForm.getSerialId());
                session.setPurchaseDate(assetForm.getPurchaseDate());
                session.setAccessory(assetForm.getAccessory());
                session.setStoringPlace(assetForm.getStoringPlace());
            }
        Asset asset = new Asset(session);
        Example<Asset> example = Example.of(asset, customExampleMatcher);
        Page<Asset> assetList = assetRepos.findAll(example, pageable);
        return assetList;
        // TODO 次は検索結果を引き継いでページネーションをする機能をセッションを使って実装する。それが終わったらAssetの検索要素全てを検索後にも表示するようHTMLを編集
	}

}
