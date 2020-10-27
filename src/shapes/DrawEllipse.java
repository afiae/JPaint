package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class DrawEllipse implements ICommand {

	private int minX, minY, ht, wd;	
	private PaintCanvasBase paintCanvas;
	private IShapes ellipse;
	private Color primary, secondary;

	public DrawEllipse(PaintCanvasBase paintCanvas, IShapes shape) {
		this.paintCanvas = paintCanvas;
		minX = Math.min(shape.getStartX(), shape.getEndX());
		minY = Math.min(shape.getStartY(), shape.getEndY());
		ht = shape.getHeight();
		wd = shape.getWidth();		
		ellipse = shape;		
		this.primary = shape.getShapeConfiguration().getPrimaryColor();
		this.secondary = shape.getShapeConfiguration().getSecondaryColor();
	}
	
	@Override
	public void run() {		
		switch (ellipse.getShapeConfiguration().getShapeShadingType()) {
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
			throw new Error("Are you sure you'd like to draw an ellipse?");
		}
	}

	private void drawFilled() {
		// Filled in oval
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setColor(primary);
		graphics2d.fillOval(minX, minY, wd, ht);
	}

	private void outlineOnly() {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		// Outlined oval
		graphics2d.setStroke(new BasicStroke(5));
		graphics2d.setColor(secondary);
		graphics2d.drawOval(minX, minY, wd, ht);
	}
}
