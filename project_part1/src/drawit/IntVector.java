package drawit;

/**
 * Each instance of this class represents a vector with integer X and Y coordinates.
 *
 * @immutable
 * 
 * @author Ahmed Shemy && Matthew Watson
 */

public final class IntVector {
	
	private final int x;
	private final int y;

	/**
	 * @mutates | this
	 *Initializes this vector with the given coordinates.
	 *
	 * @post This object's x equal the given x
     *    | getX() == x
     * @post This object's y equal the given y
     *    | getY() == y
	 */
	
	public IntVector(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @creates | result
	 * @post The result is not {@code null}
     *    | result != null
	 * @post The result is a DoubleVector object with x and y coordinates as same as this object's x and y coordinates.
	 * 		 | result.getX() == this.getX() && result.getY() == this.getY()
	 * @return a DoubleVector object that represents the same vector represented by this IntVector object.
	 */
	public DoubleVector asDoubleVector() {
		DoubleVector result = new DoubleVector(Double.valueOf(this.getX()),Double.valueOf(this.getY()));
		return result;}
	
	/**
	 * @inspects other
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post The result is the x coordinate of this vector times the y coordinate of the other vector minus 
	 * 			the y coordinate of this vector times the x coordinate of the other vector.
     *    			|result == ((long)getX() * other.getY() - (long)getY() * other.getX())
	 */
	public long crossProduct​(IntVector other) {
		long result = (long)getX() * (long)other.getY() - (long)getY() * (long)other.getX();
		return result;}
	
	/**
	 * @inspects | other
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post The result is {@code true} if this vector has crossProduct equal zero with the other vector; false otherwise.
	 * 		|result == (this.crossProduct​(other) == 0)
	 * @return whether this vector is collinear with the given vector.
	 */
	public boolean isCollinearWith​(IntVector other) {
		if (this.crossProduct​(other) == 0)
			return true;
		return false;
	}

	/**
	 * @inspects | other
	 * @pre Argument {@code other} is not {@code null}.
     *    	|other != null
	 * @post The result is the x coordinate of this vector times the x coordinate of the other vector plus 
	 * 			the y coordinate of this vector times the y coordinate of the other vector.
     *    			|result == (long)getX() * other.getX() + (long)getY() * other.getY() 
	 * @return the dot product of this vector and the given vector.
	 */
	public long dotProduct​(IntVector other) {
		long result = (long)getX() * (long)other.getX() + (long)getY() * (long)other.getY();
		return result;
		}
	
	/**
	 * @return Returns this vector's X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Returns this vector's Y coordinate.
	 */
	public int getY() {
		return y;
	}
}
