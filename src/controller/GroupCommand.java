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
		int minX = Math.min(first.getEndX(), first.getStartX());
		int maxX = Math.max(first.getEndX(), first.getStartX());
		int minY = Math.min(first.getEndY(), first.getStartY());
		int maxY = Math.max(first.getEndY(), first.getStartY());		
		
		for(IShapes s: selectedList.getShapeList()) {
			minX = Math.min(minX, Math.min(s.getEndX(), s.getStartX()));
			minY = Math.min(minY, Math.min(s.getEndY(), s.getStartY()));
			maxX = Math.max(maxX, Math.max(s.getEndX(), s.getStartX()));
			maxY = Math.max(minY, Math.max(s.getEndY(), s.getStartY()));
		}
		
		start = new Point(minX, minY);
		end = new Point(maxX, maxY);		
		group = new GroupComposite(start, end, selectedList);
		group.select();
		for(IShapes s: selectedList.getShapeList()) {
			masterList.remove(s);
		}
		selectedList.emptyList();
		selectedList.add(group);
		masterList.add(group);
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
