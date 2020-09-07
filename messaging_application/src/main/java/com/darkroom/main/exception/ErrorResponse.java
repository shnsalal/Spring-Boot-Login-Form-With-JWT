package com.darkroom.main.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
	private String statuCode;
	private String errorContent;
	private List<String> messages;
	
	public ErrorResponse(String statuCode, String errorContent, String messages) {
		this.statuCode = statuCode;
		this.errorContent = errorContent;
		this.messages = new ArrayList<>();
		this.messages.add(messages);
	}
	
	public ErrorResponse(String statuCode, String errorContent, List<String> messages) {
		this.statuCode = statuCode;
		this.errorContent = errorContent;
		this.messages = messages;
	}
	
	
	public String getStatuCode() {
		return statuCode;
	}

	public void setStatuCode(String statuCode) {
		this.statuCode = statuCode;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}


}
