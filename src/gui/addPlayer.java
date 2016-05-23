package gui;

import java.awt.BorderLayout;
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

public class addPlayer {

	public addPlayer() {
		// TODO Auto-generated constructor stub
	}

	public void createGui(){
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		//--------------------------------------------------------------
		// header
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		//goes over two cells
		constraints.gridwidth = 2;
		constraints.insets = new Insets(30,0,0,0);
		constraints.gridx = 0;
	    constraints.gridy = 0;
	    panel.add(setLabel("Geben sie die IP-Adresse ihres Mitspielers ein.", 30), constraints);
	    //-------------------------------------------------------------
	    //Label and textfield
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.anchor = GridBagConstraints.CENTER;
	    panel.add(setLabel("Name: ", 20),constraints);
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.anchor = GridBagConstraints.CENTER;
	    panel.add(addTextFields(),constraints);
	    //--------------------------------------------
	    //Label and textfield
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.CENTER;
	    panel.add(setLabel("IP-Adresse: ", 20),constraints);
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.CENTER;
	    panel.add(addTextFields(),constraints);
	    //----------------------------------------------
	    // two buttons
	    constraints.insets = new Insets(30,5,5,0);
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.anchor = GridBagConstraints.WEST;
	    panel.add(addButtons("Spieler hinzufügen"),constraints);
	    constraints.gridx = 1;
	    constraints.gridy = 3;
	    constraints.anchor = GridBagConstraints.EAST;
	    panel.add(addButtons("zurück"),constraints);
	    
	    
	    
	    //--------------------------------------------------------------
        // creating the frame
	    JFrame frame = createFrame();
		frame.add(panel, BorderLayout.NORTH);
	}
	
	
	
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("DvL Spieler hinzufügen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(600, 400);
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
		button.setPreferredSize(new Dimension(250, 50));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
	
	public JTextField addTextFields(){
		JTextField textField = new JTextField(10);
		textField.setPreferredSize(new Dimension(10, 50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		textField.setFont(font);
		return textField;
	}
	
}
