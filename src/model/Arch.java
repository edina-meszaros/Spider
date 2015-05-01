package model;

public class Arch {

	private Node target;
	private int weight;
	private boolean selected;
	
	public Arch(Node target){
		this(target, 1);
	}
	
	public Arch(Node target, int weight) {
		this.target = target;
		this.weight = weight;
		this.selected = false;
	}
	
	public Node getTarget() { return target; }
	public void setTarget(Node target) { this.target = target; }
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }	
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
}
