package org.mql.java.ui.packagediagramme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.models.PackageModel;
import org.mql.java.ui.classdiagramme.ClassPanel;

public class PackagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PackagePanel(PackageModel myPackage) {
		
		EtchedBorder border = new EtchedBorder(Color.black, Color.black);
		setBorder(new TitledBorder(border,myPackage.getQualifiedName()));
		
		//setBorder(new LineBorder(Color.black, 3));
		
		
		for (int i = 0; i < myPackage.getClasses().size(); i++) {
			
			add(new ClassPanel(myPackage.getClasses().get(i)));
			
		}
		int width = getPreferredSize().width;
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width-100, (width/2)+270));
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0; i<this.getComponentCount(); i++) 
		{
			drawBorder(g, this.getComponent(i));
		}
		
		
	}
	
	private void drawBorder(Graphics g,Component component) 
	{
		int x = component.getX();
		int y = component.getY();
		
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

}
