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
		
        Point3D p1 = new Point3D(100,200,0);
        NodeData n = new NodeData(p1);
        g.addNode(n);
        
        Point3D p2 = new Point3D(400,200,0);
        NodeData n1 = new NodeData(p2);
        g.addNode(n1);
        
        
        Point3D p3 = new Point3D(10,10,0);
        NodeData n3 = new NodeData(p3);
        g.addNode(n3);
//      Point3D p3 = new Point3D(250,400,0);
//      NodeData n2 = new NodeData(p3);
//      g.addNode(n2);
        
  
        
        
        
          g.connect(1, 2, 20);
          g.connect(3, 1, 200);
        //  g.connect(2, 3, 100);
      //  g.removeEdge(3, 1);
        
        Graph_Algo gr = new Graph_Algo();

        gr.init(g);
        System.out.println(gr.isConnected());
		
		System.out.println("before");
		
		List<node_data>print =  gr.shortestPath(1, 2);
		for (int i = 0; i < print.size(); i++) {
			System.out.println(print.get(i).getKey() + ",");
		}
		//System.out.println(node_to_print.toString());
		
		
		Graph_Gui gui = new Graph_Gui(gr);

		//gui.setVisible(true);
		
		
		
		
	}

}
