package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import javax.xml.soap.Node;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import utils.Point3D;

class Test_DGraph {
	
	private Point3D pSrc;
	private Point3D pDest;
	private EdgeData ed;
	private DGraph graph;
	
	@Test
	void milion_vertices() {
		DGraph g = new DGraph();
		Point3D prev=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nodePrev=new NodeData(prev);
		g.addNode(nodePrev);
		Point3D prev0=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nodePrev0=new NodeData(prev0);
		g.addNode(nodePrev0);
		Point3D prev1=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nodePrev1=new NodeData(prev1);
		g.addNode(nodePrev1);
		Point3D p1=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd=new NodeData(p1);
		g.addNode(nd);
		Point3D p=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd1=new NodeData(p);
		g.addNode(nd1);
		Point3D p2=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd2=new NodeData(p2);
		g.addNode(nd2);
		Point3D p3=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd3=new NodeData(p3);
		g.addNode(nd3);
		Point3D p4=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd4=new NodeData(p4);
		g.addNode(nd4);
		Point3D p5=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd5=new NodeData(p5);
		g.addNode(nd5);
		Point3D p6=new Point3D(100+Math.random()*800, 100+Math.random()*800);
		NodeData nd6=new NodeData(p6);
		g.addNode(nd6);
		for (int i = 0; i < 1000000; i++) {
		
			Point3D p7=new Point3D(100+Math.random()*800, 100+Math.random()*800);
			NodeData nd7=new NodeData(p7);
			g.addNode(nd7);
			
			g.connect(nd7.getKey(), nd.getKey(), 1);
			g.connect(nd7.getKey(), nd1.getKey(), 1);
			g.connect(nd7.getKey(), nd2.getKey(), 1);
			g.connect(nd7.getKey(), nd3.getKey(), 1);
			g.connect(nd7.getKey(), nd4.getKey(), 1);
			g.connect(nd7.getKey(), nd5.getKey(), 1);
			g.connect(nd7.getKey(), nd6.getKey(), 1);
			g.connect(nd7.getKey(), nodePrev.getKey(), 1);
			g.connect(nd7.getKey(), nodePrev1.getKey(), 1);
			g.connect(nd7.getKey(), nodePrev0.getKey(), 1);
			
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
		NodeData srcNode=new NodeData(pSrc);
		graph=new DGraph();
		graph.addNode(srcNode);
		if (this.graph.getMC()==0)
			fail("getMC function is not working");
		int m=this.graph.getMC();
		this.graph.removeNode(srcNode.getKey());
		int n=this.graph.getMC();
		if (n==m)
			fail("mcCounter is not synchronized");
	}

}
