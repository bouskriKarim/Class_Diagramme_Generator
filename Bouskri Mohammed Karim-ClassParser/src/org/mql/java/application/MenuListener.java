package org.mql.java.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mql.java.models.ProjectModel;
import org.mql.java.ui.classdiagramme.ClassDiagrammeFrame;
import org.mql.java.ui.packagediagramme.PackageDiagrammeFrame;

public class MenuListener implements ActionListener {

	private ProjectModel project;
	private boolean toClass;
	
	public MenuListener(boolean toClass,ProjectModel project) {
		this.toClass = toClass;
		this.project = project;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(toClass) 
		{
			new ClassDiagrammeFrame(project);
		}
		else 
		{
			new PackageDiagrammeFrame(project);
		}
		
	}

}
