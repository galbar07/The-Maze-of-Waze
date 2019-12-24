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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.SrcToDest;
import dataStructure.node_data;
import utils.Point3D;
import dataStructure.*;


public class Graph_Gui extends JFrame implements ActionListener{
	graph graph;
	static int i=0;
	
	public Graph_Gui(DGraph graph) {
	this.graph = graph;
	initGUI();
	}
	
	public Graph_Gui(Graph_Algo gr) {
		this.graph = gr.graph_algo;
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
			g.setColor(Color.BLUE);
			Collection<node_data> Paint_node = this.graph.getV();
			for (node_data v : Paint_node) {
				g.fillOval(v.getLocation().ix(), v.getLocation().iy(), 10, 10);
			}
	
			g.setColor(Color.red);
			for (node_data v : Paint_node) {
				Collection<edge_data> Paint_edges = this.graph.getE(v.getKey());
					for(edge_data E: Paint_edges) {
						Point3D p1 = pointreturn(E.getDest());
						Point3D p2 = pointreturn(E.getSrc());
					    if(p1!=null && p2!=null) {
						g.drawLine((int)p1.x(), (int)p2.y(),(int)p2.x(), (int)p1.y());
						g.drawString(Integer.toString((int)E.getWeight()), (int)((p1.x()+p2.x())/2),(int)((p1.y()+p2.y())/2));
					    }
					}
			   
				
				
			}
			
			
			
			
			
			
//			System.out.println("i is"+i++);
//			g.setColor(Color.BLUE);
//		    g.drawString("hi", 10, 20);
//		    graph.nodesMap.entrySet().forEach(entry->{
//		    	g.fillOval(entry.getValue().getLocation().ix(),entry.getValue().getLocation().iy(), 15, 15); 
//		    			    	
//		     });
		    
//		    g.setColor(Color.RED);
//		   graph.edgesMap.entrySet().forEach(entry->{
//			  
//			   	entry.getValue().entrySet().forEach(entry2->{
//				int num = entry2.getValue().getSrc();
//				int num2 =entry2.getValue().getDest(); 
//				Point3D p =  graph.nodesMap.get(num).getLocation();
//				Point3D p1 = graph.nodesMap.get(num2).getLocation();
//				g.setColor(Color.YELLOW);
//				g.fillOval((int)(p.ix()+14),(int)(p.y()+14), 10, 10); 
//			    g.setColor(Color.RED);
//				g.drawLine((int)p.x(), (int)p.y(),(int)p1.x(), (int)p1.y());
//				g.setColor(Color.BLACK);
//				g.drawString(Integer.toString((int)entry2.getValue().getWeight()),(int)((p.x()+p1.x())/2),(int)((p.y()+p1.y())/2));
//			 });
//			   
//			   
//			   
//		   });		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();
		if(str.equals("Paint Graph")) {
			repaint();
		}
		System.out.println(str);
	}
	
	
	private Point3D pointreturn(int key) {
		Collection<node_data> Paint_node = this.graph.getV();
		for (node_data v : Paint_node) {
			if(v.getKey() == key) {
				return v.getLocation();
			}
		}
		return null;
	}
	
	

	}
	
