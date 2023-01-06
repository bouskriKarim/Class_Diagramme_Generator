package org.mql.java.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.reflection.PackageExplorer;

public class ClassDiagrammePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private Graphics g;


	public ClassDiagrammePanel() {
		
		
		//setLayout(new FlowLayout(FlowLayout.LEFT));
	
		

		setBackground(Color.white);
		PackageExplorer pe = new PackageExplorer("Bouskri Mohammed Karim-ClassParser");
		
		
		
		JScrollPane scrollBar = new JScrollPane(this);
		scrollBar.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		
		
		for (int i = 0; i < pe.getProject().getPackages().size(); i++) {
			
			for (int j = 0; j < pe.getProject().getPackages().get(i).getClasses().size(); j++) {
				
				this.add(new ClassPanel(pe.getProject().getPackages().get(i).getClasses().get(j)));
				
			}
			
		}
		
		
		int width = getPreferredSize().width;
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, width/2));

		
	}
	

	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		//drawGrid(g); 
		Component[] components = this.getComponents();
		
		
		
		for (Component component : components) {

			int x = component.getLocation().x;
			int y = component.getLocation().y;
			
			drawBorder( g ,component,x, y); 
			
			
		}
		
		
		

	}
	
	private void drawBorder(Graphics g ,Component component,int x, int y) 
	{
		
		g.setColor(Color.decode("#53b26a"));
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setStroke(new BasicStroke(2));
		
		g.drawLine(x+40, y+45, x+40, y+component.getSize().height-45);
		g.drawLine(x+component.getSize().width-41, y+45, x+component.getSize().width-41, y+component.getSize().height-45);
		
		g.drawLine(x+45, y+40, x+component.getSize().width-45, y+40);
		g.drawLine(x+45, y+component.getSize().height-41, x+component.getSize().width-45, y+component.getSize().height-41);
		
		
		g.drawArc(x+40, y+40, 10, 10,90, 90);
		g.drawArc(x+component.getSize().width-51, y+40, 10, 10, 0, 90);
		
		g.drawArc(x+40, y+component.getSize().height-51, 10, 10,180, 90);
		g.drawArc(x+component.getSize().width-51, y+component.getSize().height-51, 10, 10,270, 90);
	}
	
	private void drawGrid(Graphics g) 
	{
		
		g.setColor(Color.lightGray);
		
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		
		int horizontalLineNumber = height/20;
		
		int space = 20;
		for (int i = 0; i < height/space; i++) {
			g.drawLine(0, i*space, width, i*space);
		}
		
		for (int i = 0; i < width/space; i++) {
			g.drawLine(i*space, 0, i*space, height);
		}
	}
	


}
