package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;

public class Node {

	private UUID id;
	private String label = null;
	private Point position = null;
	private Rectangle frame = null;
	
	public Node(){
		this.id = UUID.randomUUID();
		frame = new Rectangle();
	}
	
	public Node(Point position){
		this();
		this.position = position;
		setFrame(this.frame);
	}
	
	public Node(Point position, String label){
		this(position);
		this.label = label;
	}
	
	private void setFrame(Rectangle frame) {
        frame.setBounds(position.x, position.y, 40, 40);
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
	public Point getPosition() { return position; }
	public void setPosition(Point position) { this.position = position; }	
}
