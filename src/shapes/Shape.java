package shapes;

import controller.interfaces.IShapeConfiguration;
import model.ShapeType;
import model.interfaces.IShapes;
import point.Point;

public class Shape implements IShapes {

	private int ht, wd;
	private Point start, end;
	private IShapeConfiguration shapeConfig;	
	private int dx, dy;

	boolean isSelected;

	public Shape (Point a, Point b, IShapeConfiguration config) {
		start = a;
		end = b;		
		ht = Math.abs(start.getY() - end.getY());
		wd = Math.abs(start.getX() - end.getX());			
		shapeConfig = config;
		isSelected = false;
	}

	public ShapeType getShapeType() { return shapeConfig.getShapeType(); }	

	public void select() { isSelected = true; }

	public void deselect() { isSelected = false; }

	@Override
	public void move(int x, int y) {
		dx = x; 
		dy = y;		
		Point newStart = new Point (start.getX()+dx, start.getX()+dy);
		Point newEnd = new Point(end.getX()+dx, end.getY()+dy);
		start = newStart;
		end = newEnd;
	}

	public IShapeConfiguration getShapeConfiguration() { return shapeConfig; }

	public int getHeight() { return ht; }	

	public int getWidth() { return wd; }	
	
	public Point getStart() { return start; }
	
	public Point getEnd() { return end; }

	public int getStartX() { return start.getX(); }

	public int getStartY() { return start.getY(); }

	public int getEndX() { return end.getX(); }

	public int getEndY() { return end.getY(); }
	
	public boolean isSelected() { return isSelected; } 
}
