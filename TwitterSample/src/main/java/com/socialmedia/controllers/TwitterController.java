package com.socialmedia.controllers;

import java.util.Properties;
import com.socialmedia.dao.messages.TwitterMessageDaoImpl;
import com.socialmedia.dao.users.TwitterUserDaoImpl;

/**
 * Demo Twitter Controller implementation
 * Extends the Generic SocialMediaController class
 * 
 */

public class TwitterController extends SocialMediaController
{			
	public TwitterController(Properties props)
	{
		String connectionType = props.getProperty("ConnectionType");
		String userPath = props.getProperty("UserPath");
		String msgPath = props.getProperty("MessagePath");
		users = new TwitterUserDaoImpl(userPath,connectionType); 
		messages = new TwitterMessageDaoImpl(msgPath,connectionType);
	}
}
