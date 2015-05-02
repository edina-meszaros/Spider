package controller.actions.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;

import model.Graph;
import view.Canvas;

public class NewPage extends AbstractAction {

	private static final String icon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAA"
			+ "AsTAAALEwEAmpwYAAABMElEQVR4nKXTO04DQQwG4G/zUBoeSYGoUBpugAQ3oKPhGHAdwjUi0dEgoESiIg0"
			+ "lNAgoIBAogOxSxKNMUkVgaWSvx/9ve+yFFo4wRLXgGQamVYRxaCLPaOAn05WpFKHXQvcKfGGMfdxihCW8oY"
			+ "1aRlKL2E30UReXj8Hax0bodVxggJs4A1wG8ROqAmVk24ks71gOXycrO7UwjnOFlSIqKHEf7eRv8J0Bq4ysF"
			+ "ZXW0sUQW5E9AcroOdkJXEWF11htZM4HfJqV0wDuzvlHsulUeImHKSIrdLN+u+FLFbUDU6XgnKyJc5MXTwSD"
			+ "8DXN7oV5gj9JGeWkkS3SQicwZcN0RB+muw53OMvslEwWW6RVLrFnshz56uajS98ltnGCeoEeDiLgda69eaIk"
			+ "7dDH/PN3/gXwd3R1+WFHYAAAAABJRU5ErkJggg==";

	public NewPage() {
		super("Új", new ImageIcon(DatatypeConverter.parseBase64Binary(icon)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graph.reset();
		Canvas.getInstance().repaint();
	}

}
