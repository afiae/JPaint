package commands;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import lists.GroupList;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class UngroupCommand implements ICommand, IUndoable {

	private IShapeList selectedList, masterList;
	private IShapeList formerGroup;

	public UngroupCommand(IShapeList masterList, IShapeList selectedList) {
		this.selectedList = selectedList;
		this.masterList = masterList;
		formerGroup = new GroupList();
		CommandHistory.add(this);
	}

	@Override
	public void run() {
		int size = selectedList.getShapeList().size();
		for(int i = size-1; i >= 0; i--) {
			IShapes s = selectedList.getShapeList().get(i);
			if(s.isGroup()) {
				formerGroup.add(s);
				selectedList.remove(s);
				masterList.remove(s);
			}
		}

		//formerGroup should only have groups
		for(IShapes g: formerGroup.getShapeList()) {
			for(IShapes s: g.getGroupList().getShapeList()) {
				selectedList.add(s);
				masterList.add(s);
			}
		}
		masterList.notifyObservers();
	}

	@Override
	public void undo() { 
		//all shapes in formerGroup should be groups only
		if(formerGroup.getShapeList().size() <= 0) return;
		
		for(IShapes g: formerGroup.getShapeList()) {
			for(IShapes s: g.getGroupList().getShapeList()) {
				masterList.remove(s);
				selectedList.remove(s);
			}
			masterList.add(g);
			selectedList.add(g);
		}
		masterList.notifyObservers();
	}

	@Override
	public void redo() {
		run();
	}

}
