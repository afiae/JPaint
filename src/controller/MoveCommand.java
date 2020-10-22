package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;

public class MoveCommand implements ICommand, IUndoable{

	private int dx, dy;
	IShapeList shapes;

	public MoveCommand(Point pressed, Point released, IShapeList shapes) {
		dx = released.getX() - pressed.getX();
		dy = released.getY() - pressed.getY();
		this.shapes = shapes;
		CommandHistory.add(this);
	}


	public void run() { 
		//if not same point by click
		if(!(dx == 0 && dy == 0)) { 
			for(IShapes s : shapes.getShapeList()) {
				if(s.isSelected()) { 
					s.move(dx, dy);
				}
			}
		}
		shapes.notifyObservers();
	}

	@Override
	public void undo() {
		//subtract dx and dy
		for(IShapes s : shapes.getShapeList()) {
			if(s.isSelected()) {
				s.move(-dx, -dy);
			}
		}
		shapes.notifyObservers();
	}

	@Override
	public void redo() {
		run();
	} 
}
