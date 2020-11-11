package shapes;

import java.util.ArrayList;

import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class CopiedList implements IShapeList {
	
	private ArrayList<IShapes> clipboard;
	
	public CopiedList() {
		clipboard = new ArrayList<IShapes>();
	}

	@Override
	public void add(IShapes shape) {
		clipboard.add(shape);
	}

	@Override
	public void remove(IShapes shape) {
		clipboard.remove(shape);
	}

	@Override
	public void notifyObservers() {
		
	}

	@Override
	public ArrayList<IShapes> getShapeList() {
		return clipboard;
	}

	@Override
	public void emptyList() {
		clipboard.clear();		
	}

}
