package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class RelationModel {
	
	private String fistClassName;
	private List<String> classes;
	private String type;

	public RelationModel(String firstClassName , String type) {
		
		this.fistClassName = firstClassName;
		this.type = type;
		this.classes = new Vector<>();
	}
	
	
	

	public RelationModel(String fistClassName, List<String> classes, String type) {
		super();
		this.fistClassName = fistClassName;
		this.classes = classes;
		this.type = type;
	}


	public void addClass(String className) 
	{
		classes.add(className);
	}

	public String getFistClassName() {
		return fistClassName;
	}

	public void setFistClassName(String fistClassName) {
		this.fistClassName = fistClassName;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
