package com.online.shopping.activemq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class Config {


    public final static String QUEUE = "pingyougou.queue.solr";
    public final static String QUEUE_DELETE = "pingyougou.queue.solr.delete";

    public final static String TOPIC_SOLR = "pingyougou.topic.solr";

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
// bean.setPubSubDomain(false);  默认是 queue 方式   一对一模式

        return bean;
    }

    // topic模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        bean.setPubSubDomain(true);
        return bean;
    }
}
