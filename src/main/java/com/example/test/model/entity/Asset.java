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
@Getter
@Setter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "serial_id")
    private String serialId;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "maker_name")
    private String makerName;

    @Column(name = "accessory")
    private String accessory;

    @Column(name = "storing_place")
    private String storingPlace;

}
