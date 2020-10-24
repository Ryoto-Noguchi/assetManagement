package com.example.test.api;

import java.util.List;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
  public String index(Model model, Integer page, Integer size) {
    if (page == null) {
      page = 1;
    }
    System.out.println("ページ番号" + page);
    List<Category> categoryList = categoryService.findAllCategories();
    Page<Asset> assetList = assetService.findAllAssets(page, 10);
    System.out.println(assetList.getSize());
    model.addAttribute("assetList", assetList);
    model.addAttribute("categoryList", categoryList);
    return "index";
  }

}
