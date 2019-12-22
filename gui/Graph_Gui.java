package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.SrcToDest;
import utils.Point3D;


public class Graph_Gui extends JFrame implements ActionListener{
	DGraph graph;
	static int i=0;
	
	public Graph_Gui(DGraph graph) {
	this.graph = graph;
	
	
	

	initGUI();
	}
	
	private void initGUI() 
	{
		this.setSize(5000, 5000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("Paint Graph");
		item1.addActionListener(this);
		
		menu.add(item1);
		
	}
	
	public void paint(Graphics g)
	{
		
		
			super.paint(g);
			
			System.out.println("i is"+i++);
			g.setColor(Color.BLUE);
		    g.drawString("hi", 10, 20);
		    graph.nodesMap.entrySet().forEach(entry->{
		    	g.fillOval(entry.getValue().getLocation().ix(),entry.getValue().getLocation().iy(), 15, 15); 
		    			    	
		     });
		    
		    g.setColor(Color.RED);
		   graph.edgesMap.entrySet().forEach(entry->{
			  
			 entry.getValue().entrySet().forEach(entry2->{
				 int num = entry2.getValue().getSrc();
				 int num2 =entry2.getValue().getDest(); 
				Point3D p =  graph.nodesMap.get(num).getLocation();
				Point3D p1 = graph.nodesMap.get(num2).getLocation();
				g.setColor(Color.YELLOW);
				g.fillOval((int)(p.ix()+14),(int)(p.y()+14), 10, 10); 
			    g.setColor(Color.RED);
				   g.drawLine((int)p.x(), (int)p.y(),(int)p1.x(), (int)p1.y());
				   g.setColor(Color.BLACK);
				  g.drawString(Integer.toString((int)entry2.getValue().getWeight()),(int)((p.x()+p1.x())/2),(int)((p.y()+p1.y())/2));
			 });
			   
			   
			   
		   });		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();
		if(str.equals("Paint Graph")) {
			repaint();
		}
		System.out.println(str);
	}
	
	

	}
	
