package uk.liamp.cleaner.Helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoordinateTests {
	
	@Test
	void settersAndGettersTest() {
		Coordinate c = new Coordinate(0,1);
		int expectedX = 5;
		c.setX(expectedX);
		assertEquals(c.getX(), expectedX);
	}
	
	@Test
	void equalsOverride() {
		Coordinate a = new Coordinate(5,10);
		Coordinate b = new Coordinate(5,10);
		assertTrue(a.equals(b));
	}
	
	@Test
	void isInboundTest() {
		Coordinate a = new Coordinate(5,5);
		Coordinate b = new Coordinate(-1, 3);
		assertFalse(b.isInbound(a));
	}

}
//