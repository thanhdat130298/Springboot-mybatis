package com.example.SpringMybatisXml.Models.Comments;
import java.util.ArrayList;
// get all list
import java.util.List;

import lombok.Data;
@Data
public class CommentsDTO {
    private List<CommentModel> items = new ArrayList<CommentModel>();
}
