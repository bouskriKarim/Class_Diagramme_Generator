package org.mql.java.ui.packagediagramme;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import org.mql.java.application.MenuListener;
import org.mql.java.models.ProjectModel;

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
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Package Diagramme");
		JMenuItem toClassDiagramme = new JMenuItem("Switch to Class Diagramme");
		toClassDiagramme.addActionListener(new MenuListener(true,project));
		menu.add(toClassDiagramme);
		bar.add(menu);
		setJMenuBar(bar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}


}
