package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import view.style.Theme;

public class Output extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static Output instance = null;
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private JTextPane textEditor = new JTextPane();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private Timer grayer;

	private Output(){

		this.title.setPreferredSize(new Dimension(150, 25));
		this.title.setBackground(new Color(147, 157, 168));
		this.title.add(new JLabel("Kimenet / hibák"));

		this.textEditor.setPreferredSize(new Dimension(200, 280));
		this.textEditor.setEditable(false);
		this.textEditor.setText("");
		this.textEditor.setBackground(new Color(219,218,213));
		this.content.add(textEditor);

		JScrollPane editorScrollPane = new JScrollPane(textEditor);
		editorScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(200, 300));

		this.content.add(editorScrollPane, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setBorder(this.border);
		this.grayer = new Timer(5000, this);
	}

	public static Output getInstance() {
		if (instance == null) {
			instance = new Output();
		}
		return instance;
	}

	public JTextPane getTextEditor() {
		return textEditor;
	}

	public void setError(String error){
		StyleContext sc = new StyleContext();
		StyleContext sc2 = new StyleContext();
		Style newStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
		Style oldStyle = sc2.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setForeground(newStyle, Color.RED);
		StyleConstants.setForeground(oldStyle, Theme.DARK_GREY);

		String history = this.textEditor.getText();
		StyledDocument doc = this.textEditor.getStyledDocument();

		try {
			this.textEditor.setText("");
			if (history.length() != 0)
				doc.insertString(0, history + "\r\n\r\n", oldStyle);
			doc.insertString(doc.getLength(), error, newStyle);
			grayer.stop();
			grayer.start();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		this.textEditor.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Gray out everything
		StyleContext sc2 = new StyleContext();
		Style oldStyle = sc2.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setForeground(oldStyle, Theme.DARK_GREY);

		StyledDocument doc = this.textEditor.getStyledDocument();
		try {
			String history = this.textEditor.getText();
			this.textEditor.setText("");
			doc.insertString(0, history, oldStyle);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		grayer.stop();
	}
}
