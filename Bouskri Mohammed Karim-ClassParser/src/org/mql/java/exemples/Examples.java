package org.mql.java.exemples;


import org.mql.java.reflection.ClassParser;
import org.mql.java.reflection.PackageExplorer;
import org.mql.java.reflection.ClassExplorer;

public class Examples {

	public Examples() {
		exp03();
		//System.out.println(System.getProperty("java.class.path"));
		}
	
	
	//Affichage dans la console
	private void exp01() {
		ClassParser cls = new ClassParser("org.mql.java.models.TestInterface");
		//cls.showInConsole();
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
			
			System.out.println(p.getProject().getPackages().get(i).getQualifiedName());
			
		}
	}
	

	
	private void exp06() 
	{
		ClassParser cls = new ClassParser("org.mql.java.models.User");
		
		System.out.println(cls.getMyClass().toXML(cls.getMyClass()));
		
	}
	
	
	public static void main(String[] args) {
		new Examples();
	}
}
