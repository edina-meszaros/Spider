package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import model.Place;
import model.Transition;
import view.style.Theme;
import calculate.Simulation;

public class SimulationDrawer extends EditorDrawer{

	private Simulation simulation;
	private static final int CHAR_HEIGHT = 3;

	public SimulationDrawer(Graphics2D g2, Simulation simulation) {
		super(g2);
		this.simulation = simulation;
	}

	@Override
	public void drawTransition(Transition node, Graphics2D g2) {
		//Draw node
		if (this.simulation.getActivatableTransitions().contains(node)) {

			g2.setColor(Color.GREEN);
			g2.fillRect(node.getPosition().x, node.getPosition().y,
					Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(1.5f));
			g2.drawRect(node.getPosition().x, node.getPosition().y,
					Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

		} else {
			g2.setColor(Theme.LIGHT_GREY);
			g2.fillRect(node.getPosition().x, node.getPosition().y,
					Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

			g2.setColor(Theme.DARK_GREY);


			g2.setStroke(new BasicStroke(1.5f));
			g2.drawRect(node.getPosition().x, node.getPosition().y,
					Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

		}

		g2.setColor(Theme.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));

		// Draw label
		String label = node.getLabel();

		g2.drawString(label,
				(int) (node.getNodeCenterPosition().x - 3.5 * label.length()),
				node.getPosition().y - 5);
	}

	@Override
	protected void drawToken(Place node, Graphics2D g2) {

		int tokenNumber = simulation.getState().getMarking(node).intValue();

		switch (tokenNumber) {
		case 0:
			break;
		case 1:
			g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			break;
		case 2:
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			break;
		case 3:
			g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			break;
		case 4:
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			break;
		case 5:
			g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
			break;
		default:
			String token = String.valueOf(tokenNumber);
			g2.drawString(token,
					(int) (node.getNodeCenterPosition().x - 3.5*token.length()), node.getNodeCenterPosition().y + CHAR_HEIGHT);
			break;
		}
	}




}
