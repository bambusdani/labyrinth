package main;

import javax.swing.*;

public class GUI {
	
	/**
	 * Create the GUI and show it.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window
		JFrame frame = new JFrame("Das Verrückte Labyrinth");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add label for experimentation
		JLabel label1 = new JLabel("Label1");
		frame.getContentPane().add(label1);
		
		//Display the window
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//create and show GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
