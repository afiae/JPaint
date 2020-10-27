package shapes;

import java.awt.Color;

import controller.interfaces.IShapeConfiguration;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public class ShapeConfiguration implements IShapeConfiguration {

	ShapeType shape_type;
	Color primary, secondary;
	ShapeShadingType SST;	
	
	public ShapeConfiguration(ShapeType st, ShapeShadingType sst, ShapeColor pri, ShapeColor sec) {
		shape_type = st;
		primary = ShapeColor.getColor(pri);
		secondary = ShapeColor.getColor(sec);
		SST = sst;
	}	
	
	public ShapeType getShapeType() { return shape_type; }
	
	public ShapeShadingType getShapeShadingType() { return SST; } 
	
	public Color getPrimaryColor() { return primary; }
	
	public Color getSecondaryColor() { return secondary; }
	
	
	
	
}
