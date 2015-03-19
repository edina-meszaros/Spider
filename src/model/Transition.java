package model;

import java.util.UUID;

public class Transition extends Node {
	
	public Transition(){
		super();
	}
	
	public Transition(String label){
		super(label);
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

	public UUID getId() { return this.getId(); }
	public String getLabel() { return super.getLabel(); }
	public void setLabel(String label) { super.setLabel(label); }	
}
