package drawit;

import java.util.Arrays;

/**
 * An instance of this class is a mutable abstraction storing a rounded polygon defined by a set of 
 * 2D points with integer coordinates and a nonnegative corner radius.
 * @invar This object's drawingCommands is not null
 *    | getDrawingCommands() != null
 * @invar This object's vertices is not null
 *    | getVertices() != null
 * @invar This object's radius is not negative
 * 	  | getRadius() >= 0
 *	
 * @author Ahmed Shemy && Matthew Watson
 *
 */

public class RoundedPolygon {

	/**
     * @invar | drawingCommands != null
     * @invar | vertices != null
     * @invar | radius >= 0
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
	
	public void insert(int index, IntPoint point) {
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
	 * @post The result is true if the given point is contained by the non-rounded polygon defined
	 * by this rounded polygon's vertices.
	 * 
	 */
	public boolean contains(IntPoint point) {
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

		String drawingCommands = ""; 
		
		for (int j2 = 0; j2 < getVertices().length ; j2++) {
			
			int next = 0;
			DoubleVector BCU;
			DoubleVector BAU;
			
	
			if (j2 < getVertices().length - 2)  {
				
				next = j2 + 1; 
								
				//make vector bau to calculate the length cutoff - it is the vector between b and a divided by its length to make it a unit vector
				// make it a double vector to use getsize method
				IntVector bau = getVertices()[next].minus(getVertices()[j2]);
				BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 
				
				IntVector bcu = getVertices()[next].minus(getVertices()[next+1]);
				BCU = new DoubleVector(bcu.asDoubleVector().getX()/bcu.asDoubleVector().getSize(), bcu.asDoubleVector().getY()/bcu.asDoubleVector().getSize());
				// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
				DoubleVector BSU = new DoubleVector (BCU.getX() + BAU.getX(),BCU.getY() + BAU.getY()); 
				
				// calculate unit radius 
				double unitRadius = BAU.crossProduct(BSU); 
				
				// make scale factor to apply - to scale unit radius to equal this.getRadius()
				double scaleFactor = getRadius() / unitRadius; 
				
				// find the center of the corner which is b + bsu
				double centerX = (getVertices()[next].getX() + BSU.getX() * scaleFactor); 
				double centerY = (getVertices()[next].getY() + BSU.getY() * scaleFactor); 
			
				DoublePoint c = new DoublePoint(centerX, centerY);
				bau.asDoubleVector().dotProduct(BSU);
				// compute the start angle from the cutoff on line BA to the center of the corner radius
				DoublePoint v2 = new DoublePoint(centerX + getRadius() * Math.cos(BCU.asAngle()), centerY + getRadius() * Math.sin(BCU.asAngle()));
				DoublePoint v3 = new DoublePoint(centerX + getRadius() * Math.cos(BAU.asAngle()), centerY + getRadius() * Math.sin(BAU.asAngle()));

				double startAngle =  v2.minus(c).asAngle();
				
				// compute the start angle from the cutoff on line BC to the center of the corner radius
				double angleExtent = v2.minus(c).asAngle()-v3.minus(c).asAngle()+ Math.PI; 
					
				// embed the variables into strings to append
				drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
				drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";			
				
			} else if (j2 == getVertices().length - 2){
				next = j2 + 1; 

				IntVector bau = getVertices()[next].minus(getVertices()[j2]);
				BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 
				
				IntVector bcu = getVertices()[next].minus(getVertices()[0]);
				BCU = new DoubleVector(bcu.asDoubleVector().getX()/bcu.asDoubleVector().getSize(), bcu.asDoubleVector().getY()/bcu.asDoubleVector().getSize());
				// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
				DoubleVector BSU = new DoubleVector (BCU.getX() + BAU.getX(),BCU.getY() + BAU.getY()); 
				
				// calculate unit radius 
				double unitRadius = BAU.crossProduct(BSU); 
				
				// make scale factor to apply - to scale unit radius to equal this.getRadius()
				double scaleFactor = getRadius() / unitRadius; 
				
				// find the center of the corner which is b + bsu
				double centerX = (getVertices()[next].getX() + BSU.getX() * scaleFactor); 
				double centerY = (getVertices()[next].getY() + BSU.getY() * scaleFactor); 
				DoublePoint c = new DoublePoint(centerX, centerY);
				bau.asDoubleVector().dotProduct(BSU);
				// compute the start angle from the cutoff on line BA to the center of the corner radius
				DoublePoint v2 = new DoublePoint(centerX + getRadius() * Math.cos(BCU.asAngle()), centerY + getRadius() * Math.sin(BCU.asAngle()));
				DoublePoint v3 = new DoublePoint(centerX + getRadius() * Math.cos(BAU.asAngle()), centerY + getRadius() * Math.sin(BAU.asAngle()));

				double startAngle =  v2.minus(c).asAngle();
				
				// compute the start angle from the cutoff on line BC to the center of the corner radius
				double angleExtent = v2.minus(c).asAngle()-v3.minus(c).asAngle()+ Math.PI; 
					
				// embed the variables into strings to append
				drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
				drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";
				
			}else {
				IntVector bau = getVertices()[next].minus(getVertices()[j2]);
				BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 
				
				IntVector bcu = getVertices()[next].minus(getVertices()[next+1]);
				BCU = new DoubleVector(bcu.asDoubleVector().getX()/bcu.asDoubleVector().getSize(), bcu.asDoubleVector().getY()/bcu.asDoubleVector().getSize());
				// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
				DoubleVector BSU = new DoubleVector (BCU.getX() + BAU.getX(),BCU.getY() + BAU.getY()); 
				
				// calculate unit radius 
				double unitRadius = BAU.crossProduct(BSU); 
				
				// make scale factor to apply - to scale unit radius to equal this.getRadius()
				double scaleFactor = getRadius() / unitRadius; 
				
				// find the center of the corner which is b + bsu
				double centerX = (getVertices()[next].getX() + BSU.getX() * scaleFactor); 
				double centerY = (getVertices()[next].getY() + BSU.getY() * scaleFactor); 
				DoublePoint c = new DoublePoint(centerX, centerY);
				bau.asDoubleVector().dotProduct(BSU);
				// compute the start angle from the cutoff on line BA to the center of the corner radius
				DoublePoint v2 = new DoublePoint(centerX + getRadius() * Math.cos(BCU.asAngle()), centerY + getRadius() * Math.sin(BCU.asAngle()));
				DoublePoint v3 = new DoublePoint(centerX + getRadius() * Math.cos(BAU.asAngle()), centerY + getRadius() * Math.sin(BAU.asAngle()));

				double startAngle =  v2.minus(c).asAngle();
				
				// compute the start angle from the cutoff on line BC to the center of the corner radius
				double angleExtent = v2.minus(c).asAngle()-v3.minus(c).asAngle()+ Math.PI; 
					
				// embed the variables into strings to append
				drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
				drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";	
			}
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
