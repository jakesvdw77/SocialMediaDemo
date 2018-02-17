package com.socialmedia.dao.messages;

/**
 * Message to display a received message length has been exceeded
 * Implements java.lang.RuntimeException interface
 * 
 */

public class InvalidMessageLengthException extends RuntimeException 
{
	public InvalidMessageLengthException(int length)
	{
		super("Maximum message length exceeded:"+length);
	}
}
