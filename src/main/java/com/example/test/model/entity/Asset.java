package com.example.test.model.entity;

import java.io.Serializable;
import java.util.Objects;

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
import com.example.test.model.form.SearchForm;
import com.example.test.model.session.KeywordSession;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mst_asset")
@Getter
@Setter
@JsonPropertyOrder({"資産ID", "資産種別","管理者名", "資産名", "備考", "シリアルID", "購入年月日", "メーカー名", "付属品", "保管場所", "削除フラグ", "資産種別名&ID"})
public class Asset implements Serializable {

    private static final long serialVersionUID = -2542507546380589599L;

    @JsonProperty("資産ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonProperty("資産種別")
    @Column(name = "category_id")
    private Integer categoryId;

    @JsonProperty("管理者名")
    @Column(name = "admin_name")
    private String adminName;

    @JsonProperty("資産名")
    @Column(name = "asset_name")
    private String assetName;

    @JsonProperty("備考")
    @Column(name = "remarks")
    private String remarks;

    @JsonProperty("シリアルID")
    @Column(name = "serial_id")
    private String serialId;

    @JsonProperty("購入年月日")
    @Column(name = "purchase_date")
    private String purchaseDate;

    @JsonProperty("メーカー名")
    @Column(name = "maker_name")
    private String makerName;

    @JsonProperty("付属品")
    @Column(name = "accessory")
    private String accessory;

    @JsonProperty("保管場所")
    @Column(name = "storing_place")
    private String storingPlace;

    @JsonProperty("削除フラグ")
    @Column(name = "delete_flag")
    private boolean deleteFlag;

    @JsonProperty("資産種別名&ID")
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

    public Asset(SearchForm f) {
        this.id = f.getId();
        this.categoryId = f.getCategoryId();
        this.adminName = f.getAdminName();
        this.assetName = f.getAssetName();
        this.remarks = null;
        this.serialId = null;
        this.purchaseDate = null;
        this.makerName = null;
        this.accessory = null;
        this.storingPlace = null;
    }

    public Asset(KeywordSession session) {
        this.id = session.getId();
        this.categoryId = session.getCategoryId();
        this.adminName = session.getAdminName();
        this.assetName = session.getAssetName();
        this.remarks = session.getRemarks();
        this.serialId = session.getSerialId();
        this.purchaseDate = session.getPurchaseDate();
        this.makerName = session.getMakerName();
        this.accessory = session.getAccessory();
        this.storingPlace = session.getStoringPlace();
    }

    public Asset(Integer id, Integer categoryId, String adminName, String assetName, String remarks) {
        this.id = id;
        this.categoryId = categoryId;
        this.adminName = adminName;
        this.assetName = assetName;
        this.remarks = remarks;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Asset)) {
            return false;
        }
        Asset other = (Asset)obj;
        if (!Objects.equals(id, other.id)) {
            return false;
        }
        if (!Objects.equals(categoryId, other.categoryId)) {
            return false;
        }
        if (!Objects.equals(adminName, other.adminName)) {
            return false;
        }
        if (!Objects.equals(assetName, other.assetName)) {
            return false;
        }
        if (!Objects.equals(remarks, other.remarks)) {
            return false;
        }
        if (!Objects.equals(serialId, other.serialId)) {
            return false;
        }
        if (!Objects.equals(purchaseDate, other.purchaseDate)) {
            return false;
        }
        if (!Objects.equals(accessory, other.accessory)) {
            return false;
        }
        if (!Objects.equals(storingPlace, other.storingPlace)) {
            return false;
        }
        return true;
    }
}
