package models;

import static org.junit.Assert.*;

import org.junit.Test;



public class ActivityTest {
	private Activity[] activities ={
			new Activity("walk", "fridge", 0.001),
	};
	
	Activity test = new Activity("walk", "fridge", 0.001);
	
	@Test
	public void testCreate(){
		assertEquals("walk", test.type);
		assertEquals("fridge", test.location);
		assertEquals(0.001, test.distance, 0.0001);
	}
	
	@Test
	public void testToString(){
		assertEquals("Activity{" + test.id + ", walk, fridge, 0.001, []}", test.toString());
	}


}
