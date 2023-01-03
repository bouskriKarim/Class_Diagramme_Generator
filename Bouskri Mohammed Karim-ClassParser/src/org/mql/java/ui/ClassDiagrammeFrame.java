package org.mql.java.ui;




import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ClassDiagrammeFrame extends JFrame{


	private static final long serialVersionUID = 1L;
	private ClassDiagrammePanel cdp;
	
	public ClassDiagrammeFrame() {
		cdp = new ClassDiagrammePanel();
		setContentPane(cdp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public static void main(String[] args) {
		new ClassDiagrammeFrame();
	}

}
