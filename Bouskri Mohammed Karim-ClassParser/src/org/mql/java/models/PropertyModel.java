package org.mql.java.models;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

public class PropertyModel {
	
	private String name;
	private String type;
	private String visibility;

	public PropertyModel() {
	}

	public PropertyModel(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public PropertyModel(String name, String type, String visibility) {
		super();
		this.name = name;
		this.type = type;
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String toXML(PropertyModel propertyModel,String type) 
	{
		String xmlFormat ="";
		
		xmlFormat +="\n<"+type+" name = '"+propertyModel.getName()+"' type = '"+propertyModel.getType()+"' visibility = '"+propertyModel.getVisibility() +"'>\n</"+type+">"; 
		
		return xmlFormat;
	}
	

}
