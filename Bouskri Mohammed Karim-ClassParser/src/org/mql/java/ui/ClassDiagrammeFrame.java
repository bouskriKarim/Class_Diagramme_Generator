package org.mql.java.ui;



import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ClassDiagrammeFrame extends JFrame{


	private static final long serialVersionUID = 1L;
	private ClassDiagrammePanel cdp;
	
	public ClassDiagrammeFrame() {
		cdp = new ClassDiagrammePanel();
		//setContentPane(cdp);
		
		JScrollPane jsp = new JScrollPane(cdp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ClassDiagrammeFrame();
	}

}
