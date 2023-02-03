package com.online.shopping.service;

import com.online.shopping.entity.PageResult;
import com.online.shopping.pojo.TbItem;
import com.online.shopping.pojogroup.Goods;

import java.util.List;

public interface GoodsService {
    void addGoods(Goods goods);

    PageResult search(String page, String rows);

    void updateStatue(Long[] ids, Integer status);

    List<TbItem> findTbItemWithGoodsIds(Long[] ids, Integer status);
}
