package controller;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.GroupComposite;

public class GroupCommand implements ICommand, IUndoable {

	IShapeList selectedList, masterList;
	int minX, minY, maxX, maxY;
	Point start, end;
	IShapes group;
	
	
	public GroupCommand (IShapeList masterList, IShapeList selectedList) {
		this.selectedList = selectedList;		
		this.masterList = masterList;
		CommandHistory.add(this);
	}
	
	@Override
	public void run() {		
		IShapes first = selectedList.getShapeList().get(0);
		minX = Math.min(first.getEndX(), first.getStartX());
		maxX = Math.max(first.getEndX(), first.getStartX());
		minY = Math.min(first.getEndY(), first.getStartY());
		maxY = Math.max(first.getEndY(), first.getStartY());
		
		for(IShapes s: selectedList.getShapeList()) {
			minX = Math.min(minX, Math.min(s.getEndX(), s.getStartX()));
			maxX = Math.max(maxX, Math.max(s.getEndX(), s.getStartX()));
			minY = Math.min(minY, Math.min(s.getEndY(), s.getStartY()));
			maxY = Math.max(maxY, Math.max(s.getEndY(), s.getStartY()));
		}
		
		start = new Point(minX, maxY);
		end = new Point(maxX, minY);	
		group = new GroupComposite(start, end, selectedList);
		//group.select(); group constructor defaults to isSelected()
		for(IShapes s: selectedList.getShapeList()) {
			masterList.remove(s);
		}
		selectedList.emptyList();
		selectedList.add(group);
		masterList.add(group); // should set call to update canvas
		CommandHistory.add(this);		
	}
	
	@Override
	public void undo() {
		for(IShapes s : group.getGroupList().getShapeList()) {
			masterList.add(s);
			selectedList.add(s);
		}
		group.getGroupList().emptyList();
		masterList.remove(group);
		selectedList.remove(group);	
	}
	
	@Override
	public void redo() {
		run();		
	}

}

