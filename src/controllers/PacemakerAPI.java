package controllers;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import models.User;

public class PacemakerAPI {

	//private List<User> users= new ArrayList<User>();
	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> emailIndex = new HashMap<>();
	/**public List<User> getUsers(){
		return users;
	}**/
	public Collection<User>getUsers(){
		return userIndex.values();
	}
	
	public void deleteUsers(){
		userIndex.clear();
		emailIndex.clear();
	}
	
	/**public User createUser(String firstName, String lastName, String email, String password){
		User user= new User(firstName, lastName, email, password);
		users.add(user);
		return user;
	}**/
	public User createUser(String firstName, String lastName, String email, String password){
		User user = new User(firstName, lastName, email, password);
		userIndex.put(user.id, user);
		emailIndex.put(email, user);
		return user;
	}
	
	/**public User getUser(String email){
		for (User user:users){
			if(email.equals(user.email))
				return user;
		}**/
	public User getUserByEmail(String email){
		return emailIndex.get(email);
	}
	
	public User getUser(Long id){
		return userIndex.get(id);
	}
	
	/**public void deleteUser(String email){
		User foundUser = null;
		for(User user:users){
			if(email.equals(user.email))
				foundUser=user;
		}if(foundUser!=null){
			users.remove(foundUser);
		}
	}**/
	public void deleteUser(Long id){
		User user = userIndex.remove(id);
		emailIndex.remove(user.email);
	}
	
}
