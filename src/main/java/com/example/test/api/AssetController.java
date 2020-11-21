package com.example.test.api;

import com.example.test.model.entity.Asset;
import com.example.test.model.form.ModifyForm;
import com.example.test.model.form.RegisterForm;
import com.example.test.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/asset")
public class AssetController {

  @Autowired
  AssetService assetService;

  @PostMapping("/modify")
  public String modify(@ModelAttribute("modifyForm") ModifyForm form) {
    Asset newAsset = new Asset(form);
    int count = assetService.update(newAsset);
    System.out.println(count + "件更新しました");
    return "redirect:/index/";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute("registerForm") RegisterForm form) {
    Asset newAsset = new Asset(form);
    int count = assetService.insert(newAsset);
    System.out.println(count + "件登録しました");
    return "redirect:/index/";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    int count = assetService.logicalDeleteById(id);
    System.out.println(count + "件削除しました");
    return "redirect:/index/";
  }

}
