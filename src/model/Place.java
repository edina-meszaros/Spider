package model;

import java.awt.Point;
import java.util.UUID;

public class Place extends Node {

	private Integer tokens = 0;	
	
	public Place(){
		
	}
	public Place(Point position){
		super(position);
	}

	public Place(Point position, String label){
		super(position, label);
	}
	
	public Place(Point position, String label,  int tokens){
		super(position, label);
		this.tokens = tokens;
	}
	
	@Override
	public boolean equals(Object object) {
		if(super.equals(object) && this.tokens == ((Place) object).getTokens()){
			return true;
		}

		return false;
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
		sb.append(this.getLabel());
		sb.append(" ;");
		sb.append(this.tokens.toString());
		sb.append(" ;");
		
		return sb.toString();
	}
	
	public UUID getId(){ return super.getId(); }
	public String getLabel() { return super.getLabel(); }
	public void setLabel(String label) { super.setLabel(label); }
	public Integer getTokens() { return this.tokens; }
	public void setTokens(int tokens) { this.tokens = tokens; }
	public Point getPosition() { return super.getPosition(); }
	public void setPosition(Point position) { super.setPosition(position); }
	
}
