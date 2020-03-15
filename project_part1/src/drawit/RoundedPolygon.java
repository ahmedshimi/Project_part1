package drawit;

//deal with illegal arguments defensively.

/**
 * An instance of this class is a mutable abstraction storing a rounded polygon defined by a set of 
 * 2D points with integer coordinates and a nonnegative corner radius.
 * 
 * @author Ahmed Shemy && Matthew Watson
 *
 */
public class RoundedPolygon {

	private String drawingCommands;
	
	private IntPoint[] vertices;
	
	private int radius;
	
	public RoundedPolygon() {}
	
	public void insert​(int index,IntPoint point) {
		setVertices(PointArrays.insert​(getVertices(), index, point));
		
	}
			
	public void remove​(int index) {
		setVertices(PointArrays.remove​(getVertices(), index));
	}
	
	public void update​(int index,IntPoint point) {
		setVertices(PointArrays.update​(getVertices(), index, point));
	}
			
	/**
	 * 
	 * @post true if the given point is contained by the (non-rounded) polygon defined by this rounded polygon's vertices. 
	 * This method does not take into account this rounded polygon's corner radius; it assumes a corner radius of zero.
	 * A point is contained by a polygon if it coincides with one of its vertices, or if it is on one of its edges, 
	 * or if it is in the polygon's interior.
	 * 		|array.getVertices().contains(point) == true; 
	 */
	public boolean contains​(IntPoint point) {
		
		return false;}
	
	
	/**
	 * @post A string representation of a set of drawing commands for drawing this rounded polygon.
	 */
	public String getDrawingCommands() {
		return drawingCommands;
	}

	/**
	 * @post a new array whose elements are the vertices of this rounded polygon.
	 */
	public IntPoint[] getVertices() {
		IntPoint[] result = this.vertices.clone();
		return result;
	}

	/**
	 * Sets the vertices of this rounded polygon to be equal to the elements of the given array.
	 * @throws IllegalArgumentException - if the given vertices do not define a proper polygon.
	 * | PointArrays.checkDefinesProperPolygon(newVertices) != null
	 */
	public void setVertices(IntPoint[] vertices) {
		if (PointArrays.checkDefinesProperPolygon​(vertices) != null)
		this.vertices = vertices;
	}

	/**
	 * @post the radius of the corners of this rounded polygon.
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
