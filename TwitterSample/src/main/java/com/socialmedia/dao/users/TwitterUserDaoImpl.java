package com.socialmedia.dao.users;

/**
 * User Access Implementation class to read a simulated users feed from a file
 * Implements the UserDao interface
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.socialmedia.entities.User;

public class TwitterUserDaoImpl implements UserDao {

	protected List<User> messageUsers;
	protected String connectionPath;
	final String fileType;

	public TwitterUserDaoImpl(String connectionPath, String type) {
		this.setConnectionPath(connectionPath);
		this.fileType = type;
		messageUsers = new LinkedList<User>();
		registerUsers();
	}

	public String getConnectionPath() {
		return connectionPath;
	}

	public void setConnectionPath(String connectionPath) {
		this.connectionPath = connectionPath;
	}

	@Override
	public void registerUsers() {
		FileReader fileReader = null;
		
		try {
			if (messageUsers == null)
				messageUsers = new LinkedList<User>(); // only need to initialize the
				
				if (fileType.equals("file"))
					fileReader = new FileReader(connectionPath);
				else {
					ClassLoader classLoader = getClass().getClassLoader();
					File file = new File(classLoader.getResource(connectionPath).getFile());
					fileReader = new FileReader(file);
				}

				BufferedReader bufferedReader = new BufferedReader(fileReader); // Closed in finally block to ensure it closed
				String line;

				while ((line = bufferedReader.readLine()) != null) {
					// Each line will contain the information about a user.
					// Split the users input by first looking for 'follows' and
					// then ',' in the input string
					// The user following the other users will be the first user
					// in the list.

					String[] userList = line.split("\\sfollows\\s|,+");
					User user = null;

					// first register all the users
					for (int i = 0; i < userList.length; i++) {
						String nextUserName = userList[i].trim();
						if (i == 0) // The first entry will be our user name
						{
							user = findUser(nextUserName);
							if (user == null) {
								user = new User(nextUserName);
								this.addUser(user);
							}
							continue;
						} else {
							User follow = findUser(nextUserName);
							if (follow == null) {
								follow = new User(nextUserName);
								this.addUser(follow);
							}
							// Using a set so there wont be duplicates
							user.startFollowing(follow);
						}
					}
				}

			} catch (IOException e) {
				throw new RuntimeException("User file not found");
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
		
	}

	@Override
	public User findUser(String name) 
	{
		for (User user : messageUsers) {
			if (user.getUserName().equals(name))
				return user;
		}
		return null;
	}

	@Override
	public void addUser(User user)
	{
		messageUsers.add(user);
	}

	@Override
	public List<User> getSortedUserList() {
		Collections.sort(messageUsers); // Because User class implements
										// Comparable we can sort the list
		return this.messageUsers;
	}

	@Override
	public List<User> getUsers() {
		return this.messageUsers;
	}
}
