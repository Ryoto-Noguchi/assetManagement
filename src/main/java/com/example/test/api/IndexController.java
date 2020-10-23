package com.example.test.api;

import com.example.test.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class IndexController {
    
    @Autowired
    AssetService assetService;
}