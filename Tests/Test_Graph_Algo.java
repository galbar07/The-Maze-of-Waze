package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import gui.Graph_Gui;
import utils.Point3D;

class Test_Graph_Algo {
	private NodeData srcNode;
	private NodeData destNode;
	private NodeData nd;
	private Point3D pSrc;
	private Point3D pDest;
	private DGraph graph;
	private Graph_Algo gr;

	@BeforeEach
	void buildGraph(){
		graph=new DGraph();
		this.pSrc=new Point3D(1,1);
		this.pDest=new Point3D(3,3);
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		graph.addNode(srcNode);
		graph.addNode(destNode);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.nd=new NodeData(new Point3D(2,2));
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
			fail("shortestPath algorithm is not working");
		double dist=gr.shortestPathDist(srcNode.getKey(), nd.getKey());
		if (dist!=2)
			fail("shortestPathDist algorithm is not working");
	}

	@Test
	void testTSP() {
		this.graph.connect(this.srcNode.getKey(), this.nd.getKey(), 2);
		NodeData n  = new NodeData(new Point3D(7,7,7));
		this.graph.addNode(n);
		this.gr.init(graph);
		int key = this.graph.nodeSize();
		List<Integer>list = new ArrayList<Integer>();
		for(int i=1;i<=key;i++) {
			list.add(this.graph.getNode(i).getKey());
		}
		List<node_data>tspList = gr.TSP(list);
		String res="";
		if(tspList!=null) 
			fail("TSP algorithm is not working");
		int key2 = this.graph.nodeSize();
		List<Integer>list2 = new ArrayList<Integer>();
		for(int i=1;i<key2;i++) {
			list2.add(this.graph.getNode(i).getKey());
		}
		List<node_data>tspList2 = gr.TSP(list2);
		for(int i=0;i<tspList2.size();i++) {
			if(i==tspList2.size()-1) {
				res+=""+tspList2.get(i).getKey();
			}
			else {
				res+=""+tspList2.get(i).getKey()+"->";
			}
		}
		if (!res.equals("1->2->3"))
			fail("TSP algorithm is not working");
	}

	@Test
	void testCopy() {
		this.gr.init(graph);
		dataStructure.graph g=new DGraph();
		g=this.gr.copy();
		if(this.graph.getNode(srcNode.getKey())!=g.getNode(srcNode.getKey()))
			fail("copy function is not working");
		if(this.graph.getNode(destNode.getKey())!=g.getNode(destNode.getKey()))
			fail("copy function is not working");
		if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getSrc()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getSrc())
			fail("copy function is not working");
		if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getDest()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getDest())
			fail("copy function is not working");
	}
	
	
	
	
	
	@Test
	void testSave() {
		
		gr.init(graph);
		gr.save("test.txt");
	
	}
	
}
