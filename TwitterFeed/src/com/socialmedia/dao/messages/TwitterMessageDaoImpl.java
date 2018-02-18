package com.socialmedia.dao.messages;

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
	
	public String getMessagePath() {
		return messagePath;
	}

	public void setMessagePath(String messagePath) {
		this.messagePath = messagePath;
	}

	public TwitterMessageDaoImpl(String path)
	{
		this.messagePath = path;
	}		
	
	@Override
	public Map loadAllMessages() {
		
	// We don't want to read the entire file into memory as this will grow overtime
	// Rather we will read the feed line by line each time a user tries to filter.	
	// If the line matches the User filter it will be added 		
		Map<String,List<Message>> userMap = new HashMap();
		FileReader fileReader = null;
		try
		{
		try {
			File file = new File(messagePath);
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);				
			String line;				
			
			while ((line = bufferedReader.readLine()) != null) 
			{
				//Each line will contain the information about a user.	
				// Split the users input by first looking for 'follows' and then ',' in the input string
				// The user following the other users will be the first user in the list.
				String[] messageList = line.split(">\\s");
				
				if(messageList.length >1)
				{
					String userKey = messageList[0];
					
					if(messageList[1].length() > MAX_MESSAGE_SIZE)
						throw new InvalidMessageLengthException(MAX_MESSAGE_SIZE);
					
					Message msg = new Message(userKey,messageList[1]);					
					List userMessages = null;			
					boolean isNew = false;
					if(userMap.containsKey(userKey))
					{
						userMessages = userMap.get(userKey);
						userMessages.add(msg);					
						userMessages = userMap.get(userKey);
					}
					else
					{
						userMessages = new LinkedList();
						userMessages.add(msg);
						userMap.put(userKey, userMessages);
					}
				}						
			}
			fileReader.close();				
			}
		catch (IOException e) 
		{
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			if(fileReader != null)
				fileReader.close();
		}
		
		}
		catch(Exception err)
		{
			throw new RuntimeException(err.getMessage());
		}			
		
		return userMap;
	  }
}
