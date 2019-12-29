package tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import gui.Graph_Gui;
import utils.Point3D;

public class testing {
	

	public static void main(String[] args) {
		 NodeData srcNode;
		 NodeData destNode;
		 NodeData nd;
		 Point3D pSrc;
		 Point3D pDest;
		 DGraph graph;
		 Graph_Algo gr;
		graph=new DGraph();
		pSrc=new Point3D(100,233);
		pDest=new Point3D(300,323);
		srcNode=new NodeData(pSrc);
		destNode=new NodeData(pDest);
		graph.addNode(srcNode);
		graph.addNode(destNode);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		nd=new NodeData(new Point3D(256,234));
		graph.addNode(nd);
		graph.connect(destNode.getKey(),nd.getKey(), 3);
		graph.connect(nd.getKey(),srcNode.getKey(), 4);
		gr = new Graph_Algo();
		gr.init(graph);
		gr.save("test.txt");
//		gr.init("test.txt");
			
		
		
//		Graph_Gui g = new Graph_Gui(gr);
//		g.setVisible(true);

	}

}
