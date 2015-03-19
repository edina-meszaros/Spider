package model;

import java.util.UUID;

public class Place extends Node {

	private Integer tokens = 0;	
	
	public Place(){
		super();
	}

	public Place(String label){
		super(label);
	}
	
	public Place(String label, int tokens){
		super(label);
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
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		result = prime * result + ((this.getLabel() == null) ? 0 : this.getLabel().hashCode());
		result = prime * result + this.tokens;
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
}
