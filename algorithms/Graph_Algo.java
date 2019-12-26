package algorithms;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.sun.corba.se.impl.orbutil.graph.NodeData;

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

	@Override
	public void init(graph g) {
		graph_algo = (DGraph) g;
	}

	@Override
	public void init(String file_name) {
		deserialize(file_name);
	}

	@Override
	public void save(String file_name) {
		serialize(file_name); 
	}
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
	
	private void colorWhite(Collection<node_data> v) {
		for (node_data node : v) {
			node.setTag(0);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		shortestPath(src, dest);
		return this.graph_algo.getNode(dest).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		boolean stop = false;
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
		if(node.getWeight()== Double.MAX_VALUE)return null;		
		while(!node.getInfo().equals("null")) {
			str = node.getInfo();
			List.add(this.graph_algo.getNode((search)));
			search = Integer.parseInt(str);	
			node = this.graph_algo.getNode(search);				
		}
		List.add(this.graph_algo.getNode(src));
		Collections.reverse(List);
	return List;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		return null;
	}

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





