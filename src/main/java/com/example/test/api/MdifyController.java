package com.example.test.api;

// import com.example.test.model.entity.Asset;
// import com.example.test.model.form.ModifyForm;
import com.example.test.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MdifyController {

  @Autowired
  AssetService assetService;

  // @PostMapping("/modify")
  // public String modify(@ModelAttribute("modifyForm") ModifyForm form) {
  //   Asset newAsset = new Asset(form);
  //   int count = assetService.update(newAsset);
  //   System.out.println(count + "件更新しました");
  //   return "redirect:/index/";
  // }
}
