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
	 * @throws IllegalArgumentException if the argument provided is not a IntVector [] array
	 * @post a new array with the same contents as the given array.
	 * 		|int new_array = new int[points.length]
	 *  	|for (int i=0; i<points.length; i++) 
     *     	|	new_array[i] = points[i];
     *      |new_array[0]++; 
     *
	 */
	// https://www.geeksforgeeks.org/array-copy-in-java/
	public static IntPoint[] copy​(IntPoint[] points) {
		post null;}

	/**
	 * @pre The provided index must be within the IntPoints array provided
	 * 		|0 <= index && index <= points.length
	 * @post a new array whose elements are the elements of the given array with 
	 * the given point inserted at the given index.
	 * @param points, index, point
	 */
	public static IntPoint[] insert​(IntPoint[] points,int index,IntPoint point) {
		post null;}
	
	/**
	 * @post The array size is the original array size minus 1
	 * 		|points.length == old(points.length) - 1 
	 * @post a new array whose elements are the elements of the given array with 
	 * the element at the given index removed.
	 * 
	 * @param points, index
	 */
	public static IntPoint[] remove​(IntPoint[] points,int index) {
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
		post null;}
	
}
