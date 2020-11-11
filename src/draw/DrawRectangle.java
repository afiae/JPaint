package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class DrawRectangle implements ICommand{

	private IShapes rectangle;
	private int minX, minY, ht, wd;;	
	private PaintCanvasBase paintCanvas;
	private Color primary, secondary;

	public DrawRectangle (PaintCanvasBase paintCanvas, IShapes shape) {		
		this.paintCanvas = paintCanvas;
		minX = Math.min(shape.getStartX(), shape.getEndX());
		minY = Math.min(shape.getStartY(), shape.getEndY());
		ht = shape.getHeight();
		wd = shape.getWidth();		
		rectangle = shape;	
		this.primary = shape.getShapeConfiguration().getPrimaryColor();
		this.secondary = shape.getShapeConfiguration().getSecondaryColor();
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
		graphics2d.fillRect(minX, minY, wd, ht);
	}

	private void outlineOnly() {
		// Outlined rectangle
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setStroke(new BasicStroke(5));
		graphics2d.setColor(secondary);
		graphics2d.drawRect(minX, minY, wd, ht);
	}
}
