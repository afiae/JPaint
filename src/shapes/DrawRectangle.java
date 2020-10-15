package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class DrawRectangle implements ICommand{

	IShapes rectangle;
	private int minX, minY;	
	PaintCanvasBase paintCanvas;
	
	Color primary, secondary;
	
	public DrawRectangle (PaintCanvasBase PCB, IShapes shape) {		
		paintCanvas = PCB;
		minX = Math.min(shape.getStartX(), shape.getEndX());
		minY = Math.min(shape.getStartY(), shape.getEndY());
		rectangle = shape;		
		
		primary = shape.getShapeConfiguration().getPrimaryColor();
		secondary = shape.getShapeConfiguration().getSecondaryColor();
	}
	
	@Override
	public void run() {
		switch (rectangle.getShapeConfiguration().getShapeShadingType()) {
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
			throw new Error("Are you sure you'd like to draw a rectangle?");
		}
	}
	
	private void drawFilled() {
		// Filled in rectangle
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(primary);
        graphics2d.fillRect(minX, minY, rectangle.getWidth(), rectangle.getHeight());
	}

	private void outlineOnly() {
        // Outlined rectangle
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(secondary);
        graphics2d.drawRect(minX, minY, rectangle.getWidth(), rectangle.getHeight());
		
	}


}
