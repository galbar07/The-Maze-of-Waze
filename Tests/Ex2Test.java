package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.*;
import dataStructure.*;
import utils.*;
import gui.*;

/**
 * EX2 Structure test:
 * 1. make sure your code compile with this dummy test (DO NOT Change a thing in this test). 
 * 2. the only change require is to run your GUI window (see line 64).
 * 3. EX2 auto-test will be based on such file structure.
 * 4. Do include this test-case in you submitted repository, in folder Tests (under src).
 * 5. Do note that all the required packages are imported - do NOT use other 
 * 
 * @author boaz.benmoshe
 *
 */
class Ex2Test {
	private static graph _graph;
	private static graph_algorithms _alg;
	public static final double EPS = 0.001; 
	private static Point3D min = new Point3D(0,0,0);
	private static Point3D max = new Point3D(100,100,0);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		_graph = tinyGraph();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConnectivity() {
		_alg = new Graph_Algo(_graph);
		boolean con = _alg.isConnected();
		if(!con) {
			fail("shoulbe be connected");
		}
	}
	@Test
	void testgraph() {
		boolean ans = drawGraph(_graph);
		assertTrue(ans);
	}
	
	private static graph tinyGraph() {
		graph ans = new DGraph();
		return ans;
	}
	boolean drawGraph(graph g) { 
	        Point3D p1 = new Point3D(-200,200,0);
	        NodeData n = new NodeData(p1);
	        g.addNode(n);
	        
	        Point3D p2 = new Point3D(100,-100,0);
	        NodeData n1 = new NodeData(p2);
	        g.addNode(n1);
	        
	        
	        
	        Point3D p3 = new Point3D(300,150,0);
	        NodeData n3 = new NodeData(p3);
	        g.addNode(n3);
	        
	        g.connect(1, 2, 10);
	        g.connect(2, 1, 100);
	        g.connect(2, 3, 40);
	        g.connect(3, 1, 100);
	      
			Graph_GUI gui = new Graph_GUI(g);

			gui.setVisible(true);
		    return true;
		
	}

}