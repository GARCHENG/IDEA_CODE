package com.online.shopping.service;

import com.online.shopping.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemService {
    Map<String, List<TbItem>> search(String keywords);

    void saveItemsToSlor(String text);
}
