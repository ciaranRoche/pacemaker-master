package controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Activity;
import models.Location;
import models.User;
import utils.Serializer;

public class PacemakerAPI {

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> emailIndex = new HashMap<>();
	private Map<Long, Activity>activitiesIndex = new HashMap<>();

	private Serializer serializer;
	
	public PacemakerAPI(){
		
	}
	
	public PacemakerAPI(Serializer serializer){
		this.serializer = serializer;
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		serializer.read();
		activitiesIndex = (Map<Long, Activity>) serializer.pop();
		emailIndex = (Map<String, User>) serializer.pop();
		userIndex = (Map<Long, User>) serializer.pop();
	}
	
	void store() throws Exception{
		serializer.push(userIndex);
		serializer.push(emailIndex);
		serializer.push(activitiesIndex);
		serializer.write();
	}

	public Collection<User>getUsers(){
		return userIndex.values();
	}
	
	public void deleteUsers(){
		userIndex.clear();
		emailIndex.clear();
	}
	
	public User createUser(String firstName, String lastName, String email, String password){
		User user = new User(firstName, lastName, email, password);
		userIndex.put(user.id, user);
		emailIndex.put(email, user);
		return user;
	}
	
	public User getUserByEmail(String email){
		return emailIndex.get(email);
	}
	
	public User getUSER(Long id){
		return userIndex.get(id);
	}
	
	public void deleteUser(Long id){
		User user = userIndex.remove(id);
		emailIndex.remove(user.email);
	}
	
	public Activity createActivity(Long id, String type, String location, double distance){
		Activity activity = null;
		Optional<User>user = Optional.fromNullable(userIndex.get(id));
		if(user.isPresent()){
			activity = new Activity (type, location, distance);
			user.get().activities.put(activity.id, activity);
			activitiesIndex.put(activity.id, activity);
		}return activity;
	}
	
	public Activity getActivity(Long id){
		return activitiesIndex.get(id);
	}
	
	public void addLocation(Long id, float latitude, float longitude){
		Optional<Activity>activity = Optional.fromNullable(activitiesIndex.get(id));
		if(activity.isPresent()){
			activity.get().route.add(new Location(latitude,longitude));
		}
	}
}
