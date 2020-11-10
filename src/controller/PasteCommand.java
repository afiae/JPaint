package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.GroupComposite;
import shapes.GroupList;
import shapes.PastedShapes;
import shapes.Shape;

public class PasteCommand implements IUndoable, ICommand {

	private IShapeList clipboard, masterList, pastedShapes;


	public PasteCommand(IShapeList clipboard, IShapeList masterList) {
		this.clipboard = clipboard;
		this.masterList = masterList;
		this.pastedShapes = new PastedShapes(masterList);	
	}

	@Override
	public void run() {
		for(IShapes shape : clipboard.getShapeList() ) {
			//offset start and end points 
			//Point start = new Point(shape.getStartX()+50, shape.getStartY()+50);
			//Point end  = new Point(shape.getEndX()+50, shape.getEndY()+50);

			Point start = shape.getStart();
			Point end = shape.getEnd();
			IShapes offsetShape; 
			
			if(shape.isGroup()) {
				offsetShape = new GroupComposite(start, end, copyGroupList(shape));
				offsetShape.deselect();
			}
			else {
				offsetShape = new Shape(start, end, shape.getShapeConfiguration());
			}
			
			offsetShape.move(50, 50);
			pastedShapes.add(offsetShape);
			masterList.add(offsetShape);	
		}
		CommandHistory.add(this);
	}

	private IShapeList copyGroupList(IShapes group) {
		IShapeList oldGroup = group.getGroupList();
		IShapeList newGroup = new GroupList();
		
		for(IShapes s: oldGroup.getShapeList()) {
			IShapes newShape = new Shape(s.getStart(), s.getEnd(), s.getShapeConfiguration());
			newGroup.add(newShape);
		}		
		return newGroup;
	}
	
	
	@Override
	public void undo() {
		for(IShapes s : pastedShapes.getShapeList()) { 
			masterList.remove(s); 
		}
	}

	@Override
	public void redo() {
		for(IShapes s : pastedShapes.getShapeList()) { 
			masterList.add(s);
		}
	}

}
