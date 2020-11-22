/**
 * 
 */
package com.test.message.model;

/**
 * @author Kusha
 *
 */
public class MessageRequest {
	
	private String message;
	private String phoneNo;
	
	public String getMessage() {
		return message;
	}
	public void setMesage(String message) {
		this.message = message;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	@Override
	public String toString() {
		return "MessageRequest [message=" + message + ", phoneNo=" + phoneNo + "]";
	}
	
	

}
