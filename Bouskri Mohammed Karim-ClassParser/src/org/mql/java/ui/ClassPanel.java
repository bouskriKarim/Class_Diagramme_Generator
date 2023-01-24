package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.mql.java.models.ClassModel;

public class ClassPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel classNamePanel;
	
	public ClassPanel(ClassModel myClass) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		classNamePanel = new JPanel();
		classNamePanel.setBackground(Color.decode("#ceefe1"));
		JLabel className = new JLabel(isInterface(myClass));
		setName(myClass.getQualifiedName());
		Font font = new Font("Arial", Font.ITALIC|Font.BOLD, 15);
		className.setFont(font);
		classNamePanel.add(className);
		this.add(classNamePanel);
		
		this.add(new PropertiesPanel(myClass.getProperties()));

		
		
		classNamePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#53b26a")));
		

		CompoundBorder compoundBorder = new CompoundBorder(new EmptyBorder(40, 40, 40, 40),new LineBorder(Color.white, 1, true));
		
		setBorder(compoundBorder);
		
		setBackground(new Color(0,0,0,0));
		
		this.add(new MethodesPanel(myClass.getMethodes()));
		
	}
	
	public static String isInterface(ClassModel myClass) 
	{
		Class<?> cls ;
		try {
			cls = Class.forName(myClass.getQualifiedName());
			
			if(cls.isInterface()) 
			{
				return "<< interface >>\n"+new JLabel("\n").getName() + myClass.getName();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERREUR : "+e.getMessage());
		}
		
		return myClass.getName();
		
		
	}
	
}
