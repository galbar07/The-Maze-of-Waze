package tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
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
//      Point3D p3 = new Point3D(250,400,0);
//      NodeData n2 = new NodeData(p3);
//      g.addNode(n2);
        
  
        
        
        
          g.connect(1, 2, 20);
          g.connect(2, 1, 40);
//        g.connect(3, 1, 200);
      //  g.removeEdge(3, 1);
        
        Graph_Algo gr = new Graph_Algo();

        gr.init(g);
        System.out.println(gr.isConnected());
		
		System.out.println("before");
		Graph_Gui gui = new Graph_Gui(gr);

		gui.setVisible(true);
		
		
		
		
	}

}
