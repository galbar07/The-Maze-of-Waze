package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;


import javax.swing.JFrame;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;
import dataStructure.*;


public class Graph_Gui extends JFrame implements ActionListener,MouseListener{
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
		
		StdDraw.isMousePressed();
		
		
		StdDraw.setCanvasSize(1000,1000);
		Font font = new Font("Arial", Font.BOLD, 20);

		double max_x = Double.MIN_VALUE;
		double max_y = Double.MIN_VALUE;
		Collection<node_data>search = this.graph.getV();
		for (node_data v : search) {
			max_x = Math.max(max_x, v.getLocation().x());
			max_y = Math.max(max_y, v.getLocation().y());
			
		}		
		StdDraw.setXscale(-1*max_x,max_x+100);
		StdDraw.setYscale(-1*max_y,max_y+100);
		
		StdDraw.setPenRadius(0.02);
		Collection<node_data> Paint_node = this.graph.getV();
		for (node_data v : Paint_node) {
			StdDraw.setPenColor(Color.black);
			StdDraw.point(v.getLocation().x(), v.getLocation().y());
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.setFont(font);
			StdDraw.text(v.getLocation().x(), v.getLocation().y()+7, Integer.toString(v.getKey()));
		}
		StdDraw.setPenRadius(0.005);
		for (node_data v : Paint_node) {
			Collection<edge_data> Paint_edges = this.graph.getE(v.getKey());
			if(Paint_edges==null)
				break;
				for(edge_data E: Paint_edges) {
					Point3D p1 = pointreturn(E.getDest());
					Point3D p2 = pointreturn(E.getSrc());
				    if(p1!=null && p2!=null) {
				    	StdDraw.setPenRadius(0.005);
				    	StdDraw.setPenColor(Color.BLACK);
					StdDraw.line(p1.x(), p1.y(),p2.x(), p2.y());
					StdDraw.text((int)((p1.x()+p2.x())/2),(int)((p1.y()+p2.y())/2), Integer.toString((int)E.getWeight()));
					StdDraw.setPenColor(Color.PINK);
					StdDraw.setPenRadius(0.02);
					
					Point3D p4 = new Point3D((int)((p1.x()+p2.x())/2),(int)((p1.y()+p2.y())/2));
					
					
					for(int i=0;i<2;i++) {
						Point3D p5 = new Point3D((int)(p4.x()+p1.x())/2,(int)(p4.y()+p1.y())/2);
						p4 = new Point3D(p5);
					}
					StdDraw.point(p4.x(),p4.y());
			    }
				}
		   
			
			
		}	
}
		
		
		
		
		
		


		
	
	
	public void paint(Graphics g)
	{
		
		
			super.paint(g);
			//g.fillOval(0, 0, 100, 100);
			g.setColor(Color.BLUE);
			Collection<node_data> Paint_node = this.graph.getV();
			for (node_data v : Paint_node) {
				g.fillOval(v.getLocation().ix(), v.getLocation().iy(), 10, 10);
			}
	
			g.setColor(Color.red);
			for (node_data v : Paint_node) {
				Collection<edge_data> Paint_edges = this.graph.getE(v.getKey());
				if(Paint_edges==null)
					break;
					for(edge_data E: Paint_edges) {
						Point3D p1 = pointreturn(E.getDest());
						Point3D p2 = pointreturn(E.getSrc());
					    if(p1!=null && p2!=null) {
						g.drawLine((int)p1.x(), (int)p2.y(),(int)p2.x(), (int)p1.y());
						g.drawString(Integer.toString((int)E.getWeight()), (int)((p1.x()+p2.x())/2),(int)((p1.y()+p2.y())/2));
					    }
					}
			   
				
				
			}	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse enertrd");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point3D p = new Point3D(e.getX(),e.getY());
		NodeData n = new NodeData(p);
		this.graph.addNode(n);
		System.out.println("x is" + e.getX());
		System.out.println("y is" + e.getY());
		initGUI();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	}
	
