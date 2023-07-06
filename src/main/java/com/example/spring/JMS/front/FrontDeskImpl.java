package com.example.spring.JMS.front;

import com.example.spring.JMS.Mail;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

//JmsGatewaySupport로 jms template와 setter 메서도 대체
public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {
    @Transactional
    public void sendMail(final Mail mail) {
        /*
        getJmsTemplate().send(session -> {
            MapMessage message = session.createMapMessage();
            message.setString("mailId", mail.getMailId());
            message.setString("country", mail.getCountry());
            message.setDouble("weight", mail.getWeight());
            return message;
        });
         */

        /*
        //SimpleMessageConverter 이용한 메시지 변환기
        Map<String, Object> map = new HashMap<>();
        map.put("mailId", mail.getMailId());
        map.put("country", mail.getCountry());
        map.put("weight", mail.getWeight());
        getJmsTemplate().convertAndSend(map);
         */

        //mailmessageconvert() 이용
        getJmsTemplate().convertAndSend(mail);
    }
}


/*
public class FrontDeskImpl implements FrontDesk{
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void sendMail(final Mail mail) {
        jmsTemplate.send(destination, session -> {
            MapMessage message = session.createMapMessage();
            message.setString("mailId", mail.getMailId());
            message.setString("country", mail.getCountry());
            message.setDouble("weight", mail.getWeight());
            return message;
        });
    }
}
*/