package com.hd.ibus.util.activeMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSON;

public class AutoUDPPushServiceImpl implements PushService{

	
	@Autowired  
    private JmsTemplate jmsTemplate; 
	
	/** 
     * 这里是根据MQ配置文件定义的queue来注入的，也就是这里将会把不同的内容推送到不同的queue中 
     */  
  

    public void push(final Object info,final Destination destination) {  
        pushExecutor.execute(new Runnable() {  
        	
            public void run() {   
            	long start = System.currentTimeMillis();
                jmsTemplate.send(destination, new MessageCreator() {  
                    public Message createMessage(Session session) throws JMSException {  
                        // User p = (User) info;  
                        return session.createTextMessage(JSON.toJSONString(info));  
                    }  
                }); 
                long end = System.currentTimeMillis();
                System.out.println(" jmsTemplate.send(destination, new MessageCreator():"+(end-start));
            }             
        });  
    }
 
    
    
}
