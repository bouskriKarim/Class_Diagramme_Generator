package org.mql.java.ui.classdetails;

import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.reflection.ClassParser;

public class InformationPanel extends JPanel {
	
	private LabeledLabel interfacesCount,
	propertiesCount,
	constructorCount,
	methodesCount,
	internClassess,
	extensionChainLabel;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InformationPanel(ClassParser cp) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(interfacesCount = new LabeledLabel("Interfaces Count", cp.getInterfacesCount()+""));
		
		add(propertiesCount = new LabeledLabel("Properties Count",cp.getPropertiesCount()+""));
		add(constructorCount = new LabeledLabel("constructors Count",cp.getConstructorsCount()+""));
		add(methodesCount = new LabeledLabel("Methodes Count", cp.getMethodCount()+""));
		add(internClassess = new LabeledLabel("Intern Classess count", cp.getInternClassessCount()+""));
		
		
		TextArea extensionChain = new TextArea();
		extensionChain.setEditable(false);
		for (int i = 0; i < cp.getMyClass().getExtensionChain().size(); i++) {
			extensionChain.append(cp.getMyClass().getExtensionChain().get(i)+"\n");
		}
		add(new LabeledLabel("Extension Chain : ",""));
		add(extensionChain);
		
		EtchedBorder border = new EtchedBorder();
		setBorder(new TitledBorder(border,"Class Information"));
		
	}

}
