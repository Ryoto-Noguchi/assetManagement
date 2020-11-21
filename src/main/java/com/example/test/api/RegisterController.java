package com.example.test.api;

import com.example.test.model.entity.Asset;
import com.example.test.model.form.RegisterForm;
import com.example.test.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  @Autowired
  AssetService assetService;

  @PostMapping("/register")
  public String register(@ModelAttribute("registerForm") RegisterForm form) {
    Asset newAsset = new Asset(form);
    int count = assetService.insert(newAsset);
    System.out.println(count + "件登録しました");
    return "redirect:/index/";
  }

}
