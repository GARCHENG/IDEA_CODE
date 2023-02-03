package com.online.shopping.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.api.client.json.Json;
import com.online.shopping.entity.PageResult;
import com.online.shopping.mapper.TbGoodsDescMapper;
import com.online.shopping.mapper.TbGoodsMapper;
import com.online.shopping.mapper.TbItemMapper;
import com.online.shopping.pojo.*;
import com.online.shopping.pojogroup.Goods;
import com.online.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;
    @Autowired
    private TbItemMapper tbItemMapper;


    @Override
    @Transactional
    public void addGoods(Goods goods) {
        TbGoods tbGoods = goods.getGoods();
        tbGoods.setAuditStatus("0");
        tbGoods.setSellerId("test");

        tbGoodsMapper.insertSelective(tbGoods);

        TbGoodsDesc goodsDesc = goods.getGoodsDesc();
        goodsDesc.setGoodsId(tbGoods.getId());
        tbGoodsDescMapper.insertSelective(goodsDesc);

        String itemImages = goodsDesc.getItemImages();
        System.out.println(itemImages);
        List<Map> maps = JSON.parseArray(itemImages, Map.class);
        String url = (String) maps.get(0).get("url");
        System.out.println(url);

        List<TbItem> itemList = goods.getItemList();
        for (TbItem tbItem : itemList) {
            tbItem.setGoodsId(tbGoods.getId());
            tbItem.setTitle(tbGoods.getGoodsName());
            tbItem.setCategoryid(tbGoods.getCategory3Id());
            tbItem.setCreateTime(new Date());
            tbItem.setUpdateTime(new Date());
            tbItem.setImage(url);

            tbItemMapper.insertSelective(tbItem);
        }

    }

    @Override
    public PageResult search(String page, String rows) {
        PageResult pageResult = new PageResult();

        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(rows));

        TbGoodsExample selectByExample = new TbGoodsExample();
        TbGoodsExample.Criteria criteria = selectByExample.createCriteria();
        criteria.andSellerIdEqualTo("test");
        List<TbGoods> tbGoods = tbGoodsMapper.selectByExample(selectByExample);
        pageResult.setRows(tbGoods);

        int i = tbGoodsMapper.countByExample(selectByExample);
        pageResult.setTotal(i);
        return pageResult;
    }

    @Override
    public void updateStatue(Long[] ids, Integer status) {
        for (Long id : ids) {
            TbGoods tbGoods = new TbGoods();
            tbGoods.setId(id);
            tbGoods.setAuditStatus(status+"");
            tbGoodsMapper.updateByPrimaryKeySelective(tbGoods);
        }

    }

    @Override
    public List<TbItem> findTbItemWithGoodsIds(Long[] ids, Integer status) {
        TbItemExample selectByExample = new TbItemExample();
        TbItemExample.Criteria criteria = selectByExample.createCriteria();
        List<Long> idsList = Arrays.asList(ids);
        criteria.andGoodsIdIn(idsList);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(selectByExample);
        return tbItemList;
    }
}
