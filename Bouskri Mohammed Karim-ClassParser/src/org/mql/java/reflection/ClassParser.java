package org.mql.java.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.mql.java.models.ClassModel;
import org.mql.java.models.MethodModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.PropertyModel;
import org.mql.java.ui.ClassNameFrame;

public class ClassParser {

	private Class<?> cls;
	private ClassModel myClass;
	
	public ClassParser(String className) {
		try {
			this.cls = Class.forName(className);
			myClass = new ClassModel(cls.getSimpleName(), cls.getSuperclass().getSimpleName());
			myClass.setVisbility(getVisibility(cls.getModifiers()));
			getProperties();
			getAllMethodes();
			getAllConstructors();
			showClassHeader();
			getExtensionChain(className);
			getIntenrClassess();
			getAnnotation();
		} catch (Exception e) {
			System.out.println("ERREUR : " + e.getMessage());
		}
		
	}

	
	
	public ClassModel getMyClass() {
		return myClass;
	}

	private String getVisibility(int modifier) {
		return Modifier.toString(modifier);
	}
	
	private void getProperties()
	{
		Field flieds[] = cls.getDeclaredFields();
		for (Field field : flieds) {
			
			getAgregation(field);
			
			myClass.addPropertie(new PropertyModel(field.getName(),field.getType().getSimpleName(),getVisibility(field.getModifiers())));
		}
	}
	
	private void getAllMethodes() 
	{		
		int i = 0;
		Method[] methodes = cls.getDeclaredMethods();
		
		for (Method method : methodes) {
			myClass.addMethod(new MethodModel(method.getName(), method.getReturnType().getSimpleName(),getVisibility(method.getModifiers())));
			Parameter[] parameters = method.getParameters();
			for (Parameter parameter : parameters) {
				getUses(parameter);
				myClass.getMethods().get(i).addParameter(new PropertyModel(parameter.getName(),parameter.getType().getSimpleName()));
			}
			i++;
		}
	}
	
	private void getAllConstructors()
	{
		int i = 0;
		Constructor<?>[] constructors = cls.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			myClass.addConstructor(new MethodModel(cls.getSimpleName(),"",getVisibility(constructor.getModifiers())));
			Parameter[] parameters = constructor.getParameters();
			for (Parameter parameter : parameters) {
				getUses(parameter);
				myClass.getConstructors().get(i).addParameter(new PropertyModel(parameter.getName(),parameter.getType().getSimpleName()));
			}
			i++;	
		}
	}
	
	public void getIntenrClassess()
	{
		Class<?> classess[] = cls.getDeclaredClasses();
		for (Class<?> class1 : classess) {
			myClass.addClass(new ClassParser(class1.getName()).getMyClass());
			
		}
	}
	
	private void showInternClassess() 
	{
		Class<?> classess[] = cls.getDeclaredClasses();
		for (Class<?> class1 : classess) {
			new ClassParser(class1.getName()).showInConsole();
			
		}
	}
	
	
	public int getInterfacesCount() 
	{
		return cls.getInterfaces().length;
	}
	
	public int getPropertiesCount() 
	{
		return cls.getDeclaredFields().length;
	}
	
	public int getConstructorsCount() 
	{
		return cls.getDeclaredConstructors().length;
	}
	
	public int getMethodCount() 
	{
		return cls.getDeclaredMethods().length;
	}
	
	public int getInternClassessCount() 
	{
		return cls.getDeclaredClasses().length;
	}
	
	private String showClassHeader() 
	{
		String classHeader ="";
		for(int i = 0; i < cls.getInterfaces().length ; i++) 
		{
			myClass.addInterface(cls.getInterfaces()[i].getSimpleName());
		}
		
		int interfacesCount = myClass.getInterfaces().size();		
		classHeader+=getVisibility(cls.getModifiers())+" " + myClass.getName()+" extends "+myClass.getSuperClass();
		
		if(interfacesCount >0) 
		{
			classHeader+=" implements ";
			for(int i = 0 ;i<interfacesCount -1;i++) 
			{
				classHeader+=myClass.getInterfaces().get(i)+", ";
			}
			classHeader+=myClass.getInterfaces().get(interfacesCount-1);
		}
		myClass.setClassHeader(classHeader);
		return classHeader;
	}
	
	private void showProperties() 
	{
		int propertiesCount = myClass.getProperties().size();
		for(int i = 0 ; i<propertiesCount;i++) 
		{
			System.out.println("   "+myClass.getProperties().get(i).getVisibility() +" "+ myClass.getProperties().get(i).getType() +" "+ myClass.getProperties().get(i).getName()+";");
			
		}
	}
	
	private void showConstructors() 
	{
		int constructorCount = myClass.getConstructors().size();
		for(int i = 0; i<constructorCount;i++) 
		{
			System.out.print("   "+myClass.getConstructors().get(i).getVisibility()+" "+myClass.getConstructors().get(i).getType()+" "+ myClass.getConstructors().get(i).getName() + "(");
			
			int parameterCount = myClass.getConstructors().get(i).getParameters().size();
			
			if(parameterCount > 0) 
			{
				for(int j = 0 ;j<parameterCount-1;j++) 
				{
					System.out.print(myClass.getConstructors().get(i).getParameters().get(j).getType() + " "+ myClass.getConstructors().get(i).getParameters().get(j).getName()+", ");
				}
				System.out.print(myClass.getConstructors().get(i).getParameters().get(parameterCount-1).getType() + " "+ myClass.getConstructors().get(i).getParameters().get(parameterCount-1).getName());
			}
			
			
			System.out.print("){}");
			
			System.out.println("");
		}
	}
	
	private void showMethodes() 
	{
		int methodesCount = myClass.getMethods().size();
		for(int i = 0; i<methodesCount;i++) 
		{
			System.out.print("   "+myClass.getMethods().get(i).getVisibility()+" "+myClass.getMethods().get(i).getType()+" "+ myClass.getMethods().get(i).getName() + "(");
			
			int parameterCount = myClass.getMethods().get(i).getParameters().size();
			if(parameterCount > 0) 
			{
				for(int j = 0 ;j<parameterCount-1;j++) 
				{
					System.out.print(myClass.getMethods().get(i).getParameters().get(j).getType() + " "+ myClass.getMethods().get(i).getParameters().get(j).getName()+", ");
				}
				System.out.print(myClass.getMethods().get(i).getParameters().get(parameterCount-1).getType() + " "+ myClass.getMethods().get(i).getParameters().get(parameterCount-1).getName());

			}
			
			
			System.out.print("){}");
			System.out.println();
		}
	}
	
	
	public List<String> getExtensionChain(String className)
	{
		Class<?> motherClass;
		try {
			motherClass = Class.forName(className).getSuperclass();
			myClass.addSuperClass(motherClass.getSimpleName());
			if(!motherClass.getName().equals("java.lang.Object")) 
			{
				return getExtensionChain(motherClass.getName());
			}
			else 
			{
				return myClass.getExtensionChain();
			}
				
		} catch (ClassNotFoundException e) {
			System.out.println("ERREUR" + e.getMessage());
			return null;
		}
	}
	
	private void getAnnotation() 
	{
		Annotation[] annotations = cls.getDeclaredAnnotations();
		
		for (Annotation annotation : annotations) {
			System.out.println(annotation.toString());
		}
		
	}
	
	
	public void showInConsole() 
	{		
		System.out.println(myClass.getClassHeader()+ " {");

		System.out.println(" ");
		
		showProperties();
		
		System.out.println(" ");
		
		showConstructors();
		
		System.out.println(" ");
		
		showMethodes();
		
		System.out.println();
		
		showInternClassess();
		
		System.out.println("}");
		
	}
	
	private void getAgregation(Field field) 
	{
		if(Collection.class.isAssignableFrom(field.getType())) 
		{
			
			ParameterizedType p = (ParameterizedType)field.getGenericType();
			Class<?> c = (Class<?>) p.getActualTypeArguments()[0];
			if(!c.getName().equals("java.lang.String") && !c.isPrimitive()) myClass.addAgregat(c.getName());
		}
		else if(!field.getType().isPrimitive() && !field.getType().getName().equals("java.lang.String") && !field.getType().getGenericSuperclass().getTypeName().equals("java.lang.Number"))
		{
			myClass.addAgregat(field.getType().getName());
		}
	}
	
	private void getUses(Parameter field) 
	{
		if(Collection.class.isAssignableFrom(field.getType())) 
		{
			
			ParameterizedType p = (ParameterizedType)field.getParameterizedType();
			Class<?> c = (Class<?>) p.getActualTypeArguments()[0];
			myClass.addUse(c.getName());
		}
		else if(!field.getType().isPrimitive() && !field.getType().getName().equals("java.lang.String") && !field.getType().getGenericSuperclass().getTypeName().equals("java.lang.Number"))
		{
			myClass.addUse(field.getType().getName());
		}
	}
	
	
	public void showInSwing() 
	{
		new ClassNameFrame();
	}
	
}
