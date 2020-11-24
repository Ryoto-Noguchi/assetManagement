package com.example.test.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.model.form.SearchForm;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

  @Autowired
  private AssetService assetService;

  @Autowired
  private CategoryService categoryService;

  public static final int PAGESIZE = 10;
  public static final Sort SORT = Sort.by("id").ascending();

  /**
   * トップページの初期表示と検索時、ページ番号押下時の資産リスト表示
   * @param page
   * @param model
   * @return リクエストされたページの資産リスト
   */
  @GetMapping(path = { "/", "/{page:^[1-9][0-9]*$}" })
  public String assetPage(@PathVariable(name = "page") Optional<Integer> page, @ModelAttribute("searchForm") Optional<SearchForm> f, Model model) {
    int currentPage = page.orElse(1); // リクエストされたページ
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
    Pageable pageable = PageRequest.of(currentPage - 1, PAGESIZE, SORT);
    Page<Asset> assetPage = assetService.findSearchedAndPaginatedPage(f, pageable);
    model.addAttribute("assetPage", assetPage);

    int totalPages = assetPage.getTotalPages(); // 最後ページ取得
    model.addAttribute("lastPage", totalPages);

    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);

    if (f.get().getAdminName() != null) { model.addAttribute("adminName", assetService.adminNameShape(f.get().getAdminName())); }
    if (f.get().getAssetName() != null) { model.addAttribute("assetName", assetService.assetNameShape(f.get().getAssetName())); }
    model.addAttribute("id", f.get().getId());
    model.addAttribute("cateogryId", f.get().getCategoryId());

    return "index";
  }

  /**
   * 詳細画面に遷移するメソッド
   * @param id
   * @param model
   * @return
   */
  @GetMapping("/detail/{id}")
  public String goDetail(@PathVariable("id") int id , Model model) {
    Asset asset = assetService.findById(id);
    model.addAttribute("asset", asset);
    return "detail";
  }

  /**
   * 登録画面へ遷移するメソッド
   * @param model
   * @return
   */
  @GetMapping("/register")
  public String goRegister(Model model) {
    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);
    int newId = assetService.getNewAssetId() + 1;
    model.addAttribute("newId", newId);
    return "register";
  }

  /**
   * 修正画面へ遷移するメソッド
   * @param id
   * @param model
   * @return
   */
  @PostMapping("/modify")
  public String goModdify(@RequestParam("id") int id, Model model) {
    List<Category> categoryList = categoryService.findAllCategories();
    Asset asset = assetService.findById(id);
    model.addAttribute("asset", asset);
    model.addAttribute("categoryList", categoryList);
    return "modify";
  }
}
