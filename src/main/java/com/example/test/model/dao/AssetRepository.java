package com.example.test.model.dao;

import java.util.List;

import com.example.test.model.entity.Asset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    Page<Asset> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM mst_asset AS a LEFT JOIN mst_category AS c ON a.category_id = c.category_id WHERE a.id = :id", nativeQuery = true)
    Asset findById(@Param("id") int id);

    List<Asset> findAllByIdInOrderByIdAsc(List<Integer> ids);

    @Query(value = "SELECT * FROM mst_asset WHERE id = CASE WHEN :id = 0 THEN id ELSE :id END AND category_id = CASE WHEN :categoryId = 0 THEN category_id ELSE :categoryId END AND admin_name LIKE concat('%', CASE WHEN :adminName = '' THEN admin_name ELSE :adminName END, '%') AND asset_name LIKE concat('%', CASE WHEN :assetName = '' THEN asset_name ELSE :assetName END, '%')", nativeQuery = true)
	List<Asset> findByIdAndCategoryIdAndAdminNameAndAssetName(
        @Param("id") Integer id,
        @Param("categoryId")Integer categoryId,
        @Param("adminName") String adminName,
        @Param("assetName") String assetName);

    @Query(value = "SELECT COUNT(*) FROM mst_asset", nativeQuery = true)
	int findAllCnt();

    @Modifying
    @Query(value = "INSERT INTO mst_asset (id, category_id, admin_name, asset_name, remarks, serial_id, purchase_date, maker_name, accessory, storing_place) VALUES (:#{#newAsset.id}, :#{#newAsset.categoryId}, :#{#newAsset.adminName}, :#{#newAsset.assetName}, :#{#newAsset.remarks}, :#{#newAsset.serialId}, :#{#newAsset.purchaseDate}, :#{#newAsset.makerName}, :#{#newAsset.accessory}, :#{#newAsset.storingPlace})", nativeQuery = true)
	int register(@Param("newAsset") Asset newAsset);

    @Modifying
    @Query(value = "UPDATE mst_asset SET category_id = :#{#newAsset.categoryId}, admin_name = :#{#newAsset.adminName}, asset_name = :#{#newAsset.assetName}, remarks = :#{#newAsset.remarks}, serial_id = :#{#newAsset.serialId}, purchase_date = :#{#newAsset.purchaseDate}, maker_name = :#{#newAsset.makerName}, accessory = :#{#newAsset.accessory}, storing_place = :#{#newAsset.storingPlace} WHERE id = :#{#newAsset.id}", nativeQuery = true)
	int update(@Param("newAsset") Asset newAsset);

}
