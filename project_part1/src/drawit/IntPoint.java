package drawit;

/**
 * Each instance of this class represents a point with integer X and Y coordinates.
 *
 * @immutable
 * @author Ahmed Shemy & Matthew Watson
 */
// deal with illegal arguments contractually.
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
	 * @post|result.getX() == this.getX() 
	 * 		|result.getY() == this.getY()
	 * @return a DoublePoint object that represents the same 2D point represented by this IntPoint object.
	 */
	public DoublePoint asDoublePoint(){
		DoublePoint result = new DoublePoint(Double.valueOf(this.getX()),Double.valueOf(this.getY()));
		return result;}
	
	/**
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @return  true if this point has the same coordinates as the given point;
	 * 
	 * returns false otherwise.
	 */
	public boolean equals​(IntPoint other){
		if (this.getX() == other.getX() && this.getY() == other.getY())
			return true;
		
		return false;
	}	
	
	/**
	 * @pre Argument {@code vector} is not {@code null}.
     *    	|vector != null
	 * @post result.getX() == this.getX() + other.getX() 
	 * 		|result.getY() == this.getY() + other.getY()
	 * @return an IntPoint object representing the point obtained by displacing this point by the given vector.
	 */
	public IntPoint plus​(IntVector vector) {
		IntPoint result = new IntPoint(this.getX() + vector.getX(),this.getY() + vector.getY());
		return result;
		}
	
	/**
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post The resulting IntVector is the original vector minus the input other
	 * 		|result.getX() == this.getX() - other.getX() 
	 * 		|result.getY() == this.getY() - other.getY()
	 * @return an IntVector object representing the displacement from other to this.
	 */
	public IntVector minus​(IntPoint other) {
		IntVector result = new IntVector(this.getX() - other.getX(),this.getY() - other.getY());
		return result;}
	
	// Call this point a. First check if ba is collinear with bc. If not, return false. 
	// Then check that the dot product of ba and bc is between zero and the dot product of bc and bc.
	/**
	 * @pre Argument {@code b, c} is not {@code null}.
     *    | b != null && c != null
	 * @return true if this point is on open line segment bc. An open line segment does not include its endpoints.
	 */
	public boolean isOnLineSegment​(IntPoint b,IntPoint c) {
		if(b.minus​(this).isCollinearWith​(b.minus​(c)))
			if(b.minus​(this).dotProduct​(b.minus​(c))>= 0 && b.minus​(this).dotProduct​(b.minus​(c)) < b.minus​(c).dotProduct​(b.minus​(c)))
				return true;
		return false;}
	
	
	/**
	 * 
	 * @pre The line segments have at most one point in common.
	 * @return true if the open line segment ab intersects the open line segment cd.
	 */
	public static boolean lineSegmentsIntersect​(IntPoint a,IntPoint b,IntPoint c,IntPoint d) {
		if(Math.signum(a.minus​(c).crossProduct​(a.minus​(b))) * Math.signum(a.minus​(d).crossProduct​(a.minus​(b))) < 0)
			if(Math.signum(c.minus​(a).crossProduct​(c.minus​(d))) * Math.signum(c.minus​(b).crossProduct​(c.minus​(d))) < 0)
				return true;
		return false;
	}
	
	/**
	 * 
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
