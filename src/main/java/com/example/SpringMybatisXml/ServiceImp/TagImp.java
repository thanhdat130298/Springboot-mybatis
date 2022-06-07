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
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Tags.ItemTagDTO;
import com.example.SpringMybatisXml.Models.Tags.TagModel;
import com.example.SpringMybatisXml.Repositories.TagsRepository;
import com.example.SpringMybatisXml.Services.TagsService;

@Service
public class TagImp implements TagsService {

	@Autowired
	Helper Helper;
	@Autowired
	UserImp uService;
	@Autowired
	TagsRepository tRepo;

//	***************** DONE
	@Override
	public List<TagModel> getAllTags() {
		return tRepo.getAllTags();
	}

	@Override
	public NotifyModel createTag(TagModel tag, Errors errors) {
		if (utils.getRole() == Role.ADMIN) {
			// get latest id user to increment
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
//			UserInfoModel tokenInfo = utils.getTokenInfo();
//			CategoryModel lastestCate = cRepo.getLastestCategory();
//			cate.setCateId(utils.autoCreaId(lastestCate != null ? lastestCate.getCateId() : 0));
			if (tRepo.createTag(tag) != 1)
				new BadRequest400("Could not create tag !");
			return new NotifyModel("Success", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to create tag !");
	}

	@Override
	public ItemTagDTO getTagById(int tagId) {
		TagModel tag = tRepo.getTagById(tagId);
		if (tag == null) {
			throw new NotFound404("Tag is not exist!");
		}
		ItemTagDTO t = new ItemTagDTO("success", HttpStatus.OK.value(), tag);
		return t;
	}

	@Override
	public NotifyModel updateTag(int tagId, TagModel tag, Errors errors) {
		if (utils.getRole() == Role.ADMIN) {
			if (errors.hasErrors()) {
				String message = utils.validateInput(errors);
				throw new BadRequest400(message);
			}
			tag.setTagId(tagId);
			if (tRepo.getTagById(tagId) == null)
				throw new NotFound404("Tag is Not Exist!");
			if (tRepo.updateTag(tag) == 0) {
				throw new BadRequest400("Could not update!");
			}
			return new NotifyModel("Updated!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to update tag !");

	}

	@Override
	public NotifyModel deleteTag(int tagId) {
		if (utils.getRole() == Role.ADMIN) {
//			UserInfoModel tokenInfo = utils.getTokenInfo();
			if (tRepo.getTagById(tagId) == null)
				throw new NotFound404("Tag is Not Exist!");

			if (tRepo.deleteTag(tagId) == 0)
				throw new NotFound404("Could not delete this tag!");
			return new NotifyModel("Deleted!", HttpStatus.OK.value());
		}
		throw new BadRequest400("You have not permission to delete tag !");
	}

}
