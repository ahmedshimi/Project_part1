package drawit;

/**
 * Each instance of this class represents a vector with double X and Y coordinates.
 *
 * @immutable
 * @author Ahmed Shemy & Matthew Watson
 */

public final class DoubleVector extends Object{

	private double x;
	private double y;
	@SuppressWarnings("unused")
	private double size;
	
	/**
	 *Initializes this vector with the given coordinates.
	 */
	public DoubleVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the angle from positive X to this vector, in radians. 
	 * The angle from positive X to positive Y is Math.PI / 2; 
	 * the angle from positive X to negative Y is -Math.PI / 2.
	 */
	public double asAngle() {
		double angle = Math.atan2(y,x);
		return angle;
	}
	
	/**
	 * @post result == this.getX() * other.getY() - this.getY() * other.getX()
	 * @param other
	 * @return the cross product of this vector and the given vector.
	 */
	public double crossProduct​(DoubleVector other) {
		double result = this.getX() * other.getY() - this.getY() * other.getX();
		return result;
	}
	
	/**
	 * @post result == this.getX() * other.getX() + this.getY() * other.getY()
	 * @param other
	 * @return the dot product of this vector and the given vector.
	 */
	public double dotProduct​(DoubleVector other) {
		double result = this.getX() * other.getX() + this.getY() * other.getY();
		return result;
		}
	
	public DoubleVector plus​(DoubleVector other) {
		DoubleVector result = new DoubleVector(this.getX() + other.getX(),this.getY() + other.getY());
		return result;
		}
	
	/**
	 * 
	 * @return a DoubleVector object whose coordinates are obtained by multiplying 
	 * this vector's coordinates by the given scale factor.
	 */
	public DoubleVector scale​(double d) {
		DoubleVector result = new DoubleVector(this.getX() * d,this.getY() * d);
		return result;
	}
	/**
	 * @return Returns this vector's X coordinate.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return Returns this vector's Y coordinate.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @post This object's size is more than or equal zero.
	 * 		|getSize() >= 0
	 * @return Returns this vector's size.
	 */
	@SuppressWarnings("unused")
	public double getSize() {
		double size = Math.sqrt((this.getX() * this.getX() + this.getY() * this.getY()));
		return size;
	}
}
