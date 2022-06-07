package com.example.SpringMybatisXml.Controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Models.Posts.ItemPostDTO;
import com.example.SpringMybatisXml.Models.Posts.PostModel;
import com.example.SpringMybatisXml.Models.Posts.PostsDTO;
import com.example.SpringMybatisXml.Services.PostsService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

	@Autowired
	private PostsService PostsService;

	// GET ALL
	@GetMapping()
	public PostsDTO getAllPosts(@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "rowSize", required = false, defaultValue = "10") int rowSize) {
		return PostsService.getAllPosts(sortBy, pageNo, rowSize);
	}

	@GetMapping("/get-my-posts")
	public PostsDTO getMyPosts(@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "rowSize", required = false, defaultValue = "10") int rowSize) {
		return PostsService.getMyPosts(sortBy, pageNo, rowSize);
	}

	// create post
	@PostMapping()
	public NotifyModel createPost(@Valid @RequestBody PostModel post, Errors errors) {
		return PostsService.createPost(post, errors);
	}

//	// GET USER BY postId
	@GetMapping("/{postId}")
	public ItemPostDTO getPostById(@PathVariable int postId) {
		return PostsService.getPostById(postId);
	}

//
	// update user rest api
	@PutMapping("/{postId}")
	public NotifyModel updatePost(@PathVariable int postId, @RequestBody PostModel postDetails, Errors errors) {
		return PostsService.updatePost(postId, postDetails, errors);
	}

//
//	// delete user rest api: ok
	@DeleteMapping("/{postId}")
	public NotifyModel deletePost(@PathVariable int postId) {
		return PostsService.deletePost(postId);
	}
}
