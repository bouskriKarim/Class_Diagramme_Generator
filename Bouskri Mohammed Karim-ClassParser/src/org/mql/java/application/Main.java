package org.mql.java.application;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import org.mql.java.models.ProjectModel;
import org.mql.java.ui.classdiagramme.ClassDiagrammePanel;
import org.mql.java.xml.parser.ProjectSaxParser;

public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main(ProjectModel project) {
		ClassDiagrammePanel cdp = new ClassDiagrammePanel(project);
		JScrollPane jsp = new JScrollPane(cdp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Class Diagramme");
		JMenuItem toPackageDiagramme = new JMenuItem("Switch to Package Diagramme");
		toPackageDiagramme.addActionListener(new MenuListener(false,project));
		menu.add(toPackageDiagramme);
		bar.add(menu);
		setJMenuBar(bar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		ProjectSaxParser projectParser = new ProjectSaxParser("resources/XMLFile.xml");
		
		new Main(projectParser.getMyProject());
	}
	
}
