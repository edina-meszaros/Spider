package model;

import java.util.UUID;
import java.io.Serializable;

public class Arch implements Serializable {

	private UUID id;
	private Node target;
	private int weight;
	private boolean selected;

	public Arch() {
		this(null, 1);
	}
	
	public Arch(Node target){
		this(target, 1);
	}

	public Arch(Node target, int weight) {
		this.id = UUID.randomUUID();
		this.target = target;
		this.weight = weight;
		this.selected = false;
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}

	@Override
	public int hashCode() {
		final int prime = 11;
		int result = super.hashCode();
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(this.getId().toString());
		sb.append(" ;");
		sb.append(this.getTarget().toString());
		sb.append(" ;");
		sb.append(this.getWeight());
		sb.append(" ;");

		return sb.toString();
	}


	public UUID getId() { return id; }
	public Node getTarget() { return target; }
	public void setTarget(Node target) { this.target = target; }
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }

}
