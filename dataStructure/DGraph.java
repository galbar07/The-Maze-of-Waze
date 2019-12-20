package dataStructure;

import java.util.*; 


public class DGraph implements graph{
	
	static int mcCounter=0;
	HashMap<Integer, node_data> nodesMap = new HashMap<Integer, node_data>();
	HashMap<SrcToDest, edge_data> edgesMap = new HashMap<SrcToDest, edge_data>();
	HashMap<Integer,ArrayList<edge_data>>  nodeNeighbors = new HashMap<Integer,ArrayList<edge_data>>();


	
	@Override
	public node_data getNode(int key) {
		if (nodesMap.containsKey(key))
			return nodesMap.get(key);
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		SrcToDest edgeToCheck = new SrcToDest(src, dest);
		if(edgesMap.containsKey(edgeToCheck))
			return edgesMap.get(edgeToCheck);
		return null;
	}

	@Override
	public void addNode(node_data n) {
		mcCounter++;
		nodesMap.put(n.getKey(), n);		
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(nodesMap.containsKey(src) && nodesMap.containsKey(dest)) {
		mcCounter++;
		EdgeData edge= new EdgeData(src, dest, w); 	
		SrcToDest newEdge = new SrcToDest(src, dest);
		ArrayList<edge_data> addedEdge = new ArrayList<edge_data>();
			if (!edgesMap.containsKey(newEdge) ) {
				if (nodeNeighbors.containsKey(src)) {
					nodeNeighbors.replace(src, addedEdge);						
					addedEdge=nodeNeighbors.get(src);
					addedEdge.add(edge);
				}
				else {
					addedEdge.add(edge);
					nodeNeighbors.put(src, addedEdge);
				}
				edgesMap.put(newEdge, edge);
			}
			addedEdge.add(edge);
			nodeNeighbors.putIfAbsent(src,addedEdge);
		}
		//Add run time exception no suck vertices is found
		
	}

	@Override
	public Collection<node_data> getV() {
		return nodesMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		ArrayList<edge_data> neighbors	= nodeNeighbors.get(node_id);	
		return neighbors;
		
	}

	@Override
	public node_data removeNode(int key) {
		if (nodesMap.containsKey(key) && nodeNeighbors.containsKey(key)) {
			mcCounter++;
			nodeNeighbors.remove(key);
			nodesMap.remove(key);
			for (int i = 0; i < edgesMap.size(); i++) {
				//edgesMap.remove();
			}
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		//////
		mcCounter++;
		return null;
	}

	@Override
	public int nodeSize() {
		return nodesMap.size();
	}

	@Override
	public int edgeSize() {
		return edgesMap.size();
	}

	@Override
	public int getMC() {
		return mcCounter;
	}
	
	
	   class SrcToDest{
		private int src;
		private int dest;
		
		public SrcToDest(int src,int dest) {
			this.src=src;
			this.dest=dest;
		}		
	}

}
