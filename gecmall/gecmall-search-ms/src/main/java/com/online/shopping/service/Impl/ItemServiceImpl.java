package com.online.shopping.service.Impl;

import com.alibaba.fastjson.JSON;
import com.online.shopping.pojo.TbItem;
import com.online.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Override
    public Map<String, List<TbItem>> search(String keywords) {
        Map<String, List<TbItem>> listMap = new HashMap<>();

        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title").contains(keywords);
        query.addCriteria(criteria);
        Page<TbItem> collection1 = solrTemplate.query("collection1", query, TbItem.class);

        listMap.put("rows",collection1.getContent());

        return listMap;
    }

    @Override
    public void saveItemsToSlor(String text) {
        List<TbItem> tbItemList = JSON.parseArray(text, TbItem.class);
        solrTemplate.saveBeans("collection1",tbItemList);
        solrTemplate.commit("collection1");
    }
}
