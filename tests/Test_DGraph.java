package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.soap.Node;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import gui.Graph_Gui;
import utils.Point3D;

class Test_DGraph {
	
	private Point3D pSrc;
	private Point3D pDest;
	private EdgeData ed;
	private DGraph graph;
	
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
	 
	}
	
	@BeforeEach
	void buildEdge(){
		this.graph=new DGraph();
		this.pSrc=new Point3D(1,1,1);
		this.pDest=new Point3D(3,3,3);
		
		
	}
	
	@Test
	void testGetNode() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();
		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		if (this.graph.getNode(srcNode.getKey())!=srcNode)
			fail("getNode is not working");
			
	}
	
	@Test
	void testGetEdge() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph.addNode(srcNode);
		graph.addNode(destNode);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		if (this.graph.getEdge(srcNode.getKey(), destNode.getKey()).getSrc()!=this.ed.getSrc())
			fail ("getEdge is not working");
		if (this.graph.getEdge(srcNode.getKey(), destNode.getKey()).getDest()!=this.ed.getDest())
			fail ("getEdge is not working");
	}
	
	@Test 
	void testAddNode() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		NodeData nd=new NodeData(new Point3D(5,5,5));
		this.graph.addNode(nd);
		if (this.graph.getV().size()!=3)
			fail("addNode function is not working");
	}
	
	@Test
	void testConnect() {
		NodeData n = new NodeData(new Point3D(2,2,2));
		NodeData n1 = new NodeData(new Point3D(4,4,4));
		
		this.graph.addNode(n);
		this.graph.addNode(n1);
		this.graph.connect(n.getKey(), n1.getKey(), 1);
		if (this.graph.getE(n.getKey()).size()!=1)
			fail ("connect function is not working");
	}
	
	@Test
	void testGetV() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		if (this.graph.getV().size()!=2)
			fail ("getV function is not working");
	}

	@Test
	void testGetE() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		if (this.graph.getE(srcNode.getKey()).size()!=1)
			fail ("getE function is not working");
	}
	
	@Test
	void testRemoveNode() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.graph.removeNode(srcNode.getKey());
		if (this.graph.getV().size()!=1)
			fail("removeNode function is not working");
	}
	
	@Test
	void testRemoveEdge() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.graph.removeEdge(srcNode.getKey(), destNode.getKey());
		if (this.graph.getE(srcNode.getKey()).size()!=0)
			fail("removeEdge function is not working");
	}
	
	@Test
	void testNodeSizeAndEdgeSize() {
		NodeData srcNode=new NodeData(pSrc);
		NodeData destNode=new NodeData(pDest);
		graph=new DGraph();

		graph.addNode(srcNode);
		graph.addNode(destNode);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 12);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		if (this.graph.nodeSize()!=2)
			fail("nodeSize function is not working");
			if (this.graph.edgeSize()!=1)
				fail("edgeSize function is not working");
	}
	
	@Test
	void testGetMC() {
		if (this.graph.getMC()==0)
			fail("getMC function is not working");
	}

}
