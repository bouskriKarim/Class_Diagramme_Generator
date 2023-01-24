package org.mql.java.ui.classdiagramme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.models.ClassModel;
import org.mql.java.models.ProjectModel;

public class ClassDiagrammePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private Graphics g;
	private Component[] components;
	private List<ClassModel> allClasses = new Vector<>();


	public ClassDiagrammePanel(ProjectModel project) {
		
		
		
		setBackground(Color.white);
		
		
		
		
		for (int i = 0; i < project.getPackages().size(); i++) {
			List<ClassModel> classes = project.getPackages().get(i).getClasses();
			int classesSize = classes.size();
			for (int j = 0; j < classesSize; j++) { 
				allClasses.add(classes.get(j));
				this.add(new ClassPanel(classes.get(j)));
			}
		}
		int width = getPreferredSize().width;
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, width/2));

		
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		components = this.getComponents();
		for( int i = 0; i < allClasses.size(); i++) 
		{
			
			drawRelations(g,allClasses.get(i).getQualifiedName(), allClasses.get(i).getAggregation(),"agregation");
			
			if(getComponentByName(allClasses.get(i).getSuperClass()) != null) drawRelation(g, getComponentByName(allClasses.get(i).getSuperClass()), getComponentByName(allClasses.get(i).getQualifiedName()), "inheritance");
			if(allClasses.get(i).getInterfaces().size() != 0) {

				drawRelations(g,allClasses.get(i).getQualifiedName(), allClasses.get(i).getInterfaces(),"implementation");
			}
		
		}
		drawGrid(g);
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		components = this.getComponents();
		
		for (Component component : components) {

			int x = component.getLocation().x;
			int y = component.getLocation().y;			
			drawBorder(component,x, y); 

		}
	}
	
	private void drawBorder(Component component,int x, int y) 
	{
		
		g.setColor(Color.decode("#53b26a"));
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setStroke(new BasicStroke(2));
		
		g.drawLine(x+40, y+45, x+40, y+component.getSize().height-45);
		g.drawLine(x+component.getSize().width-41, y+45, x+component.getSize().width-41, y+component.getSize().height-45);
		
		g.drawLine(x+45, y+40, x+component.getSize().width-45, y+40);
		g.drawLine(x+45, y+component.getSize().height-41, x+component.getSize().width-45, y+component.getSize().height-41);
		
		
		g.drawArc(x+40, y+40, 10, 10,90, 90);
		g.drawArc(x+component.getSize().width-51, y+40, 10, 10, 0, 90);
		
		g.drawArc(x+40, y+component.getSize().height-51, 10, 10,180, 90);
		g.drawArc(x+component.getSize().width-51, y+component.getSize().height-51, 10, 10,270, 90);
	}
	
	private void drawGrid(Graphics g) 
	{
		g.setColor(Color.lightGray);
		
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		
		int space = 20;
		for (int i = 0; i < height/space; i++) g.drawLine(0, i*space, width, i*space);
		
		for (int i = 0; i < width/space; i++) {
			g.drawLine(i*space, 0, i*space, height);
		}
	}
	
	private void drawSign(Graphics g ,int x , int y ,String direction,String relationType) 
	{
		if(relationType.equals("agregation")) drawAgregationSign(g,x, y,direction);
		else if(relationType.equals("implementation")) 
		{
			if(direction.equals("down")) 
			{
				y += 10;
				g.drawPolygon(new int[] {x, x + 10, x + 20 }, new int[] {y + 10, y-10, y + 10}, 3);
			}

			else g.drawPolygon(new int[] {x, x - 10, x + 10 }, new int[] {y, y-20, y - 20}, 3);
		}
		else 
		{
			
			if(direction.equals("up")) 
			{
				y += 10;
				x -= 10;
				g.drawPolygon(new int[] {x, x + 10, x + 20 }, new int[] {y + 10, y-10, y + 10}, 3);
			}

			else g.drawPolygon(new int[] {x, x - 10, x + 10 }, new int[] {y, y-20, y - 20}, 3);
		}
	}
	
	private void drawAgregationSign(Graphics g,int x, int y, String direction) 
	{
		x-= 10;
		
		if(direction.equals("down")) 
		{
			y -= 10;
			g.drawPolygon(new int[] {x, x + 10, x + 20, x + 10 }, new int[] {y, y - 10, y  , y + 10}, 4);
		}
		else 
		{
			y += 10;
			g.drawPolygon(new int[] {x, x + 10, x + 20, x + 10 }, new int[] {y, y - 10, y  , y + 10}, 4);
		}
	}

	private void drawRelation(Graphics g,Component motherClass, Component childClass,String relationType)
	{
		String direction ="";
		
		int xs = 0;
		int ys = 0;
		int xe = 0;
		int ye = 0;
		
		if(motherClass.getX() < childClass.getX())
		{
			if(motherClass.getY()  < childClass.getY() ) 
			{
				direction = "up";
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + motherClass.getHeight() - 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + 40;
			}
			else 
			{
				direction = "down";
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + childClass.getHeight() - 40;
			}
		}else 
		{
			if(motherClass.getY() < childClass.getY()) 
			{
				direction = "up";
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + motherClass.getHeight() - 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2 ;
				ye = childClass.getY() + 40;
			}
			else 
			{
				direction = "down";
				
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() +  40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + childClass.getHeight() - 40;
			}
		}

		if(relationType.equals("implementation")) 
		{
			drawSign(g,xe, ye,direction,relationType);
			
			if(direction.equals("up")) drawDashedLine(g,xs, ys, xe , ye - 20);
			else drawDashedLine(g,xs, ys - 20, xe - 30, ye);
		}else 
		{
			drawSign(g,xs, ys,direction,relationType);
			
			if(direction.equals("up")) g.drawLine(xs, ys + 20, xe - 50 , ye);
			else g.drawLine(xs, ys - 20, xe - 50, ye);
		}
	}

	private void drawRelations(Graphics g,String firstClassName, List<String> classesNames,String type) 
	{
		int size = classesNames.size();
		for (int i = 0; i < size; i++) {
			drawRelation(g,getComponentByName(firstClassName),getComponentByName(classesNames.get(i)),type);
		}
	}
	

	private Component getComponentByName(String name) 
	{
		for (int i = 0; i < components.length; i++) {
			if(components[i].getName().equals(name)) return components[i]; 
		}
		return null;
	}
	
	private void drawDashedLine(Graphics g,int xs, int ys, int xe, int ye) 
	{
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{9}, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(xs, ys, xe, ye);
		g2d.dispose();
	}
	

}
