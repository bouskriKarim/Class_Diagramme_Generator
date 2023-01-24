package org.mql.java.ui.packagediagramme;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.PackageModel;
import org.mql.java.xml.parser.ProjectSaxParser;

public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main(PackageModel p) {
		PackagePanel pdp = new PackagePanel(p);
		
		JScrollPane jsp = new JScrollPane(pdp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);
		//setContentPane(pdp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ProjectSaxParser projectParser = new ProjectSaxParser("resources/XMLFile.xml");
		
		new Main(projectParser.getMyProject().getPackages().get(3));
	}

}
