package com.example.test.api;

import com.example.test.model.entity.Asset;
import com.example.test.service.AssetService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/detail")
public class AssetController {

  @Autowired
  AssetService assetService;

  /**
   * 資産IDリンク押下で資産詳細ページへ遷移するメソッド
   * @param id
   * @return 商品詳細
   */
  @GetMapping("/{id}")
  public String goDetail(@PathVariable("id") int id , Model model) {
    Asset asset = assetService.findById(id);
    System.out.println(ToStringBuilder.reflectionToString(asset, ToStringStyle.MULTI_LINE_STYLE));
    model.addAttribute("asset", asset);
    return "asset_detail";
  }

}
