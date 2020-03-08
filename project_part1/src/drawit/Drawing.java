package drawit; 

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Drawing extends Frame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Drawing drawing; 
	
	static TestDrawing drawingCanvas; 
	
	public Drawing() {
        
		super("DrawIt");

		Menu fileMenu = new Menu("File",true);
		fileMenu.add("New");      
		fileMenu.add("Save...");      
		fileMenu.add("Load...");      
		fileMenu.addSeparator();
		fileMenu.add("Undo");   
		fileMenu.addSeparator();   
		fileMenu.add("Quit");
		fileMenu.addActionListener(this);


		MenuBar mb = new MenuBar();
		mb.add(fileMenu);

		setMenuBar(mb);

		drawingCanvas = new TestDrawing();
		add(drawingCanvas);

		addWindowListener(
				new WindowAdapter() {  // Window listener object closes the window and ends the
					//   program when the user clicks the window's close box.
					public void windowClosing(WindowEvent evt) {
						dispose();
						System.exit(0);
					}
				}
				);

		pack();
		setSize(500, 500); 
		setVisible(true); 



	}
	

class TestDrawing extends Canvas implements MouseListener, MouseMotionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		int xCoordinate; 
		int yCoordinate; 
		int prevX; 
		int prevY; 
		
		boolean dragging = false; 
		
		Graphics gc; 
		
		TestDrawing() {
	         // Construct the canvas, and set it to listen for mouse events.
	         // Also create an array to hold the lines that are displayed on
	         // the canvas.
	      setBackground(Color.white);
	      
	      addMouseListener(this);
	      addMouseMotionListener(this);
		}
		

		@Override
		public void mouseDragged(MouseEvent event) {
			if (!dragging)  // Make sure that the drag operation has been properly started.
		         return;
		      gc.drawLine(xCoordinate,yCoordinate,prevX,prevY);  // Erase the previous line.
		      prevX = event.getX();
		      prevY = event.getY();
		      gc.drawLine(xCoordinate,yCoordinate,prevX,prevY);  // Draw the new line.
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent event) {
			xCoordinate = event.getX();
			yCoordinate = event.getY();
		    prevX = xCoordinate;
		    prevY = yCoordinate;
		    dragging = true;
		    gc = getGraphics();  // Get a graphics context for use while drawing
		    gc.setXORMode(getBackground());
		    gc.drawLine(xCoordinate, yCoordinate, prevX, prevY);
			
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (!dragging)  // Make sure that the drag operation has been properly started.
		         return;
		      gc.drawLine(xCoordinate,yCoordinate,prevX,prevY);  // Erase the previous line.
		      int endX = event.getX();  // Where the mouse was released.
		      int endY = event.getY();
		      gc.setPaintMode();
		      gc.drawLine(xCoordinate, yCoordinate, endX, endY);  // Draw the permanent line in regular "paint" mode.
		      gc.dispose();
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
       
     drawing = new Drawing();
  }

}

