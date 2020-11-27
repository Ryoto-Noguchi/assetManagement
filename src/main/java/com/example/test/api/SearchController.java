package com.example.test.api;

import java.util.List;
import java.util.Optional;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController {
  @Autowired
  private AssetService assetService;

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/search")
  public String search(@ModelAttribute("assetForm") Asset assetForm, @PathVariable(name = "page") Optional<Integer> page, Model model) {
    int currentPage = page.orElse(1); // 押下されたページリンクの数字(リクエストされたページ番号)
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
    Sort sort = Sort.by("id").ascending();
    Pageable pageable = PageRequest.of(currentPage - 1, 10, sort);
    Page<Asset> assetPage = assetService.search(assetForm, pageable);
    model.addAttribute("assetPage", assetPage);

    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);
    if (assetForm.getAdminName() != null) { model.addAttribute("adminName", assetService.adminNameShape(assetForm.getAdminName())); }
    if (assetForm.getAssetName() != null) { model.addAttribute("assetName", assetService.assetNameShape(assetForm.getAssetName())); }
    model.addAttribute("id", assetForm.getId());
    model.addAttribute("cateogryId", assetForm.getCategoryId());
    return "practice";
  }

}
