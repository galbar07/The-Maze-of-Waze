package dataStructure;

import java.util.*; 


public class DGraph implements graph{

	static int mcCounter=0;
	public HashMap<Integer, node_data> nodesMap = new HashMap<Integer, node_data>();
	public HashMap<SrcToDest, edge_data> edgesMap = new HashMap<SrcToDest, edge_data>();
	HashMap<Integer,HashMap<SrcToDest, edge_data>>nodeNeighbors = new HashMap<Integer,HashMap<SrcToDest, edge_data>>();



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
//			HashMap<SrcToDest, edge_data> neighborHash = new HashMap<SrcToDest,edge_data>();
			if (!edgesMap.containsKey(newEdge) ) {
				edgesMap.put(newEdge, edge);

//				if(!nodeNeighbors.containsKey(src)) {
//					if (!nodeNeighbors.containsValue(neighborHash)) {
//						nodeNeighbors.put(src, neighborHash);
//					}
//					neighborHash.put(newEdge, edge);
//					nodeNeighbors.put(src, neighborHash);
//				}
							}
		}
		//Add run time exception no suck vertices is found

	}

	@Override
	public Collection<node_data> getV() {
		return nodesMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		HashMap< SrcToDest, edge_data> h = nodeNeighbors.get(node_id);
		return (Collection<edge_data>) h;

	}

	@Override
	public node_data removeNode(int key) {
		if (nodesMap.containsKey(key) && nodeNeighbors.containsKey(key)) {
			node_data nd = nodesMap.get(key);
			mcCounter++;
			nodeNeighbors.remove(key);
			nodesMap.remove(key);
			edgesMap.entrySet().forEach(entry->{
				if(entry.getValue().getSrc() == key) {
					SrcToDest rmv = new SrcToDest(key, entry.getValue().getDest());
					edgesMap.remove(rmv);
				}

			});
			return nd;
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		SrcToDest src_to_dest= new SrcToDest(src, dest);
		edge_data ed = edgesMap.get(src_to_dest);
		if (edgesMap.containsKey(src_to_dest)) {
			mcCounter++;
			edgesMap.remove(src_to_dest);
			nodeNeighbors.get(src).remove(src_to_dest);			
			return ed;
		}
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


	
	

}
