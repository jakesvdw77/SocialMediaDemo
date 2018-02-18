package com.socialmedia.dao.messages;

import java.util.List;
import java.util.Map;

import com.socialmedia.entities.Message;

public interface MessageDao 
{
	Map<String,List<Message>> loadAllMessages();
}
