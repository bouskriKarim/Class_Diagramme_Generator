package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.models.ClassModel;

public class ClassPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel classNamePanel;
	

	

	public ClassPanel(ClassModel myClass) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		
		classNamePanel = new JPanel();
		classNamePanel.setBackground(new Color(224, 255, 201));
		
		JLabel className = new JLabel(myClass.getName());
		classNamePanel.add(className);
		this.add(classNamePanel);

		this.add(new PropertiesPanel(myClass.getProperties()));

		
		
		classNamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		

		setBorder(new LineBorder(Color.black, 1, true));
		
		//this.add(new MethodesPanel(myClass.getMethodes()));
		
	}

}
