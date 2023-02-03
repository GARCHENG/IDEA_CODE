package com.online.shopping.service;

import com.online.shopping.pojo.TbItemCat;

import java.util.List;

public interface TBItemCatService {

    List<TbItemCat> findByParentId(Integer parentId);

    TbItemCat findOneById(String id);

    void update(TbItemCat tbItemCat);

    void add(TbItemCat tbItemCat);

    void delByIds(String[] ids);

    List<TbItemCat> findAll();
}
