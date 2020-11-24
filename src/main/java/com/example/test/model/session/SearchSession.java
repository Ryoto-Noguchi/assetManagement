package com.example.test.model.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SearchSession implements Serializable {
  private static final long serialVersionUID = 7665697344483144258L;

  private Integer id;
  private Integer categoryId;
  private String adminName;
  private String assetName;
}
