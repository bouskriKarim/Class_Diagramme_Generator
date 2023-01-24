package org.mql.java.ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mql.java.models.PropertyModel;

public class PropertiesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertiesPanel(List<PropertyModel> properties)  {
		
		add(new JLabel("\n"));

		
		setBackground(Color.decode("#ceefe1"));
		
		setLayout(new GridLayout(0, 1));
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#53b26a")));
		Font font = new Font("Arial",7,15);
		
		for (int i = 0; i < properties.size(); i++) {
			JLabel p = new JLabel("  "+getVisibility(properties.get(i).getVisibility()) + " "+ properties.get(i).getName() +" : " + properties.get(i).getType()+"  ");
			p.setFont(font);
			this.add(p);
		}
		add(new JLabel("\n"));
		
		
	}
	private String getVisibility(String visibility) 
	{
		if(visibility.contains("public"))
		{
			return "+";
		}
		else if(visibility.contains("private")) 
		{
			return "-";
		}
		return "*";
	}
}
