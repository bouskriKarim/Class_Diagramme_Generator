package org.mql.java.ui.packagediagramme;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.models.ProjectModel;

public class PackageDiagrammePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PackageDiagrammePanel(ProjectModel project) {
		
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		for (int i = 0; i <project.getPackages().size(); i++) {
			
			add(new PackagePanel(project.getPackages().get(i)));
			
		}
		setBackground(Color.white);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 drawGrid(g); 
	}
	
	private void drawGrid(Graphics g) 
	{
		g.setColor(Color.lightGray);
		
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		
		int space = 20;
		for (int i = 0; i < height/space; i++) g.drawLine(0, i*space, width, i*space);
		
		for (int i = 0; i < width/space; i++) {
			g.drawLine(i*space, 0, i*space, height);
		}
	}
	
}
