package org.mql.java.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class MethodModel {
	
	private String name;
	private String type;
	private List<PropertyModel> parameters;
	private String visibility;

	public MethodModel() {
		// TODO Auto-generated constructor stub
	}

	public MethodModel(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public MethodModel(String name, String type, String visibility,List<PropertyModel> parameters) {
		super();
		this.name = name;
		this.type = type;
		this.parameters = parameters;
	}
	
	public MethodModel(String name, String type, String visibility) {
		super();
		this.name = name;
		this.type = type;
		this.visibility = visibility;
		this.parameters = new Vector<>();
	}
	
	public void addParameter(PropertyModel parameter) 
	{
		parameters.add(parameter);
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

	public List<PropertyModel> getParameters() {
		return parameters;
	}

	public void setParameters(List<PropertyModel> parameters) {
		this.parameters = parameters;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String toXML(MethodModel methodModel)
	{
		String xmlFormat ="";
		Field[] fields = this.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			try {
				if(field.getName().equals("parameters")) 
				{
					xmlFormat +="\n<parameters>";
					for (int i = 0; i < methodModel.getParameters().size(); i++) {
						
						xmlFormat += "\n<parameter>"+methodModel.getParameters().get(i).toXML(methodModel.getParameters().get(i))+"\n</parameter>";
					}
					xmlFormat +="\n</parameters>";
				}
				else 
				{
					field.setAccessible(true);
					xmlFormat +="\n<"+field.getName()+">"+field.get(methodModel)+"</"+field.getName()+">";
					field.setAccessible(false);
				}
				
				
				
			} catch (Exception e) {
				System.out.println("ERREUR :" + e.getMessage());
			}
			
		}
		
		return xmlFormat;
	}

}
