package model;

import java.awt.Point;

public class Transition extends Node {
	
	public Transition(Point position){
		this(position, "");
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
		final int prime = 11;
		int result = super.hashCode();
		result = prime * result + ((super.getId() == null) ? 0 : super.getId().hashCode());
		return result;
	}
	
	@Override
	public String toString() {		
		return super.toString();
	}

}
