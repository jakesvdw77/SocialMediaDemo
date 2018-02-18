package com.socialmedia.entities;

import java.util.HashSet;
import java.util.Set;

/*
 * Entity class that stores the user details
 */
public class User implements Comparable
{
	private String userName;
	private Set<User> followers;

	public User()
	{ 
		followers = new HashSet();
	}
	
	@Override
	public String toString() {
		return userName;
	}

	public User(String userName)
	{
		this.userName = userName;		
		followers = new HashSet();
				
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
	public int compareTo(Object o) 
	{		
		return this.userName.compareTo(((User)o).getUserName());
	}
		
}
