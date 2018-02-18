package com.socialmedia.controllers;

import java.util.Properties;
import com.socialmedia.dao.messages.TwitterMessageDaoImpl;
import com.socialmedia.dao.users.TwitterUserDaoImpl;

public class TwitterController extends SocialMediaController
{	
		
	public TwitterController(Properties props)
	{
		String connectionType = props.getProperty("ConnectionType");
		String userPath = props.getProperty("UserPath");
		String msgPath = props.getProperty("MessagePath");
		
		if(connectionType.equals("file"))	   // currently we only have one type that we read
		{
			users = new TwitterUserDaoImpl(userPath); 
			messages = new TwitterMessageDaoImpl(msgPath);
		}		
		else
			
			throw new RuntimeException("Invalid Controller type :"+connectionType);
		
		// if we later want to use different data format or database new implementation can be parsed
		//TO:Do Implement other formats.
		
		messages = new TwitterMessageDaoImpl(msgPath);  
		
	}

	
}
