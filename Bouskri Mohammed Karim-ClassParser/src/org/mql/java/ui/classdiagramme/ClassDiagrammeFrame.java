package org.mql.java.ui.classdiagramme;



import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.ProjectModel;
import org.mql.java.xml.parser.ProjectSaxParser;

public class ClassDiagrammeFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	private ClassDiagrammePanel cdp;
	
	public ClassDiagrammeFrame(ProjectModel project) {
		cdp = new ClassDiagrammePanel(project);
		JScrollPane jsp = new JScrollPane(cdp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

}
