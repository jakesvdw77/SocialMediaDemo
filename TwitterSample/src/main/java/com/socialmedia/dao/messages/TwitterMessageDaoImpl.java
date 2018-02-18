package com.socialmedia.dao.messages;

/**
 * Data Access Implementation class to read a simulated Twitter feed from a file
 * Implements the MessageDao interface
 * 
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.socialmedia.entities.Message;


public class TwitterMessageDaoImpl implements MessageDao 
{
	String messagePath;	
	final int MAX_MESSAGE_SIZE = 140;
	final String fileType;

	private Map<String, List<Message>> userMap;
	
	public TwitterMessageDaoImpl(String path, String type) {
		this.messagePath = path;
		this.fileType = type;
	}

	public String getMessagePath() {
		return messagePath;
	}

	public void setMessagePath(String messagePath) {
		this.messagePath = messagePath;
	}

	@Override
	public Map<String,List<Message>> loadAllMessages() {

		// We don't want to read the entire file into memory as this will grow
		// overtime
		// Rather we will read the feed line by line each time a user tries to
		// filter.
		// If the line matches the User filter it will be added

		userMap = new HashMap<String,List<Message>>();
		FileReader fileReader = null;

			try {
				if (fileType.equals("file"))
					fileReader = new FileReader(messagePath);
				else 
				{
					// This done to read local for JUnit tests
					ClassLoader classLoader = getClass().getClassLoader();
					File file = new File(classLoader.getResource(messagePath).getFile());
					fileReader = new FileReader(file);
				}

				BufferedReader bufferedReader = new BufferedReader(fileReader); // Closed in finally block to ensure it gets closed
				String line;

				
					while ((line = bufferedReader.readLine()) != null) 
					{
				
						try
						{
					// Each line will contain the information about a user.
					// Split the users input by first looking for 'follows' and
					// then ',' in the input string
					// The user following the other users will be the first user
					// in the list.
					String[] messageList = line.split(">\\s");

					if (messageList.length > 1) {
						String userKey = messageList[0];

						if (messageList[1].length() > MAX_MESSAGE_SIZE)
							throw new InvalidMessageLengthException(MAX_MESSAGE_SIZE);

						Message msg = new Message(userKey, messageList[1]);
						List<Message> userMessages = null;
						if (userMap.containsKey(userKey)) {
							userMessages = userMap.get(userKey);
							userMessages.add(msg);
							userMessages = userMap.get(userKey);
						} else {
							userMessages = new LinkedList<Message>();
							userMessages.add(msg);
							userMap.put(userKey, userMessages);
						}
					}				
				}
						catch(InvalidMessageLengthException lenghtError)
						{
							// We have two options
							// Either just ignore the record or throw a runtime exception //
							// example throw lenghtError
							// In this implementation we will just print the message and continue
							System.out.println(lenghtError.getMessage());
						}
					}				
					
			} 
		catch (IOException e) {
			throw new RuntimeException("Message file not found");
		}

		finally 
		{
			try
			{
				if (fileReader != null) // Guarantee that file reader gets closed
					fileReader.close();
			}
			catch(Exception err)
			{
				throw new RuntimeException(err.getMessage());
			}
		}		
		return userMap;	
	}

	@Override
	public Map<String, List<Message>> getLastLoadedMessages() {
		return userMap;
	}
		
}
