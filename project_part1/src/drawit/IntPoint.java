package drawit;

/**
 * Each instance of this class represents a point with integer X and Y coordinates.
 *
 * @immutable
 *    
 * @author Ahmed Shemy && Matthew Watson
 */

public final class IntPoint extends Object {
	
	private final int x;
	private final int y;
	
	/**
	 * @mutates | this
	 *Initializes this point with the given coordinates.
	 *
	 * @post This object's x equal the given x
     *    | getX() == x
     * @post This object's y equal the given y
     *    | getY() == y
	 */
	
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @creates | result
	 * @post The result is not {@code null}
     *    | result != null
	 * @post The result is a DoublePoint object with x and y coordinates as same as this object's x and y coordinates.
	 * 		 | result.getX() == this.getX() && result.getY() == this.getY()
	 * @return a DoublePoint object that represents the same 2D point represented by this IntPoint object.
	 */
	
	public DoublePoint asDoublePoint() {
		DoublePoint result = new DoublePoint(Double.valueOf(this.getX()),Double.valueOf(this.getY()));
		return result;}
	
	/**
	 * 
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post
     *      The result is {@code true} if this point has the same coordinates as the given point ; false otherwise.
     *    | result == (this.getX() == other.getX() && this.getY() == other.getY())
	 * 
	 */
	
	public boolean equals(IntPoint other) {
		if (this.getX() == other.getX() && this.getY() == other.getY())
			return true;
		return false;
	}
	
	/**
	 * 
	 * @pre Argument {@code vector} is not {@code null}.
     *    	|vector != null
	 * @post
	 * 		The result is an IntPoint object representing the point obtained by displacing this point by the given vector.
	 * 		| result.getX() == this.getX() + vector.getX() && result.getY() == this.getY() + vector.getY()
	 * @throws ArithmeticException - if the result x or y coordinates will flow over Integer.MAX_VALUE.
	 * | Long.valueOf(this.getX()) + Long.valueOf(vector.getX()) > Long.valueOf(Integer.MAX_VALUE)
	 * 	 Long.valueOf(this.getY()) + Long.valueOf(vector.getY()) > Long.valueOf(Integer.MAX_VALUE)
	 */
	
	public IntPoint plus(IntVector vector) throws ArithmeticException {
	
		if (Long.valueOf(this.getX()) + Long.valueOf(vector.getX()) > Long.valueOf(Integer.MAX_VALUE)|| Long.valueOf(this.getY()) + Long.valueOf(vector.getY()) > Long.valueOf(Integer.MAX_VALUE)) {
				throw new ArithmeticException("overflow exception, int too big");
		}		
		IntPoint result = new IntPoint(this.getX() + vector.getX(),this.getY() + vector.getY());
		return result;
		}
	
	/**
	 * 
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post The resulting IntVector is the original vector minus the input other
	 * 		|result.getX() == this.getX() - other.getX() && result.getY() == this.getY() - other.getY()
	 * @return an IntVector object representing the displacement from other to this.
	 */
	
	
	public IntVector minus (IntPoint other) {
		IntVector result = new IntVector(this.getX() - other.getX(),this.getY() - other.getY());
		return result;}
	
	/**
	 * 
	 * @pre Argument {@code b, c} is not {@code null}.
     *    | b != null && c != null
     * @post
     *      The result is {@code true} iff this point is on line segment bc excluding its end points.
     *      | result == (
     *      | b.minus(this).isCollinearWith(b.minus(c)) 
     *      | && b.minus(this).dotProduct(b.minus(c))> 0 
     *      | && b.minus(this).dotProduct(b.minus(c)) < b.minus(c).dotProduct(b.minus(c))
     *      |)
	 */
	
	
	public boolean isOnLineSegment(IntPoint b, IntPoint c) {
		if(b.minus(this).isCollinearWith(b.minus(c)))
			if(b.minus(this).dotProduct(b.minus(c))> 0 && b.minus(this).dotProduct(b.minus(c)) < b.minus(c).dotProduct(b.minus(c)))
				return true;
		return false;
		}
	
	
	/**
	 * 
	 * @pre The line segments have at most one point in common.
	 * Either the minimum or the maximum of one line can be within the minimum and maximum of the other line, but not both
	 * 		|(Math.min(a.getX(), b.getX()) <= Math.max(c.getX(), d.getX()) && Math.max(a.getX(), b.getX()) <= Math.max(c.getX(), d.getX())) || ((Math.min(c.getX(), d.getX()) <= Math.max(a.getX(), b.getX()) && Math.max(c.getX(), d.getX()) <= Math.max(a.getX(), b.getX()))
	 * 
	 * @return true if the open line segment ab intersects the open line segment cd.
	 */
	
	public static boolean lineSegmentsIntersect(IntPoint a, IntPoint b, IntPoint c, IntPoint d) {
		if(Math.signum(a.minus(c).crossProduct(a.minus(b))) * Math.signum(a.minus(d).crossProduct(a.minus(b))) < 0)
			if(Math.signum(c.minus(a).crossProduct(c.minus(d))) * Math.signum(c.minus(b).crossProduct(c.minus(d))) < 0)
				return true;
		return false;
	}
	
	
	/**
	 * @return this point's X coordinate.
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * @return this point's Y coordinate.
	 */
	
	public int getY() {
		return y;
	}
}
