package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class DeleteCommand implements ICommand, IUndoable {
	
	private IShapeList masterList, selectedList;
	
	public DeleteCommand(IShapeList masterList, IShapeList selectedList) {
		this.masterList = masterList;
		this.selectedList = selectedList;
		CommandHistory.add(this);
	}
	
	@Override
	public void run() {
		for(IShapes shape : selectedList.getShapeList())
			masterList.remove(shape);
	}

	@Override
	public void undo() {
		for(IShapes shape : selectedList.getShapeList()) {
			masterList.add(shape);
		}
	}
	
	@Override
	public void redo() {
		run();
	}


}