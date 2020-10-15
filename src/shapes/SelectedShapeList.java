package shapes;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.util.ArrayList;

import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import view.interfaces.PaintCanvasBase;

public class SelectedShapeList implements IShapeList{

	ArrayList<IShapes> selectedShapeList;
	IShapeList shapeList;
	IShapes selectedArea;
	PaintCanvasBase pcb;

	public SelectedShapeList(IShapeList shape_list, PaintCanvasBase paintCanvas) {
		shapeList = shape_list;
		selectedShapeList = new ArrayList<>();	
		pcb = paintCanvas;
	}

	@Override
	public void add(IShapes shape) { 
		shape.select();
		selectedShapeList.add(shape); 
		notifyObservers();
	}

	@Override
	public void remove(IShapes shape) { 
		selectedShapeList.remove(shape); 
		notifyObservers();
	}

	@Override
	public ArrayList<IShapes> getShapeList() { return selectedShapeList; }

	@Override
	public void notifyObservers() {
		IShapeDrawer SD = new OutlineSelectedShape(pcb);
		SD.draw(shapeList);

	}
}
