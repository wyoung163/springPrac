package com.example.spring.JMS.Back;

import com.example.spring.JMS.MailMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
@EnableTransactionManagement
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactoryB() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public Queue mailDestinationB() {
        return new ActiveMQQueue("mail.queue");
    }

    @Bean
    public JmsTemplate jmsTemplateB() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactoryB());
        jmsTemplate.setDefaultDestinationName("mail.queue");
        jmsTemplate.setMessageConverter(mailMessageConverterB());
        jmsTemplate.setReceiveTimeout(10000);
        return jmsTemplate;
    }

    @Bean
    public MailMessageConverter mailMessageConverterB() {
        return new MailMessageConverter();
    }

    //트랜잭션 관리
    @Bean
    public PlatformTransactionManager transactionManagerB() {
        return new JmsTransactionManager(connectionFactoryB());
    }

    @Bean
    public BackOfficeImpl backOffice() {
        BackOfficeImpl backOffice = new BackOfficeImpl();
        backOffice.setJmsTemplate(jmsTemplateB());
        return backOffice;
    }

    /*
        기본 목적지 설정
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
            jmsTemplate.setReceiveTimeout(10000);
            return jmsTemplate;
        }

        @Bean
        public BackOfficeImpl backOffice() {
            BackOfficeImpl backOffice = new BackOfficeImpl();
            backOffice.setJmsTemplate(jmsTemplate());
            return backOffice;
        }
     */
}

