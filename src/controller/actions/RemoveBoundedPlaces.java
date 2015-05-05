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

	@Override
    public void mouseClicked(MouseEvent e) {

		Graph.getInstance().getGraph();

		Transition transition = null;
		Place newPlace = null;
		Arch inbound = null;
		Arch outbound = null;

		for(Node node : Graph.getInstance().getGraph().keySet()){
			for(Arch arch : Graph.getInstance().getEdges(node)){

				if(arch.getTarget() instanceof Place){

					Place targetPlace = (Place) arch.getTarget();

					if(targetPlace.getBound() > 0){

						Point position = new Point(targetPlace.getPosition().x - 100, targetPlace.getPosition().y);

						newPlace = new Place(position, targetPlace.getLabel() + "'",
								targetPlace.getBound() - targetPlace.getTokens());

						outbound = new Arch(node,arch.getWeight());

						// add new inbound arch for the node

						for(Arch arch2 : Graph.getInstance().getEdges(targetPlace)){
							if(arch2.getTarget() instanceof Transition){
								transition = (Transition) arch2.getTarget();
								inbound = new Arch(newPlace, arch2.getWeight());
							}
						}


					}
				}


			}


		}

		if(newPlace != null && transition != null && outbound != null && inbound != null){

			List<Arch> edges = new ArrayList<Arch>();
			edges.add(outbound);
			Graph.getInstance().getGraph().put(newPlace, edges);

			edges = new ArrayList<Arch>();
			edges.add(inbound);
			Graph.getInstance().getGraph().put(transition, edges);
		}

		Canvas.getInstance().repaint();
	}

}
