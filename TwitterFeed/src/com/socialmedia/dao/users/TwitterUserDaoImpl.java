package com.socialmedia.dao.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.socialmedia.entities.User;

public class TwitterUserDaoImpl implements UserDao 
{
	protected List<User> messageUsers;		
	protected String connectionPath;	
	
	
	public TwitterUserDaoImpl(String connectionPath)
	{
		this.setConnectionPath(connectionPath);		
		//messageUsers = new HashSet();		
		
		messageUsers = new LinkedList();
		registerUsers();
	}
	
	public String getConnectionPath() {
		return connectionPath;
	}


	public void setConnectionPath(String connectionPath) {
		this.connectionPath = connectionPath;
	}	
	
	@Override
	public void registerUsers() 
	{
		try
		{
			if(messageUsers == null)
				messageUsers = new LinkedList();  // only need to initialize the set when the data gets loaded
			FileReader fileReader = null;
			
			try {
				File file = new File(connectionPath);
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);				
				String line;				
				
				while ((line = bufferedReader.readLine()) != null) 
				{
					//Each line will contain the information about a user.	
					// Split the users input by first looking for 'follows' and then ',' in the input string
					// The user following the other users will be the first user in the list.		
					
					String[] userList = line.split("\\sfollows\\s|,+"); 
					Set<UserDao> follows = new HashSet<UserDao>();					
					User user = null;
					
					// first register all the users
					for(int i =0 ; i < userList.length;i++)
					{
						String nextUserName = userList[i].trim();
						if(i == 0 ) // The first entry will be our user name
						{
							user = findUser(nextUserName);
							if(user == null)
							{
								user = new User(nextUserName);							
								this.addUser(user);							
							}							
							continue;
						}
						else
						{
							User follow = findUser(nextUserName);
							if(follow == null)
							{							
								follow = new User(nextUserName);
								this.addUser(follow);
							}
							// Using a set so there wont be duplicates 					
							user.startFollowing(follow);
						}						
					}	
				}
							
			} catch (IOException e) 
			{
				throw new RuntimeException(e.getMessage());
			}
			finally
			{
				if(fileReader != null)		// Guarantee that file reader gets closed
					fileReader.close();	
			}
		}
		catch(Exception err)
		{
			throw new RuntimeException(err.getMessage());
		}		
	}

	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		
		for(User user : messageUsers)
		{
			if(user.getUserName().equals(name))
				return user;
		}
		
		return null;
	}

	@Override
	public void addUser(User user) {
		messageUsers.add(user);		
	}

	@Override
	public List<User> getSortedUserList() 
	{
		Collections.sort(messageUsers); // Because User class implements Comparable we can sort the list
		return this.messageUsers;
	}

	@Override
	public List<User> getUsers() {
		return this.messageUsers;
	}
 }
