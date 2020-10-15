package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IShapeConfiguration;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.Shape;


public class CreateShapeCommand implements ICommand, IUndoable{
	
	IShapes s;
	IShapeList shapeList;
	
	public CreateShapeCommand (Point start, Point end, IShapeConfiguration config, IShapeList sl) {
		shapeList = sl;
		s = new Shape(start, end, config);
		CommandHistory.add(this);
	}
	
	public void run() {	shapeList.add(s); }

	@Override
	public void undo() { shapeList.remove(s); }

	@Override
	public void redo() { run(); }

}
