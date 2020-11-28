package com.example.test.api;

import java.util.ArrayList;
import java.util.List;

import com.example.test.model.entity.Asset;
import com.example.test.model.form.CSV;
import com.example.test.model.form.CsvColumn;
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

  /**
   * 資産情報修正メソッド
   * @param form
   * @return
   */
  @PostMapping("/modify")
  public String modify(@ModelAttribute("modifyForm") ModifyForm form) {
    Asset newAsset = new Asset(form);
    int count = assetService.update(newAsset);
    System.out.println(count + "件更新しました");
    return "redirect:/index/refresh";
  }

  /**
   * 資産情報登録メソッド
   * @param form
   * @return
   */
  @PostMapping("/register")
  public String register(@ModelAttribute("registerForm") RegisterForm form) {
    Asset newAsset = new Asset(form);
    int count = assetService.insert(newAsset);
    System.out.println(count + "件登録しました");
    return "redirect:/index/refresh";
  }

  /**
   * 資産情報削除メソッド
   * @param id
   * @return
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    int count = assetService.logicalDeleteById(id);
    System.out.println(count + "件削除しました");
    return "redirect:/index/refresh";
  }

  /**
   * CSVダウンロードメソッド
   * @param records
   * @return
   * @throws JsonProcessingException
   */
  @PostMapping(value = "/csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
      + "; charset=UTF-8; Content-Disposition: attachment")
  @ResponseBody
  public Object csvDownload(@ModelAttribute("csvForm") CSV records) throws JsonProcessingException {
    List<CsvColumn> csvList = new ArrayList<>();
    for (int i = 0; i < records.getId().size(); i++) { // レコードの数ぶんだけループ回して
      csvList.add(new CsvColumn(records.getId().get(i), records.getCategoryId().get(i), records.getAdminName().get(i), records.getAssetName().get(i), records.getRemarks().get(i)));
    }
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(CsvColumn.class).withHeader();
    return mapper.writer(schema).writeValueAsString(csvList);
  }

}
