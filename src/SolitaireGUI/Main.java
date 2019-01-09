package SolitaireGUI;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
        solitaireGUI window = new solitaireGUI();
        window.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getFrame().setVisible(true);
	}
}
