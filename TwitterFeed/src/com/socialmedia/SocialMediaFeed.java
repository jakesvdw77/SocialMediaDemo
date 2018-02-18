package com.socialmedia;

/**
 * Author : Jaco van der Westhuizen 
 * Simple example to read user registered on Social media platform
 * Sample data feed is read from a file.
 * The users are alphabetically sorted and messages are displayed
 * Messages are not allowed to exceed 140 characters
 * The application follows makes use of abstraction to hide the internal workings from the user
 * This also allows for future changes to the underlying protocol or other medium to be added. 
 * Various JUnit tests is available to test functionality
 * 
 **/

import java.util.Properties;
import com.socialmedia.controllers.SocialMediaController;
import com.socialmedia.controllers.TwitterController;

public class SocialMediaFeed {

	/*
	 * How to run
	 * Open Command Prompt
	 * java SocialMedia -users <path> -messages <path>
	 * Example 
	 * java SocialMedia -users tweet.txt -message tweets.txt -platform Twitter -data file	 
	 * To run the default sample files use the jar file
	 */		
	
	public static void main(String[] args) 
	{	
		SocialMediaController controller = null;		
		String platform    = "twitter";		// default platform to use	
		String userPath    = "user.txt";	// default sample user file
		String messagePath = "tweet.txt";	// default sample messages file
		String messageType =  "file";	// Default way to read data
		ActionType action = ActionType.messages;
		
		
		for(int i =0 ; i < args.length;i++)
		{
			switch(i)
			{
				case 0:
					userPath = args[i];
					break;
				
				case 1:
					messagePath = args[i];
					break;
					
				case 2:
					action = Enum.valueOf(ActionType.class, args[i]);
					break;
			}
		}
		
		// Ignore case
		switch(platform.toLowerCase())
		{
			case "twitter":

				// use Properties so we can extend this later if need be
					Properties props = new Properties();
					props.setProperty("UserPath", userPath);	
					props.setProperty("MessagePath", messagePath);
					props.setProperty("ConnectionType", messageType); 					
					controller = new TwitterController(props);
					break;
					
/*
 * We could later implement other social media feeds
 * As an example

				// use Properties so we can extend this later if need be
					Properties props = new Properties();
					props.setProperty("UserPath", userPath);	
					props.setProperty("MessagePath", messagePath);
					props.setProperty("ConnectionType", messageType); 					
					controller = new FaceBookController(props);
					break;

 */
					
			default:
			{
				System.out.println("Platform not currently supported");
			}
		}	
		
		switch(action)
		{						
			case messages:
				controller.printMessages();
			break;
			
			case users:
				controller.printUsers();
			break;
		 }
		}
	
	enum ActionType
	{
		options,
		messages,
		users
		
	}

}
