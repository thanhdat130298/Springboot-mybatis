package com.example.SpringMybatisXml.Services;

import com.example.SpringMybatisXml.Models.Comments.CommentModel;
import com.example.SpringMybatisXml.Models.Comments.CommentsDTO;
import com.example.SpringMybatisXml.Models.ModelCommon.NotifyModel;

public interface CommentsService {

	CommentsDTO getCommentsByPost(int postId, int size, int no);

	NotifyModel addComment(CommentModel cmt);

	NotifyModel updateComment(int cmtId, CommentModel cmt);

	NotifyModel deleteComment(int cmtId);

	CommentModel getComment(int cmtId);

}
