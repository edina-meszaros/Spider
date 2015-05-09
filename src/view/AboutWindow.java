package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutWindow extends JFrame {

    public AboutWindow() {
        super();

        this.setSize(600, 362);

        JLabel icon = new JLabel(new ImageIcon("logo-medium.png"));
        icon.setSize(317, 332);
        this.add(icon, BorderLayout.LINE_START);

        JLabel data = new JLabel();
        data.setText("<html><p style=\"margin:5px;font-size:12px;\">Név: <b>Spider Petriháló elemző izé</b></p>" +
                "<p style=\"margin:5px;font-size:10px;\">Készítette: <i>Mészáros Edina</i></p>");
        data.setVerticalAlignment(JLabel.TOP);
        this.add(data, BorderLayout.CENTER);

        JPanel buttonLine = new JPanel();
        buttonLine.setLayout(new BoxLayout(buttonLine, BoxLayout.X_AXIS));

        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(300, 30));
        buttonLine.add(padding);
        JButton close = new JButton();
        close.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.windowForComponent((Component) e.getSource());
                window.dispose();
            }
        });
        close.setText("Bezárás");
        buttonLine.add(close);
        this.add(buttonLine, BorderLayout.PAGE_END);
    }

}
