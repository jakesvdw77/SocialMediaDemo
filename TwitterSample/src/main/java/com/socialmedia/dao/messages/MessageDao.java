package com.socialmedia.dao.messages;

import java.util.List;
import java.util.Map;

import com.socialmedia.entities.Message;


/**
 * Interface that specifies how the data implementations should be implemented
 * 
 */

public interface MessageDao 
{
	Map<String,List<Message>> loadAllMessages();	
	Map<String,List<Message>> getLastLoadedMessages();
}
