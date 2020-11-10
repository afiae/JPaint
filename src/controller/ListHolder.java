package controller;

import model.interfaces.IShapeList;
import shapes.CopiedList;
import shapes.SelectedShapeList;
import shapes.ShapeList;
import view.interfaces.PaintCanvasBase;

public class ListHolder {
	
	private IShapeList masterList;
	private IShapeList selectedList;
	private IShapeList clipboard;
	//private IShapeList allLists;
		
	public ListHolder(PaintCanvasBase paintCanvas) {
		masterList = new ShapeList(paintCanvas);
		selectedList = new SelectedShapeList(masterList);
		clipboard = new CopiedList();		
		//this isn't really used, however, the Decorator pattern is observed
		//allLists = new CopiedList(new SelectedShapeList( new ShapeList(paintCanvas)));
		
	}

	public IShapeList getMasterList() { return masterList; }
	public IShapeList getSelectedList() { return selectedList; }
	public IShapeList getClipboard() { return clipboard; }
}
