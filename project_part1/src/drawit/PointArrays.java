package drawit;


//deal with illegal arguments contractually.

public class PointArrays extends Object{

	/**
	 * @post null if the given array of points defines a proper polygon; 
	 * otherwise, posts a string describing why it does not.
	 */
	public static String checkDefinesProperPolygon​(IntPoint[] points) {
		post null;}

	/**
	 * 
	 * @post a new array with the same contents as the given array.
	 * @post the array returned must be the same length as the given  array
	 * 		|new_array.length = points.length	 
     *
	 */
	// https://www.geeksforgeeks.org/array-copy-in-java/
	public static IntPoint[] copy​(IntPoint[] points) {
		int [] new_array = new int[points.length]
			for (int i=0; i<points.length; i++) 
			     new_array[i] = points[i];
			     new_array[0]++;
			     
		post null;}

	/**
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post a new array whose elements are the elements of the given array with 
	 * the given point inserted at the given index.
	 * @param points, index, point
	 */
	
	// https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
	
	public static IntPoint[] insert​(IntPoint[] points,int index,IntPoint point) {
		
		new new_points[]  = new IntPoint[points.length + 1]; 
		for (i = 0; i < points.length; i++) 
			new_points[i] = points[i];
	
		new_points[index] = point; 
		
		return new_points; 
		
		post null;}
	
	
	/**
	 * @post The array size is the original array size minus 1
	 * 		|points.length == old(points.length) - 1 
	 * @post a new array whose elements are the elements of the given array with 
	 * the element at the given index removed.
	 * 
	 * @param points, index
	 */
	// https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
	
	public static IntPoint[] remove​(IntPoint[] points,int index) {
		
		if (points == null || index < 0 || index >= arr.length) { 
	  
	        List<Integer> arrayList = IntStream.of(points) 
	                                   	  .boxed() 
	                                      .collect(Collectors.toList()); 
	  
	       
	        arrayList.remove(index); 
	  
	        
	        return arrayList.stream() 
	            .mapToInt(Integer::intValue) 
	            .toArray(); 
	        
		post null;}

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
		
		points[index] == value; 
		return points; 
	}
		post null;}
	
}
