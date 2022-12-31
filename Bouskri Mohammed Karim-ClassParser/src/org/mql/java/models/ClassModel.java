package org.mql.java.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class ClassModel {
	
	private String visbility;
	private String name;
	private String superClass;
	private List<String> extensionChain;
	private List<String> interfaces;
	private List<PropertyModel> properties;	
	private List<MethodModel> constructors;
	private List<MethodModel> methodes;
	private List<ClassModel> internClass;
	private String classHeader;

	public ClassModel(String name , String superClass) {
		
		this.name = name;
		this.superClass = superClass;
		
		interfaces = new Vector<>();
		properties = new Vector<>();
		constructors = new Vector<>();
		methodes = new Vector<>();
		internClass = new Vector<>();
		extensionChain = new Vector<>();
	}
	
	public void addInterface(String myInterface) 
	{
		interfaces.add(myInterface);
	}
	
	public void addPropertie(PropertyModel propertie) 
	{
		properties.add(propertie);
	}
	
	public void addConstructor(MethodModel constructor) 
	{
		constructors.add(constructor);
	}
	
	public void addMethod(MethodModel method) 
	{
		methodes.add(method);
	}
	
	public void addClass(ClassModel cls) 
	{
		internClass.add(cls);
	}
	
	public void addSuperClass(String className) 
	{
		extensionChain.add(className);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<PropertyModel> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyModel> properties) {
		this.properties = properties;
	}

	public List<MethodModel> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<MethodModel> constructors) {
		this.constructors = constructors;
	}

	public List<MethodModel> getMethods() {
		return methodes;
	}

	public void setMethods(List<MethodModel> methodes) {
		this.methodes = methodes;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public String getVisbility() {
		return visbility;
	}

	public void setVisbility(String visbility) {
		this.visbility = visbility;
	}

	public List<MethodModel> getMethodes() {
		return methodes;
	}

	public void setMethodes(List<MethodModel> methodes) {
		this.methodes = methodes;
	}

	public List<ClassModel> getInternClass() {
		return internClass;
	}

	public void setInternClass(List<ClassModel> internClass) {
		this.internClass = internClass;
	}

	public List<String> getExtensionChain() {
		return extensionChain;
	}

	public void setExtensionChain(List<String> extensionChain) {
		this.extensionChain = extensionChain;
	}

	public String getClassHeader() {
		return classHeader;
	}

	public void setClassHeader(String classHeader) {
		this.classHeader = classHeader;
	}
	
	public String toXML(ClassModel myClass) 
	{	
		String xmlFormat ="";
		Field[] fields = this.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			if(field.getName().equals("extensionChain")) 
			{
				xmlFormat += "\n<inheritanceChain>";
				for (int i = 0; i < myClass.getExtensionChain().size(); i++) {
					
					xmlFormat +="\n<className>"+myClass.getExtensionChain().get(i)+"</className>";
				}
				xmlFormat += "\n</inheritanceChain>";
			}
			else if(field.getName().equals("interfaces")) 
			{
				xmlFormat += "\n<interfaces>";
				for (int i = 0; i < myClass.getInterfaces().size(); i++) {
					
					xmlFormat +="\n<interface>"+myClass.getInterfaces().get(i)+"</interface>";
				}
				xmlFormat += "\n</interfaces>";
			}
			else if(field.getName().equals("properties"))
			{
				xmlFormat += "\n<properties>";
				for (int i = 0; i < myClass.getProperties().size(); i++) {
					
					xmlFormat +="\n<property>"+ myClass.getProperties().get(i).toXML(myClass.getProperties().get(i))+"\n</property>";
					
				}
				xmlFormat += "\n</properties>";
			}
			else if(field.getName().equals("constructors")) 
			{
				xmlFormat += "\n<constructors>";
				for (int i = 0; i < myClass.getConstructors().size(); i++) {
					
					xmlFormat +="\n<constructor>"+ myClass.getConstructors().get(i).toXML(myClass.getConstructors().get(i))+"\n</constructor>";
					
				}
				xmlFormat += "\n</constructors>";
			}
			else if(field.getName().equals("methodes")) 
			{
				xmlFormat += "\n<methodes>";
				for (int i = 0; i < myClass.getMethodes().size(); i++) {
					
					xmlFormat +="\n<methode>"+ myClass.getMethodes().get(i).toXML(myClass.getMethodes().get(i))+"\n</methode>";
					
				}
				xmlFormat += "\n</methodes>";
			}
			else if(field.getName().equals("internClass")) 
			{
				xmlFormat += "\n<internClasses>";
				for (int i = 0; i < myClass.getInternClass().size(); i++) {
					
					xmlFormat +="\n<internClass>"+ myClass.getInternClass().get(i).toXML(myClass.getInternClass().get(i))+"\n</internClass>";
					
				}
				xmlFormat += "\n</internClasses>";
			}
			else 
			{
				try {
					field.setAccessible(true);
					xmlFormat +="\n<"+field.getName()+">"+field.get(myClass)+"</"+field.getName()+">";
					field.setAccessible(false);
				} catch (Exception e) {
					System.out.println("ERREUR :" + e.getMessage());
				}
			}
			
			
		}
		return xmlFormat;
	}
	

}
