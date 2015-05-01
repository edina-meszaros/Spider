package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static MainMenu instance = null;
	private JMenu menu;
	private JMenuItem newPage;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem exit;

	private MainMenu() {
		super();

		//A menu example:

		menu = new JMenu("Fájl");
		//menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");

		this.add(menu);

		newPage = new JMenuItem();
		menu.add(newPage);

		menu.addSeparator();

		//a group of JMenuItems
		save = new JMenuItem();
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menu.add(save);

		load = new JMenuItem();
		menu.add(load);

		menu.addSeparator();
		exit = new JMenuItem();
		menu.add(exit);

		menu = new JMenu("Súgó");
		this.add(menu);

		//Build second menu in the menu bar.
		menu = new JMenu("Névjegy");
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		this.add(menu);


	}

	public static MainMenu getInstance() {
		if (instance == null) {
			instance = new MainMenu();
		}
		return instance;
	}

	public JMenuItem getNewPage() { return newPage; }
	public JMenuItem getSave() { return save; }
	public JMenuItem getLoad() { return load; }
	public JMenuItem getExit() { return exit; }
}
