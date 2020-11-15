package com.example.test.model.dao;

import java.util.List;

import com.example.test.model.entity.Asset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    Page<Asset> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM mst_asset AS a LEFT JOIN mst_category AS c ON a.category_id = c.category_id WHERE a.id = :id", nativeQuery = true)
    Asset findById(@Param("id") int id);

    List<Asset> findAll();

    @Query(value = "SELECT * FROM mst_asset WHERE id = CASE WHEN :id = 0 THEN id ELSE :id END AND category_id = CASE WHEN :categoryId = 0 THEN category_id ELSE :categoryId END AND admin_name = CASE WHEN :adminName = '' THEN admin_name ELSE :adminName END AND asset_name = CASE WHEN :assetName = '' THEN asset_name ELSE :assetName END", nativeQuery = true)
	List<Asset> findByIdAndCategoryIdAndAdminNameAndAssetName(
        @Param("id") Integer id,
        @Param("categoryId")Integer categoryId,
        @Param("adminName") String adminName,
        @Param("assetName") String assetName);
}
