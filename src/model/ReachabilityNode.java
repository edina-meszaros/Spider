package model;

import java.util.ArrayList;
import java.util.List;

public class ReachabilityNode {

    private State state = new State();
    private ReachabilityNode parentNode;
    private Transition parentTransition;
    private Boolean old = false;
    private List<ReachabilityNode> children = new ArrayList<>();

    public List<ReachabilityNode> getChildren() {
    	return children;
    }

    public void addChild(ReachabilityNode child) {
    	this.children.add(child);
    }

    public Boolean hasChild(ReachabilityNode child) {
        return this.children.contains(child);
    }

    public void removeChild(ReachabilityNode child) {
        this.children.remove(child);
    }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
    public ReachabilityNode getParentNode() { return parentNode; }
    public void setParentNode(ReachabilityNode parentNode) { this.parentNode = parentNode; }
    public Transition getParentTransition() { return parentTransition; }
    public void setParentTransition(Transition parentTransition) { this.parentTransition = parentTransition; }
    public Boolean isOld() { return old; }
    public void setOld(Boolean old) { this.old = old; }
}
