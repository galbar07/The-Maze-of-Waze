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
		this.srcNode=new NodeData(pSrc);
		this.destNode=new NodeData(pDest);
		this.ed=new EdgeData(srcNode.getKey(), destNode.getKey(), 10);
		
	}
	

	@Test
	void testGetDest() {
		assertTrue(ed.getDest()==2);
	}
	@Test
	void testGetSrc() {
		//check why it's not working
		assertTrue(ed.getSrc()==1);
	}
	
	@Test
	void testGetWeight() {
		if (this.ed.getWeight()!=10)
			fail("getWeight function is not working");
	}
	
	@Test
	void testSetInfo() {
		this.ed.setInfo("Info");
		assertTrue(ed.getInfo().equals("Info"));
	}
	@Test
	void testGetInfo() {
		if (!this.ed.getInfo().equals(""))
			fail("getInfo function is not working");
	}
	
	@Test
	void testSetTag() {
		this.ed.setTag(5);
		if (this.ed.getTag()!=5)
			fail("setTag function is not working");
	}
	
	@Test
	void testGetTag() {
		if (this.ed.getTag()!=0)
			fail("getTag function is not working");
	}
}
