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
	private List<String> aggregation;
	private List<String> uses;

	public ClassModel(String name , String superClass) {
		
		this.name = name;
		this.superClass = superClass;
		
		interfaces = new Vector<>();
		properties = new Vector<>();
		constructors = new Vector<>();
		methodes = new Vector<>();
		internClass = new Vector<>();
		extensionChain = new Vector<>();
		aggregation = new Vector<>();
		uses = new Vector<>();
	}
	
	public void addAgregat(String className) 
	{
		aggregation.add(className);
	}
	
	public void addUse(String className) 
	{
		uses.add(className);
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
	
	
	public List<String> getAggregation() {
		return aggregation;
	}

	public void setAggregation(List<String> aggregation) {
		this.aggregation = aggregation;
	}

	public List<String> getUses() {
		return uses;
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}

	public String toXML(ClassModel myClass) 
	{	
		String xmlFormat ="";

		xmlFormat +="\n<class className = '"+myClass.getName()+"' vidisility = '"+ myClass.getVisbility()+"' superClass = '"+myClass.getSuperClass()+"'>";
		
		xmlFormat += "\n<inheritanceChain>";
		List<String> inheritanceChain = myClass.getExtensionChain();
		int inheritanceChainSize = inheritanceChain.size();
		
		for (int i = 0; i < inheritanceChainSize; i++) {
			
			xmlFormat +="\n<className>"+inheritanceChain.get(i)+"</className>";
		}
		xmlFormat += "\n</inheritanceChain>";

		xmlFormat += "\n<interfaces>";
		List<String> interfaces = myClass.getInterfaces();
		int interfacesSize = interfaces.size();
		
		for (int i = 0; i < interfacesSize; i++) {
			
			xmlFormat +="\n<interface>"+interfaces.get(i)+"</interface>";
		}
		xmlFormat += "\n</interfaces>";

		xmlFormat += "\n<properties>";
		List<PropertyModel> properties = myClass.getProperties();
		int propertiesSize = properties.size();
		for (int i = 0; i < propertiesSize; i++) {
			
			xmlFormat +=properties.get(i).toXML(properties.get(i),"property");
			
		}
		xmlFormat += "\n</properties>";

		xmlFormat += "\n<constructors>";
		List<MethodModel> constructors = myClass.getConstructors();
		int constructorsSize = constructors.size();
		for (int i = 0; i <constructorsSize; i++) {
			
			xmlFormat +=constructors.get(i).toXML(constructors.get(i),"constructor");
			
		}
		xmlFormat += "\n</constructors>";

		xmlFormat += "\n<methodes>";
		List<MethodModel> methodes = myClass.getMethodes();
		int methodesSize = methodes.size();
		for (int i = 0; i < methodesSize; i++) {
			
			xmlFormat +=methodes.get(i).toXML(methodes.get(i),"method");
			
		}
		xmlFormat += "\n</methodes>";

		xmlFormat += "\n<aggregation>";
		List<String> aggregation = myClass.getAggregation();
		int aggregationSize = aggregation.size();
		for (int i = 0; i <aggregationSize; i++) {
			
			xmlFormat +="\n<className>"+ aggregation.get(i)+"\n</className>";
			
		}
		xmlFormat += "\n</aggregation>";
		
		
		xmlFormat += "\n<uses>";
		List<String> uses = myClass.getUses();
		int usesSize = uses.size();
		for (int i = 0; i <usesSize; i++) {
			
			xmlFormat +="\n<className>"+ uses.get(i)+"\n</className>";
			
		}
		xmlFormat += "\n</uses>";
		
		
		xmlFormat += "\n<internClasses>";
		List<ClassModel> internClasses = myClass.getInternClass();
		int internClassesSize = internClasses.size();
		for (int i = 0; i <internClassesSize; i++) {
			
			xmlFormat +="\n<internClass>"+ internClasses.get(i).toXML(internClasses.get(i))+"\n</internClass>";
			
		}
		xmlFormat += "\n</internClasses>";
		
		return xmlFormat + "</class>";
	}
	

}
