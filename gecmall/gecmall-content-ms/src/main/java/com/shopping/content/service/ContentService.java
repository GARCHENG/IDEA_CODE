package com.shopping.content.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbContent;

import java.util.List;

public interface ContentService {

    PageResult search(Integer page, Integer rows);

    TbContent findOne(String id);

    void update(TbContent tbContent);

    void add(TbContent tbContent);

    List<TbContent> findByCategoryId(String categoryId);
}
