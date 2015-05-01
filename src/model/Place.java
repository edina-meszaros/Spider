package model;

import java.awt.Point;
import java.io.Serializable;

public class Place extends Node implements Serializable {

	private Integer tokens = 0;
	private Integer bound = 0;

	public Place() {
		this(new Point(100, 100), "", 0);
	}

	public Place(Point position) {
		this(position, "", 0);
	}

	public Place(Point position, String label) {
		this(position, label, 0);
	}
	
	public Place(Point position, String label, int tokens){
		super(position, label);
		this.tokens = tokens;
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
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getId().toString());
		sb.append(" ;");
		sb.append(super.getLabel());
		sb.append(" ;");
		sb.append(this.tokens.toString());
		sb.append(" ;");
		
		return sb.toString();
	}
	
	public Integer getTokens() { return this.tokens; }
	public void setTokens(int tokens) { this.tokens = tokens; }
	public Integer getBound() { return bound; }
	public void setBound(Integer bound) { this.bound = bound; }	
}
