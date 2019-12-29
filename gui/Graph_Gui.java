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
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;
import dataStructure.*;


public class Graph_Gui extends JFrame implements ActionListener, MouseListener {
	graph graph;
	static int i=0;

	public Graph_Gui(DGraph graph) {
		this.graph = graph;
		initGUI();
	}

	private void initGUI()  
	{	
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		Menu menu2 = new Menu("Algorithams");
		menuBar.add(menu);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("Draw Graph");
		item1.addActionListener(this);
		MenuItem item2 = new MenuItem("Draw from file");
		item2.addActionListener(this);
		MenuItem item3 = new MenuItem("Save to file");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("Is connected");
		item4.addActionListener(this);
		MenuItem item5 = new MenuItem("find Shortest path");
		item5.addActionListener(this);
		MenuItem item6= new MenuItem("find Shortest path distance");
		item6.addActionListener(this);
		MenuItem item7= new MenuItem("TSP");
		item7.addActionListener(this);

		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		menu2.add(item7);
		this.addMouseListener(this);

	}	
	public void paint()
	{
		StdDraw.setCanvasSize(800,800);
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
	private void drawfromfile() {
		JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnV = jf.showOpenDialog(null);
		Graph_Algo gr = new Graph_Algo();
		if (returnV == JFileChooser.APPROVE_OPTION) {
			File selected = jf.getSelectedFile();
			gr.init(selected.getAbsolutePath());
		}
		this.graph = gr.copy();
	}

	private void Savetofile() {
		Graph_Algo gr = new Graph_Algo();
		gr.init(this.graph);
		JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnV = jf.showOpenDialog(null);
		if (returnV == JFileChooser.APPROVE_OPTION) {
			gr.save(jf.getSelectedFile()+".txt");
		}	
	}

	private void Shortest_path_distance() {
		try {
			JFrame Show = new JFrame();
			paint();
			String Src = JOptionPane.showInputDialog(Show,"Enter Source-Node:");
			String Dest = JOptionPane.showInputDialog(Show,"Enter Destination-Node:");	
			int src = Integer.parseInt(Src);
			int dest = Integer.parseInt(Dest);	
			Graph_Algo gr = new Graph_Algo();
			gr.init(this.graph);
			paint();
			double num = gr.shortestPathDist(src, dest);
			JOptionPane.showMessageDialog(Show,"The 'cost' of the shortest path is: " + num);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void isconneted() {

		JFrame Show = new JFrame();
		Graph_Algo gr = new Graph_Algo();
		gr.init(this.graph);
		paint();
		if (gr.isConnected()) 
			JOptionPane.showMessageDialog(Show,"The Graph is connected");	
		else
			JOptionPane.showMessageDialog(Show,"The Graph is not connected");	
	}



	private void Shortest_path() {
		try {
			JFrame Show = new JFrame();
			paint();
			String Src = JOptionPane.showInputDialog(Show,"Enter Source Node:");
			String Dest = JOptionPane.showInputDialog(Show,"Enter Destination Node:");

			int src = Integer.parseInt(Src);
			int dest = Integer.parseInt(Dest);

			Graph_Algo gr = new Graph_Algo();
			gr.init(this.graph);
			String res = "";			
			List<node_data> list = gr.shortestPath(src, dest);
			if (list==null)
				JOptionPane.showMessageDialog(Show,"There is no path between the source and destinetion node");	
			else {

				for(int i=0;i<list.size();i++) {
					if(i==list.size()-1) {
						res+=list.get(i).getKey();
					}
					else {
						res+=list.get(i).getKey()+"->";
					}

				}
				JOptionPane.showMessageDialog(Show,"The Graph Shorthest path from src to dest is : " + res);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}


	}



	private void tsp() {

		Graph_Algo gr = new Graph_Algo();
		gr.init(this.graph);

		JFrame Show = new JFrame();
		paint();
		String num = JOptionPane.showInputDialog(Show,"Enter number of elements:");
		int key = Integer.parseInt(num);
		List<Integer>list = new ArrayList<Integer>();
		for(int i=0;i<key;i++) {
			String num2 = JOptionPane.showInputDialog(Show,"Enter vertices:");
			int key2 = Integer.parseInt(num2);
			list.add(key2);
		}
		List<node_data>tspList = gr.TSP(list);
		String res="";
		if(tspList==null) {
			JOptionPane.showMessageDialog(Show,"There is no path between all nodes you entered");

		} 
		else {
			for(int i=0;i<tspList.size();i++) {

				if(i==tspList.size()-1) {
					res+=""+tspList.get(i).getKey();
				}
				else {
					res+=""+tspList.get(i).getKey()+"->";
				}
			}
			JOptionPane.showMessageDialog(Show,"The Graph relatively short path between the vertices you entered is: " + res);	
		}
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
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch(str)
		{
		case "Draw Graph":
			paint();
			break;
		case "Draw from file": drawfromfile();
		break;
		case "Save to file": Savetofile();
		break;
		case "find Shortest path distance":Shortest_path_distance();
		break;
		case "find Shortest path":Shortest_path();
		break;
		case "Is connected":isconneted();
		break;
		case "TSP":tsp();
		break;
		}
	}
}


