package controller;

import controller.interfaces.ICommand;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import view.interfaces.PaintCanvasBase;


public class SelectCommand implements ICommand{

	private IShapeList masterList, selectedList;
	int minX, maxX, minY, maxY;
	PaintCanvasBase paintCanvas;

	public SelectCommand(Point start, Point end, IShapeList masterList, IShapeList selectedList, PaintCanvasBase paintCanvas) {

		this.paintCanvas = paintCanvas;
		this.masterList = masterList;
		this.selectedList = selectedList;

		minX = Math.min(start.getX(), end.getX());
		maxX = Math.max(start.getX(), end.getX());
		minY = Math.min(start.getY(), end.getY());
		maxY = Math.max(start.getY(), end.getY());	

		refresh();
		
	}

	private void refresh() {
		for(IShapes s : selectedList.getShapeList()) {
			s.deselect();
		}
		selectedList.emptyList();
		masterList.notifyObservers();
	}

	public void run() {
		for(IShapes shape : masterList.getShapeList()) {
			if(isSelected(shape)) {
				//shape.select();	
				selectedList.add(shape);
				selectedList.notifyObservers();
			}
		}		
	}

	private boolean isSelected(IShapes shape) {
		int shapeMinX = Math.min(shape.getStartX(), shape.getEndX());
		int shapeMinY = Math.min(shape.getStartY(), shape.getEndY());
		int shapeMaxX = Math.max(shape.getStartX(), shape.getEndX());
		int shapeMaxY = Math.max(shape.getStartY(), shape.getEndY());
		if(shapeMinX <= maxX 
				&& shapeMaxX >= minX
				&& shapeMinY <= maxY 
				&& shapeMaxY >= minY
		   ) return true;

		return false;
	}
}
