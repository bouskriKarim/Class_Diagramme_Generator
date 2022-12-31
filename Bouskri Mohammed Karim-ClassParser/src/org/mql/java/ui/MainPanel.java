package org.mql.java.ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.mql.java.reflection.ClassParser;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SquelettePanel squelttePanel;
	private InformationPanel infoPanel;
	

	public MainPanel(ClassParser cp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(squelttePanel = new SquelettePanel(cp.getMyClass()));
		add(infoPanel = new InformationPanel(cp)); 
	}

}
