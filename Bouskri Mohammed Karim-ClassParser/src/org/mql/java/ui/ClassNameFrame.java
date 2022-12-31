package org.mql.java.ui;

import javax.swing.JFrame;

public class ClassNameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassNamePanel panel;
	
	public ClassNameFrame() {
		panel = new ClassNamePanel();
		setContentPane(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,130);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ClassNameFrame();
	}

}
