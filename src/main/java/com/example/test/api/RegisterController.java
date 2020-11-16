package com.example.test.api;

import com.example.test.model.form.RegisterForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {

  @GetMapping("/register")
  public String register(@ModelAttribute("registerForm") RegisterForm form, Model model) {
    return "register";
  }

}
