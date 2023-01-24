package org.mql.java.file;

import java.io.FileWriter;
import java.io.IOException;


import org.mql.java.reflection.PackageExplorer;

public class FileGenerator {

	public FileGenerator() {
			FileWriter myWriter;
			PackageExplorer pe = new PackageExplorer("Bouskri Mohammed Karim-ClassParser");
			
			try {
				myWriter = new FileWriter("resources/XMLFile.xml");

				myWriter.write(pe.getProject().toXML(pe.getProject()));
				
				
			    myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      
	}
	public static void main(String[] args) {
		new FileGenerator();
	}

}
