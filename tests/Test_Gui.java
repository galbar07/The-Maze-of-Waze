package tests;

import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_Gui;
import utils.Point3D;

public class Test_Gui {

	public static void main(String[] args) {
        DGraph g = new DGraph();
		
        Point3D p1 = new Point3D(-200,200,0);
        NodeData n = new NodeData(p1);
        g.addNode(n);
        
        Point3D p2 = new Point3D(100,-100,0);
        NodeData n1 = new NodeData(p2);
        g.addNode(n1);
        
        
        
        Point3D p3 = new Point3D(300,150,0);
        NodeData n3 = new NodeData(p3);
        g.addNode(n3);
        
        g.connect(1, 2, 10);
        g.connect(2, 1, 100);
        g.connect(2, 3, 40);
        g.connect(3, 1, 100);
      
		Graph_Gui gui = new Graph_Gui(g);

		gui.setVisible(true);
		
		
		
		
	}

}
