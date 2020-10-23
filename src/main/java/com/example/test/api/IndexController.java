package com.example.test.api;

import java.util.List;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class IndexController {
    
  @Autowired
  AssetService assetService;

  @Autowired
  CategoryService categoryService;

  @GetMapping("/")
  public String index(Model model) {
    List<Category> categoryList = categoryService.findAllCategories();
    List<Asset> assetList = assetService.findAllAssets();
    for (Asset asset : assetList) {
      System.out.println("fetched ID : " + asset.getId());
    }
    model.addAttribute("assetList", assetList);
    model.addAttribute("categoryList", categoryList);
    return "index";
  }

}