package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<TbBrand> findAll();

    PageResult findPage(Integer pageNum, Integer pageSize);

    TbBrand findOne(Integer id);

    void updateBrand(TbBrand tbBrand);

    void addBrand(TbBrand tbBrand);

    void deleteByIds(String[] ids);

    List<Map> selectOptionList();
}
