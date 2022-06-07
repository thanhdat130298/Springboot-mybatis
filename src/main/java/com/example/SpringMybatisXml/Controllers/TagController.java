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

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Tags.ItemTagDTO;
import com.example.SpringMybatisXml.Models.Tags.TagModel;
import com.example.SpringMybatisXml.Services.TagsService;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

	@Autowired
	private TagsService TagService;

	// GET ALL
	@GetMapping()
	public List<TagModel> getAllTags() {
		return TagService.getAllTags();
	}

	// create post
	@PostMapping()
	public NotifyModel createTag(@Valid @RequestBody TagModel tag, Errors errors) {
		return TagService.createTag(tag, errors);
	}

//	// GET USER BY cateId
	@GetMapping("/{tagId}")
	public ItemTagDTO getTagById(@PathVariable int tagId) {
		return TagService.getTagById(tagId);
	}

//
	// update user rest api
	@PutMapping("/{tagId}")
	public NotifyModel updateTag(@PathVariable int tagId, @RequestBody TagModel tagDetails, Errors errors) {
		return TagService.updateTag(tagId, tagDetails, errors);
	}

//
//	// delete user rest api: ok
	@DeleteMapping("/{tagId}")
	public NotifyModel deleteTag(@PathVariable int tagId) {
		return TagService.deleteTag(tagId);
	}
}
