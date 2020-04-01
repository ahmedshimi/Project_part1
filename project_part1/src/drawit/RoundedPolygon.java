package drawit;

import java.util.ArrayList;
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

	private String drawingCommands = "";

	private IntPoint[] vertices;

	private int radius;

	public RoundedPolygon() {}

	/**
	 * 
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if {@code point} is null.
	 *    | point == null
	 *    
	 * @throws IllegalArgumentException if {@code index} is not within the vertices array length.
	 *    | 0 > index || index > getVertices().length
	 * @param index
	 * @param point
	 */


	public void insert(int index, IntPoint point) {
		if (point == null)
			throw new IllegalArgumentException("Point to be inserted is null.");
		if (0 > index || index > getVertices().length)
			throw new IllegalArgumentException("This index does not exist.");
		setVertices(PointArrays.insert(getVertices(), index, point));	
	}

	/**
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if {@code index} is not within the vertices array length.
	 *    | 0 > index || index >= getVertices().length
	 * @param index
	 */


	public void remove (int index) {
		if (0 > index || index >= getVertices().length) {
			throw new IllegalArgumentException("This index does not exist.");
		}else setVertices(PointArrays.remove(getVertices(), index));
	}


	/**
	 * 
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if {@code point} is null.
	 *    | point == null
	 *    
	 * @throws IllegalArgumentException if {@code index} is not within the vertices array length.
	 *    | 0 > index || index >= getVertices().length
	 * @param index
	 * @param point
	 */

	public void update(int index, IntPoint point) {
		if (point == null)
			throw new IllegalArgumentException("The new point is null.");
		if (0 > index || index >= getVertices().length)
			throw new IllegalArgumentException("This index does not exist.");
		setVertices(PointArrays.update(getVertices(), index, point));
	}

	/**
	 * 
	 * @post The result is true if the given point is contained by the non-rounded polygon defined
	 * by this rounded polygon's vertices.
	 * 
	 */

	public boolean contains(IntPoint point) {
		for (int j=0; j<getVertices().length; j++)
			if (point.equals(getVertices()[j]))
				return true;
		for (int j=0; j<getVertices().length - 1; j++)
			if (point.isOnLineSegment(getVertices()[j], getVertices()[j+1]))
				return true;
		if (point.isOnLineSegment(getVertices()[0], getVertices()[getVertices().length -1]))
			return true;
		final int INF = 10000; 
		IntPoint extreme = new IntPoint(INF, point.getY());
		for (int j=0; j<getVertices().length; j++)
			if (getVertices()[j].isOnLineSegment(point, extreme)) {
				final int NINF = -10000;
				IntPoint extreme2 = new IntPoint(NINF, point.getY());
				for (int j1=0; j1<getVertices().length - 1; j1++) 
					if (IntPoint.lineSegmentsIntersect(point, extreme2, getVertices()[j1], getVertices()[j1+1]))
						return true;
				if (IntPoint.lineSegmentsIntersect(point, extreme2, getVertices()[0], getVertices()[getVertices().length-1]))
					return true;
				for (int j1=0; j1<getVertices().length; j1++)
					if (getVertices()[j1].isOnLineSegment(point, extreme2))
						return true;
			}
		int count = 0;		
		for (int j1=0; j1<getVertices().length - 1; j1++)
			if (IntPoint.lineSegmentsIntersect(point, extreme, getVertices()[j1], getVertices()[j1+1]))
				count++;
		if(IntPoint.lineSegmentsIntersect(point, extreme, getVertices()[0], getVertices()[getVertices().length-1]))
			count++;
		if (count % 2 != 0)
			return true;

		return false;
	}


	/**
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if the radius to be adjusted is more than half of any edge in the polygon.
	 *    | Arrays.stream(sides).anyMatch(e -> (e/2) < getRadius())
	 * 
	 * @post The result is a string representation of a set of drawing commands for drawing this rounded polygon.
	 */

	public String getDrawingCommands() {
		if (getVertices().length < 3) {
			return this.drawingCommands;
		}
		String drawingCommands = this.drawingCommands; 
		ArrayList <Float> sides = new ArrayList<Float>();
		for (int j = 0; j < getVertices().length - 1 ; j++){
			sides.add((float) (getVertices()[j].minus(getVertices()[j+1]).asDoubleVector().getSize()) / 2);
		}
		sides.add((float) (getVertices()[getVertices().length-1].minus(getVertices()[0]).asDoubleVector().getSize() / 2));
		
		for (int j = 0; j < getVertices().length ; j++) {
			if (getRadius() > sides.get(j)) {
				throw new IllegalArgumentException("radius can not be more than half of adjacent edges");
			}}
		
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
				
				if (getVertices()[j2].minus(getVertices()[j2+1]).isCollinearWith(getVertices()[j2+1].minus(getVertices()[j2+2]))){
					drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+((getVertices()[j2].getX()+getVertices()[j2+1].getX())/2) +" "+ ((getVertices()[j2].getY()+getVertices()[j2+1].getY())/2) + "\r\n";
					drawingCommands = drawingCommands + "line " + ((getVertices()[j2].getX()+getVertices()[j2+1].getX())/2)  +" "+ ((getVertices()[j2].getY()+getVertices()[j2+1].getY())/2)  +" "+ getVertices()[j2+1].getX() +" "+ getVertices()[j2+1].getY() + "\r\n";				
					drawingCommands = drawingCommands + "line " + getVertices()[j2+1].getX() +" "+ getVertices()[j2+1].getY() +" "+ ((getVertices()[j2+2].getX()+getVertices()[j2+1].getX())/2)  +" "+ ((getVertices()[j2+2].getY()+getVertices()[j2+1].getY())/2) + "\r\n";
					continue;
					
				} else {

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
					double angleExtent= v3.minus(c).asAngle()-startAngle;

					if(angleExtent < (-Math.PI)) {
						angleExtent = angleExtent + 2 * Math.PI;
					} else if(angleExtent > Math.PI) {
						angleExtent = angleExtent - 2 * Math.PI;
					}

					// embed the variables into strings to append
					drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
					drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";	
				}

			} else if (j2 == getVertices().length - 2){
				
				next = j2 + 1; 

				IntVector bau = getVertices()[next].minus(getVertices()[j2]);
				BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 

				IntVector bcu = getVertices()[next].minus(getVertices()[0]);
				BCU = new DoubleVector(bcu.asDoubleVector().getX()/bcu.asDoubleVector().getSize(), bcu.asDoubleVector().getY()/bcu.asDoubleVector().getSize());
				// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
				DoubleVector BSU = new DoubleVector (BCU.getX() + BAU.getX(),BCU.getY() + BAU.getY());
				
				if (getVertices()[j2].minus(getVertices()[j2+1]).isCollinearWith(getVertices()[j2+1].minus(getVertices()[0]))){
					drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle()))+" "+((getVertices()[j2].getX()+getVertices()[j2+1].getX())/2) +" "+ ((getVertices()[j2].getY()+getVertices()[j2+1].getY())/2) + "\r\n";
					drawingCommands = drawingCommands + "line " + ((getVertices()[j2].getX()+getVertices()[j2+1].getX())/2) +" "+ ((getVertices()[j2].getY()+getVertices()[j2+1].getY())/2)  +" "+ getVertices()[j2+1].getX() +" "+ getVertices()[j2+1].getY() + "\r\n";				
					drawingCommands = drawingCommands + "line " + getVertices()[j2+1].getX() +" "+ getVertices()[j2+1].getY()  +" "+ ((getVertices()[0].getX()+getVertices()[j2+1].getX())/2) +" "+ ((getVertices()[0].getY()+getVertices()[j2+1].getY())/2) + "\r\n";
					continue;
				} else {

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
					double angleExtent= v3.minus(c).asAngle()-startAngle;

					if(angleExtent < (-Math.PI)) {
						angleExtent = angleExtent + 2 * Math.PI;
					} else if(angleExtent > Math.PI) {
						angleExtent = angleExtent - 2 * Math.PI;
					}

					// embed the variables into strings to append
					drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
					drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";
				}
			}else {
				
				IntVector bau = getVertices()[next].minus(getVertices()[j2]);
				BAU = new DoubleVector(bau.asDoubleVector().getX()/bau.asDoubleVector().getSize(), bau.asDoubleVector().getY()/bau.asDoubleVector().getSize()); 

				IntVector bcu = getVertices()[next].minus(getVertices()[next+1]);
				BCU = new DoubleVector(bcu.asDoubleVector().getX()/bcu.asDoubleVector().getSize(), bcu.asDoubleVector().getY()/bcu.asDoubleVector().getSize());
				// create vector bsu from demo - unit vector pointing to bisector, which is equal to bau + bcu
				DoubleVector BSU = new DoubleVector (BCU.getX() + BAU.getX(),BCU.getY() + BAU.getY()); 

				if (getVertices()[j2].minus(getVertices()[next]).isCollinearWith(getVertices()[next].minus(getVertices()[next+1]))){
					drawingCommands = drawingCommands + "line " + getVertices()[j2].getX() +" "+ getVertices()[j2].getY() +" "+((getVertices()[j2].getX()+ getVertices()[next].getX())/2)  +" "+ ((getVertices()[j2].getY()+ getVertices()[next].getY())/2) + "\r\n";
					drawingCommands = drawingCommands + "line " + ((getVertices()[j2].getX()+ getVertices()[next].getX())/2)  +" "+ ((getVertices()[j2].getY()+ getVertices()[next].getY())/2)   +" "+ getVertices()[next].getX() +" "+ getVertices()[next].getY() + "\r\n";				
					drawingCommands = drawingCommands + "line " + getVertices()[next].getX()  +" "+ getVertices()[next].getY()  +" "+ ((getVertices()[next].getX()+ getVertices()[next+1].getX())/2) +" "+ ((getVertices()[next].getY()+ getVertices()[next+1].getY())/2) + "\r\n";
					continue;
				} else {

					// calculate unit radius 
					double unitRadius = BAU.crossProduct(BSU);

					// make scale factor to apply - to scale unit radius to equal this.getRadius()
					double scaleFactor = getRadius() / unitRadius; 

					// find the center of the corner which is b + bsu
					double centerX = (getVertices()[next].getX() + BSU.getX() * scaleFactor); 
					double centerY = (getVertices()[next].getY() + BSU.getY() * scaleFactor); 
					DoublePoint c = new DoublePoint(centerX, centerY);
					// compute the start angle from the cutoff on line BA to the center of the corner radius
					DoublePoint v2 = new DoublePoint(centerX + getRadius() * Math.cos(BCU.asAngle()), centerY + getRadius() * Math.sin(BCU.asAngle()));
					DoublePoint v3 = new DoublePoint(centerX + getRadius() * Math.cos(BAU.asAngle()), centerY + getRadius() * Math.sin(BAU.asAngle()));

					double startAngle =  v2.minus(c).asAngle();

					// compute the start angle from the cutoff on line BC to the center of the corner radius
					double angleExtent= v3.minus(c).asAngle()-startAngle;

					if(angleExtent < (-Math.PI)) {
						angleExtent = angleExtent + 2 * Math.PI;
					} else if(angleExtent > Math.PI) {
						angleExtent = angleExtent - 2 * Math.PI;
					}

					// embed the variables into strings to append
					drawingCommands = drawingCommands + "line " + (getVertices()[j2].getX() + getRadius() * Math.cos(BAU.asAngle()))  +" "+ (getVertices()[j2].getY() + getRadius() * Math.sin(BAU.asAngle())) +" "+ v2.getX() +" "+ v2.getY() + "\r\n";				
					drawingCommands = drawingCommands + "arc " + c.getX() +" "+ c.getY() +" "+ getRadius() +" "+ startAngle +" "+ angleExtent +"\r\n";	
				}
			}
		}

		return drawingCommands.toString(); 
	}


	/**
	 * 
	 * 
	 * Returns a new array whose elements are the vertices of this rounded polygon.
	 * @inspects | this
	 * 
	 */

	public IntPoint[] getVertices() {
		if (this.vertices == null) {
			return new IntPoint[]{};
		}else {
		return PointArrays.copy(this.vertices);}
	}

	/**
	 * 
	 * @mutates | this
	 * Sets the vertices of this rounded polygon to be equal to the elements of the given array.
	 * 
	 * @throws IllegalArgumentException if argument {@code vertices} is {@code null}.
	 *    | vertices == null
	 * @throws IllegalArgumentException if any of the elements of the given array is {@code null}.
	 *    | Arrays.stream(vertices).anyMatch(e -> e == null)
	 */

	public void setVertices(IntPoint[] vertices) {
		if (vertices == null || Arrays.stream(vertices).anyMatch(e -> e == null) || vertices.length<2) {
			throw new IllegalArgumentException();
		}else {
				if(PointArrays.checkDefinesProperPolygon(vertices) == null) {
					this.vertices = vertices;
				}else {throw new IllegalArgumentException("Not proper Polygon");}
		}
	}

	/**
	 * @inspects | this
	 * @return the radius of the corners of this rounded polygon.
	 */

	public int getRadius() {
		return this.radius;
	}

	/**
	 * @inspects | radius
	 * @mutates | this
	 * Sets this rounded polygon's corner radius to the given value.
	 * @throws IllegalArgumentException - if the given radius is negative.
	 * 		| radius < 0
	 */

	public void setRadius(int radius) {
		if (!(radius < 0)) {
			this.radius = radius;
		}else {
			throw new IllegalArgumentException("Radius is negative");}
	}
}
