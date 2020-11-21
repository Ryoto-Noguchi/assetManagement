package com.example.test.api;

import com.example.test.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

  @Autowired
  AssetService assetService;

  // @PostMapping("/delete")
  // public String delete(@RequestParam("id") int id) {
  //   int count = assetService.logicalDeleteById(id);
  //   System.out.println(count + "件削除しました");
  //   return "redirect:/index/";
  // }
}
