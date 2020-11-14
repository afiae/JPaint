package commands;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import lists.SimpleList;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class DeleteCommand implements ICommand, IUndoable {
	
	private IShapeList masterList, selectedList, deletedList;
	
	public DeleteCommand(IShapeList masterList, IShapeList selectedList) {
		this.masterList = masterList;
		this.selectedList = selectedList;
		this.deletedList = new SimpleList();
		CommandHistory.add(this);
	}
	
	@Override
	public void run() {
		for(IShapes shape : selectedList.getShapeList()) {
			masterList.remove(shape);
			deletedList.add(shape);
		}
		selectedList.emptyList();
		masterList.notifyObservers();
	}

	@Override
	public void undo() {
		for(IShapes shape : deletedList.getShapeList()) {
			masterList.add(shape);
			selectedList.add(shape);
		}
		deletedList.emptyList();
		masterList.notifyObservers();
	}
	
	@Override
	public void redo() {
		run();
	}


}