package controller;

import java.awt.Color;
import java.util.EnumMap;
import model.ShapeColor;

public class ColorSingleton {
	
	private static ColorSingleton instance = new ColorSingleton();
	private EnumMap<ShapeColor, Color> colorMap;
	
	private ColorSingleton() {
		colorMap = new EnumMap<ShapeColor, Color>(ShapeColor.class);		
		colorMap.put(ShapeColor.BLACK, Color.BLACK);
		colorMap.put(ShapeColor.BLUE, Color.BLUE);
		colorMap.put(ShapeColor.CYAN, Color.CYAN);
		colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
		colorMap.put(ShapeColor.GRAY, Color.GRAY);
		colorMap.put(ShapeColor.GREEN, Color.GREEN);
		colorMap.put(ShapeColor.LIGHT_GRAY, Color.GRAY);
		colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
		colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
		colorMap.put(ShapeColor.PINK, Color.PINK);
		colorMap.put(ShapeColor.RED, Color.RED);
		colorMap.put(ShapeColor.WHITE, Color.WHITE);
		colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
	}
	
	public static Color getColor(ShapeColor sColor) {
		return instance.retrieve(sColor);
	}
	
	private Color retrieve(ShapeColor sColor) {
		return colorMap.get(sColor);
	}

}
