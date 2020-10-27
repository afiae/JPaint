package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.PastedShapes;
import shapes.Shape;

public class PasteCommand implements IUndoable, ICommand {

	private IShapeList clipboard, masterList, pastedShapes;
	

	public PasteCommand(IShapeList clipboard, IShapeList masterList) {
		this.clipboard = clipboard;
		this.masterList = masterList;
		pastedShapes = new PastedShapes(masterList);		
		CommandHistory.add(this);
	}

	@Override
	public void run() {
		for(IShapes shape : clipboard.getShapeList() ) {
			//offset start and end points 
			Point start = new Point(shape.getStartX()+20, shape.getStartY()+20);
			Point end  = new Point(shape.getEndX()+20, shape.getEndY()+20);
			IShapes offsetShape = new Shape(start, end, shape.getShapeConfiguration());
			
			pastedShapes.add(offsetShape);
			masterList.add(offsetShape);

		}
		//masterList.notifyObservers();
	}

	@Override
	public void undo() {
		for(IShapes s : pastedShapes.getShapeList()) {
			masterList.remove(s);
		}
		pastedShapes.emptyList();;
	}

	@Override
	public void redo() {
		run();
	}

}
