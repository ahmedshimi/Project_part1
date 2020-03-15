package drawit;

/**
 * Each instance of this class represents a vector with integer X and Y coordinates.
 *
 * @immutable
 * @author Ahmed Shemy && Matthew Watson
 */

//deal with illegal arguments contractually.

public final class IntVector {

	private int x;
	private int y;

	/**
	 *Initializes this vector with the given coordinates.
	 */
	
	public IntVector(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return a DoubleVector object that represents the same vector represented by this IntVector object.
	 */
	public DoubleVector asDoubleVector() {
		DoubleVector result = new DoubleVector(Double.valueOf(this.getX()),Double.valueOf(this.getY()));
		return result;}
	
	/**
	 * @post result == (long)getX() * other.getY() - (long)getY() * other.getX()
	 * @return the cross product of this vector and the given vector.
	 */
	public long crossProduct​(IntVector other) {
		long result = (long)getX() * other.getY() - (long)getY() * other.getX();
		return result;}
	
	/**
	 * @post result == (this.crossProduct(other) == 0)
	 * @return whether this vector is collinear with the given vector.
	 */
	public boolean isCollinearWith​(IntVector other) {
		if (this.crossProduct​(other) == 0)
			return true;
		return false;
	}

	/**
	 * @post result == (long)getX() * other.getX() + (long)getY() * other.getY()
	 * @return the dot product of this vector and the given vector.
	 */
	public long dotProduct​(IntVector other) {
		long result = (long)getX() * other.getX() + (long)getY() * other.getY();
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
