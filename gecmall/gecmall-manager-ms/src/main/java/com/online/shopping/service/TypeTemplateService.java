package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {
    PageResult findPage(Integer page, Integer rows);

    TbTypeTemplate findOne(String id);

    void update(TbTypeTemplate tbTypeTemplate);

    void add(TbTypeTemplate tbTypeTemplate);

    void deleteByIds(String[] ids);

    List<Map> findTypeTemplateList();

    List<Map> findBySpecList(String id);
}
