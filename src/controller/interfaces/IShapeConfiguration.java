package controller.interfaces;

import java.awt.Color;
import model.ShapeShadingType;
import model.ShapeType;

public interface IShapeConfiguration {
	public ShapeType getShapeType();
	public ShapeShadingType getShapeShadingType();
	public Color getPrimaryColor();
	public Color getSecondaryColor();
}
