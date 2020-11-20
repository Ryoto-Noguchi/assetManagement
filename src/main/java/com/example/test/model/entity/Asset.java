package com.example.test.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.test.model.form.ModifyForm;
import com.example.test.model.form.RegisterForm;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_asset")
@Getter
@Setter
public class Asset implements Serializable {

    private static final long serialVersionUID = -2542507546380589599L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

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

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    public Asset() {}

    public Asset(RegisterForm f) {
        this.id = f.getId();
        this.categoryId = f.getCategoryId();
        this.adminName = f.getAdminName();
        this.assetName = f.getAssetName();
        this.remarks = f.getRemarks();
        this.serialId = f.getSerialId();
        this.purchaseDate = f.getPurchaseDate();
        this.makerName = f.getMakerName();
        this.accessory = f.getAccessory();
        this.storingPlace = f.getStoringPlace();
    }

    public Asset(ModifyForm f) {
        this.id = f.getId();
        this.categoryId = f.getCategoryId();
        this.adminName = f.getAdminName();
        this.assetName = f.getAssetName();
        this.remarks = f.getRemarks();
        this.serialId = f.getSerialId();
        this.purchaseDate = f.getPurchaseDate();
        this.makerName = f.getMakerName();
        this.accessory = f.getAccessory();
        this.storingPlace = f.getStoringPlace();
    }

}
