package drawit;

public final class DoubleVector extends Object{

	private double x;
	private double y;
	private double size;
	
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
		return 0;
	}
	
	/**
	 * @post result == this.getX() * other.getY() - this.getY() * other.getX()
	 * @param other
	 * @return the cross product of this vector and the given vector.
	 */
	public double crossProduct​(DoubleVector other) {
		double result = 0;
		return result;
	}
	
	/**
	 * @post result == this.getX() * other.getX() + this.getY() * other.getY()
	 * @param other
	 * @return the dot product of this vector and the given vector.
	 */
	public double dotProduct​(DoubleVector other) {
		double result = 0;
		return result;
		}
	
	public DoubleVector plus​(DoubleVector other) {
		return null;
		}
	
	/**
	 * 
	 * @return a DoubleVector object whose coordinates are obtained by multiplying 
	 * this vector's coordinates by the given scale factor.
	 */
	public DoubleVector scale​(double d) {
		return null;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getSize() {
		return size;
	}
}
