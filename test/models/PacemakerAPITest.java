package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.PacemakerAPI;
import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;

public class PacemakerAPITest {
	private PacemakerAPI pacemaker;
	
	@Before
	public void setup(){
		pacemaker = new PacemakerAPI();
		for (User user : users){
			pacemaker.createUser(user.firstName, user.lastName, user.email, user.password);
		}
	}
	
	@After
	public void tearDown(){
		pacemaker = null;
	}
	
	/**@Test
	public void testUser(){
		assertEquals(0, pacemaker.getUsers().size());
	}**/
	
	@Test 
	public void testUser(){
		assertEquals (users.length, pacemaker.getUsers().size());
	    pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
	    assertEquals (users.length+1, pacemaker.getUsers().size());
	    assertEquals (users[0], pacemaker.getUserByEmail(users[0].email));
	}
	
/**	@Test
	public void testEquals(){
		User homer = new User("homer", "simpson", "homer@simpson.com", "secret");
		 User homer2 = new User ("homer", "simpson", "homer@simpson.com",  "secret"); 
		 User bart   = new User ("bart", "simpson", "bartr@simpson.com",  "secret"); 
		 
		 assertEquals(homer, homer);
		 assertEquals(homer, homer2);
		 assertNotEquals(homer, bart);
	}**/
	
	@Test
	public void testUsers(){
		assertEquals(users.length, pacemaker.getUsers().size());
		for (User user : users){
			User eachUser = pacemaker.getUserByEmail(user.email);
			assertEquals(user, eachUser);
			assertNotSame(user, eachUser);
		}
	}
	
	@Test
	public void testDeleteUsers(){
		assertEquals(users.length, pacemaker.getUsers().size());
		User marge = pacemaker.getUserByEmail("marge@simpson.com");
		pacemaker.deleteUser(marge.id);
		assertEquals(users.length-1, pacemaker.getUsers().size());
	}
	
}
