package drawit;

import java.util.Arrays;

//deal with illegal arguments defensively.

/**
 * An instance of this class is a mutable abstraction storing a rounded polygon defined by a set of 
 * 2D points with integer coordinates and a nonnegative corner radius.
 * @invar This object's drawingCommands is not null
 *    | getDrawingCommands() != null
 * @invar This object's vertices is not null
 *    | getVertices() != null
 * @invar This object's radius is not negative
 * 	  | getRadius() => 0
 *	
 * @author Ahmed Shemy && Matthew Watson
 *
 */

public class RoundedPolygon {

	/**
     * @invar | drawingCommands != null
     * @invar | vertices != null
     * @invar | radius => 0
     */
	
	private String drawingCommands;
	
	private IntPoint[] vertices;
	
	private int radius;
	
	public RoundedPolygon() {}
	
	/**
	 * @inspects | point
	 * @mutates | this
	 * 
	 * @param index
	 * @param point
	 */
	public void insert​(int index,IntPoint point) {
		setVertices(PointArrays.insert​(getVertices(), index, point));	
	}
	
	/**
	 * @mutates | this
	 * 
	 * @param index
	 */
	public void remove​(int index) {
		setVertices(PointArrays.remove​(getVertices(), index));
	}
	
	/**
	 * @inspects | point
	 * @mutates | this
	 * 
	 * @param index
	 * @param point
	 */
	public void update​(int index,IntPoint point) {
		setVertices(PointArrays.update​(getVertices(), index, point));
	}
			
	/**
	 * @inspects | point
	 * 
	 * @post The result is true iff the given point is contained by the non-rounded polygon defined
	 * by this rounded polygon's vertices.
	 * 
	 */
	public boolean contains​(IntPoint point) {
		for (int j=0; j<getVertices().length; j++)
			if (point.equals​(getVertices()[j]))
				return true;
		for (int j=0; j<getVertices().length - 1; j++)
			if (point.isOnLineSegment​(getVertices()[j], getVertices()[j+1]))
				return true;
		if (point.isOnLineSegment​(getVertices()[0], getVertices()[getVertices().length -1]))
			return true;
	    final int INF = 10000; 
		IntPoint extreme = new IntPoint(INF, point.getY());
		for (int j=0; j<getVertices().length; j++)
			if (getVertices()[j].isOnLineSegment​(point, extreme)) {
				final int NINF = -10000;
				IntPoint extreme2 = new IntPoint(NINF, point.getY());
				for (int j1=0; j1<getVertices().length - 1; j1++) 
					if (IntPoint.lineSegmentsIntersect​(point, extreme2, getVertices()[j1], getVertices()[j1+1]))
						return true;
				if (IntPoint.lineSegmentsIntersect​(point, extreme2, getVertices()[0], getVertices()[getVertices().length-1]))
				return true;
				for (int j1=0; j1<getVertices().length; j1++)
					if (getVertices()[j1].isOnLineSegment​(point, extreme2))
							return true;
			}
		int count = 0;		
		for (int j1=0; j1<getVertices().length - 1; j1++)
			if (IntPoint.lineSegmentsIntersect​(point, extreme, getVertices()[j1], getVertices()[j1+1]))
				count++;
		if(IntPoint.lineSegmentsIntersect​(point, extreme, getVertices()[0], getVertices()[getVertices().length-1]))
				count++;
		if (count % 2 != 0)
			return true;
		
		return false;
		}
	
	
	/**
	 * @post The result is a string representation of a set of drawing commands for drawing this rounded polygon.
	 */
	public String getDrawingCommands() {
		String empty = new String ("");
		if (getVertices().length < 3) 
			return empty; 
		
		ArrayList<String> drawingCommands = new ArrayList<String>(); 
		 
		for (int j=1; j < getVertices().length; j++) {

			drawingCommands.add(String.format("line %d %d %d %d"+"%n", getVertices()[j-1].getX(), getVertices()[j-1].getX(), getVertices()[j].getX(), getVertices()[j].getX()));
			
			// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
			IntVector bsu = new IntVector ((getVertices()[j-1].getX() + getVertices()[j].getX()/2) + (getVertices()[j+1].getX() + getVertices()[j].getX()/2), (getVertices()[j-1].getY() + getVertices()[j].getY()/2) + (getVertices()[j+1].getY() + getVertices()[j].getY()/2)); 
			
			// find the center of the corner which is b + bsu
			int centerx = (getVertices()[j].getX() + bsu.getX()); 
			int centery = (getVertices()[j].getY() + bsu.getY()); 
			
			// make vector bau to calculate the length cutoff - it is the vector between b and a divided by its length to make it a unit vector
			// make it a double vector to use getsize method
			IntVector bau = new IntVector(getVertices()[j].getX() + getVertices()[j-1].getX(), getVertices()[j].getY() + getVertices()[j-1].getY()); 
			DoubleVector BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 
			
			// calculate unit radius 
			double unitRadius = BAU.crossProduct​(bsu.asDoubleVector()); 
			
			// make scale factor to apply - to scale unit radius to equal this.getRadius()
			double scaleFactor = this.getRadius() / unitRadius; 
			
			// determine the point along the line BA where the cuttoff occurs, and where the tart angle begins
			DoublePoint arcStartPoint = new DoublePoint ((getVertices()[j].getX() - getVertices()[j-1].getX())/scaleFactor, (getVertices()[j].getY() - getVertices()[j-1].getY())/scaleFactor); 
			DoublePoint arcEndPoint = new DoublePoint ((getVertices()[j+1].getX() - getVertices()[j].getX())/scaleFactor, (getVertices()[j+1].getY() - getVertices()[j].getY())/scaleFactor); 
			
			// create the angle vector between the corner center and the point of the length cutoff
			DoubleVector startAngleVector = new DoubleVector(centerx - arcStartPoint.getX(), centery - arcStartPoint.getY());
			
			DoubleVector endAngleVector = new DoubleVector(arcEndPoint.getX() - centerx, arcEndPoint.getY() - centery);
			
			// compute the start angle from the cutoff on line BA to the center of the corner radius
			double startAngle =  startAngleVector.asAngle(); 
			
			// compute the start angle from the cutoff on line BC to the center of the corner radius
			double angleExtent = endAngleVector.asAngle(); 
				
			// embed the variables into strings to append
			drawingCommands.add(String.format("arc %d %d %d %d"+"%n", centerx, centery, getRadius(), startAngle, angleExtent)); 		
			
		}
		return drawingCommands.toString(); 
	}

	/**
	 * Returns a new array whose elements are the vertices of this rounded polygon.
	 * 
	 * @creates | result
	 * @inspects | this
	 * 
	 */
	public IntPoint[] getVertices() {
		IntPoint[] result = this.vertices.clone();
		return result;
	}

	/**
	 * Sets the vertices of this rounded polygon to be equal to the elements of the given array.
	 * 
	 * @throws IllegalArgumentException if argument {@code vertices} is {@code null}.
     *    | vertices == null
     * @throws IllegalArgumentException if any of the elements of the given array is {@code null}.
     *    | Arrays.stream(vertices).anyMatch(e -> e == null)
	 * @throws IllegalArgumentException - if the given vertices do not define a proper polygon.
	 * | PointArrays.checkDefinesProperPolygon(vertices) != null
	 */
	public void setVertices(IntPoint[] vertices) {
		if (vertices != null && !(Arrays.stream(vertices).anyMatch(e -> e == null)) && PointArrays.checkDefinesProperPolygon​(vertices) == null)
		this.vertices = vertices;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * @return the radius of the corners of this rounded polygon.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets this rounded polygon's corner radius to the given value.
	 * @throws IllegalArgumentException - if the given radius is negative.
	 * | radius < 0
	 */
	public void setRadius(int radius) {
		if (!(radius < 0))
		this.radius = radius;
	}
}
