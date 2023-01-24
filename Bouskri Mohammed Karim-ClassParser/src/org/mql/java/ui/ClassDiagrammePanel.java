package org.mql.java.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.models.RelationModel;
import org.mql.java.reflection.PackageExplorer;

public class ClassDiagrammePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private Graphics g;
	
	private List<RelationModel> relations = new Vector<>();
	private Component[] components;


	public ClassDiagrammePanel() {
		
		
		
		setBackground(Color.white);
		PackageExplorer pe = new PackageExplorer("Bouskri Mohammed Karim-ClassParser");
		
		
		
		JScrollPane scrollBar = new JScrollPane(this);
		scrollBar.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		
		
		for (int i = 0; i < pe.getProject().getPackages().size(); i++) {
			
			for (int j = 0; j < pe.getProject().getPackages().get(i).getClasses().size(); j++) { 
				
				if(pe.getProject().getPackages().get(i).getClasses().get(j).getAggregation().size() == 0) 
				{
					
				}
				else 
				{
					RelationModel rm = new RelationModel(pe.getProject().getPackages().get(i).getClasses().get(j).getQualifiedName(), pe.getProject().getPackages().get(i).getClasses().get(j).getAggregation(), "agregation");
					relations.add(rm);
				}
				
				this.add(new ClassPanel(pe.getProject().getPackages().get(i).getClasses().get(j)));
			}
			
		}
		
		
		int width = getPreferredSize().width;
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, width/2));

		
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		components = this.getComponents();
		
		System.out.println(relations.size());
		
		for( int i = 0; i < relations.size(); i++) 
		{
			System.out.println(relations.get(i).getFistClassName());
			for (int j = 0; j < relations.get(i).getAgrregatClasses().size(); j++) {
				System.out.println(relations.get(i).getAgrregatClasses().get(j));
			}
			
			drawAgregations(g,relations.get(i).getFistClassName(), relations.get(i).getAgrregatClasses());
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		//drawGrid(g); 
		components = this.getComponents();
		
		/*System.out.println(relations.size());
		
		for( int i = 0; i < relations.size(); i++) 
		{
			System.out.println(relations.get(i).getFistClassName());
			for (int j = 0; j < relations.get(i).getAgrregatClasses().size(); j++) {
				System.out.println(relations.get(i).getAgrregatClasses().get(j));
			}
			
			drawAgregations(relations.get(i).getFistClassName(), relations.get(i).getAgrregatClasses());
		}*/
		
		//drawRelation(components[0], components[3]);
		

		for (Component component : components) {

			int x = component.getLocation().x;
			int y = component.getLocation().y;
			
			
			
			
			drawBorder(component,x, y); 
			
			//drawTriangle(x + component.getWidth()/2 - 10,y+40,"");
			
			//drawTriangle(x + component.getWidth()/2 - 10,y + component.getHeight() - 30);
			
			//drawAgregationSign(x + component.getWidth()/2 - 10,y + component.getHeight() - 30);
			
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
		
		int horizontalLineNumber = height/20;
		
		int space = 20;
		for (int i = 0; i < height/space; i++) {
			g.drawLine(0, i*space, width, i*space);
		}
		
		for (int i = 0; i < width/space; i++) {
			g.drawLine(i*space, 0, i*space, height);
		}
	}
	
	private void drawTriangle(int x , int y ,String direction) 
	{
		
		if(direction.equals("up")) 
		{
			g.drawPolygon(new int[] {x, x + 10, x + 20 }, new int[] {y, y-10, y}, 3);
		}
		else if(direction.equals("left")) 
		{
			g.drawPolygon(new int[] {x, x + 10, x + 10 }, new int[] {y, y-10, y + 10}, 3);
		}
		else if(direction.equals("down")) 
		{
			g.drawPolygon(new int[] {x, x - 10, x + 10 }, new int[] {y, y-10, y - 10}, 3);
		}
		else 
		{
			g.drawPolygon(new int[] {x, x - 10, x - 10 }, new int[] {y, y-10, y + 10}, 3);
		}
	}
	
	private void drawAgregationSign(Graphics g,int x, int y) 
	{
		g.drawPolygon(new int[] {x, x + 10, x + 20, x + 10 }, new int[] {y, y - 10, y  , y + 10}, 4);
	}

	
	private void drawRelation(Graphics g,Component motherClass, Component childClass)
	{
		
		int xs = 0;
		int ys = 0;
		int xe = 0;
		int ye = 0;
		
		if(motherClass.getX() < childClass.getX())
		{
			if(motherClass.getY()  < childClass.getY() ) 
			{
				if(motherClass.getY() + 200 < childClass.getY()) 
				{
					xs = motherClass.getX() + motherClass.getWidth() / 2;
					ys = motherClass.getY() + motherClass.getHeight() - 40;
					
					xe = childClass.getX() + childClass.getWidth() / 2 ;
					ye = childClass.getY() + 40;
				}
				else 
				{
					xs = motherClass.getX() + 40;
					ys = motherClass.getY() + motherClass.getHeight() / 2;
					
					xe = childClass.getX() + childClass.getWidth() + 40 ;
					ye = childClass.getY() + 40;
				}
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + motherClass.getHeight() - 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + 40;
			}
			else 
			{
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + childClass.getHeight() - 40;
			}
			
		}else 
		{
			if(motherClass.getY() < childClass.getY()) 
			{
				if(motherClass.getY() + 200 < childClass.getY()) 
				{
					xs = motherClass.getX() + motherClass.getWidth() / 2;
					ys = motherClass.getY() + motherClass.getHeight() - 40;
					
					xe = childClass.getX() + childClass.getWidth() / 2 ;
					ye = childClass.getY() + 40;
				}
				else 
				{
					xs = motherClass.getX() + 40;
					ys = motherClass.getY() + motherClass.getHeight() / 2;
					
					xe = childClass.getX() + childClass.getWidth() + 40 ;
					ye = childClass.getY() + 40;
				}
				
			}
			else 
			{
				
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() +  40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + childClass.getHeight() - 40;
				
				
			}
		}
		
		
		/*if(motherClass.getX() < childClass.getX()) 
		{
			if(motherClass.getY() < childClass.getY()) 
			{
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() + motherClass.getHeight() - 40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + 40;
			}
			else 
			{
				xs = motherClass.getX() + motherClass.getWidth() / 2;
				ys = motherClass.getY() +  40;
				
				xe = childClass.getX() + childClass.getWidth() / 2;
				ye = childClass.getY() + childClass.getHeight() - 40;
			}
		}
		else 
		{
			
		}*/
		
		drawAgregationSign(g,xs, ys);
		g.drawLine(xs, ys, xe , ye);
		
	}

	private void drawAgregations(Graphics g,String firstClassName, List<String> classesNames) 
	{
		
		int size = classesNames.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			drawRelation(g,getComponentByName(firstClassName),getComponentByName(classesNames.get(i)));
		}
	}

	private Component getComponentByName(String name) 
	{
		for (int i = 0; i < components.length; i++) {
			if(components[i].getName().equals(name)) return components[i]; 
		}
		return null;
	}
	

}
