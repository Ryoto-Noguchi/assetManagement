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
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
   * トップページの初期表示とページ番号押下時の資産リスト表示
   * @param page
   * @param model
   * @return リクエストされたページの資産リスト
   */
  @GetMapping(path = { "/", "/{page:^[1-9][0-9]*$}" })
  public String assetPage(@PathVariable(name = "page") Optional<Integer> page, Model model) {
    int currentPage = page.orElse(1); // リクエストされたページ
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
    Pageable pageable = PageRequest.of(currentPage - 1, PAGESIZE, SORT);
    Page<Asset> assetPage = assetService.findPaginatedPage(pageable);
    model.addAttribute("assetPage", assetPage);

    int totalPages = assetPage.getTotalPages(); // 最後ページ取得
    model.addAttribute("lastPage", totalPages);

    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);

    return "index";
  }

  /**
   * 検索メソッド
   * @param f
   * @param page
   * @param model
   * @return
   */
  @RequestMapping(value = "/searchAsset", method = RequestMethod.POST)
  public String search(@ModelAttribute("searchForm") SearchForm f, @PathVariable(name = "page") Optional<Integer> page, Model model) {
    int currentPage = page.orElse(1); // リクエストされたページ
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
    Pageable pageable = PageRequest.of(currentPage - 1, PAGESIZE, SORT);
    Page<Asset> assetPage = assetService.findSearchedAndPaginatedPage(f, pageable);
    model.addAttribute("assetPage", assetPage);
    return "index";
  }

  @GetMapping("/register")
  public String goRegister(Model model) {
    List<Category> categories = categoryService.findAllCategories();
    model.addAttribute("categories", categories);

    int newId = assetService.getNewAssetId() + 1;
    model.addAttribute("newId", newId);
    return "register";
  }
}
