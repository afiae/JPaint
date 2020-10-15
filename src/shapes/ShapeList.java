package shapes;

import java.util.ArrayList;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeList implements IShapeList {
	private ArrayList <IShapes> shapeList;
	private PaintCanvasBase paintCanvas;
	private ApplicationState as;


	public ShapeList(PaintCanvasBase pc, ApplicationState AS) { 
		shapeList = new ArrayList<IShapes>();
		paintCanvas = pc;
		as = AS;
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
		IShapeDrawer sd = new ShapeDrawer(paintCanvas, as);
		sd.draw(this);	
	}	

	public ArrayList<IShapes> getShapeList() { return shapeList; }
}
