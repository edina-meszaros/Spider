package model;

import java.util.UUID;

public class Node {

	private UUID id;
	private String label = null;
	
	public Node(){
		generateId();
	}
	
	public Node(String label){
		this();
		this.label = label;
	}
	
	private void generateId(){
		this.id = UUID.randomUUID();		
	}
	
	@Override
	public boolean equals(Object object) {
		if ( this == object ) return true;
		if ( !(object instanceof Node) ) return false;
		
		Node node = (Node) object;		
		if(this.id.equals(node.getId())) return true;
		
		if (this.label == null) {
			if (node.getLabel() != null)
				return false;
		} else if (this.label.equals(node.getLabel()))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.label == null) ? 0 : this.label.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.id.toString());
		sb.append(" ;");
		sb.append(this.label);
		sb.append(" ;");
		
		return sb.toString();
	}

	public UUID getId() { return this.id; }
	public String getLabel() { return this.label; }
	public void setLabel(String label) { this.label = label; }	
}
