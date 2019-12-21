package tests;

import dataStructure.DGraph;
import dataStructure.NodeData;
import gui.Graph_Gui;
import utils.Point3D;

public class Test_Gui {

	public static void main(String[] args) {
        DGraph g = new DGraph();
		
        Point3D p3 = new Point3D(100,200,0);
        NodeData n = new NodeData(p3);
        g.addNode(n);
        
        
		for (int i = 0; i < 10; i++) {
			Point3D p = new Point3D(Math.random()*1000,Math.random()*500);
			Point3D p1 = new Point3D(Math.random()*1000,Math.random()*500);

			NodeData n4 = new NodeData(p);
			NodeData n5 = new NodeData(p1);
			g.addNode(n4);
			g.addNode(n5);
			
			g.connect(n4.getKey(), n5.getKey(), Math.random()*50);
		}
		System.out.println("before");
		Graph_Gui gui = new Graph_Gui(g);
		gui.setVisible(true);
		
		
	}

}
