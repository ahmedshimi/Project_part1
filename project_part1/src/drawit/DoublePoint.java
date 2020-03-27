package drawit;

/**
 * Each instance of this class represents a point with double X and Y coordinates.
 *
 * @immutable
 * @author Ahmed Shemy && Matthew Watson
 */
public final class DoublePoint extends Object{

	private double x;
	private double y;

	/**
	 *Initializes this point with the given coordinates.
	 */
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}




	public DoublePoint plus(DoubleVector other) {
		double x = this.getX() + other.getX();
		double y = this.getY() + other.getY();
		DoublePoint result = new DoublePoint(x,y);
		return result;
	}


	public DoubleVector minus(DoublePoint other) {
		double x = this.getX() - other.getX();
		double y = this.getY() - other.getY();
		DoubleVector result = new DoubleVector(x,y);
		return result;
	}

	/**
	 * 
	 * @return an IntPoint object whose coordinates are obtained 
	 * by rounding the coordinates of this point to the nearest integer.
	 */

	public IntPoint round() {
		IntPoint result = new IntPoint((int)Math.round(this.getX()),(int)Math.round(this.getY()));
		return result;
	}

	/**
	 * @return this point's X coordinate.
	 */

	public double getX() {
		return x;
	}

	/**
	 * @return Returns this point's Y coordinate.
	 */

	public double getY() {
		return y;
	}
}
