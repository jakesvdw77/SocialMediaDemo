package com.socialmedia;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Properties;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.socialmedia.controllers.SocialMediaController;
import com.socialmedia.entities.User;

public class TwitterTester 
{
	Properties props;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	public TwitterTester()
	{		
		props = new Properties();
	}
	
	// Test the see if an error is thrown when the user file can't be found
	@Test
	public void testUsersNotFound()
	{
	    exception.expect(RuntimeException.class);		
	    exception.expectMessage("User file not found");
		props.setProperty("ConnectionType", "file");
		props.setProperty("UserPath", "xx");			
		SocialMediaController controller = SocialMediaController.getInstance("twitter", props);
		controller.printMessages();
	}
	
	// Test the see if the default files can be read
	@Test
	public void testDefaultFiles()
	{
		props.setProperty("ConnectionType", "local");
		props.setProperty("MessagePath", "tweet.txt");
		props.setProperty("UserPath", "user.txt");
		SocialMediaController controller = SocialMediaController.getInstance("twitter", props);	
		controller.printMessages();
	}
	
	
	// Test the see that a message passes if its length is 140 or less characters
	// Test the see that the message is ignored if its length is greater that 140 characters
	// The test file has 2 message for User Alan 
	// The first message is < 140 characters and the second message is > 140
	// So we should only get 1 message back
	
	@Test
	public void testDefaultMessageLength()
	{
		props.setProperty("ConnectionType", "local");
		props.setProperty("MessagePath", "invalidmessage.txt");
		props.setProperty("UserPath", "user.txt");
		SocialMediaController controller = SocialMediaController.getInstance("twitter", props);		
		controller.getMessages().loadAllMessages();
		int size = controller.getMessages().getLastLoadedMessages().get("Jakes").size();
		assertTrue(size == 1);
	}
	
	
	// Test that the user file is parsed correctly	
	@Test
	public void testUserParser()
	{
		props.setProperty("ConnectionType", "local");
		props.setProperty("MessagePath", "tweet.txt");
		props.setProperty("UserPath", "user.txt");
		SocialMediaController controller = SocialMediaController.getInstance("twitter", props);		
		controller.getMessages().loadAllMessages();
		int size = controller.getUsers().getUsers().size();
		assertTrue(size == 3);
	}
	
	// Test that the user file is sorted correctly
	@Test
	public void testUserSorter()
	{
		props.setProperty("ConnectionType", "local");
		props.setProperty("MessagePath", "tweet.txt");
		props.setProperty("UserPath", "user.txt");
		SocialMediaController controller = SocialMediaController.getInstance("twitter", props);		
		controller.getMessages().loadAllMessages();
		List<User> userList = controller.getUsers().getSortedUserList();
		assertEquals(userList.get(0).getUserName(),"Jakes");
		assertEquals(userList.get(1).getUserName(),"Jane");		
		assertEquals(userList.get(2).getUserName(),"Peter");
	}
}
