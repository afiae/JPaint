package shapes;

import java.util.ArrayList;

import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class GroupList implements IShapeList {

	private ArrayList<IShapes> group;
	
	
	public GroupList() {
		group = new ArrayList<IShapes>();
	}
	
	@Override
	public void add(IShapes shape) {
		group.add(shape);

	}

	@Override
	public void remove(IShapes shape) {
		group.remove(shape);

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void emptyList() {
		group.clear();
	}

	@Override
	public ArrayList<IShapes> getShapeList() {
		return group;
	}

}
