package com.socialmedia.test;

import java.util.Properties;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.socialmedia.controllers.SocialMediaController;
import com.socialmedia.controllers.TwitterController;

public class TwitterTester 
{

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	public void testUsersNotFound()
	{
	    exception.expect(RuntimeException.class);    
		Properties p = new Properties();	
		Properties props = new Properties();
		props.setProperty("ConnectionType", "file");
		props.setProperty("UserPath", "xx");			
		SocialMediaController controller = new TwitterController(props);
		controller.printMessages();
	}
	
	@Test
	public void testDefaultFiles()
	{
		Properties p = new Properties();	
		Properties props = new Properties();
		props.setProperty("ConnectionType", "file");
		props.setProperty("MessagePath", "tweet.txt");
		props.setProperty("UserPath", "user.txt");
		SocialMediaController controller = new TwitterController(props);		
		controller.printMessages();
	}
}
