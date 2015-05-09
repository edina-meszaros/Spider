package controller.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.Canvas;

public class RemoveBoundedPlaces extends MouseAdapter {

	private Transition transition = null;
	private Place newPlace = null;
	private Arch inbound = null;
	private Arch outbound = null;

	@Override
    public void mouseClicked(MouseEvent e) {

		removeBounds();
	}

	private void removeBounds() {

		//if(Graph.getInstance().hasBoundedPlace()){

			for(Node node : Graph.getInstance().getGraph().keySet()){
				for(Arch arch : Graph.getInstance().getEdges(node)){

					if(arch.getTarget() instanceof Place){

						Place targetPlace = (Place) arch.getTarget();

						if(targetPlace.getBound() > 0){
							setNewPlace(node, arch, targetPlace);
							targetPlace.setBound(0);
							break;
						}


					}
				}
			}

			createNewPlace();

			Canvas.getInstance().repaint();

			transition = null;
			newPlace = null;
			inbound = null;
			outbound = null;
//		}else{
//			return;
//		}
//
//		removeBounds();
	}

	private void createNewPlace() {
		if(newPlace != null && transition != null && outbound != null && inbound != null){

			List<Arch> edges = new ArrayList<Arch>();
			edges.add(outbound);
			Graph.getInstance().getGraph().put(newPlace, edges);

			List<Arch> edges2 = new ArrayList<Arch>();
			edges2.add(inbound);
			Graph.getInstance().getGraph().put(transition, edges2);
		}
	}

	private void setNewPlace(Node node, Arch arch, Place targetPlace) {
		Point position = new Point(targetPlace.getPosition().x + 45, targetPlace.getPosition().y);

		newPlace = new Place(position, targetPlace.getLabel() + "'",
				targetPlace.getBound() - targetPlace.getTokens());

		outbound = new Arch(node,arch.getWeight());

		for(Arch arch2 : Graph.getInstance().getEdges(targetPlace)){
			if(arch2.getTarget() instanceof Transition){
				transition = (Transition) arch2.getTarget();
				inbound = new Arch(newPlace, arch2.getWeight());
			}
		}
	}

}