package org.mql.java.ui.packagediagramme;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.ProjectModel;
import org.mql.java.xml.parser.ProjectSaxParser;

public class PackageDiagrammeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PackageDiagrammePanel pdp;
	
	public PackageDiagrammeFrame(ProjectModel project) {
		
		pdp = new PackageDiagrammePanel(project);	
		JScrollPane jsp = new JScrollPane(pdp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		ProjectSaxParser projectParser = new ProjectSaxParser("resources/XMLFile.xml");
		
		new PackageDiagrammeFrame(projectParser.getMyProject());
		
	}

}
