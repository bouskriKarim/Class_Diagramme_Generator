package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.ClassModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;


public class PackageExplorer {

	private ProjectModel project;
	
	public PackageExplorer(String projectName) {
		
		project = new ProjectModel(projectName);
		
		String classPath = System.getProperty("java.class.path");
		String[] s = classPath.split("\\\\");
		String newClassPath = s[0];
		
		for (int i = 1; i < s.length-2; i++) {
			newClassPath += "/"+s[i];
		}
		
		newClassPath +="/"+projectName+"/bin";
		File directory = new File(newClassPath);
		
		
		getPackages(directory.listFiles());
		addClassesToPackages();

	}
	
	private void getPackages(File[] files) 
	{
		
		for (File file : files) {
			if(file.isDirectory() ) 
			{
				int size = file.listFiles().length;

				if(size == 0 || file.listFiles()[0].getName().contains(".class") || file.listFiles()[size-1].getName().contains(".class")) 
				{
					project.addPackage(new PackageModel(file.getName(), file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("bin")).replace("\\", ".").substring(4)));
				}
				
				getPackages(file.listFiles());
			}
		}
	}
	
	private void addClassesToPackages() 
	{
		List<PackageModel> packages = project.getPackages();
		
		for (PackageModel packageModel : packages) {
			String[] classesName = new ClassExplorer(packageModel.getQualifiedName()).getClassList();
			for (String className : classesName) {
				packageModel.addClass(new ClassParser(className).getMyClass());
			}
			
		}
		
	}
	
	public ProjectModel getProject()
	{
		return project;
	}



}
