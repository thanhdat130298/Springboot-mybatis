package com.example.SpringMybatisXml.Services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Models.Categories.CategoryModel;
import com.example.SpringMybatisXml.Models.Categories.ItemCateDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;

public interface CategoriesService {

	List<CategoryModel> getAllCategories();

	NotifyModel createCategory(@Valid CategoryModel cate, Errors errors);

	ItemCateDTO getCategoryByCateId(int userId);
	
	NotifyModel updateCategory(int cateId, CategoryModel cate, Errors errors);

	NotifyModel deleteCatgogry(int postId);

}
