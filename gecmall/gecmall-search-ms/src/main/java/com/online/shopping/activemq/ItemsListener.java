package com.online.shopping.activemq;

import com.online.shopping.service.ItemService;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class ItemsListener {

    @Autowired
    private ItemService itemService;

    @JmsListener(destination = Config.TOPIC_SOLR,containerFactory = "jmsListenerContainerTopic")
    public void onItemSearch(Message message){
        TextMessage textMessage = (TextMessage) message;
        String text = null;
        try {
            text = textMessage.getText();
            System.out.println("接收到的json数据为:"+text);
            itemService.saveItemsToSlor(text);
            System.out.println("solr数据更新成功!");
        } catch (JMSException e) {
            e.printStackTrace();
        }



    }

}
