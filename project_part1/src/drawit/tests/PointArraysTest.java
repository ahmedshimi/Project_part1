package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import drawit.IntPoint;
import drawit.PointArrays;

class PointArraysTest {

	@Test
	void test() {
		IntPoint[] myArray = new IntPoint[100]; 
		IntPoint myPoint = new IntPoint(1,1); 
		IntPoint myPoint2 = new IntPoint(10,3);
		IntPoint myPoint3 = new IntPoint(9,8);
		
		
		myArray = PointArrays.insert​(myArray, 0, myPoint);
		myArray = PointArrays.insert​(myArray, 1, myPoint2);
		myArray = PointArrays.insert​(myArray, 2, myPoint3);
		myArray = PointArrays.insert​(myArray, 1, myPoint3);
		
		
		assertEquals(104, myArray.length); 
		
		myArray = PointArrays.remove​(myArray, 1);
				
		assertEquals(103, myArray.length);
				
		IntPoint[] newArray = PointArrays.copy​(myArray); 
			
		myArray = PointArrays.update​(myArray, 0, myPoint2);
		
		assertEquals(103, newArray.length);
		
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
		
		System.out.print(ver[0].getX());
		System.out.print(ver[0].getY());
		System.out.print(ver[1].getX());
		System.out.print(ver[1].getY());	
		System.out.print(ver[2].getX());
		System.out.print(ver[2].getY());	
		System.out.print(ver[3].getX());
		System.out.print(ver[3].getY());
		System.out.print(ver[4].getX());
		System.out.print(ver[4].getY());
		
		System.out.print(Arrays.deepToString(ver));
		assertEquals(null,PointArrays.checkDefinesProperPolygon​(ver));
			
	}

}
