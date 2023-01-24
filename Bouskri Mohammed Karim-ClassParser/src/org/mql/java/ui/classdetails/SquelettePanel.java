package org.mql.java.ui.classdetails;

import java.awt.Font;
import java.awt.TextArea;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.mql.java.models.PropertyModel;
import org.mql.java.reflection.ClassParser;

public class SquelettePanel extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TextArea textArea = new TextArea();
	private org.mql.java.models.ClassModel cl;
	
	private List<PropertyModel> properties;
	private List<org.mql.java.models.MethodModel> constructors;
	private List<org.mql.java.models.MethodModel> methodes;
	
	public SquelettePanel(org.mql.java.models.ClassModel cl) {
		this.cl = cl;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		textArea.setEditable(false);
		
		textArea.append(cl.getClassHeader() +" {\n\n");
		
		addProperties(cl);
		addConstructors(cl);
		addMethods(cl);
		addInterneClass();
		
		textArea.setFont(new Font("TimesRoman",Font.BOLD,15));
		
		add(textArea);
		
		
		}
		
		
		
	
		
	private void addProperties(org.mql.java.models.ClassModel cl) 
	{
		properties = cl.getProperties();
		int propertyCount = properties.size();
		for (int i = 0; i < propertyCount; i++) {
			
			textArea.append("\t"+properties.get(i).getVisibility()+" ");
			textArea.append(properties.get(i).getType()+" ");
			textArea.append(properties.get(i).getName()+" ;\n");
			
		}
		textArea.append("\n");
	}
	
	private void addConstructors(org.mql.java.models.ClassModel cl) 
	{
		constructors = cl.getConstructors();
		int ConstructursCount = constructors.size();
		for (int i = 0; i < ConstructursCount; i++) {
		
			textArea.append("\n\t"+constructors.get(i).getVisibility()+" ");
			textArea.append(constructors.get(i).getName()+" (");
			int parameterCount =constructors.get(i).getParameters().size();
			if(parameterCount > 0) 
			{
				List<PropertyModel> parameters = constructors.get(i).getParameters();
				
				for (int j = 0; j < parameterCount-1; j++) {
					textArea.append(parameters.get(j).getType()+" ");
					textArea.append(parameters.get(j).getName()+", ");
				}
				textArea.append(parameters.get(parameterCount-1).getType()+" ");
				textArea.append(parameters.get(parameterCount-1).getName());
			}
			
			
			textArea.append("){}");
		}
		textArea.append("\n");
	}
	
	private void addMethods(org.mql.java.models.ClassModel cl) 
	{
		methodes = cl.getMethodes();
		int methodesCount = methodes.size();
		for (int i = 0; i < methodesCount; i++) {
			
			textArea.append("\n\t"+methodes.get(i).getVisibility()+" ");
			textArea.append(methodes.get(i).getType()+" ");
			textArea.append(methodes.get(i).getName()+" (");
			int parameterCount = methodes.get(i).getParameters().size();
			if(parameterCount > 0) 
			{
				List<PropertyModel> parameters = methodes.get(i).getParameters();

				for (int j = 0; j < parameterCount - 1; j++) {
					textArea.append(parameters.get(j).getType()+" ");
					textArea.append(parameters.get(j).getName()+", ");
				}
				textArea.append(parameters.get(parameterCount - 1).getType()+" ");
				textArea.append(parameters.get(parameterCount - 1).getName());
			}
			
			
			textArea.append("){}");
	}

	
}
	
	private void addInterneClass() 
	{
		int interneClassess = cl.getInternClass().size();
		for(int i = 0 ; i < interneClassess ; i++)
		{
			textArea.append("\n\n"+cl.getInternClass().get(i).getClassHeader() +" {\n\n");
			addProperties(cl.getInternClass().get(i));
			addConstructors(cl.getInternClass().get(i));
			addMethods(cl.getInternClass().get(i));
		
		}
		
		textArea.append("\n}");
	}
}
