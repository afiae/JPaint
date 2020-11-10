package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.ListHolder;
import controller.MouseHandler;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;


public class Main {
	public static void main(String[] args){
		PaintCanvasBase paintCanvas = new PaintCanvas();
		IGuiWindow guiWindow = new GuiWindow(paintCanvas);
		IUiModule uiModule = new Gui(guiWindow);
		ApplicationState appState = new ApplicationState(uiModule);	
		
		//instantiates a ShapeList, SelectedList, and CopiedList
		ListHolder listHolder = new ListHolder(paintCanvas);
		IJPaintController controller = new JPaintController(uiModule, appState, listHolder);
		controller.setup();

		paintCanvas.addMouseListener(new MouseHandler(paintCanvas, appState, 
										listHolder.getMasterList(), listHolder.getSelectedList()));
	}
}
