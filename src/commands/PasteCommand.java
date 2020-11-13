package commands;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import group.GroupComposite;
import lists.GroupList;
import lists.PastedShapes;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.Shape;

public class PasteCommand implements IUndoable, ICommand {

	private IShapeList clipboard, masterList, pastedShapes;


	public PasteCommand(IShapeList clipboard, IShapeList masterList) {
		this.clipboard = clipboard;
		this.masterList = masterList;
		this.pastedShapes = new PastedShapes(masterList);
		CommandHistory.add(this);
	}

	@Override
	public void run() {
		for(IShapes shape : clipboard.getShapeList() ) {
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
		masterList.notifyObservers();
	}

	private IShapeList copyGroupList(IShapes group) {
		IShapeList oldGroup = group.getGroupList();
		IShapeList newGroup = new GroupList();

		for(IShapes s: oldGroup.getShapeList()) {
			if(s.isGroup()) {
				IShapes nGroup= new GroupComposite(s.getStart(), s.getEnd(), copyGroupList(s));
				nGroup.deselect();
				newGroup.add(nGroup);
			}
			else {
				IShapes newShape = new Shape(s.getStart(), s.getEnd(), s.getShapeConfiguration());
				newGroup.add(newShape);
			}
			
		}		
		return newGroup;
	}


	@Override
	public void undo() {
		for(IShapes s : pastedShapes.getShapeList()) { 
			masterList.remove(s); 
		}
		masterList.notifyObservers();
	}

	@Override
	public void redo() {
		for(IShapes s : pastedShapes.getShapeList()) { 
			masterList.add(s);
		}
		masterList.notifyObservers();
	}

}
