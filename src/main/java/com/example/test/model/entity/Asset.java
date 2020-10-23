package com.example.test.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_asset")
public class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "categry_id")
    @Getter
    @Setter
    private Integer categoryId;

    @Column(name = "admin_name")
    @Getter
    @Setter
    private String adminName;

    @Column(name = "asset_name")
    @Getter
    @Setter
    private String assetName;

    @Column(name = "remarks")
    @Getter
    @Setter
    private String remarks;

}