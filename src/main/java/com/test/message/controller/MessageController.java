/**
 * 
 */
package com.test.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.test.message.model.MessageRequest;
import com.test.message.publisher.Publisher;


/**
 * @author Kusha
 *
 */
@RestController
@RequestMapping(path = "/message")
@CrossOrigin(origins = "*")
public class MessageController {
	
	private static final String SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String RESPONSE = "response";
	private static final String RESPONSE_Message = "Message Placed in Queue Successfully";
	
	@Autowired
	Publisher publisher;
	
	@PostMapping(path = "/api/v1/send", consumes = "application/json")
	@ResponseBody
	public <T> Map<String, T> sendMessage(@RequestBody MessageRequest messageRequest) {
		
		Map<String, String> sendInfo = new HashMap<String, String>();
		Map<String, T> response = new HashMap<>();
		sendInfo.put("phoneNo", messageRequest.getPhoneNo());
		sendInfo.put("message", messageRequest.getMessage());
		
		Gson gson = new Gson(); 
		String json = gson.toJson(sendInfo);
		
		
		publisher.produceMsg(json);
		
		response.put(MESSAGE, (T) SUCCESS);
		response.put(RESPONSE, (T) RESPONSE_Message);

		return response;
	}

}
