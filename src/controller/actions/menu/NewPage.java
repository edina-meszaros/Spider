package controller.actions.menu;

import model.Graph;
import view.Canvas;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class NewPage extends AbstractAction {

	private static final String icon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAAALEwEAmpwYAAABMElEQVR4nKXTO04DQQwG4G/zUBoeSYGoUBpugAQ3oKPhGHAdwjUi0dEgoESiIg0lNAgoIBAogOxSxKNMUkVgaWSvx/9ve+yFFo4wRLXgGQamVYRxaCLPaOAn05WpFKHXQvcKfGGMfdxihCW8oY1aRlKL2E30UReXj8Hax0bodVxggJs4A1wG8ROqAmVk24ks71gOXycrO7UwjnOFlSIqKHEf7eRv8J0Bq4ysFZXW0sUQW5E9AcroOdkJXEWF11htZM4HfJqV0wDuzvlHsulUeImHKSIrdLN+u+FLFbUDU6XgnKyJc5MXTwSD8DXN7oV5gj9JGeWkkS3SQicwZcN0RB+muw53OMvslEwWW6RVLrFnshz56uajS98ltnGCeoEeDiLgda69eaIk7dDH/PN3/gXwd3R1+WFHYAAAAABJRU5ErkJggg==";

	public NewPage() {
		super("Új", new ImageIcon(DatatypeConverter.parseBase64Binary(icon)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graph.reset();
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}

}
