package com.piyush.InventoryManagementSystem.service.impl;


import com.piyush.InventoryManagementSystem.dto.CategoryDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Category;
import com.piyush.InventoryManagementSystem.exceptions.DuplicateValueException;
import com.piyush.InventoryManagementSystem.exceptions.NotFoundException;
import com.piyush.InventoryManagementSystem.repository.CategoryRepository;
import com.piyush.InventoryManagementSystem.service.CategoryService;
import com.piyush.InventoryManagementSystem.utility.ConverterUtility;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private ConverterUtility converterUtility;

    @Autowired
    private UserUtility userUtility;

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {

        Category existingCategory = categoryRepository.findByCategoryAndSubCategoryAndTopicAndUserId(categoryDTO.getCategory(),
                categoryDTO.getSubCategory(),
                categoryDTO.getTopic(), userUtility.getLoggedInUser().getId());

        if (existingCategory!=null) {
            if(existingCategory.getCategory().equalsIgnoreCase(categoryDTO.getCategory().trim())
            && existingCategory.getSubCategory().equalsIgnoreCase(categoryDTO.getSubCategory().trim())
            && existingCategory.getTopic().equalsIgnoreCase(categoryDTO.getTopic().trim()))
            throw new DuplicateValueException("Duplicate Category/Subcategory/Topics not Allowed");
        }

        Category categoryToSave = converterUtility.categoryDtoToCategory(categoryDTO);
        categoryToSave.setUser(userUtility.getLoggedInUser());
        categoryRepository.save(categoryToSave);
        return Response.builder()
                .status(200)
                .message("Category created successfully.")
                .build();
    }

    @Override
    public Response updateCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = converterUtility.categoryDtoToCategory(categoryDTO);
        categoryToSave.setUser(userUtility.getLoggedInUser());
        categoryRepository.save(categoryToSave);
        return Response.builder()
                .status(200)
                .message("Category Updated successfully")
                .build();
    }

    @Override
    public Response getAllCategories(int page, int size, String sortBy, String direction, String category, String subCategory,String topic,String active) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> page1 = categoryRepository
                .findByCategoryContainingAndSubCategoryContainingAndTopicContainingAndActiveContainingAndUserId(
                        category != null ? category : "",
                        subCategory != null ? subCategory : "",
                        topic != null ? topic : "",
                        active != null ? active : "",
                        userUtility.getLoggedInUser().getId(),
                        pageable
                );

        List<Category> categoryList = page1.getContent();
        List<CategoryDTO> categoryListDTO = converterUtility.categoryListToCategoryListDTO(categoryList);
        return Response.builder()
                .status(200)
                .message("success")
                .categories(categoryListDTO)
                .totalPages(page1.getTotalPages())
                .totalElements(page1.getTotalElements())
                .last(page1.isLast())
                .first(page1.isFirst())
                .empty(page1.isEmpty())
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category Not Found"));
        CategoryDTO categoryDTO = converterUtility.categoryToCategoryDTO(category);
        return Response.builder()
                .status(200)
                .message("success")
                .category(categoryDTO)
                .build();
    }

    @Override
    @Transactional
    public Response deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category Not Found"));
        categoryRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Category Successfully Deleted")
                .build();
    }

    @Override
    public List<String> getAllCategoryList() {
        List<String> categoryList = categoryRepository.findAllCategoryNames();
        return categoryList;
    }

    @Override
    public Map<String, String> getCategoryByCategory(String category) {
        return null;
    }

    @Override
    public Map<String, Long> getCategoryCount() {
        return null;
    }

    @Override
    public List<String> getSubCategoryByCategory(String category) {
       List<String> catList = categoryRepository.findSubCategoryByCategory(category);
        return catList;
    }

    @Override
    public List<String> findTopicBySubCategory(String category) {
        List<String> topicList = categoryRepository.findTopicBySubCategory(category);
        return topicList;
    }

    @Override
    public List<String> getUserCategoryList() {
        Long userId = userUtility.getLoggedInUser().getId();
        List<String> categoryList = categoryRepository.findUserCategoryList(userId);
        return categoryList;
    }

    @Override
    public List<String> getUserSubCategoryByCategory(String category) {
        List<String> catList = categoryRepository.findSubCategoryByCategoryAndUserid(category, userUtility.getLoggedInUser().getId());
        return catList;
    }

    @Override
    public List<String> findTopicBySubCategoryAndUserid(String category) {
        List<String> topicList = categoryRepository.findTopicBySubCategoryAndUserid(category, userUtility.getLoggedInUser().getId());
        return topicList;
    }
}
