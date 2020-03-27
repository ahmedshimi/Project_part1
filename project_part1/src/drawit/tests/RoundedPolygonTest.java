package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import drawit.IntPoint;
import drawit.PointArrays;
import drawit.RoundedPolygon;

class RoundedPolygonTest {

	@Test
	void test() {

		RoundedPolygon RP1 = new RoundedPolygon();
		
		IntPoint p1 = new IntPoint(0,0);
		IntPoint p2 = new IntPoint(3,0);
		IntPoint p3 = new IntPoint(3,3);
		IntPoint p4 = new IntPoint(0,3);
		IntPoint p5 = new IntPoint(1,4);

		IntPoint[] ver = new IntPoint[5];
		
		ver = PointArrays.update(ver, 0, p1);
		ver = PointArrays.update(ver, 1, p2);
		ver = PointArrays.update(ver, 2, p3);
		ver = PointArrays.update(ver, 3, p5);
		ver = PointArrays.update(ver, 4, p4);
		
		RP1.setVertices(ver);
		
		IntPoint p6 = new IntPoint(0,0);
		IntPoint p7 = new IntPoint(1,3);
		IntPoint p8 = new IntPoint(0,1);
		IntPoint p9 = new IntPoint(0,2);
		IntPoint p10 = new IntPoint(-1,1);
		IntPoint p11 = new IntPoint(4,1);
		IntPoint p12 = new IntPoint(-1,0);
		IntPoint p13 = new IntPoint(-69,3);
		IntPoint p14 = new IntPoint(1,4);

		IntPoint p15 = new IntPoint(-1,4);

		IntPoint p16 = new IntPoint(-169,4);

		
		assertEquals(true, RP1.contains(p6));
		assertEquals(true, RP1.contains(p7));
		assertEquals(true, RP1.contains(p8));
		assertEquals(true, RP1.contains(p9));
		assertEquals(false, RP1.contains(p10));
		assertEquals(false, RP1.contains(p11));
		assertEquals(false, RP1.contains(p12));
		assertEquals(false, RP1.contains(p13));
		assertEquals(true, RP1.contains(p14));
		assertEquals(false, RP1.contains(p15));
		assertEquals(false, RP1.contains(p16));

		
		RoundedPolygon RP2 = new RoundedPolygon();
		
		IntPoint p101 = new IntPoint(100,100);
		IntPoint p102 = new IntPoint(200,100);
		IntPoint p103 = new IntPoint(200,200);
		IntPoint p104 = new IntPoint(100,200);
		
		IntPoint p105 = new IntPoint(400, 400); 
		
		ver2 = PointArrays.update(ver2, 0, p101);
		ver2 = PointArrays.update(ver2, 1, p102);
		ver2 = PointArrays.update(ver2, 2, p103);
		ver2 = PointArrays.update(ver2, 3, p104);

		IntPoint[] ver2 = new IntPoint[4];
		
		ver2 = PointArrays.update(ver2, 0, p101);
		ver2 = PointArrays.update(ver2, 1, p102);
		ver2 = PointArrays.update(ver2, 2, p103);
		ver2 = PointArrays.update(ver2, 3, p104);
	
		RP2.setVertices(ver2);
		
		// make a new array that is the copy of ver2 to test copy function
		IntPoint[] ver3 = PointArrays.copy(ver2); 
		RoundedPolygon RP3 = new RoundedPolygon(); 
		
		// make new rounded polygon with the same vertices as rp2
		RP3.setVertices(ver3); 
	
		// test that their vertices are the same- can we use deep to string to test this?
		assertEquals(Arrays.deepToString(RP3.getVertices()), Arrays.deepToString(RP2.getVertices())); 
		
		RP2.setRadius(10);
		
		// test the getter method for radius, did not include in the first submission
		assertEquals(10, RP2.getRadius()); 
		
		// test to insert and remove an index within the array length, will not affect the final string output
		RP2.insert(3, p105); 
		RP2.remove(3); 
		
		System.out.print(RP2.getDrawingCommands());
		
		String expectedString = "line 110.0 100.0 190.0 100.0\r\n" + 
				"arc 190.0 110.0 10 -1.5707963267948966 1.5707963267948966\r\n" + 
				"line 200.0 110.0 200.0 190.0\r\n" + 
				"arc 190.0 190.0 10 0.0 1.5707963267948966\r\n" + 
				"line 190.0 200.0 110.0 200.0\r\n" + 
				"arc 110.0 190.0 10 1.5707963267948966 1.5707963267948966\r\n" + 
				"line 100.0 190.0 100.0 110.0\r\n" + 
				"arc 110.0 110.0 10 3.141592653589793 1.5707963267948966\r\n";
		assertEquals(expectedString, RP2.getDrawingCommands());	
		
		
	}

}
