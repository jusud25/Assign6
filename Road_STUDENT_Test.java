

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	private Road roadTest;
	
	@Before
	public void setUp() throws Exception {
		roadTest = new Road(new Town("Town 1"), new Town("Town 3"), 3, "Road 1");
	}

	@After
	public void tearDown() throws Exception {
		roadTest = null;
	}
	
	@Test
	public void compareToTest() {
		assertEquals(0, roadTest.compareTo(new Road(new Town("Town 1"), new Town("Town3"), 3, "Road 1")));
		assertEquals(0, roadTest.compareTo(roadTest));
		assertEquals(0, roadTest.compareTo(new Road(new Town("Town 2"), new Town("Town1"), 2, "Road 1")));
	}
	
	@Test
	public void equalsTest() {
		assertEquals(true, roadTest.equals(new Road(new Town("Town 1"), new Town("Town 3"), 3, "Road 1")));
		assertEquals(true, roadTest.equals(roadTest));
	}
	
	@Test
	public void containsTest() {
		assertEquals(false, roadTest.containsTown(new Town("Town 5")));
		assertEquals(false, roadTest.containsTown(new Town("Town 2")));
		assertEquals(true, roadTest.containsTown(new Town("Town 3")));
	}
}