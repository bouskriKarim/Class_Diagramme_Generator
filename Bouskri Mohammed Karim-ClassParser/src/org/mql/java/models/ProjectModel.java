package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class ProjectModel {

	private String projectName;
	private List<PackageModel>packages;
	
	public ProjectModel() {
		packages = new Vector<>();
	}

	public ProjectModel(String projectName) {
		super();
		this.projectName = projectName;
		packages = new Vector<>();
	}
	
	public void addPackage(PackageModel myPackage) 
	{
		packages.add(myPackage);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<PackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}
	
	public String toXML(ProjectModel project) 
	{
		String XMLFormat="";
		
		XMLFormat +="\n<project projectName = '"+project.getProjectName()+"'>";
		XMLFormat +="\n<packages>";
		for (int i = 0; i < project.getPackages().size(); i++) {
			XMLFormat += project.getPackages().get(i).toXml(project.getPackages().get(i));
		}
		XMLFormat +="\n</packages>";
		
		return XMLFormat+"</project>";
				
	}
	

}
