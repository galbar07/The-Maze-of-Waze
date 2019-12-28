package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;

class Test_Graph_Algo {
	private NodeData srcNode;
	private NodeData destNode;
	private NodeData nd;
	private Point3D pSrc;
	private Point3D pDest;
	private EdgeData ed;
	private DGraph graph;
	private Graph_Algo gr;
	
	@BeforeEach
	void buildEdge(){
		graph=new DGraph();
		this.pSrc=new Point3D(1,1,1);
		this.pDest=new Point3D(3,3,3);
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.nd=new NodeData(new Point3D(2,2,2));
		this.graph.addNode(nd);
		this.graph.connect(destNode.getKey(),nd.getKey(), 3);
		this.graph.connect(nd.getKey(),srcNode.getKey(), 4);
		gr = new Graph_Algo();
		
	}
	
	@Test
	void testIsConnected() {
        this.gr.init(graph);
		assertTrue(gr.isConnected());
	}
	
	@Test
	void testShortestPathAndDist() {
	
		this.graph.connect(srcNode.getKey(), nd.getKey(), 2);
	      this.gr.init(graph);
	      ArrayList<node_data>List = new ArrayList<node_data>();
	      List=(ArrayList<node_data>) gr.shortestPath(srcNode.getKey(), nd.getKey());
	      if (List.size()!=2)
	    	  fail("shortestPath algorithem is not working");
	      double dist=gr.shortestPathDist(srcNode.getKey(), nd.getKey());
	      if (dist!=2)
	    	  fail("shortestPathDist algorithem is not working");
	}

	@Test
	void testTSP() {
		
	}
	
	@Test
	void testCopy() {
		Graph_Algo g=new Graph_Algo();
		g=(Graph_Algo) this.gr.copy();
		if (!g.equals(gr))
			fail("copt function is not working");
	}
}
