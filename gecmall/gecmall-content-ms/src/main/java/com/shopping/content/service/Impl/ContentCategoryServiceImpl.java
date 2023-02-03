package com.shopping.content.service.Impl;

import com.online.shopping.pojo.TbContentCategory;
import com.shopping.content.mapper.TbContentCategoryMapper;
import com.shopping.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    @Override
    public List<TbContentCategory> findAll() {
        List<TbContentCategory> categoryList = tbContentCategoryMapper.selectByExample(null);
        return categoryList;
    }
}
