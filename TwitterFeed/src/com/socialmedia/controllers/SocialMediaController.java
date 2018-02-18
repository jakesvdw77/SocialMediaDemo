package com.socialmedia.controllers;

import java.util.List;
import java.util.Map;

import com.socialmedia.dao.messages.InvalidMessageLengthException;
import com.socialmedia.dao.messages.MessageDao;
import com.socialmedia.dao.users.UserDao;
import com.socialmedia.entities.User;

public class SocialMediaController {
		
	UserDao users;
	MessageDao messages;
	
	// Provides default printing template to display messages
	public void printMessages() 
	{
		Map userMessages = messages.loadAllMessages();
		
		for(User user : users.getSortedUserList())
		{
			System.out.println(user);
			
			if(userMessages.containsKey(user.toString()))
			{
				for(Object msg : (List)(userMessages.get(user.toString())))
				{
					try
					{
						System.out.println(msg);
					}
					catch(InvalidMessageLengthException lenght)
					{						
					}
				}
			}
		}
	}

	// Provides default printing template to users and who they follow
	public void printUsers() {

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

}
