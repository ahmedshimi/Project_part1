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

		// sample unit test with random numbers- will try his implementation hints after 
		// can't yet close the loop, so the print out is missing the last 2 indices and needs to go back to 0
		IntPoint [] commandsTest = new IntPoint[6]; 
		IntPoint s1 = new IntPoint(11, 3); 
		IntPoint s2 = new IntPoint(2, 12); 
		IntPoint s3 = new IntPoint(8, 5); 
		IntPoint s4 = new IntPoint(4, 4); 

		commandsTest = PointArrays.update​(commandsTest, 0, s1);
		commandsTest = PointArrays.update​(commandsTest, 1, s2);
		commandsTest = PointArrays.update​(commandsTest, 2, s3);
		commandsTest = PointArrays.update​(commandsTest, 3, s4);
		commandsTest = PointArrays.update​(commandsTest, 4, p10);
		commandsTest = PointArrays.update​(commandsTest, 5, p11);

		RoundedPolygon square = new RoundedPolygon();

		square.setVertices(commandsTest); 

		System.out.print(square.getDrawingCommands()); 


		


	}

}
