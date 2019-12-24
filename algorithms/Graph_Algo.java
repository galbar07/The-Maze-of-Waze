package algorithms;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
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
		List<node_data> List =  shortestPath(src, dest);
		return List.size();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {//copy?
		ArrayList<node_data>List = new ArrayList<node_data>();
		Collection<node_data> dij = this.graph_algo.getV();
		PriorityQueue<node_data> queue = new PriorityQueue<>(dij.size());
		//comperator

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
				}

				String str = "";
				int search = dest;
				node_data node = this.graph_algo.getNode(dest);
				while(!str.equals("null")) {
					str = node.getInfo();
					List.add(this.graph_algo.getNode((search)));
					search = Integer.parseInt(str);	
					node = this.graph_algo.getNode(search);				
				}
				//reverse lise here!!
				List.add(this.graph_algo.getNode(src));
			}
		}
	return List;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		return null;
	}

	@Override
	public graph copy() {//For each

		graph copy = new DGraph();
		Collection<node_data>Node_copy = this.graph_algo.getV();
		for (node_data v : Node_copy) {
			copy.addNode(v);
			Collection<edge_data>edge_copy = this.graph_algo.getE(v.getKey());
			for (edge_data E : edge_copy) {
				copy.connect(v.getKey(), E.getDest(), E.getWeight());
			}
			
			
		}
		
		
		
//		Iterator<node_data> itr = Node_copy.iterator();
//
//		while(itr.hasNext()) {//Try for each	
//			copy.addNode(itr.next());
//			Collection<edge_data>edge_copy =this.graph_algo.getE(itr.next().getKey());
//			Iterator<edge_data>itr_edge = edge_copy.iterator();
//			while(itr_edge.hasNext()) {
//				copy.connect(itr.next().getKey(), itr_edge.next().getDest(), itr_edge.next().getWeight());
//			}
//		}
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
				// TODO Auto-generated catch block
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





