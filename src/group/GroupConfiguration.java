package group;

import java.awt.Color;

import controller.interfaces.IShapeConfiguration;
import model.ShapeShadingType;
import model.ShapeType;

public class GroupConfiguration implements IShapeConfiguration {

	@Override
	public ShapeType getShapeType() {
		// TODO Auto-generated method stub
		return ShapeType.RECTANGLE;
	}

	@Override
	public ShapeShadingType getShapeShadingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getPrimaryColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getSecondaryColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
