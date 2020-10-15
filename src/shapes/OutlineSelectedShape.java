package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import controller.CanvasReset;
import controller.interfaces.IReset;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class OutlineSelectedShape implements IShapeDrawer {

	PaintCanvasBase paintCanvas;

	public OutlineSelectedShape(PaintCanvasBase PCB) { 	paintCanvas = PCB; 	}

	@Override
	public void draw(IShapeList allShapes) {
		
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		
		//reset canvas
		IReset r = new CanvasReset(paintCanvas);
		r.reset();


		//redraw all the shapes
		//allShapes.drawShapes();		

		// draw selected Shape outline
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9},0);
		graphics2d.setStroke(stroke);
		graphics2d.setColor(Color.GRAY);
		for(IShapes s : allShapes.getShapeList()) {
			if(s.isSelected()) {
				graphics2d.drawRect(Math.min(s.getEndX(), s.getStartX())-2, 
						Math.min(s.getEndY()-2, s.getStartY()), s.getWidth(), s.getHeight());
			}
		}
	}
}
