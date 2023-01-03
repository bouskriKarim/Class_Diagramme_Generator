package org.mql.java.ui;


import java.awt.Color;
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
		

		
		setBackground(new Color(224, 255, 201));
		
		setLayout(new GridLayout(0, 1));
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		for (int i = 0; i < properties.size(); i++) {
			JLabel p = new JLabel("  "+getVisibility(properties.get(i).getVisibility()) + " "+ properties.get(i).getName() +" : " + properties.get(i).getType()+"  ");
			this.add(p);
		}
		
		
		
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
