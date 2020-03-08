package drawit;


//deal with illegal arguments contractually.

public class PointArrays extends Object{

	/**
	 * @return null if the given array of points defines a proper polygon; 
	 * otherwise, returns a string describing why it does not.
	 */
	public static String checkDefinesProperPolygon​(IntPoint[] points) {
		return null;}

	/**
	 * @return a new array with the same contents as the given array.
	 */
	public static IntPoint[] copy​(IntPoint[] points) {
		return null;}

	/**
	 * @pre 0 <= index && index <= points.length
	 * @return a new array whose elements are the elements of the given array with 
	 * the given point inserted at the given index.
	 */
	public static IntPoint[] insert​(IntPoint[] points,int index,IntPoint point) {
		return null;}
	
	/**
	 * @return a new array whose elements are the elements of the given array with 
	 * the element at the given index removed.
	 */
	public static IntPoint[] remove​(IntPoint[] points,int index) {
		return null;}

	/**
	 * @return a new array whose elements are the elements of the given array with 
	 * the element at the given index replaced by the given point.
	 */
	public static IntPoint[] update​(IntPoint[] points,int index,IntPoint value) {
		return null;}
	
}
