package drawit;

import java.util.Arrays;

/**
 * 
 * @author Ahmed Shemy && Matthew Watson
 *
 */

public class PointArrays extends Object{

	/**
	 * @inspects | points
	 * @pre Argument {@code points} is not {@code null}.
     *    | points != null
	 * @post The result is {@code null} if the given array of points defines a proper polygon; 
	 * otherwise, the result is {@code NotNull} a string describing why it does not.
	 */

	public static String checkDefinesProperPolygon​(IntPoint[] points) {
		if (points.length < 2)
			return "Not proper- Exactly two points";
		for (int j=0; j<points.length; j++)
			  for (int k=j+1; k <points.length; k++)
			    if (k!=j && points[j].equals(points[k]))
			      return "Not proper- Identical vertices";
		for(int j=0; j<points.length; j++) 
			for(int k=0; k<=points.length-2; k++) 
				if(points[j].isOnLineSegment​(points[k], points[k+1]))
					return "Not proper- Vertex on edge";
		for(int j=0; j<points.length; j++)
			if(points[j].isOnLineSegment​(points[points.length - 1], points[0]))
				return "Not proper- Vertex on edge";
		for (int j=0; j<points.length-1; j++)
			for (int k=0; k<points.length-1; k++)
				if(IntPoint.lineSegmentsIntersect​(points[j], points[j+1], points[k], points[k+1]))
					return "Not proper- Intersecting edges";
		for (int k=0; k<points.length-1; k++)
			if(IntPoint.lineSegmentsIntersect​(points[points.length - 1], points[0], points[k], points[k+1]))
				return "Not proper- Intersecting edges";
		
		return null;
		}

	/**
	 * @creates | result
	 * @inspects | points
	 * @pre Argument {@code points} is not {@code null}.
     *    	|points != null
	 * @post The result is a new array with the same contents as the given array.
	 * 		| Arrays.equals(result, points)
	 * @post The result is an array with the same length as the given array.
	 * 		|result.length == points.length
	 */

	public static IntPoint[] copy​(IntPoint[] points) {
		IntPoint[] result = new IntPoint[points.length]; 
			for (int i=0; i < points.length; i++) {
			     result[i] = points[i]; }
			 
			return result; 
			     
		}

	/**
	 * @creates | result
	 * @inspects | points
	 * @pre Argument {@code points} is not {@code null}.
     *    	|points != null
     * @pre Argument {@code point} is not {@code null}.
     *    	|point != null
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index < points.length
	 * @post The result is a new array whose length is the length of the provided array plus 1.
	 * 		|result.length == points.length + 1
	 * @post The result is a new array with provided array existing elements before the 
	 * index remain unchanged and in the same indexes.
	 * 		| Arrays.equals(result, 0, index, points, 0, index)
	 * @post The result is a new array with provided array existing elements at the index
	 *  and after remain unchanged but their indexes are all shifted +1.
	 * 		| Arrays.equals(result, index +1, result.length, points, index, points.length)
	 * @post The result is an array with {@code point} at the {@code index}
	 * 		| result[index] == point
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
	 * @creates | result
	 * @inspects | points
	 * @pre Argument {@code points} is not {@code null}.
     *    	|points != null
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post The result is a new array whose length is the length of the provided array minus 1.
	 * 	|result.length == points.length - 1
	 * @post The result is a new array with provided array existing elements before the 
	 * index remain unchanged and in the same indexes.
	 * 		| Arrays.equals(result, 0, index, points, 0, index)
	 * @post The result is a new array with provided array existing elements after the index
	 * remain unchanged but their indexes are all shifted -1.
	 * 		| Arrays.equals(result, index , result.length, points, index + 1, points.length)
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
	 * @creates | result
	 * @inspects | points
	 * @pre Argument {@code points} is not {@code null}.
     *    	|points != null
     * @pre Argument {@code value} is not {@code null}.
     *    	|value != null
	 * @post the number of array elements remains unchanged
	 * 		|result.length == points.length
	 * @post The result is a new array with provided array existing elements before the 
	 * index remain unchanged and in the same indexes.
	 * 		| Arrays.equals(result, 0, index, points, 0, index)
	 * @post The result is a new array with provided array existing elements after the index
	 * remain unchanged and in the same indexes.
	 * | Arrays.equals(result, index + 1, result.length, points, index + 1, points.length)
	 * @param points, index, value
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
