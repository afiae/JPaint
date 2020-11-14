package commands;
import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import group.GroupComposite;
import lists.SimpleList;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;

public class GroupCommand implements ICommand, IUndoable {

	IShapeList selectedList, masterList, groupList, undoList;
	int minX, minY, maxX, maxY;
	Point start, end;
	IShapes group;


	public GroupCommand (IShapeList masterList, IShapeList selectedList) {
		this.selectedList = selectedList;		
		this.masterList = masterList;
		groupList = new SimpleList();
		undoList = new SimpleList();
		CommandHistory.add(this);
	}

	@Override
	public void run() {	
		if(selectedList.getShapeList().size() < 1 ) return;		
		group = getGroup(selectedList);		
		for(IShapes s: selectedList.getShapeList()) {
			masterList.remove(s);
		}
		selectedList.emptyList();
		selectedList.add(group);
		masterList.add(group);

		groupList.add(group);
		masterList.notifyObservers();
	}

	private GroupComposite getGroup(IShapeList list) {
		IShapes first = list.getShapeList().get(0);
		minX = Math.min(first.getEndX(), first.getStartX());
		maxX = Math.max(first.getEndX(), first.getStartX());
		minY = Math.min(first.getEndY(), first.getStartY());
		maxY = Math.max(first.getEndY(), first.getStartY());

		for(IShapes s: list.getShapeList()) {
			minX = Math.min(minX, Math.min(s.getEndX(), s.getStartX()));
			maxX = Math.max(maxX, Math.max(s.getEndX(), s.getStartX()));
			minY = Math.min(minY, Math.min(s.getEndY(), s.getStartY()));
			maxY = Math.max(maxY, Math.max(s.getEndY(), s.getStartY()));
		}		
		start = new Point(minX, maxY);
		end = new Point(maxX, minY);
		//group.select(); group constructor defaults to isSelected()
		return new GroupComposite(start, end, selectedList);
	}

	@Override
	public void undo() {		
		for(IShapes g: groupList.getShapeList()) {
			for(IShapes s: g.getGroupList().getShapeList()) {
				masterList.add(s);
				selectedList.add(s);
			}
			masterList.remove(g);
			selectedList.remove(g);
			undoList.add(g);
		}
		groupList.emptyList();
		masterList.notifyObservers();
	}

	@Override
	public void redo() {	
		for(IShapes g: undoList.getShapeList()) {		
			for(IShapes s: g.getGroupList().getShapeList()) {
				masterList.remove(s);
				selectedList.remove(s);
			}
			masterList.add(g);
			selectedList.add(g);
			groupList.add(g);
		}
		undoList.emptyList();
		masterList.notifyObservers();
	}
}

