package model.interfaces;

import controller.interfaces.IShapeConfiguration;


public interface IShapes {

	public int getHeight();
	public int getWidth();
	public int getStartX();
	public int getStartY();
	public int getEndX();
	public int getEndY();
	public IShapeConfiguration getShapeConfiguration();
	
	public void select();
	public void deselect();
	public boolean isSelected();
	
	public void move(int x, int y);

}
