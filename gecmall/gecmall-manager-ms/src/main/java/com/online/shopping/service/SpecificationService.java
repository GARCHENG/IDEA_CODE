package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojogroup.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    PageResult findPage(Integer page, Integer rows);

    Specification findOneWithOptions(String id);

    void update(Specification specification);

    void add(Specification specification);

    void delete(String[] ids);

    List<Map> selectOptionList();
}
