package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import junit.framework.AssertionFailedError;
import utils.Point3D;

class Test_EdgeData {
	private NodeData srcNode;
	private NodeData destNode;
	private Point3D pSrc;
	private Point3D pDest;
	private EdgeData ed;


	@BeforeEach
	void buildEdge(){

		this.pSrc=new Point3D(1,1,1);
		this.pDest=new Point3D(3,3,3);
	}



	@Test
	void testGetWeight() {
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);

		if (this.ed.getWeight()!=10)
			fail("getWeight function is not working");
		try {
			EdgeData newEdge=new EdgeData(destNode.getKey(), srcNode.getKey(), -1);
			fail("Should have throw an exception on negative weight");
		} catch (Exception e) {
		}
	}

	@Test
	void testSetInfo() {
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);

		this.ed.setInfo("Info");
		assertTrue(ed.getInfo().equals("Info"));
	}
	@Test
	void testGetInfo() {
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);

		if (!this.ed.getInfo().equals(""))
			fail("getInfo function is not working");
	}

	@Test
	void testSetTag() {
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);

		this.ed.setTag(5);
		if (this.ed.getTag()!=5)
			fail("setTag function is not working");
	}

	@Test
	void testGetTag() {
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);

		if (this.ed.getTag()!=0)
			fail("getTag function is not working");
	}


	@Test
	void testSameEdges() {

		DGraph g = new DGraph();

		Point3D p1 = new Point3D(-200,200,0);
		NodeData n = new NodeData(p1);
		g.addNode(n);

		Point3D p2 = new Point3D(100,-100,0);
		NodeData n1 = new NodeData(p2);
		g.addNode(n1);
		
		NodeData nd=new NodeData(this.pDest);
		
		g.connect(n.getKey(), n1.getKey(), 100);
		
		g.connect(n.getKey(), n1.getKey(), 20);

		if (g.getEdge(n.getKey(), n1.getKey()).getWeight()!=100)
			fail ("Didn't need to update the weight");
		try {
			g.connect(n.getKey(), nd.getKey(), 10);
			fail("Should've thrown an exception");
		} catch (Exception e) {

		}


	}
}
