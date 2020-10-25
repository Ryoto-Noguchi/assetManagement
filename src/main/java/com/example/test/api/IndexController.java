package com.example.test.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.test.model.entity.Asset;
import com.example.test.model.entity.Category;
import com.example.test.service.AssetService;
import com.example.test.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {

  @Autowired
  private AssetService assetService;

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(path = { "/", "/{page:^[1-9][0-9]*$}" }, method = RequestMethod.GET)
  public String assetList(@PathVariable(name = "page") Optional<Integer> page, Model model) {
    int currentPage = page.orElse(1);
    System.out.println("リクエストされたページ：" + currentPage);
    int pageSize = 10;
    Page<Asset> assetList = assetService.findPaginatedPage(PageRequest.of(currentPage - 1, pageSize, Sort.by("id").ascending()));
    for (Asset asset : assetList) {
      System.out.println("取得した資産ID：" + asset.getId());
    }
    model.addAttribute("assetList", assetList);

    int totalPages = assetList.getTotalPages();
    model.addAttribute("lastPage", totalPages);

    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    List<Category> categoryList = categoryService.findAllCategories();
    model.addAttribute("categoryList", categoryList);

    return "index";
  }

}
