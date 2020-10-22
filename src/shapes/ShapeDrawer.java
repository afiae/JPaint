package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.ICommand;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

import view.interfaces.PaintCanvasBase;

public class ShapeDrawer implements IShapeDrawer {

	PaintCanvasBase paintCanvas;
	ICommand cmd;

	public ShapeDrawer(PaintCanvasBase pcb) { paintCanvas = pcb; }

	public void draw( IShapeList shapes ) {		
		//draw blank white rectangle to 'clear' canvas
		Graphics2D g = paintCanvas.getGraphics2D();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 90000, 90000);

		if(shapes.getShapeList().size() < 1) return;

		//iterate through list and redraw all shapes		
		for(IShapes s: shapes.getShapeList()) {
			switch (s.getShapeConfiguration().getShapeType()) {
			case RECTANGLE:
				cmd = new DrawRectangle(paintCanvas, s);
				break;
			case ELLIPSE:
				cmd = new DrawEllipse(paintCanvas, s);
				break;
			case TRIANGLE: 
				cmd = new DrawTriangle(paintCanvas, s);
				break;
			default:
				throw new Error("Unknown shape -- cannot draw");
			} 
			cmd.run();			
		}
	}
}
