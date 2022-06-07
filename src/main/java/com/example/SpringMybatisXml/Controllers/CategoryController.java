package com.example.SpringMybatisXml.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMybatisXml.Models.Categories.CategoryModel;
import com.example.SpringMybatisXml.Models.Categories.ItemCateDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Services.CategoriesService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoriesService CateService;

	// GET ALL
	@GetMapping()
	public List<CategoryModel> getAllCategories() {
		return CateService.getAllCategories();
	}

	// create post
	@PostMapping()
	public NotifyModel createCategory(@Valid @RequestBody CategoryModel cate, Errors errors) {
		return CateService.createCategory(cate, errors);
	}

//	// GET USER BY cateId
	@GetMapping("/{cateId}")
	public ItemCateDTO getCategoryByCateId(@PathVariable int cateId) {
		return CateService.getCategoryByCateId(cateId);
	}

//
	// update user rest api
	@PutMapping("/{cateId}")
	public NotifyModel updateCategory(@PathVariable int cateId, @RequestBody CategoryModel cateDetails, Errors errors) {
		return CateService.updateCategory(cateId, cateDetails, errors);
	}

//
//	// delete user rest api: ok
	@DeleteMapping("/{cateId}")
	public NotifyModel deleteCatgogry(@PathVariable int cateId) {
		return CateService.deleteCatgogry(cateId);
	}
}
