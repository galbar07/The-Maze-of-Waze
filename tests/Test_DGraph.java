package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.NodeData;
import gui.Graph_Gui;
import utils.Point3D;

class Test_DGraph {

	@Test
	void milion_vertices() {
		DGraph g = new DGraph();
		
		for (int i = 0; i < 1000000; i++) {
			Point3D p1=new Point3D(100+Math.random()*800, 100+Math.random()*800);
			Point3D p=new Point3D(100+Math.random()*800, 100+Math.random()*800);
			NodeData nd=new NodeData(p1);
			NodeData nd1=new NodeData(p);
			g.addNode(nd);
			g.addNode(nd1);
			g.connect(nd1.getKey(), nd.getKey(), 0);
			
		}
		Graph_Gui gui = new Graph_Gui(g);
	 
	}

}
