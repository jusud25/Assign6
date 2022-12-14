
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class Town_STUDENT_Test {
	private Town towns;
	@Before
	public void setUp() throws Exception {
		towns = new Town("Town 2");
		 
	}

	@After
	public void tearDown() throws Exception {
		towns = null;
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, towns.compareTo(new Town("Town 2")));
		assertEquals(0, towns.compareTo(towns));
		assertEquals(-1, towns.compareTo(new Town("Town 3")));
		assertEquals(2, towns.compareTo(new Town("Town 0")));
	}
	
	@Test
	public void testEquals() {
		assertEquals(true, towns.equals(new Town("Town 2")));
		assertEquals(false, towns.equals(new Town("1")));
		assertEquals(true, towns.equals(towns));
	}
}
