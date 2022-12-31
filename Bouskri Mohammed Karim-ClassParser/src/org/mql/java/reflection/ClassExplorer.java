package org.mql.java.reflection;

import java.io.File;
import java.util.Vector;

public class ClassExplorer {

	private String packageName;
	private Vector<String> v = new Vector<String>();
	
	public ClassExplorer(String packageName) {
		this.packageName = packageName;
		
		String classPath = System.getProperty("java.class.path");
		String packagepath = packageName.replace(".", "/");
		File directory = new File(classPath+"/"+packagepath);

		
		getClassList(directory.listFiles());
		
	}
	
	
	private void getClassList(File[] f) {	
		
		for (int i = 0; i < f.length; i++) {
			if(f[i].isFile() && f[i].getName().endsWith(".class"))
			{
				String name = f[i].getName().replace(".class","");
				v.add(packageName+"."+ name);
			}
			else if (f[i].isDirectory()) 
			{
				packageName+="."+f[i].getName();
				getClassList(f[i].listFiles());
			}
		}
	}
	
	public String[] getClassList()
	{
		String t[] = new String[v.size()];
		return v.toArray(t);
	}
}