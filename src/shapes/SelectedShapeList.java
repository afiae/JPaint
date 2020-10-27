package shapes;

import java.util.ArrayList;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class SelectedShapeList implements IShapeList{

	private ArrayList<IShapes> selectedShapes;
	private IShapeList masterList;

	public SelectedShapeList( IShapeList masterList ) {
		selectedShapes = new ArrayList<IShapes>();	
		this.masterList = masterList;
	}

	@Override
	public void add(IShapes shape) {
		shape.select();
		selectedShapes.add(shape);
		notifyObservers();
	}

	@Override
	public void remove(IShapes shape) {
		shape.deselect();
		selectedShapes.remove(shape);
		notifyObservers();
	}

	@Override
	public void notifyObservers() {
		masterList.notifyObservers();
	}

	@Override
	public ArrayList<IShapes> getShapeList() {
		return selectedShapes;
	}

	@Override
	public void emptyList() {
		selectedShapes.clear();		
	}
}
