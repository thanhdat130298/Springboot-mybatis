package com.example.SpringMybatisXml.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMybatisXml.Models.Comments.CommentModel;
import com.example.SpringMybatisXml.Models.Comments.CommentsDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;
import com.example.SpringMybatisXml.Services.CommentsService;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

	@Autowired
	private CommentsService CommentsService;

	// GET ALL
	@GetMapping()
	public CommentsDTO getCommentsByPost(@RequestParam int postId, int size, int no) {
		return CommentsService.getCommentsByPost(postId, size, no);
	}

	// create post
	@PostMapping()
	public NotifyModel addComment(@RequestBody CommentModel cmt) {
		return CommentsService.addComment(cmt);
	}
	@PutMapping("/{cmtId}")
	public NotifyModel updateComment(@PathVariable int cmtId, @RequestBody CommentModel cmt) {
		return CommentsService.updateComment(cmtId, cmt);
	}

	@DeleteMapping("/{cmtId}")
	public NotifyModel deleteComment(@PathVariable int cmtId) {
		return CommentsService.deleteComment(cmtId);
	}
}
