package dataStructure;

import utils.Point3D;

public class NodeData implements node_data, Comparable<node_data> {
	static int counter=1;
	private int key;
	private Point3D location=null;
	private double weight=Double.MAX_VALUE;
	private String info;
	private int tag=0;
	
	public NodeData(Point3D p) { //check which other constructrs are there
		this.key=counter++;
		this.location=p; //need to deep copy p
		this.weight=Double.MAX_VALUE;
		this.info=""; //need to check wtf is info
		this.tag=0;	
	}
	
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location=p;		
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		if (w<0)
			throw new RuntimeException("The weight must be positive");
		this.weight=w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}


	@Override
	public int compareTo(node_data n) {
		if (this.getWeight()>n.getWeight())
			return 1;
		else if (this.getWeight()<n.getWeight())
			return -1;
		else
			return 0;
	}
	

}
