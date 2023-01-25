package org.mql.java.application;



import org.mql.java.ui.classdiagramme.ClassDiagrammeFrame;
import org.mql.java.xml.parser.ProjectSaxParser;

public class Main{

	public Main(String xmlFileName) {
		
		ProjectSaxParser projectParser = new ProjectSaxParser("resources/XMLFile.xml");
		new ClassDiagrammeFrame(projectParser.getMyProject());
	}
	
	
	public static void main(String[] args) {
		new Main("resources/XMLFile.xml");
	}
	
}
