package com.example.test.model.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class KeywordSession implements Serializable {
  private static final long serialVersionUID = -5439855597313313768L;

  private Integer id;
  private Integer categoryId;
  private String adminName;
  private String assetName;
  private String remarks;
  private String serialId;
  private String purchaseDate;
  private String makerName;
  private String accessory;
  private String storingPlace;
}
