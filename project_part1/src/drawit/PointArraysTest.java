package drawit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
			
	}

}
