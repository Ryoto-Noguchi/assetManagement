package com.example.test.service;

import java.util.List;

import com.example.test.model.dao.CategoryRepository;
import com.example.test.model.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepos;

    /** すべての資産種別を取得するメソッド
     * @param null
     * @return SELECT * FROM mst_category
     */ 
    public List<Category> findAllCategories(){
        return categoryRepos.findAll();
    }
}