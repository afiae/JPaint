package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.interfaces.ICommand;
import controller.interfaces.IShapeConfiguration;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import point.Point;
import shapes.ShapeConfiguration;
import shapes.ShapeList;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {
	private static PaintCanvasBase pcb;
	private static ApplicationState as;
	private static Point start;
	private static Point end;
	private static IShapeList shapeList;
	//private static IShapeList selected;

	public MouseHandler(PaintCanvasBase paintCanvas, ApplicationState AS, ShapeList SL) { 
		pcb = paintCanvas;
		as = AS;
		shapeList = SL;
	}

	@Override
	public void mousePressed ( MouseEvent e ) { 
		start = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased ( MouseEvent e ) { 
		end = new Point(e.getX(), e.getY());
		//selected = new SelectedShapeList(shapeList, pcb);
		ICommand cmd;
		
		switch(as.getActiveMouseMode()) {
		case DRAW:
			IShapeConfiguration config = new ShapeConfiguration(as.getActiveShapeType(), as.getActiveShapeShadingType(), 
																as.getActivePrimaryColor(), as.getActiveSecondaryColor());
			cmd = new CreateShapeCommand(start, end, config, shapeList);
			break;
		case MOVE:
			cmd = new MoveCommand(start, end, shapeList);
			break;
		case SELECT:
			cmd = new SelectCommand(start, end, shapeList, pcb);
			break;
		default:
			throw new Error("I didn't get that. Please try again");
		}
		cmd.run();
	}
}
