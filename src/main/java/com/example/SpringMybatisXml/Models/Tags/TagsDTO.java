package com.example.SpringMybatisXml.Models.Tags;
import java.util.ArrayList;
// get all list
import java.util.List;

import lombok.Data;
@Data
public class TagsDTO {
    private List<TagModel> items = new ArrayList<TagModel>();
}
