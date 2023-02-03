package com.online.shopping.service.Impl;

import com.online.shopping.mapper.TbGoodsDescMapper;
import com.online.shopping.mapper.TbGoodsMapper;
import com.online.shopping.mapper.TbItemCatMapper;
import com.online.shopping.mapper.TbItemMapper;
import com.online.shopping.pojo.*;
import com.online.shopping.service.PageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    private String pageDir="D:\\粤嵌\\粤嵌（暑假）\\第三阶段\\16.电商项目\\HBuilder_CODE\\前端网页\\前端网页\\pingyouge-page-web\\";

    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;


    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void getItemHtml(String id) {

        System.out.println("html生成中,请稍后~~~~");
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        try {
            Template template = configuration.getTemplate("item.ftl");

            Map dataModel = new HashMap<>();

            TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(Long.parseLong(id));
            dataModel.put("goods",tbGoods);

            TbGoodsDescExample example = new TbGoodsDescExample();
            TbGoodsDescExample.Criteria criteria = example.createCriteria();
            criteria.andGoodsIdEqualTo(Long.parseLong(id));
            List<TbGoodsDesc> tbGoodsDescs = tbGoodsDescMapper.selectByExample(example);
            dataModel.put("goodsDesc",tbGoodsDescs.get(0));

            String itemCat1 = tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory1Id()).getName();
            String itemCat2 = tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory2Id()).getName();
            String itemCat3 = tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory3Id()).getName();
            dataModel.put("itemCat1", itemCat1);
            dataModel.put("itemCat2", itemCat2);
            dataModel.put("itemCat3", itemCat3);

            TbItemExample example2=new TbItemExample();
            TbItemExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andGoodsIdEqualTo(Long.parseLong(id));//SPU ID
            criteria2.andStatusEqualTo("1");//状态有效
            example.setOrderByClause("is_default desc");//按是否默认字段进行降序排序，目的是返回的结果第一条为默认SKU

            List<TbItem> itemList = tbItemMapper.selectByExample(example2);
            dataModel.put("itemList", itemList);

            Writer out=new FileWriter(pageDir+id+".html");

            template.process(dataModel, out);//输出
            System.out.println("html生成完成~~~~");
            out.close();

        }catch (Exception e){

        }

    }
}
