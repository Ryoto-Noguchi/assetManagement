package com.example.test.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
  private Integer id;
  private Integer categoryId;
  private String categoryName;
  private String adminiName;
  private String assetName;
  private String remarks;
  private String serialId;
  private String purchaseDate;
  private String makerName;
  private String accessory;
  private String storingPlace;
}
