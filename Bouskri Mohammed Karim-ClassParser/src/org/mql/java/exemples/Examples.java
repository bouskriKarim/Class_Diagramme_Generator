package org.mql.java.exemples;


import org.mql.java.reflection.ClassParser;
import org.mql.java.reflection.PackageExplorer;
import org.mql.java.xml.parser.ProjectSaxParser;
import org.mql.java.models.User;
import org.mql.java.reflection.ClassExplorer;

public class Examples {

	public Examples() {
		exp07();
		//System.out.println(System.getProperty("java.class.path"));
		}
	
	
	//Affichage dans la console
	private void exp01() {
		ClassParser cls = new ClassParser("org.mql.java.enums.TestInterface");
		//System.out.println(cls.getMyInterface().getMethods().get(0).getParameters().get(0).getType());
	}
	
	//Afficage dans l'interface Swing
	private void exp02() {
		ClassParser cls = new ClassParser("");
		cls.showInSwing();

	}
	
	//Affichage des nom des classes dans un package
	private void exp03() 
	{
		ClassExplorer p = new ClassExplorer("org.mql.java.ui");
		int sl = p.getClassList().length;
		String[] s = p.getClassList();
		for (int i = 0; i < sl; i++) {
			System.out.println(s[i]);
		}
	}
	
	private void exp04() 
	{
		
		ClassExplorer p = new ClassExplorer("org.mql.java.models");
		int sl = p.getClassList().length;
		String[] s = p.getClassList();
		for (int i = 0; i < sl; i++) {
			ClassParser cls = new ClassParser(s[i]);
			cls.showInConsole();
		}
	}
	
	private void exp05() 
	{
		PackageExplorer p = new PackageExplorer("Bouskri Mohammed Karim-ClassParser");
		
		for (int i = 0; i < p.getProject().getPackages().size(); i++) {
			
			
			for (int j = 0; j <p.getProject().getPackages().get(i).getClasses().size() ; j++) {
				
				System.out.println("Class : "+p.getProject().getPackages().get(i).getClasses().get(j).getName());
				
				for (int j2 = 0; j2 < p.getProject().getPackages().get(i).getClasses().get(j).getAggregation().size(); j2++) {
					
					System.out.println("\t"+p.getProject().getPackages().get(i).getClasses().get(j).getAggregation().get(j2));
					
				}
				
			}
			
		}
	}
	

	
	private void exp06() 
	{
		ClassParser cls = new ClassParser("org.mql.java.models.User");
		
		System.out.println(cls.getMyClass().toXML(cls.getMyClass()));
		
	}
	
	private void exp07() 
	{
		ProjectSaxParser p = new ProjectSaxParser("resources/XMLFile.xml");
		
		for (int i = 0; i < p.getMyProject().getPackages().get(3).getClasses().get(1).getAggregation().size(); i++) {
			System.out.println("agg = "+p.getMyProject().getPackages().get(3).getClasses().get(5).getAggregation().get(1));
		}
	}
	
	private void exp08() 
	{
		
	}

	
	
	public static void main(String[] args) {
		new Examples();
	}
}
