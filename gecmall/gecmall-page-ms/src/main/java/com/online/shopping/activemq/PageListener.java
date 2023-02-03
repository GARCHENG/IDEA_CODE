package com.online.shopping.activemq;

import com.online.shopping.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class PageListener {

    @Autowired
    private PageService pageService;

    @JmsListener(destination = Config.TOPIC_HTML,containerFactory = "jmsListenerContainerTopic")
    public void onMessageToHtml(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            String id = textMessage.getText();
            pageService.getItemHtml(id);
            System.out.println("HTML页面生成成功~~~~");
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
