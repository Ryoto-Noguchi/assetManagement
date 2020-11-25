package com.example.test.api;

import java.util.List;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.model.form.SearchForm;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController {
  @Autowired
  private AssetService assetService;

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/search")
  public String search(@ModelAttribute("searchForm") SearchForm form,  Model model) {
    List<Asset> assetPage = assetService.search(form);
    model.addAttribute("assetPage", assetPage);
    
    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);
    if (form.getAdminName() != null) { model.addAttribute("adminName", assetService.adminNameShape(form.getAdminName())); }
    if (form.getAssetName() != null) { model.addAttribute("assetName", assetService.assetNameShape(form.getAssetName())); }
    model.addAttribute("id", form.getId());
    model.addAttribute("cateogryId", form.getCategoryId());
    return "practice";
  }

}
