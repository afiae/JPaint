package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;

public class MoveCommand implements ICommand, IUndoable{

	private int dx, dy;
	IShapeList selectedShapes;


	public MoveCommand(Point pressed, Point released, IShapeList selected) {
		dx = released.getX() - pressed.getX();
		dy = released.getY() - pressed.getY();
		selectedShapes = selected;

	}


	public void run() { 
		//if same point by click
		if(!(dx == 0 && dy == 0)) { 
			for(IShapes s : selectedShapes.getShapeList()) {
				s.move(dx, dy);
			}
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		//subtract dx and dy
		for(IShapes s : selectedShapes.getShapeList()) {
			s.move(-dx, -dy);
		}
	}

	@Override
	public void redo() {
		run();
	} 
}
