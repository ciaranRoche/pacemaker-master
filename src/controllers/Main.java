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
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
  public static void main(String[] args) throws Exception{
	  File datastore = new File("datastore2.xml");
	  Serializer serializer = new XMLSerializer(datastore);
	  
	  PacemakerAPI pacemakerAPI = new PacemakerAPI(serializer);
	  if(datastore.isFile()){
		  pacemakerAPI.load();
	  }
	  
	  pacemakerAPI.createUser("Bart", "Simpson", "bart@simpson.com", "secret");
	  pacemakerAPI.createUser("Homer", "Simpson", "homer@simpson.com", "secret");
	  pacemakerAPI.createUser("Lisa", "Simpson","lisa@simpson.com", "secret");
	  
	  Collection<User>users = pacemakerAPI.getUsers();
	  System.out.println(users);
	  
	  User homer = pacemakerAPI.getUserByEmail("homer@simpson.com");
	  pacemakerAPI.createActivity(homer.id, "drinking pints", "geoffs" , 12.5);
	  
	  pacemakerAPI.store();
//	  pacemakerAPI.deleteUser(homer.id);
//	  users = pacemakerAPI.getUsers();
//	  System.out.println(users);
//	  
//	  XStream xstream = new XStream(new DomDriver());
//	  ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("datastore.xml"));
//	  out.writeObject(users);
//	  out.close();
	  
	  
  }

}