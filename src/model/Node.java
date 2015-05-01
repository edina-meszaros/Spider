package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;

import view.style.Theme;

public class Node implements Serializable {

	private UUID id;
	private String label = "";
	private Point position = null;
	private boolean selected;

	public Node() {
		this(new Point(100, 100), "");
	}
	
	public Node(Point position){
		this(position, "");
	}
	
	public Node(Point position, String label){
		this.id = UUID.randomUUID();
		this.selected = false;
		this.position = position;
		this.label = label;
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
	
	public Point getNodeCenterPosition(){
		return new Point(this.position.x + Theme.NODE_CENTER, this.position.y + Theme.NODE_CENTER);
	}

	public UUID getId() { return this.id; }
	public String getLabel() { return this.label; }
	public void setLabel(String label) { this.label = label; }
	public Point getPosition() { return this.position; }
	public void setPosition(Point position) { this.position = position; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
}
