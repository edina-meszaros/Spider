package model;

import java.awt.Point;
import java.util.UUID;

public class Transition extends Node {
	
	public Transition(Point position){
		super(position);
	}
	
	public Transition(Point position, String label){
		super(position, label);
	}
	
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {		
		return super.toString();
	}

	public UUID getId() { return super.getId(); }
	public String getLabel() { return super.getLabel(); }
	public void setLabel(String label) { super.setLabel(label); }
	public Point getPosition() { return super.getPosition(); }
	public void setPosition(Point position) { super.setPosition(position); }
}
