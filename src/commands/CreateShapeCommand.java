package commands;

import controller.interfaces.ICommand;
import controller.interfaces.IShapeConfiguration;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.Shape;


public class CreateShapeCommand implements ICommand, IUndoable{

	IShapes s;
	IShapeList masterList;

	public CreateShapeCommand (Point start, Point end, IShapeConfiguration config, IShapeList masterList) {
		this.masterList = masterList;
		s = new Shape(start, end, config);
		CommandHistory.add(this);
	}

	public void run() {	
		masterList.add(s);
		masterList.notifyObservers();

	}

	@Override
	public void undo() { 
		masterList.remove(s); 
		masterList.notifyObservers();
	}

	@Override
	public void redo() { run(); }

}
