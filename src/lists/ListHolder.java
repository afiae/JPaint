package lists;

import model.interfaces.IShapeList;
import view.interfaces.PaintCanvasBase;

public class ListHolder {
	
	private IShapeList masterList;
	private IShapeList selectedList;
	private IShapeList clipboard;
	//private IShapeList allLists;
		
	public ListHolder(PaintCanvasBase paintCanvas) {
		masterList = new ShapeList(paintCanvas);
		selectedList = new SelectedShapeList(masterList);
		clipboard = new SimpleList();		
		//this isn't really used, however, the Decorator pattern is observed
		//allLists  = new SelectedShapeList( new ShapeList(paintCanvas)));
		
	}

	public IShapeList getMasterList() { return masterList; }
	public IShapeList getSelectedList() { return selectedList; }
	public IShapeList getClipboard() { return clipboard; }
}