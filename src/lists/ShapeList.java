package lists;

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
		//notifyObservers();
	}	
	
	public void remove(IShapes shape) {
		masterList.remove(shape);
		//notifyObservers();
	}
		
	public void notifyObservers() {
		UpdateCanvas us = new UpdateCanvas(paintCanvas);
		us.update(this);
	}	

	public ArrayList<IShapes> getShapeList() { return masterList; }

	@Override
	public void emptyList() { masterList.clear(); }
}
