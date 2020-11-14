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
    List<Asset> findById(int assetId);
    List<Asset> findAll();

    @Query(value = "SELECT * FROM mst_asset a WHERE a.id = CASE WHEN :id IS NULL THEN :id ELSE a.id END AND a.category_id = CASE WHEN :categoryId IS NULL THEN :categoryId ELSE a.category_id END AND  a.admin_name = CASE WHEN :adminName IS NULL THEN :adminName ELSE a.admin_name END AND  a.asset_name = CASE WHEN :assetName IS NULL THEN :assetName ELSE a.asset_name END", nativeQuery = true)
	List<Asset> findByIdAndCategoryIdAndAdminNameAndAssetName(
        @Param("id") Integer id,
        @Param("categoryId")Integer categoryId,
        @Param("adminName") String adminName,
        @Param("assetName")String assetName);
}
