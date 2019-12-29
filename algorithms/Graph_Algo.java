package algorithms;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.xml.crypto.NodeSetData;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Eden Reuveni
 * @author Gal bar
 *
 */
public class Graph_Algo implements graph_algorithms{

	public graph graph_algo;

	public Graph_Algo() {
		graph_algo = new DGraph();
	}
	/**
	 * Init this set of algorithms on the parameter - graph.
	 * @param g
	 */
	@Override
	public void init(graph g) {
		graph_algo = (DGraph) g;
	}
	/**
	 * Init a graph from file
	 * @param file_name
	 */
	@Override
	public void init(String file_name) {
		deserialize(file_name);
	}
	/** Saves the graph to a file.
	 * 
	 * @param file_name
	 */
	@Override
	public void save(String file_name) {
		serialize(file_name); 
	}
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return the boolean value of the question
	 */
	@Override
	public boolean isConnected() {
		Collection<node_data> forSearch=this.graph_algo.getV();
		for (node_data v : forSearch) {
			int num=myNeighbors(v);
			if (num<forSearch.size())
				return false;
		}
		return true;
	}
	
	/**
	 * Determines which of the nodes in the graph are 
	 * connected by an edge, meaning- this node's neighbors
	 * and counts them
	 * @param v the given node
	 * @return the amount of neighbors
	 */
	private int myNeighbors(node_data v) {
		int ans=0;
		colorWhite(this.graph_algo.getV());
		Stack<node_data> s=new Stack<node_data>();
		s.push(v);
		while (!s.isEmpty()) {
			node_data nd=s.pop();
			if (nd.getTag()==0) {
				ans++;
				ArrayList<node_data> T = allWhiteNeighbors(nd);
				nd.setTag(1);
				for (int i = 0; i < T.size(); i++) {
					s.push(T.get(i));
				}
			}
		}
		return ans;
	}
	/**
	 *  Determines which of this node's neighbors is white,
	 *  meaning- not been visited, with tag=0
	 * @param n the given node
	 * @return a list of all this node neighbors
	 */
	private ArrayList<node_data> allWhiteNeighbors(node_data n) {
		Collection<edge_data> edges = this.graph_algo.getE(n.getKey());
		ArrayList<node_data>List= new ArrayList<node_data>();
		if(edges!=null) {
			for (edge_data ed : edges) {
				if (ed.getTag()==0) {
					List.add(this.graph_algo.getNode(ed.getDest()));
				}
			}
		}
		return List;
	}
	/**
	 *Colors all this node's neighbors white, sets tag to 0
	 * @param v the given node
	 */
	private void colorWhite(Collection<node_data> v) {
		for (node_data node : v) {
			node.setTag(0);
		}
	}
	/**
	 * Returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return the distance of the shortest path between src and dest
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		List <node_data> listFromShortest=shortestPath(src, dest);
		if (listFromShortest==null)
			return Double.MIN_VALUE;
		return this.graph_algo.getNode(dest).getWeight();
	}
	/**
	 * Returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		boolean stop = false;
		if (this.graph_algo.getNode(src)==null || (this.graph_algo.getNode(dest)==null))
				return null;
		ArrayList<node_data>List = new ArrayList<node_data>();
		Collection<node_data> dij = this.graph_algo.getV();
		PriorityQueue<node_data> queue = new PriorityQueue<node_data>();
		for (node_data v : dij) {
			v.setWeight(Double.MAX_VALUE);
			v.setInfo("null");			
		}
		this.graph_algo.getNode(src).setWeight(0);
		queue.addAll(dij);
		this.graph_algo.getNode(src).setInfo("null");
		while (!queue.isEmpty()) {
			node_data nd = queue.poll();

			Collection<edge_data> edges = this.graph_algo.getE(nd.getKey());
			ArrayList<node_data>ListOfNeighbors= new ArrayList<node_data>();
			if(edges!=null) {
				for (edge_data ed : edges) {

					ListOfNeighbors.add(this.graph_algo.getNode(ed.getDest()));

				}
				for (int i = 0; i < ListOfNeighbors.size(); i++) {
					if (ListOfNeighbors.get(i).getWeight()> nd.getWeight() + this.graph_algo.getEdge(nd.getKey(), ListOfNeighbors.get(i).getKey()).getWeight()) {
						ListOfNeighbors.get(i).setWeight(nd.getWeight() + this.graph_algo.getEdge(nd.getKey(), ListOfNeighbors.get(i).getKey()).getWeight());
						ListOfNeighbors.get(i).setInfo(""+nd.getKey());
					}
					if(ListOfNeighbors.get(i).getKey()==dest) {
						stop = true;
						break;
					}
				}
				if(stop) {
					break;
				}
			}
		}
		String str = "";
		int search = dest;
		node_data node = this.graph_algo.getNode(dest);
		if(node.getWeight()== Double.MAX_VALUE)
			return null;		
		while(!node.getInfo().equals("null")) {
			str = node.getInfo();
			List.add(this.graph_algo.getNode((search)));
			search = Integer.parseInt(str);	
			node = this.graph_algo.getNode(search);				
		}
		List.add(this.graph_algo.getNode(src));
		Collections.reverse(List);
		if (!List.contains(this.graph_algo.getNode(dest)))	
			return null;
		return List;
	}
	/**
	 * Computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem, 
	 * as you can visit a node more than once, and there is no need to return to source node - 
	 * just a simple path going over all nodes in the list. 
	 * @param targets the given list of the nodes wanted to go through
	 * @return a list of the nodes visited in this path
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if (targets.isEmpty())
			return null;
		Graph_Algo gr=new Graph_Algo();
		gr.init(this.graph_algo);
		List <node_data> nodesFromTSP = new ArrayList<node_data>();
		int firstNode = targets.get(0);
		for (int i = 1; i < targets.size(); i++) {
			int nextNode=targets.get(i);
			if (gr.shortestPath(firstNode, nextNode)==null)
				return null;
			nodesFromTSP.addAll(gr.shortestPath(firstNode, nextNode));
			nodesFromTSP.remove(graph_algo.getNode(nextNode));
			firstNode=nextNode;
		}
		nodesFromTSP.addAll(gr.shortestPath(firstNode, firstNode));
		return nodesFromTSP;
	}
	/** 
	 * Compute a deep copy of this graph.
	 * @return the copied graph
	 */
	@Override
	public graph copy() {

		graph copy = new DGraph();
		Collection<node_data>Node_copy = this.graph_algo.getV();
		for (node_data v : Node_copy) {
			copy.addNode(v);
		}
		for (node_data v : Node_copy) {
			Collection<edge_data>edge_copy = this.graph_algo.getE(v.getKey());
			if(edge_copy==null) break;
			for (edge_data E : edge_copy) {
				copy.connect(v.getKey(), E.getDest(), E.getWeight());
			}
			
			
		}
		return copy;
	}
/**
 * 
 * @param file_name
 */
	//add info here
	private void serialize(String file_name) {
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(this.graph_algo); 

			out.close(); 
			file.close(); 
		}

		catch(IOException  ex) 
		{ 
			System.out.println("IOException is caught"); 
		}               
	}
/**
 *
 * @param file_name
 */
	//add info here
	private void deserialize(String file_name) {
		graph_algo = new DGraph();
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			try {
				graph_algo = (DGraph)in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			in.close(); 
			file.close(); 
		}
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 


	}
}





