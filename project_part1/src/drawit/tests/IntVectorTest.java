package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawit.IntVector;

class IntVectorTest {

	@Test
	void test() {
		IntVector myIntVector1 = new IntVector(-1,-2);
		IntVector myIntVector2 = new IntVector(3,1);
		IntVector myIntVector3 = new IntVector(1,2);

		assertEquals(-1, myIntVector1.getX());
		assertEquals(-2,myIntVector1.getY());
		assertEquals(5, myIntVector1.crossProduct(myIntVector2));
		assertEquals(true, myIntVector3.isCollinearWith(myIntVector1));
		assertEquals(myIntVector3.dotProduct(myIntVector3), myIntVector1.dotProduct(myIntVector1));
	
	
	}

}
