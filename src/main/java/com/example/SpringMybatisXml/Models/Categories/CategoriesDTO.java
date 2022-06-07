package com.example.SpringMybatisXml.Models.Categories;
import java.util.ArrayList;
// get all list
import java.util.List;

import lombok.Data;
@Data
public class CategoriesDTO {
    private List<CategoryModel> items = new ArrayList<CategoryModel>();
}
