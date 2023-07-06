package com.example.spring.JMS.Back;

import com.example.spring.JMS.Mail;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

// JmsGatewaySupport로 jms template와 setter 메서도 대체
public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {
    @Transactional
    public Mail receiveMail() {
        /*
        MapMessage message = (MapMessage) getJmsTemplate().receive();
        try {
            if (message == null) {
                return null;
            }
            Mail mail = new Mail();
            mail.setMailId(message.getString("mailId"));
            mail.setCountry(message.getString("country"));
            mail.setWeight(message.getDouble("weight"));
            return mail;
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
         */

        /*
        //SimpleMessageConverter 이용한 메시지 변환기
        Map<String, Object> map = (Map<String, Object>) getJmsTemplate().receiveAndConvert();
        Mail mail = new Mail();
        mail.setMailId((String) map.get("mailId"));
        mail.setCountry((String) map.get("country"));
        mail.setWeight((Double) map.get("weight"));
        return mail;
         */

        //mailmessageconvert() 이용
        return (Mail) getJmsTemplate().receiveAndConvert();
    }
}

/*
public class BackOfficeImpl  implements BackOffice {

    private JmsTemplate jmsTemplate;
    private Destination destination;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Mail receiveMail() {
        MapMessage message = (MapMessage) jmsTemplate.receive(destination);
        try {
            if (message == null) {
                return null;
            }
            Mail mail = new Mail();
            mail.setMailId(message.getString("mailId"));
            mail.setCountry(message.getString("country"));
            mail.setWeight(message.getDouble("weight"));
            return mail;
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
 */