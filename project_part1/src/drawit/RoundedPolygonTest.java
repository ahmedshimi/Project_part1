package drawit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		
		RP1.remove​(3);
		System.out.print(RP1.getVertices()[3].getX());
		
		RP1.insert​(3, p1);
		System.out.print(RP1.getVertices()[3].getX());

		RP1.update​(3, p2);
		System.out.print(RP1.getVertices()[3].getX());


	}

}
