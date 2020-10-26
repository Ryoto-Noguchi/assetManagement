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

  @Query(value = "SELECT * FROM mst_asset AS a LEFT JOIN mst_category AS c ON a.category_id = c.category_id WHERE id = #{id} AND category_id = #{categoryId} AND admin_name = #{adminName} AND asset_name = #{assetName}", nativeQuery = true)
  List<Asset> findByIdAndCategoryIdAndAdminNameAndAssetName(
      @Param("id") Integer id, 
      @Param("categoryId") Integer categoryId, 
      @Param("adminName") String adminName,
      @Param("assetName")	String assetName);
  }
