package org.mql.java.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledLabel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabeledLabel(String label, String result) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) 
		{
			label = label + " : ";
		}
		JLabel l1 = new JLabel(label);
		JLabel l2 = new JLabel(result);
		add(l1);
		add(l2);
	}
	

}
