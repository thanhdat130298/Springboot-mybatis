package com.example.SpringMybatisXml.Services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Tags.ItemTagDTO;
import com.example.SpringMybatisXml.Models.Tags.TagModel;

public interface TagsService {

	List<TagModel> getAllTags();

	NotifyModel createTag(@Valid TagModel tag, Errors errors);

	ItemTagDTO getTagById(int tagId);

	NotifyModel updateTag(int tagId, TagModel tag, Errors errors);

	NotifyModel deleteTag(int tagId);

}
