package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class DrawTriangle implements ICommand {
	
	private IShapes triangle;
	private PaintCanvasBase paintCanvas;
	private int[] XCoords;
	private int[] YCoords;
	private final int numPts = 3;
	private Color primary, secondary;

	public DrawTriangle(PaintCanvasBase paintCanvas, IShapes shape) {
		triangle = shape;			
		this.paintCanvas = paintCanvas;	
		primary = shape.getShapeConfiguration().getPrimaryColor();
		secondary = shape.getShapeConfiguration().getSecondaryColor();
	}
	
	@Override
	public void run() {		
		setPts();		
		switch (triangle.getShapeConfiguration().getShapeShadingType()) {
		case FILLED_IN :
			drawFilled();
			break;
		case OUTLINE: 
			outlineOnly();
			break;
		case OUTLINE_AND_FILLED_IN:
			drawFilled();
			outlineOnly();
			break;
		default:
			throw new Error("Are you sure you'd like to draw a triangle?");
		}		
	}

	private void setPts() {
		int[] xs = {triangle.getStartX(), triangle.getEndX(), triangle.getStartX()};
		int[] ys = {triangle.getStartY(), triangle.getEndY(), triangle.getEndY()};		
		XCoords = xs;
		YCoords = ys;
	}
	
	
	private void drawFilled() {
		// Filled in triangle
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setColor(primary);
		graphics2d.fillPolygon(XCoords, YCoords, numPts);		
	}

	private void outlineOnly() {
		//Outlined triangle
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setStroke(new BasicStroke(5));
		graphics2d.setColor(secondary);
		graphics2d.drawPolygon(XCoords, YCoords, numPts);
	}

}