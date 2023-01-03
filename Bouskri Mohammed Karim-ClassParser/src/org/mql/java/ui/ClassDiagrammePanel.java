package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import org.mql.java.reflection.PackageExplorer;

public class ClassDiagrammePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	


	public ClassDiagrammePanel() {
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		setBackground(Color.white);
		PackageExplorer pe = new PackageExplorer("Bouskri Mohammed Karim-ClassParser");
		
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		JScrollPane scrollBar = new JScrollPane(this);
		scrollBar.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		for (int i = 0; i < pe.getProject().getPackages().size(); i++) {
			
			for (int j = 0; j < pe.getProject().getPackages().get(i).getClasses().size(); j++) {
				
				this.add(new ClassPanel(pe.getProject().getPackages().get(i).getClasses().get(j)));
				
			}
			
		}
		
	}

}
