package gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class startMenu {
	public void createGui(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		createFrame().add(panel);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 1;
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(setLabel("Das verrückte Labyrinth", 50), constraints);
        //##############################################################
        constraints.gridx= 0;
        constraints.gridy++;
        panel.add(addButtons("Spiel erstellen"),constraints);
        constraints.gridx++;
        panel.add(addButtons("Spiel beitreten"),constraints);
        constraints.gridx++;
        panel.add(addButtons("Spielanleitung"),constraints);
	}
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("Start Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200, 500);
		frame.setLocation(300, 200);
		return frame;
	}
	
	public JLabel setLabel(String labelString, int size){
		JLabel label = new JLabel(labelString);
		label.setFont(new Font("Serif", Font.PLAIN, size));
		return label;
	}
	
	public JButton addButtons(String text){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(250, 60));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
}
