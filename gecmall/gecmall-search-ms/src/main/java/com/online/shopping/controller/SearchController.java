package com.online.shopping.controller;

import com.online.shopping.pojo.TbItem;
import com.online.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("itemsearch-ms/")
public class SearchController {
//    localhost:8989/shopping-search/itemsearch-ms/search

    @Autowired
    private ItemService itemService;

    @RequestMapping("search")
    public Map search(@RequestBody Map map){
        Map<String, List<TbItem>> listMap = itemService.search((String) map.get("keywords"));
        return listMap;
    }

}
