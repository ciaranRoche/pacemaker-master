package controllers;

import java.io.File;
import java.io.FileWriter;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;

//import utils.FileLogger;
import models.User;

public class Main
{
  public static void main(String[] args) throws IOException
  {
	  PacemakerAPI pacemakerAPI = new PacemakerAPI();
	  
	  pacemakerAPI.createUser("Bart", "Simpson", "bart@simpson.com", "secret");
	  pacemakerAPI.createUser("Homer", "Simpson", "homer@simpson.com", "secret");
	  pacemakerAPI.createUser("Lisa", "Simpson","lisa@simpson.com", "secret");
	  pacemakerAPI.createActivity((long) 02, "Drinking", "Geoffs", 6.5);
	  pacemakerAPI.addLocation((long) 65, 33.2f, 65.2f);
	  
	  Collection<User>users = pacemakerAPI.getUsers();
	  System.out.println(users);
	  
	  User homer = pacemakerAPI.getUserByEmail("homer@simpson.com");
	  System.out.println(homer);
	  
	  pacemakerAPI.deleteUser(homer.id);
	  users = pacemakerAPI.getUsers();
	  System.out.println(users);
	  
	  XStream xstream = new XStream(new DomDriver());
	  ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("datastore.xml"));
	  out.writeObject(users);
	  out.close();
	  
	  
  }

}