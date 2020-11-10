package model.interfaces;

import controller.interfaces.IShapeConfiguration;
import point.Point;

public interface IShapes {
	public int getHeight();
	public int getWidth();
	
	public int getStartX();
	public int getStartY();
	public int getEndX();
	public int getEndY();
	
	public Point getStart();
	public Point getEnd();
	
	public IShapeConfiguration getShapeConfiguration();	
	
	public void select();
	public void deselect();
	public boolean isSelected();	
	
	public void move(int dx, int dy);
	
	public boolean isGroup();
	public IShapeList getGroupList();
}
