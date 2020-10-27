package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.interfaces.ICommand;
import controller.interfaces.IShapeConfiguration;
import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import point.Point;
import shapes.ShapeConfiguration;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {
	private PaintCanvasBase paintCanvas;
	private ApplicationState as;
	private Point start;
	private Point end;
	private IShapeList masterList;
	private IShapeList selectedList;

	public MouseHandler(PaintCanvasBase paintCanvas, ApplicationState as, IShapeList masterList, IShapeList selectedList) { 
		this.paintCanvas = paintCanvas;
		this.as = as;
		this.masterList= masterList;
		this.selectedList = selectedList;		
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
			cmd = new CreateShapeCommand(start, end, config, masterList);
			break;
		case MOVE:
			cmd = new MoveCommand(start, end, selectedList);
			break;
		case SELECT:
			cmd = new SelectCommand(start, end, masterList, selectedList, paintCanvas);
			break;
		default:
			throw new Error("I didn't get that. Please try again");
		}
		cmd.run();
	}
}
