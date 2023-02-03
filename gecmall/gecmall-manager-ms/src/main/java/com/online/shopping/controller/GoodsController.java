package com.online.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.online.shopping.activemq.JmsConfig;
import com.online.shopping.entity.PageResult;
import com.online.shopping.entity.Result;
import com.online.shopping.pojo.TbItem;
import com.online.shopping.pojogroup.Goods;
import com.online.shopping.service.GoodsService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.List;

@RestController
@RequestMapping("goods-ms/")
public class GoodsController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("add")
    public Result add(@RequestBody Goods goods){
        Result result = new Result();
        try {
            goodsService.addGoods(goods);
            result.setSuccess(true);
            result.setMessage("添加成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加失败");
        }
        return result;


    }

//    ms/search?page=1&rows=10

    @RequestMapping("search")
    public PageResult search(String page,String rows){
        PageResult pageResult = goodsService.search(page,rows);
        return pageResult;
    }



    @RequestMapping("updateStatus")
    public Result updateStatus(Long[] ids,Integer status){
        goodsService.updateStatue(ids,status);
        if (status==1){
            //传MQ使新增审核通过的goods的json数据
            List<TbItem> tbItemList = goodsService.findTbItemWithGoodsIds(ids,status);
            final String jsonString = JSON.toJSONString(tbItemList);

            Topic topic = new ActiveMQTopic(JmsConfig.TOPIC_SOLR);

            jmsTemplate.send(topic, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(jsonString);
                }
            });
            System.out.println("生成通知solr的中间件成功");

            //生成中间件,是生成html页面
            for (final Long id : ids) {
                Topic topic1 = new ActiveMQTopic(JmsConfig.TOPIC_HTML);
                jmsTemplate.send(topic1, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(id+"");
                    }
                });

            }
            System.out.println("生成通知生成html的中间件成功");


        }

        return new Result(true,"操作成功");

    }

}
