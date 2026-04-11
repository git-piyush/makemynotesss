package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.CategoryDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories(int page, int size, String sortBy, String direction, String category, String subcategory,String topic,String active);

    Response getCategoryById(Long id);

    Response updateCategory(CategoryDTO categoryDTO);

    Response deleteCategory(Long id);

    List<String> getAllCategoryList();

    Map<String, String> getCategoryByCategory(String category);

    Map<String, Long> getCategoryCount();

    List<String> getSubCategoryByCategory(String cat);

    List<String> findTopicBySubCategory(String subCat);

    List<String> getUserCategoryList();

    List<String> getUserSubCategoryByCategory(String category);

    List<String> findTopicBySubCategoryAndUserid(String cat);
}
