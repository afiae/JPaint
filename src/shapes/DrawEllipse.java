package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class DrawEllipse implements ICommand {

	private int minX, minY, ht, wd;	
	private PaintCanvasBase paintCanvas;
	IShapes ellipse;
	

	public DrawEllipse(PaintCanvasBase PCB, IShapes shape) {
		paintCanvas = PCB;
		minX = Math.min(shape.getStartX(), shape.getEndX());
		minY = Math.min(shape.getStartY(), shape.getEndY());
		ht = shape.getHeight();
		wd = shape.getWidth();		
		ellipse = shape;
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
		graphics2d.setColor(ellipse.getShapeConfiguration().getPrimaryColor());
		graphics2d.fillOval(minX, minY, wd, ht);
	}

	private void outlineOnly() {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		// Outlined oval
		graphics2d.setStroke(new BasicStroke(5));
		graphics2d.setColor(ellipse.getShapeConfiguration().getSecondaryColor());
		graphics2d.drawOval(minX, minY, wd, ht);
	}



}
