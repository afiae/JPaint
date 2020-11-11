 package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class OutlineSelectedShape implements IShapeDrawer {

	PaintCanvasBase paintCanvas;
	
	public OutlineSelectedShape(PaintCanvasBase PCB) { 	paintCanvas = PCB; 	}

	@Override
	public void draw(IShapeList masterList) {
		
		Graphics2D graphics2d = paintCanvas.getGraphics2D();		
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9},0);
		graphics2d.setStroke(stroke);
		graphics2d.setColor(Color.GRAY);
		
		for(IShapes s : masterList.getShapeList()) {
			if(s.isSelected()) {				
				int minX = Math.min(s.getStartX(), s.getEndX());
				int minY = Math.min(s.getStartY(), s.getEndY());
				int ht = s.getHeight();
				int wd = s.getWidth();	
				int[] xs = {s.getStartX(), s.getEndX(), s.getStartX()};
				int[] ys = {s.getStartY(), s.getEndY(), s.getEndY()};	
				
				switch(s.getShapeConfiguration().getShapeType()) {
				case RECTANGLE:
					graphics2d.drawRect(minX-5, minY-5, wd+10, ht+10);
					break;
				case ELLIPSE:
					graphics2d.drawOval(minX-5, minY-5, wd+10, ht+10);
					break;
				case TRIANGLE:
					graphics2d.drawPolygon(xs, ys, 3);
					break;
				}		
			}
		}
	}
}