package org.mql.java.ui;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mql.java.models.MethodModel;

public class MethodesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MethodesPanel(List<MethodModel> methodes)  {
		
		setLayout(new GridLayout(0, 1));
		setBackground(new Color(224, 255, 201));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		for (int i = 0; i < methodes.size(); i++) {
			JLabel p = new JLabel("  "+getVisibility(methodes.get(i).getVisibility()) + " "+ methodes.get(i).getName() +"() : " + methodes.get(i).getType()+"  ");
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
