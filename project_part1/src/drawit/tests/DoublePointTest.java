package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawit.DoublePoint;

class DoublePointTest {

	@Test
	void test() {
		DoublePoint myDoublePoint = new DoublePoint(-1,1);
		assertEquals(-1,myDoublePoint.getX());
		assertEquals(1,myDoublePoint.getY());

		DoublePoint myDoublePoint2 = new DoublePoint(2,2);
		assertEquals(-3,myDoublePoint.minus​(myDoublePoint2).getX());
		assertEquals(-1,myDoublePoint.minus​(myDoublePoint2).getY());
		
		
		DoublePoint myDoublePoint3 = new DoublePoint(-1.4,1.6);
		assertEquals(-1,myDoublePoint3.round().getX());
		assertEquals(2,myDoublePoint3.round().getY());

		assertEquals(-4,myDoublePoint.plus​(myDoublePoint.minus​(myDoublePoint2)).getX());
		assertEquals(0,myDoublePoint.plus​(myDoublePoint.minus​(myDoublePoint2)).getY());


	}

}
