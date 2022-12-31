package org.mql.java.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class PackageModel {
	
	private String packageName;
	private String qualifiedName;
	private List<ClassModel> classes;

	public PackageModel() {
		
		classes = new Vector<>();
	}

	public PackageModel(String packageName, String qualifiedName) {
		super();
		this.packageName = packageName;
		this.qualifiedName = qualifiedName;
		classes = new Vector<>();
	}
	public PackageModel(String packageName, String qualifiedName,List<ClassModel> classes) {
		super();
		this.packageName = packageName;
		this.qualifiedName = qualifiedName;
		this.classes = classes;
	}

	public void addClass(ClassModel myClass) 
	{
		classes.add(myClass);
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public List<ClassModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}
	
	public String toXml(PackageModel packageModel) 
	{
		String xmlFormat ="";
		
		xmlFormat +="\n<package qualifiedName = '"+packageModel.getQualifiedName() +"'>" ;
		
		xmlFormat +="\n<classes>";
		
		for(int i = 0; i<packageModel.getClasses().size(); i++) 
		{
			xmlFormat +="\n<class>"+packageModel.getClasses().get(i).toXML(packageModel.getClasses().get(i))+"\n</class>";
		}
		xmlFormat +="</classes>";
	
		return xmlFormat+"</package>";
	}

}
