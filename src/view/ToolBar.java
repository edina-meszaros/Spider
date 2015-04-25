package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToolBar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;	
	private static ToolBar instance = null;

	private ToolBar() {
		super(new BorderLayout());
		JToolBar toolBar = new JToolBar("Tools");
		addButtons(toolBar);
		setPreferredSize(new Dimension(100, 30));
		add(toolBar, BorderLayout.PAGE_START);
	}
	
	public static ToolBar getInstance() {
		if (instance == null) {
			instance = new ToolBar();
		}
		return instance;		
	}

	protected void addButtons(JToolBar toolBar) {
		JButton button = null;

		// first button
		button = makeNavigationButton("Back24", "PREVIOUS",
				"Back to previous something-or-other", "P");
		toolBar.add(button);

		// second button
		button = makeNavigationButton("Up24", "UP", "Up to something-or-other",
				"U");
		toolBar.add(button);
	}

	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setSize(50, 50);
		button.addActionListener(this);

		button.setText(altText);

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
