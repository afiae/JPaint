package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.GroupComposite;
import shapes.GroupList;

public class UngroupCommand implements ICommand, IUndoable {

	private IShapeList selectedList, masterList;
	private IShapeList formerGroup;

	public UngroupCommand(IShapeList masterList, IShapeList selectedList) {
		this.selectedList = selectedList;
		this.masterList = masterList;
		formerGroup = new GroupList();
	}

	@Override
	public void run() {
		int size = selectedList.getShapeList().size();
		for(int i = size-1; i >= 0; i--) {
			IShapes s = selectedList.getShapeList().get(i);
			if(s.isGroup()) {
				for(IShapes shape: s.getGroupList().getShapeList()) {
					selectedList.add(shape);
					masterList.add(shape);
					formerGroup.add(shape);
				
				}
				selectedList.remove(s);
				masterList.remove(s);
				break;
			}
			else continue;
		}
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		IShapes first = formerGroup.getShapeList().get(0);
		int minX = Math.min(first.getEndX(), first.getStartX());
		int maxX = Math.max(first.getEndX(), first.getStartX());
		int minY = Math.min(first.getEndY(), first.getStartY());
		int maxY = Math.max(first.getEndY(), first.getStartY());
		
		for(IShapes s : formerGroup.getShapeList()) {
			minX = Math.min(minX, Math.min(s.getEndX(), s.getStartX()));
			minY = Math.min(minY, Math.min(s.getEndY(), s.getStartY()));
			maxX = Math.max(maxX, Math.max(s.getEndX(), s.getStartX()));
			maxY = Math.max(minY, Math.max(s.getEndY(), s.getStartY()));
		}
		
		Point start = new Point(minX, minY);
		Point end = new Point(maxX, maxY);		
		IShapes group = new GroupComposite(start, end, formerGroup);
		
		for(IShapes s: formerGroup.getShapeList()) {
			masterList.remove(s);
		}
		
		selectedList.add(group);
		masterList.add(group);
		
		formerGroup.emptyList();
	}

	@Override
	public void redo() {
		run();
	}

}
