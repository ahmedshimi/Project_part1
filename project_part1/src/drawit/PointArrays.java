package drawit;

//deal with illegal arguments contractually.

public class PointArrays extends Object{

	/**
	 * @post null if the given array of points defines a proper polygon; 
	 * otherwise, posts a string describing why it does not.
	 */
	// https://stackoverflow.com/questions/3951547/java-array-finding-duplicates
	
	public static String checkDefinesProperPolygon​(IntPoint[] points) {
		if (points.length < 2) {
			return "Not proper- Exactly two points"; 
		}
		for (int j=0; j<points.length; j++)
			  for (int k=j+1; k <points.length; k++)
			    if (k!=j && points[k] == points[j])
			      return "Not proper- Identical vertices"; 
		return null;
		}

	/**
	 * 
	 * @post a new array with the same contents as the given array.
	 * @post the array returned must be the same length as the given  array
	 * 		|points.length == old(points.length) 
     *
	 */
	// https://www.geeksforgeeks.org/array-copy-in-java/
	public static IntPoint[] copy​(IntPoint[] points) {
		points = new IntPoint[points.length]; 
			for (int i=0; i < points.length; i++) {
			     points[i] = points[i]; }
			 
			return points; 
			     
		}

	/**
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post a new array whose elements are the elements of the given array with 
	 * the given point inserted at the given index.
	 * 		|points.length == old(points.length) 
	 * @param points, index, point
	 */
	
	// https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
	
	public static IntPoint[] insert​(IntPoint[] points,int index, IntPoint point) {
		points = new IntPoint[points.length + 1];
		if(!(0 <= index && index <= points.length))
			for (int i = 0; i < points.length; i++) {
				points[i] = points[i];
			}
			points[index] = point; 	
	
		return points; 
		
		 }
	
	
	/**
	 *  
	 * @post a new array whose elements are the elements of the given array with 
	 * the element at the given index removed.
	 * @post The array returned should have a length one smaller than the original array 
	 * 	|points.length == old(points.length)
	 * @param points, index
	 */
	// https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
	
	public static IntPoint[] remove​(IntPoint[] points, int index) {
		
		points  = new IntPoint[points.length - 1]; 
		
		for (int z = 0, k = 0; z < points.length; z++) { 
			 	
            if (z == index) { 
                ; 
            } 
  
            points[k++] = points[z]; 
        } 
  
        return points; 
    } 
		
	

	/**
	 *
	 * @post a new array whose elements are the elements of the given array with 
	 * the element at the given index replaced by the given point.
	 * 		|points[index] == value 
	 * @post the number of array elements remains unchanged
	 * 		|points.length == old(points.length)
	 * @param points, index, value
	 *
	 */
	public static IntPoint[] update​(IntPoint[] points,int index,IntPoint value) {
		
		points[index] = value; 
		return points; 
	}
	
	
		}
	

