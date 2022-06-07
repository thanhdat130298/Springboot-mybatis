package com.example.SpringMybatisXml.Repositories;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringMybatisXml.Models.Tags.TagModel;

@Mapper
public interface TagsRepository {

	 public List<TagModel> getAllTags();
	 public int createTag(TagModel tag);
	 public TagModel getTagById(int tagId);
	 public int updateTag(TagModel tag);
	 public int deleteTag(int tagId);
}