/**
 * 
 */
package com.test.message.subscriber;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.test.message.service.MessageService;

/**
 * @author Kusha
 *
 */
@Component
public class Subscriber {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);
	
	@Autowired
	private MessageService messageService;
	
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(String msg) {
        LOGGER.info(msg);
        // decompose message
        Gson gson = new Gson();
        Map map = gson.fromJson(msg, Map.class);
        
        messageService.sendMessage(map);
        
        
    }
}
