package com.example.test.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({"資産ID", "資産種別", "管理者名", "資産名", "備考"})
@Data
public class CsvColumn {

  @JsonProperty("資産ID")
  private Integer id;

  @JsonProperty("資産種別")
  private Integer categoryId;

  @JsonProperty("管理者名")
  private String adminName;

  @JsonProperty("資産名")
  private String assetName;

  @JsonProperty("備考")
  private String remarks;

  public CsvColumn () {}

  public CsvColumn (Integer id, Integer categoryId, String adminName, String assetName, String remarks) {
    this.id = id;
    this.categoryId = categoryId;
    this.adminName = adminName;
    this.assetName = assetName;
    this.remarks = remarks;
  }
}
