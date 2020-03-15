package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawit.IntPoint;
import drawit.IntVector;

class IntPointTest {

	@Test
	void test() {
		IntPoint myIntPoint = new IntPoint(-1,1);
		assertEquals(-1,myIntPoint.getX());
		assertEquals(1,myIntPoint.getY());
		
		assertEquals(-1, myIntPoint.asDoublePoint().getX());
		assertEquals(1, myIntPoint.asDoublePoint().getY());
		
		IntPoint myIntPoint2 = new IntPoint(-1,1);
		IntPoint myIntPoint3 = new IntPoint(1,1);
		
		assertEquals(true, myIntPoint.equals​(myIntPoint2));
		assertEquals(false, myIntPoint.equals​(myIntPoint3));

		IntVector myIntVector = new IntVector(5,5);
		assertEquals(4,myIntPoint.plus​(myIntVector).getX());
		assertEquals(6,myIntPoint.plus​(myIntVector).getY());
		
		assertEquals(0,myIntPoint.minus​(myIntPoint2).getX());
		assertEquals(0,myIntPoint.minus​(myIntPoint2).getY());

		IntPoint myIntPoint4 = new IntPoint(0,1);
		IntPoint myIntPoint5 = new IntPoint(0,3);
		IntPoint myIntPoint6 = new IntPoint(0,2);
		IntPoint myIntPoint7 = new IntPoint(0,22);
		IntPoint myIntPoint8 = new IntPoint(2,2);
		
		assertEquals(true,myIntPoint6.isOnLineSegment​(myIntPoint4, myIntPoint5));
		assertEquals(false,myIntPoint7.isOnLineSegment​(myIntPoint4, myIntPoint5));
		assertEquals(false,myIntPoint8.isOnLineSegment​(myIntPoint4, myIntPoint5));

		IntPoint myIntPoint9 = new IntPoint(0,-1);
		IntPoint myIntPoint10 = new IntPoint(0,4);
		IntPoint myIntPoint11 = new IntPoint(2,2);
		IntPoint myIntPoint12 = new IntPoint(1,1);
		IntPoint myIntPoint13 = new IntPoint(1,0);
		IntPoint myIntPoint14 = new IntPoint(-1,0);
		IntPoint myIntPoint15 = new IntPoint(0,0);
		IntPoint myIntPoint16 = new IntPoint(5,0);
		IntPoint myIntPoint17 = new IntPoint(0,-5);

		
		assertEquals(true,IntPoint.lineSegmentsIntersect​(myIntPoint9, myIntPoint10, myIntPoint13, myIntPoint14));
		assertEquals(false,IntPoint.lineSegmentsIntersect​(myIntPoint9, myIntPoint10, myIntPoint11, myIntPoint12));
		assertEquals(false,IntPoint.lineSegmentsIntersect​(myIntPoint14, myIntPoint16, myIntPoint15, myIntPoint17));

	}

}
