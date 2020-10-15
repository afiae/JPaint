package model;
import java.awt.Color;

public enum ShapeColor {
	BLACK,
	BLUE,
	CYAN,
	DARK_GRAY,
	GRAY,
	GREEN,
	LIGHT_GRAY,
	MAGENTA,
	ORANGE,
	PINK,
	RED,
	WHITE,
	YELLOW;

	public static Color getColor(Enum color) {
		if(color == BLACK) return Color.BLACK;
		if(color == BLUE) return Color.BLUE;
		if(color == CYAN) return Color.CYAN;
		if(color == DARK_GRAY) return Color.DARK_GRAY;
		if(color == GRAY) return Color.GRAY;
		if(color == GREEN) return Color.GREEN;
		if(color ==  LIGHT_GRAY) return Color.LIGHT_GRAY;
		if(color == MAGENTA) return Color.MAGENTA;
		if(color == ORANGE) return Color.ORANGE;
		if(color == PINK) return Color.PINK;
		if(color == RED) return Color.RED;
		if(color == WHITE) return Color.WHITE;
		if(color == YELLOW) return Color.YELLOW;
		return null;
	}
}
