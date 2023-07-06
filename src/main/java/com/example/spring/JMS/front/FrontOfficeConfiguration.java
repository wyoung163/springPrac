package com.example.spring.JMS.front;

import com.example.spring.JMS.MailMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
public class FrontOfficeConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public Queue mailDestination() {
        return new ActiveMQQueue("mail.queue");
    }

    //jms template은 jms 연결, 세션을 얻고 해제하는 일을 도와주며 메시지 전송
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName("mail.queue");
        jmsTemplate.setMessageConverter(mailMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public MailMessageConverter mailMessageConverter() {
        return new MailMessageConverter();
    }


    @Bean
    public FrontDeskImpl frontDesk() {
        FrontDeskImpl frontDesk = new FrontDeskImpl();
        frontDesk.setJmsTemplate(jmsTemplate());
        //frontDesk.setDestination(mailDestination());
        return frontDesk;
    }
    /*
    `   기본 목적지 정하기
        @Bean
        public ConnectionFactory connectionFactory() {
            return new ActiveMQConnectionFactory("tcp://localhost:61616");
        }

        @Bean
        public Queue mailDestination() {
            return new ActiveMQQueue("mail.queue");
        }

        @Bean
        public JmsTemplate jmsTemplate() {
            JmsTemplate jmsTemplate = new JmsTemplate();
            jmsTemplate.setConnectionFactory(connectionFactory());
            jmsTemplate.setDefaultDestination(mailDestination());
            return jmsTemplate;
        }

        @Bean
        public FrontDeskImpl frontDesk() {
            FrontDeskImpl frontDesk = new FrontDeskImpl();
            frontDesk.setJmsTemplate(jmsTemplate());
            return frontDesk;
        }
     */
}

