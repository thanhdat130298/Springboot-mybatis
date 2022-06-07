package com.example.SpringMybatisXml.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Config.Helper;
import com.example.SpringMybatisXml.Config.Role;
import com.example.SpringMybatisXml.Config.utils;
import com.example.SpringMybatisXml.Exception.BadRequest400;
import com.example.SpringMybatisXml.Exception.NotFound404;
import com.example.SpringMybatisXml.Models.Categories.CategoryModel;
import com.example.SpringMybatisXml.Models.Categories.ItemCateDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Repositories.CategoriesRepository;
import com.example.SpringMybatisXml.Services.CategoriesService;

@Service
public class CategoryImp implements CategoriesService {

	@Autowired
	Helper Helper;
	@Autowired
	UserImp uService;
	@Autowired
	CategoriesRepository cRepo;

//	***************** DONE
	@Override
	public List<CategoryModel> getAllCategories() {
		return cRepo.getAllCategories();
	}

	@Override
	public NotifyModel createCategory(CategoryModel cate, Errors errors) {
		if (utils.getRole() == Role.ADMIN) {
			// get latest id user to increment
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
//			UserInfoModel tokenInfo = utils.getTokenInfo();
			CategoryModel lastestCate = cRepo.getLastestCategory();
			cate.setCateId(utils.autoCreaId(lastestCate != null ? lastestCate.getCateId() : 0));
			if (cRepo.createCategory(cate) != 1)
				new BadRequest400("Could not create post !");
			return new NotifyModel("Success", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to create category !");
	}

	@Override
	public ItemCateDTO getCategoryByCateId(int cateId) {
		CategoryModel cate = cRepo.getCategoryByCateId(cateId);
		if (cate == null) {
			throw new NotFound404("Category is not exist!");
		}
		ItemCateDTO c = new ItemCateDTO("success", HttpStatus.OK.value(), cate);
		return c;
	}

	@Override
	public NotifyModel updateCategory(int cateId, CategoryModel cateDetails, Errors errors) {
		if (utils.getRole() == Role.ADMIN) {
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
			cateDetails.setCateId(cateId);
			if (cRepo.getCategoryByCateId(cateId) == null)
				throw new NotFound404("Category is Not Exist!");
			if (cRepo.updateCategory(cateDetails) == 0) {
				throw new BadRequest400("Could not update!");
			}
			return new NotifyModel("Updated!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to update category !");

	}

	@Override
	public NotifyModel deleteCatgogry(int cateId) {
		if (utils.getRole() == Role.ADMIN) {
//			UserInfoModel tokenInfo = utils.getTokenInfo();
			if (cRepo.getCategoryByCateId(cateId) == null)
				throw new NotFound404("Post is Not Exist!");

			if (cRepo.deleteCatgogry(cateId) == 0)
				throw new NotFound404("Could not delete this category!");
			return new NotifyModel("Deleted!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to delete category !");
	}

}
