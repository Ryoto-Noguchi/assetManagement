package com.example.test.api;

// import java.util.ArrayList;
// import java.util.List;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

import com.example.test.model.entity.Asset;
import com.example.test.model.form.CSV;
import com.example.test.model.form.ModifyForm;
import com.example.test.model.form.RegisterForm;
import com.example.test.service.AssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

  /**
   * CSVダウンロード
   * @param records
   * @return
   * @throws JsonProcessingException
   */
  @PostMapping(value = "/csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
      + "; charset=UTF-8; Content-Disposition: attachment")
  @ResponseBody
  public Object csvDownload(@ModelAttribute("csvForm") CSV records) throws JsonProcessingException {
    // TODO CSVを通常の表通りに並べ直す処理
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(CSV.class).withHeader();
    return mapper.writer(schema).writeValueAsString(records);
  }

}
