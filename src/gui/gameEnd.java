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

public class gameEnd {

	public gameEnd() {
		// TODO Auto-generated constructor stub
	}
	
	
public void createGui(){
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//--------------------------------------------------------------
		// header
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		//goes over 10 cells should be all
		constraints.gridwidth = 10;
		constraints.insets = new Insets(30,0,0,0);
		constraints.gridx = 0;
	    constraints.gridy = 0;
	    panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
	    //-------------------------------------------------------------
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.gridwidth = 1;
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    panel.add(setLabel("Spieler 1 hat gewonnen!", 30), constraints);
	    //-------------------------------------------------------------
	    // three buttons
	    constraints.insets = new Insets(30,5,5,0);
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    panel.add(addButtons("erneut Spielen"), constraints);
	    
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    panel.add(addButtons("neues Spiel"), constraints);
	    
	    constraints.gridx = 3;
	    constraints.gridy = 2;
	    panel.add(addButtons("Spiel beenden"), constraints);
	    
	    
	    //--------------------------------------------------------------
        // creating the frame
	    JFrame frame = createFrame();
		frame.add(panel, BorderLayout.NORTH);
	}
	
	
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("DvL Spiel beendet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 400);
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

}
