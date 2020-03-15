package drawit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Ahmed Shemy && Matthew Watson
 *
 */

//deal with illegal arguments contractually.

public class PointArrays extends Object{

	/**
	 * @post null if the given array of points defines a proper polygon; 
	 * otherwise, posts a string describing why it does not.
	 */
	// https://stackoverflow.com/questions/3951547/java-array-finding-duplicates
	public static String checkDefinesProperPolygon​(IntPoint[] points) {
		if (points.length < 2)
			return "Not proper- Exactly two points";
		for (int j=0; j<points.length; j++)
			  for (int k=j+1; k <points.length; k++)
			    if (k!=j && points[j].equals(points[k]))
			      return "Not proper- Identical vertices";
		for(int j=0; j<points.length-2; j++)
			if(points[j+2].isOnLineSegment​(points[j], points[j+1]))
				return "Not proper- Vertex on edge";
		for (int j=0; j<points.length-3; j++)
			if(IntPoint.lineSegmentsIntersect​(points[j], points[j+1], points[j+2], points[j+3]))
				return "Not proper- Intersecting edges";
		return null;
		}

	/**
	 * 
	 * @post a new array with the same contents as the given array.
	 * @post the array returned must be the same length as the given  array
	 * 		|result.length == points.length 
     *
	 */

	public static IntPoint[] copy​(IntPoint[] points) {
		IntPoint[] result = new IntPoint[points.length]; 
			for (int i=0; i < points.length; i++) {
			     result[i] = points[i]; }
			 
			return result; 
			     
		}

	/**
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post a new array whose elements are the elements of the given array with 
	 * the given point inserted at the given index.
	 * 		|result.length == points.length + 1
	 * @post The old array existing elements before the index remain unchanged in the new array.
	 * @post The old array existing elements at the index and after are all shifted +1 
	 * in their indexing position in the new array.
	 * @param points, index, point
	 */
		
	public static IntPoint[] insert​(IntPoint[] points,int index, IntPoint point) {
		if(0 > index || index > points.length) {
			return points;
		}
		IntPoint[] result = new IntPoint[points.length + 1];
		for (int i = 0, j = 0; i < points.length; i++, j++) {
			if (i == index) {
				result[j] = point;
				j++;
			}
		result[j] = points[i];
		}
		return result;
	}
	
	
	/**
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post a new array whose elements are the elements of the given array with 
	 * the element at the given index removed.
	 * @post The array returned should have a length one smaller than the original array 
	 * 	|result.length == points.length - 1
	 * @param points, index
	 */
	
	public static IntPoint[] remove​(IntPoint[] points, int index) {
		if(0 > index || index > points.length) {
			return points;
		}
		IntPoint[] result  = new IntPoint[points.length - 1];
		for (int z = 0, k = 0; z < points.length; z++) { 
			
            if (z == index) {
                continue;
            } 
            result[k++] = points[z]; 
        } 
        return result; 
    } 
		
	
	/**
	 * @post the number of array elements remains unchanged
	 * 		|result.length == points.length
	 * @param points, index, value
	 * @returns a new array whose elements are the elements of the given array with 
	 * the element at the given index replaced by the given point.
	 *
	 */
	public static IntPoint[] update​(IntPoint[] points,int index,IntPoint value) {
		IntPoint[] result = new IntPoint[points.length]; 
		for (int i=0; i < points.length; i++) {
			if (i == index) {
				result[index] = value;
				continue;
			}
			result[i] = points[i];
		  }
		 
		return result;
	}
}
