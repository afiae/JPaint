package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import model.persistence.ApplicationState;
import shapes.ShapeList;
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
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        
        //create new ShapeList and pass to MouseHandler
        ShapeList SL = new ShapeList(paintCanvas);
		paintCanvas.addMouseListener(new MouseHandler(paintCanvas, appState, SL));
    }
}
