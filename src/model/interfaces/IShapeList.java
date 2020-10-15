package model.interfaces;
import java.util.ArrayList;

public interface IShapeList {
	public void add(IShapes shape);
	public void remove(IShapes shape);
	public void notifyObservers();
	public ArrayList<IShapes> getShapeList();
}
