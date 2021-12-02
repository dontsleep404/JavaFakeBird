package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

import screen.Screen;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {		
			public void run() {
				JFrame frame = new JFrame("Flappy bird");
				frame.add(new Screen());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}
}
