package com.socialmedia.entities;

public class Message 
{
	private String content;
	private String receiver;
	
	public Message(String receiver, String content)
	{
		this.content = content;
		this.receiver = receiver;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "@"+receiver+":"+content;
	}
	
	
}
