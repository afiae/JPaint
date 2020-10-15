package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import controller.interfaces.IReset;
import view.interfaces.PaintCanvasBase;

public class CanvasReset implements IReset {
	
	PaintCanvasBase paintCanvas;
	
	public CanvasReset(PaintCanvasBase pcb) { paintCanvas = pcb; }
	
	public void reset() {
		Graphics2D g = paintCanvas.getGraphics2D();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);				
		//System.out.println("reset complete");
	}
	

}
