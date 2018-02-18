package com.socialmedia.dao.messages;

public class InvalidMessageLengthException extends RuntimeException 
{
	public InvalidMessageLengthException(int length)
	{
		super("Maximum message length exceeded:"+length);
	}
}
