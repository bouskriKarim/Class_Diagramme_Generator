package org.mql.java.ui;

import javax.swing.JFrame;

import org.mql.java.reflection.ClassParser;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	
	public MainFrame(ClassParser cp) {
		
		mainPanel = new MainPanel(cp);
		setContentPane(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
