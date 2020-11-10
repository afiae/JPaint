package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;

public class MoveCommand implements ICommand, IUndoable{

	private int dx, dy;
	private IShapeList selectedList;

	public MoveCommand(Point pressed, Point released, IShapeList selectedList) {
		dx = released.getX() - pressed.getX();
		dy = released.getY() - pressed.getY();
		this.selectedList = selectedList;
		CommandHistory.add(this);
	}

	public void run() { 
		//if not same point by click
		if(!(dx == 0 && dy == 0)) { 
			for(IShapes s : selectedList.getShapeList()) {
				s.move(dx, dy);
			}
			selectedList.notifyObservers();
		}
		return;
	}

	@Override
	public void undo() {
		//subtract dx and dy
		for(IShapes s : selectedList.getShapeList()) {
			s.move(-dx, -dy);
		}
		selectedList.notifyObservers();
	}

	@Override
	public void redo() {
		for(IShapes s: selectedList.getShapeList()) {
			s.move(dx, dy);
		}
		selectedList.notifyObservers();
	} 
}
