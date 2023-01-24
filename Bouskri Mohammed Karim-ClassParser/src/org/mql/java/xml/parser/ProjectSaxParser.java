package org.mql.java.xml.parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.mql.java.models.ClassModel;
import org.mql.java.models.MethodModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import org.mql.java.models.PropertyModel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjectSaxParser extends DefaultHandler {
	
	private ProjectModel myProject;
	private int packageIndex = -1;
	private int classIndex = -1;
	private int methodParameterIndex = -1;
	private int constructorParameterIndex = -1;
	
	private boolean constructors = false;
	private boolean methods = false;
	
	private boolean agregations = false;
	private boolean uses = false;
	

	public ProjectSaxParser(String filePath) {
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			
			parser.parse(filePath, this);
			
		} catch (Exception e) {
			System.out.println("ERREUR : " + e.getMessage());
		} 
	}
	
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("project")) 
		{
			myProject = new ProjectModel(attributes.getValue("projectName"));
			
			//show("project : "+attributes.getValue("projectName"));
		}
		else if(qName.equals("package")) 
		{
			classIndex = -1;
			PackageModel myPackage = new PackageModel(attributes.getValue("name"), attributes.getValue("qualifiedName"));
			
			myProject.addPackage(myPackage);
			packageIndex++;
			
			//show("package :		"+attributes.getValue("qualifiedName"));
		}
		else if(qName.equals("class")) 
		{
			constructorParameterIndex = -1;
			methodParameterIndex = -1;
			ClassModel myClass = new ClassModel(attributes.getValue("className"), attributes.getValue("superClass"));
			
			myProject.getPackages().get(packageIndex).addClass(myClass);
			classIndex++;
			
			//show("class :			"+attributes.getValue("className"));
		}
		else if(qName.equals("property")) 
		{
			PropertyModel myProperty = new PropertyModel(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("visibility"));
			
			myProject.getPackages().get(packageIndex).getClasses().get(classIndex).addPropertie(myProperty);
			
			//show("property :			"+attributes.getValue("name"));
		}
		else if(qName.equals("constructor")) 
		{
			constructors = true;
			MethodModel myConstructor = new MethodModel(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("visibility"));
			
			myProject.getPackages().get(packageIndex).getClasses().get(classIndex).addConstructor(myConstructor);
			constructorParameterIndex++;
			
			//show("constructor :				"+attributes.getValue("name"));
		}
		else if(qName.equals("parameter")) 
		{
			if(constructors) 
			{
				PropertyModel myParameter = new PropertyModel(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("visibility"));

				myProject.getPackages().get(packageIndex).getClasses().get(classIndex).getConstructors().get(constructorParameterIndex).addParameter(myParameter);
			
				//show("parameter :				"+attributes.getValue("type"));
			}
			else if(methods) 
			{
				PropertyModel myParameter = new PropertyModel(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("visibility"));

				myProject.getPackages().get(packageIndex).getClasses().get(classIndex).getMethodes().get(methodParameterIndex).addParameter(myParameter);
			
				//show("parameter :				"+attributes.getValue("type"));
			}
		}
		else if(qName.equals("method")) 
		{
			methods = true;
			MethodModel myMethod = new MethodModel(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("visibility"));

			myProject.getPackages().get(packageIndex).getClasses().get(classIndex).addMethod(myMethod);
			methodParameterIndex++;
			
			//show("method :				"+attributes.getValue("name"));
		}
		
		else if(qName.equals("aggregation")) 
		{
			agregations = true;
		}
		
		else if(qName.equals("className")) 
		{
			if(agregations) 
			{
				myProject.getPackages().get(packageIndex).getClasses().get(classIndex).addAgregat(attributes.getValue("name"));
			}
			else if(uses) 
			{
				myProject.getPackages().get(packageIndex).getClasses().get(classIndex).addUse(attributes.getValue("name"));
			}
		}
		else if(qName.equals("uses")) 
		{
			uses = true;
		}

		
	}
	
	public ProjectModel getMyProject() {
		return myProject;
	}
	
	private static void show(String data) 
	{
		System.out.println(data);
	}
	
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("constructor")) 
		{
			constructors = false;
		}
		else if(qName.equals("method")) 
		{
			methods = false;
		}
		else if(qName.equals("aggregation")) 
		{
			agregations = false;
		}
		else if(qName.equals("uses"))
		{
			uses = false;
		}
	}
	
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		
	}
}
