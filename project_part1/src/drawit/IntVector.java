package drawit;

public final class IntVector {

	private int x;
	private int y;

	public IntVector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return a DoubleVector object that represents the same vector represented by this IntVector object.
	 */
	public DoubleVector asDoubleVector() {
		return null;}
	
	/**
	 * @post result == (long)getX() * other.getY() - (long)getY() * other.getX()
	 * @return the cross product of this vector and the given vector.
	 */
	public long crossProduct​(IntVector other) {
		return 0;}
	
	/**
	 * @post result == (this.crossProduct(other) == 0)
	 * @return whether this vector is collinear with the given vector.
	 */
	public boolean isCollinearWith​(IntVector other) {
		return false;
	}

	/**
	 * @post result == (long)getX() * other.getX() + (long)getY() * other.getY()
	 * @return the dot product of this vector and the given vector.
	 */
	public long dotProduct​(IntVector other) {
		return 0;}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
