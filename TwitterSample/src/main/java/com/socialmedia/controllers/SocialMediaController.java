package com.socialmedia.controllers;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.socialmedia.dao.messages.InvalidMessageLengthException;
import com.socialmedia.dao.messages.MessageDao;
import com.socialmedia.dao.users.UserDao;
import com.socialmedia.entities.Message;
import com.socialmedia.entities.User;

/**
 * Main Controller class
 * Implements default print methods for to print the users and their messages
 * Different Controller Implementation can extends this class 
 * 
 */

public class SocialMediaController 
{		
	UserDao users;
	MessageDao messages;
	
		
	/**
	 * 
	 * The platform will act as a RequestMapper to determine what service we would like use
	 * The properties will provide information to the service similar as passing parameters in the http request
	 * Currently we only have one platform twitter that we support
	 * Can easily be extended to support other formats
	 */
	public static SocialMediaController getInstance(String platform, Properties properties)
	{
		SocialMediaController controller = null;
		switch(platform.toLowerCase())
		{
			case "twitter":
					controller = new TwitterController(properties);
					break;
			default:
			{
				throw new RuntimeException("Platform not currently supported");				
			}
		}	
		
		return controller;
		 
	}
	
	
	/**
	 * Prints message for a sorted list of users
	 */
	
	public void printMessages() 
	{
		Map<String, List<Message>> userMessages = messages.loadAllMessages();
		try
		{
		
		for(User user : users.getSortedUserList())
		{
			System.out.println(user);			
			if(userMessages.containsKey(user.toString()))
			{
				for(Object msg : (List<Message>)(userMessages.get(user.toString())))
				{
					System.out.println(msg);
				}
			}
		  }
		}
		catch(InvalidMessageLengthException lenghtError)
		{	
			System.out.println(lenghtError.getMessage());
		}
	}
	
	/**
	 * Provides default printing template to users and who they follow
 	 * The Different Controller implementation classes can override this method to produce different formatted output
	 */
	
	public void printUsers() 
	{
		for(User user : users.getUsers())
		{
			System.out.println(user);			
			System.out.println("Follows:");
			for(User follows : user.getFollowers())
			{
				System.out.println("\t"+follows);	
			}
		}		
	}
	
	/**
	 * Returns the message interface messages
	 * 
	 */
	
	public MessageDao getMessages()
	{
		return messages;
	}

	/**
	 * Returns user interface users
	 * 
	 */

	public UserDao getUsers()
	{
		return users;
	}
  }
