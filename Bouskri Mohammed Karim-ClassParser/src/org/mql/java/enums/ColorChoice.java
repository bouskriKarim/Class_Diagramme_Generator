package org.mql.java.enums;

import java.awt.Color;

public enum ColorChoice {
	//rgb(105, 240, 174)
	LIGHTBLUE(new Color(224, 247, 250));
	
	private Color color;
	
	ColorChoice(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	
	public void setColor(Color color) {
		this.color = color;
	}
}
