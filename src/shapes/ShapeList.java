package shapes;

import java.util.ArrayList;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class ShapeList implements IShapeList {
	private ArrayList <IShapes> shapeList;
	private PaintCanvasBase paintCanvas;
	
	public ShapeList(PaintCanvasBase pc) { 
		shapeList = new ArrayList<IShapes>();
		paintCanvas = pc;
	} 

	public void add(IShapes shape) { 
		shapeList.add(shape); 
		notifyObservers();
	}
	
	public void remove(IShapes shape) {
		shapeList.remove(shape);
		notifyObservers();
	}
	
	public void notifyObservers() {
		IShapeDrawer sd = new ShapeDrawer(paintCanvas);
		sd.draw(this);			
	}	

	public ArrayList<IShapes> getShapeList() { return shapeList; }
}
