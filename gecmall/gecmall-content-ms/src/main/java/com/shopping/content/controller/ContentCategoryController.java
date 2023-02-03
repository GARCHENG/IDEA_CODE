package com.shopping.content.controller;

import com.online.shopping.pojo.TbContentCategory;
import com.shopping.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contentCategory-ms/")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("findAll")
    public List<TbContentCategory> findAll(){
        List<TbContentCategory> categoryList = contentCategoryService.findAll();
        return categoryList;

    }

}
