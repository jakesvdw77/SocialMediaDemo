package com.socialmedia.dao.users;

import java.util.List;
import java.util.Set;

import com.socialmedia.entities.User;

public interface UserDao 
{	
	void registerUsers();  // cater for file and database access	
	User findUser(String name);
	void addUser(User user);
	List<User>getSortedUserList();
	List<User>getUsers();
}
