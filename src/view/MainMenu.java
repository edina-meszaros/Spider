package view;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.bind.DatatypeConverter;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static final String iconSamples = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAs"
			+ "TAAALEwEAmpwYAAABUklEQVR4nH3SP0sdURAF8N/bfYjExMIPEAimFQ1YGSy0NyliitinzVeInb2mylcQwUpIbx/EQm"
			+ "wURDCgCObliY1uindW1/XPwHDnzjlz5zBzGdgQht1ZB92czbi24dSAT9jDPlYw6WmbDGc/NYvQR9XyX1jCy/hScm1eX4"
			+ "IzzGMZxw3CYby+H4czn5qq2wB38Bof8QbfMBvp21jFASbCrVAVGU6JIuBPvMcH/I0vYCbYRLglOt10uM5kd7CBt3iVLh"
			+ "L/xjrGw72Wl+S1y0x5Nedl8KKFTeVeMthvreBFQ8F47jfBH8OeVTBlsKJaQf85BdUTCkYaCkZa86kV3G6hQA/vsJYuVw"
			+ "0FV1GwFk4v+U6BfxjDpsG+v2I33Zu+G2w73LHU+owj93/bd8zhIj6XXPOXHqUWjOILtjz8723fCne0Li7dt2n8wClO8A"
			+ "fnyU23uOV/gix5Sw6iHnEAAAAASUVORK5CYII=";

	private static MainMenu instance = null;
	private JMenu fileMenu;
	private JMenu exampleSubMenu;
	private JMenu helpMenu;
	private JMenuItem newPage;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem exit;
	private JMenuItem help;
	private JMenuItem about;
	private JMenuItem ex1;
	private JMenuItem ex2;
	private JMenuItem ex3;
	private JMenuItem ex4;
	private JMenuItem ex5;

	private MainMenu() {
		super();

		//A menu example:

		fileMenu = new JMenu("Fájl");
		//menu.setMnemonic(KeyEvent.VK_A);

		this.add(fileMenu);

		newPage = new JMenuItem();
		fileMenu.add(newPage);

		fileMenu.addSeparator();

		//a group of JMenuItems
		save = new JMenuItem();
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		fileMenu.add(save);

		load = new JMenuItem();
		fileMenu.add(load);

		exampleSubMenu = new JMenu("Példák");
		exampleSubMenu.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(iconSamples)));
		exampleSubMenu.setMnemonic(KeyEvent.VK_S);

		ex1 = new JMenuItem("1. Étkező filozófusok");
		exampleSubMenu.add(ex1);

		ex2 = new JMenuItem("2. Konkurencia, szinkronizáció");
		exampleSubMenu.add(ex2);

		ex3 = new JMenuItem("3. Kommunikáció, szinkronizáció");
		exampleSubMenu.add(ex3);

		ex4 = new JMenuItem("4. Író-olvasó szinkronizáció kölcsönös kizárással");
		exampleSubMenu.add(ex4);

		ex5 = new JMenuItem("5. Nyelvelfogadás");
		exampleSubMenu.add(ex5);

		fileMenu.add(exampleSubMenu);

		fileMenu.addSeparator();
		exit = new JMenuItem();
		fileMenu.add(exit);

		this.add(fileMenu);

		helpMenu = new JMenu("Súgó");

		help = new JMenuItem();
		helpMenu.add(help);

		about = new JMenuItem();
		helpMenu.add(about);

		this.add(helpMenu);


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
	public JMenuItem getHelp() { return help; }
	public JMenuItem getAbout() { return about; }
}
