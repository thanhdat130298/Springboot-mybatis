package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringMybatisXml.Models.Categories.CategoryModel;

@Mapper
public interface CategoriesRepository {

	 public List<CategoryModel> getAllCategories();
	 public CategoryModel getCategoryByCateId(int cateId);
	 public CategoryModel getLastestCategory();
	 public int createCategory(CategoryModel cate);
	 public int updateCategory(CategoryModel cate);
	 public int deleteCatgogry(int cateId);
	 
}