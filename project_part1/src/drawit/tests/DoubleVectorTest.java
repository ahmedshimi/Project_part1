package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawit.DoubleVector;

class DoubleVectorTest {

	@Test
	void test() {
		
		DoubleVector myDoubleVector1 = new DoubleVector(1,0);
		DoubleVector myDoubleVector2 = new DoubleVector(0,4);
		DoubleVector myDoubleVector3 = new DoubleVector(0,-4);
		DoubleVector myDoubleVector4 = new DoubleVector(2,-4);
		DoubleVector myDoubleVector5 = new DoubleVector(3,4);

		
		assertEquals(1,myDoubleVector1.getX());
		assertEquals(0,myDoubleVector1.getY());
		
		assertEquals(0,myDoubleVector1.asAngle());
		assertEquals(Math.PI / 2,myDoubleVector2.asAngle());
		assertEquals(- Math.PI / 2,myDoubleVector3.asAngle());
		
		assertEquals(-4, myDoubleVector1.crossProduct(myDoubleVector4));
		assertEquals(2, myDoubleVector1.dotProduct(myDoubleVector4));
		
		assertEquals(4, myDoubleVector4.scale(2).getX());
		assertEquals(-8, myDoubleVector4.scale(2).getY());

		assertEquals(5, myDoubleVector5.getSize());

}

}
