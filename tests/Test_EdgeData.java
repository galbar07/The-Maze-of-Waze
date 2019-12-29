package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.EdgeData;
import dataStructure.NodeData;
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
		} catch (Exception e) {
			fail("Should have throw an exception on negative weight");
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
}
