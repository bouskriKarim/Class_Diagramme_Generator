package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class InterfaceModel {
	
	private String name;
	private List<PropertyModel> properties;
	private List<MethodModel> methods;
	
	public InterfaceModel() {
		
	}

	public InterfaceModel(String name) {
		super();
		this.name = name;
		this.properties = new Vector<>();
		this.methods = new Vector<>();
	}
	
	public void addProperty(PropertyModel property) 
	{
		properties.add(property);
	}
	
	public void addMethod(MethodModel method) 
	{
		methods.add(method);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PropertyModel> getProperties() {
		return properties;
	}


	public List<MethodModel> getMethods() {
		return methods;
	}
	
	
	public String toXML(InterfaceModel myInterface) 
	{
		String XMLFormat = "";
		
		XMLFormat +="<interface name = '"+myInterface.getName()+"'>";
		
		int propertiesCount = myInterface.getProperties().size();
		XMLFormat += "\n<properties> ";
		for (int i = 0; i <propertiesCount; i++) {
			
			XMLFormat += myInterface.getProperties().get(i).toXML(myInterface.getProperties().get(i), "property");
			
		}
		XMLFormat += "\n</properties> ";
		int methodesCount = myInterface.getMethods().size();
		XMLFormat += "\n<methodes> ";
		for (int i = 0; i <methodesCount; i++) {
			
			XMLFormat += myInterface.getMethods().get(i).toXML(myInterface.getMethods().get(i), "method");
		}
		XMLFormat += "\n</methodes> ";
		
		return XMLFormat+"</interface>";
	}
	
	

}
