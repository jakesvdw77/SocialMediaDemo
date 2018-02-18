package com.socialmedia.entities;


/**
 * 
 * POJO class to store users
 * Implements the Comparable inteface to enable sorting
 */

import java.util.HashSet;
import java.util.Set;

/*
 * Entity class that stores the user details
 */
public class User implements Comparable<User>
{
	private String userName;
	private Set<User> followers;

	public User()
	{ 
		followers = new HashSet<User>();
	}
	
	@Override
	public String toString() {
		return userName;
	}

	public User(String userName)
	{
		this.userName = userName;		
		followers = new HashSet<User>();
				
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	
	public void startFollowing(User user)
	{
		followers.add(user);
	}
	
	public User findFollower(String name)
	{
		for(User user : followers)
		{
			if(user.getUserName().equals(name))
				return user;
		}
		
		return null;
	}

	@Override
	public int compareTo(User user) 
	{		
		return this.userName.compareTo(user.getUserName());
	}
		
}
