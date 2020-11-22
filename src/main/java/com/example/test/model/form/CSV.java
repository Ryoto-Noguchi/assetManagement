package com.example.test.model.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({"資産ID", "資産種別", "管理者名", "資産名", "備考"})
@Data
public class CSV {

  @JsonProperty("資産ID")
  List<Integer> id;

  @JsonProperty("資産種別")
  List<Integer> categoryId;

  @JsonProperty("管理者名")
  List<String> adminName;

  @JsonProperty("資産名")
  List<String> assetName;

  @JsonProperty("備考")
  List<String> remarks;

}
