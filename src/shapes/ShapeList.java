package shapes;

import java.util.ArrayList;
import controller.UpdateCanvas;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class ShapeList implements IShapeList {
	private ArrayList <IShapes> masterList;
	private PaintCanvasBase paintCanvas;
	
	public ShapeList(PaintCanvasBase paintCanvas) { 
		masterList = new ArrayList<IShapes>();
		this.paintCanvas = paintCanvas;
	} 

	public void add(IShapes shape) { 
		masterList.add(shape); 
		notifyObservers();
	}	
	
	//takes a IShape parameter and removes the first instance of it in 
	//the masterList
	public void remove(IShapes shape) {
		masterList.remove(shape);
		notifyObservers();
	}
	
	//takes no parameter and simply removed the last item in masterList
	//specifically using for PasteCommand's undo function
	public void removeLast() {
		masterList.remove(masterList.size()-1);
		notifyObservers();
	}
	
	public void notifyObservers() {
		UpdateCanvas us = new UpdateCanvas(paintCanvas);
		us.update(this);
	}	

	public ArrayList<IShapes> getShapeList() { return masterList; }

	@Override
	public void emptyList() { masterList.clear(); }
}
