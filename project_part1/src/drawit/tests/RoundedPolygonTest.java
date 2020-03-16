package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

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
		
		ver = PointArrays.update​(ver, 0, p1);
		ver = PointArrays.update​(ver, 1, p2);
		ver = PointArrays.update​(ver, 2, p3);
		ver = PointArrays.update​(ver, 3, p5);
		ver = PointArrays.update​(ver, 4, p4);
		
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

		
		assertEquals(true, RP1.contains​(p6));
		assertEquals(true, RP1.contains​(p7));
		assertEquals(true, RP1.contains​(p8));
		assertEquals(true, RP1.contains​(p9));
		assertEquals(false, RP1.contains​(p10));
		assertEquals(false, RP1.contains​(p11));
		assertEquals(false, RP1.contains​(p12));
		assertEquals(false, RP1.contains​(p13));
		assertEquals(true, RP1.contains​(p14));
		assertEquals(false, RP1.contains​(p15));
		assertEquals(false, RP1.contains​(p16));





		


	}

}
