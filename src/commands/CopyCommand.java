package controller;

import controller.interfaces.ICommand;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class CopyCommand implements ICommand{

	private IShapeList clipboard, selectedList;

	public CopyCommand(IShapeList clipboard, IShapeList selectedList) {
		this.clipboard = clipboard; 
		this.selectedList = selectedList;
		clipboard.emptyList();
	}

	@Override
	public void run() {
		for(IShapes shape : selectedList.getShapeList()) {	
			clipboard.add(shape);
			
			//if(shape.isGroup()) System.out.println("added group");
			//else System.out.println("added shape");
		}
	}
}
