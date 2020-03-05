package drawit;

public final class IntPoint extends Object {
	
	private int x;
	private int y;
	
	/**
	 *Initializes this point with the given coordinates.
	 */
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @post result.getX() == this.getX() 
	 * result.getY() == this.getY()
	 * @return a DoublePoint object that represents the same 2D point represented by this IntPoint object.
	 */
	public DoublePoint asDoublePoint(){
		return null;}
	
	/**
	 * @return  true if this point has the same coordinates as the given point;
	 * returns false otherwise.
	 */
	public boolean equals​(IntPoint other){
		return false;
	}	
	
	/**
	 * @post result.getX() == this.getX() + other.getX() 
	 * result.getY() == this.getY() + other.getY()
	 * @return an IntPoint object representing the point obtained by displacing this point by the given vector.
	 */
	public IntPoint plus​(IntVector vector) {
		return null;
		}
	
	/**
	 * @post result.getX() == this.getX() - other.getX() 
	 * result.getY() == this.getY() - other.getY()
	 * @return an IntVector object representing the displacement from other to this.
	 */
	public IntVector minus​(IntPoint other) {
		return null;}
	
	/**
	 * 	@return true if this point is on open line segment bc. An open line segment does not include its endpoints.
	 */
	public boolean isOnLineSegment​(IntPoint b,IntPoint c) {
		return false;}
	
	
	/**
	 * @pre The line segments have at most one point in common.
	 * @return true if the open line segment ab intersects the open line segment cd.
	 */
	public static boolean lineSegmentsIntersect​(IntPoint a,IntPoint b,IntPoint c,IntPoint d) {
		return false;
	}
	
	
	/**
	 * @return this point's X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Returns this point's Y coordinate.
	 */
	public int getY() {
		return y;
	}
}
