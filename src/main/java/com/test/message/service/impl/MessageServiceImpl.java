/**
 * 
 */
package com.test.message.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.message.config.TwilioConfiguration;
import com.test.message.service.MessageService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;

/**
 * @author Kusha
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final TwilioConfiguration twilioConfiguration;
    
    @Autowired
    public MessageServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }
    
	@Override
	public void sendMessage(Map<String, String> genericInfo) {
		// TODO Auto-generated method
		PhoneNumber to = new PhoneNumber(genericInfo.get("phoneNo"));
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        Message message = Message.creator(to, from, genericInfo.get("message")).create();
		
	}
}
