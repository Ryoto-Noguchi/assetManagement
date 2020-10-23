package com.example.test.model.dao;

import java.util.List;

import com.example.test.model.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
  List<Category> findAll();
}