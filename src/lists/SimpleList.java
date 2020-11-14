package lists;

import java.util.ArrayList;

import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class SimpleList implements IShapeList {
	
	private ArrayList<IShapes> list;
	
	public SimpleList() {
		list = new ArrayList<IShapes>();
	}

	@Override
	public void add(IShapes shape) {
		list.add(shape);
	}

	@Override
	public void remove(IShapes shape) {
		list.remove(shape);
	}

	@Override
	public void notifyObservers() {
		
	}

	@Override
	public ArrayList<IShapes> getShapeList() {
		return list;
	}

	@Override
	public void emptyList() {
		list.clear();		
	}

}
