package model;

public class Arch {

	private Node target;
	private int weight;
	
	public Arch(){
		
	}
	
	public Arch(Node target, int weight) {
		this.target = target;
		this.weight = weight;
	}
	
	public Arch(Node target) {
		this(target, 1);
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}	
	
}
