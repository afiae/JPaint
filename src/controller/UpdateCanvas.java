package controller;

import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import shapes.OutlineSelectedShape;
import shapes.ShapeDrawer;
import view.interfaces.PaintCanvasBase;

public class UpdateCanvas {
	
	private PaintCanvasBase paintCanvas;
	
	public UpdateCanvas (PaintCanvasBase paintCanvas) {
		this.paintCanvas = paintCanvas;
	}
	
	public void update(IShapeList shapeList) {
		
		IShapeDrawer sd = new ShapeDrawer(paintCanvas);
		sd.draw(shapeList);		
		sd = new OutlineSelectedShape(paintCanvas);
		sd.draw(shapeList);
	}
}
