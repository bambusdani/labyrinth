package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class joinGame {
	public void createGui(){
		JPanel panel = new JPanel(new GridBagLayout());
		   //--------------------------------------------------------------
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.weighty = 1;
			constraints.insets = new Insets(0,-110,0,0); 
			constraints.gridx = 0;
			constraints.gridx++;
	        constraints.gridy = 0;
	        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
	       //--------------------------------------------------------------
	        constraints.gridx = 0;
	        constraints.gridy++;
	        panel.add(setLabel("Spiel beitreten", 25), constraints);
	      //---------------------------------------------------------------
	        constraints.gridx++;
	        constraints.gridy++;
	        panel.add(setLabel("Ihre IP-Adresse:", 25), constraints);
	        constraints.gridy++;
	        panel.add(setLabel("Übermitteln Sie die IP-Adresse dem Host", 25), constraints);
	        constraints.gridy++;
	        panel.add(setLabel("Warten Sie bis der Host das Spiel startet ", 25), constraints);
	        
	    createFrame().add(panel);
	}
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("Join Game");
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
	
	public JTextField addTextFields(){
		JTextField textField = new JTextField(10);
		textField.setPreferredSize(new Dimension(10, 50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		textField.setFont(font);
		return textField;
	}
	
	public JButton addButtons(String text){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(200, 50));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
}
