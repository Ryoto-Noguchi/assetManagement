package com.example.test.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.model.session.KeywordSession;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchController {
  @Autowired
  private AssetService assetService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private KeywordSession session;

  @GetMapping(path = {"/search", "/search/{page:^[1-9][0-9]*$}"})
  public String search(@ModelAttribute("assetForm") Asset assetForm, @PathVariable(name = "page") Optional<Integer> page, Model model) {
    int currentPage = page.orElse(1); // 押下されたページリンクの数字(リクエストされたページ番号)
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
    Sort sort = Sort.by("id").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 2, sort); // ページネーション情報作成
    Page<Asset> assetPage = assetService.search(assetForm, pageable); // 検索キーワードとページネーション情報を利用して検索
    model.addAttribute("assetPage", assetPage);

    int totalPages = assetPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      model.addAttribute("pageNumbers", pageNumbers);
    }

    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);
    if (assetForm.getAdminName() != null) { model.addAttribute("adminName", assetService.adminNameShape(session.getAdminName())); }
    if (assetForm.getAssetName() != null) { model.addAttribute("assetName", assetService.assetNameShape(session.getAssetName())); }
    model.addAttribute("id", assetForm.getId());
    model.addAttribute("categoryId", session.getCategoryId());
    return "practice";
  }

}
