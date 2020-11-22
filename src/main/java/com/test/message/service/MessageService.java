/**
 * 
 */
package com.test.message.service;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Kusha
 *
 */
@Service
public interface MessageService {

	void sendMessage(Map<String, String> genericInfo);

}
