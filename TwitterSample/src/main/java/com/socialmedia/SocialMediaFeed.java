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

	/**
		Application to simulate a social media feed using a MVC design pattern
	 */
	
	
	private static void printOptions()
	{
		System.out.println("****************************************************************************************");
		System.out.println("The parameters have to be passed in the following order");
		System.out.println();
		System.out.println("\tLocation of the users file.");
		System.out.println("\tLocation of the message file.");	
		System.out.println("Optional parameter to spesify what needs to printed [messags|users]");
		System.out.println("\tmessages is the default option to print sorted list users and their messages");
		System.out.println("\tusers displays all the users and their relationships");
		System.out.println();
		System.out.println("Optional parameter to spesify the platform. The default is \"twitter\"");
		System.out.println("Currently there is only one platform so this is reserved for future use");
		System.out.println();
		System.out.println("Examples");		
		System.out.println("If the files are in the current directory use:");				
		System.out.println("\"mytweet.txt mymessages.txt");
		System.out.println("If the files are in another directory:");
		System.out.println("\t\"c:\\twitter\\myusers.txt\" \"c:\\twitter\\mytweets.txt\"");
		System.out.println("Examples of optional parameters:");
		System.out.println("\"c:\\twitter\\myusers.txt\" \"c:\\twitter\\mytweets.txt\" messages");
		System.out.println("\"c:\\twitter\\myusers.txt\" \"c:\\twitter\\mytweets.txt\" users");		
		System.out.println("\"c:\\twitter\\myusers.txt\" \"c:\\twitter\\mytweets.txt\" messages twitter");
		System.out.println("****************************************************************************************");
	}
	
	public static void main(String[] args) 
	{	
		
		try
		{
		SocialMediaController controller = null;		
		String platform    = "twitter";		
		String messageType =  "file";	// Default way to read data, Only used for unit test to load test files 
		ActionType action = ActionType.messages;
				
		if(args.length > 0)
		{
			Properties properties = new Properties();
		for(int i =0 ; i < args.length;i++)
		{
			switch(i)
			{
				case 0:
					
					properties.setProperty("UserPath", args[i]);
					break;
				
				case 1:
					properties.setProperty("MessagePath", args[i]);
					break;
					
				case 2:
					action = Enum.valueOf(ActionType.class, args[i]);
					break;
					
				case 3:
					platform = args[i];
					break;
			}
		}		
		properties.setProperty("ConnectionType", messageType); 
		controller = SocialMediaController.getInstance(platform, properties);
		}
		else
			action = ActionType.options;
		
		switch(action)
		{						
			case messages:
				controller.printMessages();
			break;
			
			case users:
				controller.printUsers();
			break;
			
			default:
				printOptions();
				break;
		 }
		
		}
		catch(RuntimeException rt)
		{
			System.out.println(rt.getMessage());
			printOptions();
		}
	}
	
	enum ActionType
	{
		options,
		messages,
		users
		
	}

}
