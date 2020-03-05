package drawit;

public final class DoublePoint extends Object{

	private double x;
	private double y;
	
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public DoublePoint plus​(DoubleVector other) {
		return null;
		}
	
	public DoubleVector minus​(DoublePoint other) {
		return null;
		}
	
	/**
	 * 
	 * @return an IntPoint object whose coordinates are obtained 
	 * by rounding the coordinates of this point to the nearest integer.
	 */
	public IntPoint round() {
		return null;
		}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
}
