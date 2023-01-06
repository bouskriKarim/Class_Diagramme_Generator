package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	
	public GridPanel(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.lightGray);
		
		int space = 20;
		for (int i = 0; i < height/space; i++) {
			g.drawLine(0, i*space, width, i*space);
		}
		
		for (int i = 0; i < width/space; i++) {
			g.drawLine(i*space, 0, i*space, height);
		}
	}

}
