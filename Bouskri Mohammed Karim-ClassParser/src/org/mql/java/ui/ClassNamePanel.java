package org.mql.java.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.reflection.ClassParser;

public class ClassNamePanel extends JPanel {
	
	private JLabel label;
	private JTextField textField;
	private JButton button;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassNamePanel() {
		
		setLayout(new FlowLayout(FlowLayout.LEFT));

		label = new JLabel("insert Class Name : ");
		textField = new JTextField(20);
		button = new JButton("Generate");
		
		button.addActionListener(new BtnListener());
		
		add(label);
		add(textField);
		add(button);
		
		EtchedBorder border = new EtchedBorder();
		setBorder(new TitledBorder(border,"Class Name"));
		
	}
	
	public String getText() 
	{
		return textField.getText();
	}
	
	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			MainFrame main = new MainFrame(new ClassParser(getText()));
			main.show();
			
		}
		
	}
	


}
