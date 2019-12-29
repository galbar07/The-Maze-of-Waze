package dataStructure;

import java.util.*;

public class DGraph implements graph{

	static int mcCounter=0;
	public HashMap<Integer, node_data> nodesMap;
	public HashMap<Integer,HashMap<Integer,edge_data> > edgesMap;
	
   /**Default constructor
    * 
    */
	public DGraph() {
		 nodesMap = new HashMap<Integer, node_data>();
		 edgesMap = new HashMap<Integer,HashMap<Integer,edge_data> >();

	}
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_data getNode(int key) {
		if (nodesMap.containsKey(key))
			return nodesMap.get(key);
		return null;
	}
	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src the given src node key
	 * @param dest the given dest node key
	 * @return the edge that goes from src to dest
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		return edgesMap.get(src).get(dest);
	}
	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n the given node to add the graph
	 */
	@Override
	public void addNode(node_data n) {
		mcCounter++;
		nodesMap.put(n.getKey(), n);

	}
	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	@Override
	public void connect(int src, int dest, double w) {
		if(nodesMap.containsKey(src) && nodesMap.containsKey(dest)&&w>=0) {
			mcCounter++;
			EdgeData edgeToInsert = new EdgeData(src, dest, w);
			if(edgesMap.containsKey(src)) {
				
				if(!edgesMap.get(src).containsKey(dest)) {
					edgesMap.get(src).put(dest, edgeToInsert);	
					
				}
				
			}
			else {
			HashMap<Integer,edge_data> innerEdgeHash = new HashMap<Integer,edge_data>(); 
			innerEdgeHash.put(dest, edgeToInsert);
			edgesMap.put(src, innerEdgeHash);
			}
				
			
		}
		else {throw new RuntimeException("No such dest or src is found or the weight is negative");
		}	
	}
	
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data> 
	 */
	@Override
	public Collection<node_data> getV() {
		return nodesMap.values();
	}
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		if(edgesMap.containsKey(node_id)) {
		return edgesMap.get(node_id).values();}
		return null;

	}
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key the key of the node to be deleted
	 */
	@Override
	public node_data removeNode(int key) {
		if(nodesMap.containsKey(key)) {
			mcCounter++;
			node_data  nd = nodesMap.get(key); 
			nodesMap.remove(key);
			edgesMap.remove(key);
			edgesMap.entrySet().forEach(entry->{///return to this remove all the destinon node
					
					if(entry.getValue().containsKey(key)) {
						entry.getValue().remove(key);
						
					}
			});
			
		return nd;	
		}
		return null;
		
	}
	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src the given src node key
	 * @param dest the given dest node key
	 * @return the data of the removed edge (null if none).
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		if(edgesMap.containsKey(src) && edgesMap.get(src).containsKey(dest)) {
			mcCounter++;
			edge_data ed = edgesMap.get(src).get(dest);
			edgesMap.get(src).remove(dest);
			return ed;			
		}
		return null;
	}
	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return this size
	 */
	@Override
	public int nodeSize() {
		return nodesMap.size();
	}
	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return this size
	 */
	@Override
	public int edgeSize() {
		return edgesMap.size();
	}
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return the static counter mcCounter
	 */
	@Override
	public int getMC() {
		return mcCounter;
	}


	
	

}
