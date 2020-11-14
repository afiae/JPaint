package group;

import controller.interfaces.IShapeConfiguration;
import lists.SimpleList;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;

public class GroupComposite implements IShapes {
	
	private IShapeList group;
	//private IShapeList selectedList;
	private IShapeConfiguration config;
	private int ht, wd;
	private Point start, end;
	private boolean isSelected;

	
	public GroupComposite (Point a, Point b, IShapeList selectedList) {
		isSelected = true;
		start = a;
		end = b;
		ht = Math.abs(start.getY() - end.getY());
		wd = Math.abs(start.getX() - end.getX());
		
		config = new GroupConfiguration();
		//this.selectedList = selectedList;		
		
		group = new SimpleList();
		for(IShapes s: selectedList.getShapeList()) {
			group.add(s);
		}		
	}


	@Override
	public IShapeList getGroupList() { return group; }
	
	@Override
	public boolean isGroup() { return true; }
	
		
	@Override
	public int getHeight() { return ht; }

	@Override
	public int getWidth() { return wd; }

	@Override
	public int getStartX() { return start.getX(); }

	@Override
	public int getStartY() { return start.getY(); }

	@Override
	public int getEndX() { return end.getX(); }

	@Override
	public int getEndY() { return end.getY(); }

	@Override
	public Point getStart() { return start; }

	@Override
	public Point getEnd() { return end; }

	@Override
	public IShapeConfiguration getShapeConfiguration() { return config; }

	@Override
	public void select() { isSelected = true; }

	@Override
	public void deselect() { isSelected = false; }

	@Override
	public boolean isSelected() { return isSelected; }

	@Override
	public void move(int dx, int dy) {
		Point newStart = new Point (start.getX()+dx, start.getY()+dy);
		Point newEnd = new Point(end.getX()+dx, end.getY()+dy);
		start = newStart;
		end = newEnd;		
		for(IShapes s: group.getShapeList()) {
			s.move(dx, dy);
		}
	}	
}
