package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startMenu implements ActionListener{
	//===================================================================
	JFrame frame = createFrame();
	JButton buttonRules = addButtons("Spielanleitung");
	JButton buttonCreateGame = addButtons("Spiel erstellen");
	JButton buttonJoinGame = addButtons("Spiel beitreten");
	JButton about = addButtons("About");
	//====================================================================


	//====================================================================
	public void createGui(){
		//--------------------------------------------------------------
		//create new panel and gridbaglayout
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		//add panel to frame
		frame.add(panel);
		//--------------------------------------------------------------


		//--------------------------------------------------------------
		//layout set to (position=center ; x=1 ; y=0)
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 1;
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 1;
        constraints.gridy = 0;

		//create new label and set to panel
        panel.add(setLabel("Das verrückte Labyrinth", 50), constraints);
        //--------------------------------------------------------------


		//--------------------------------------------------------------
		//layout set to (x=0 ; y=1)
        constraints.gridx= 0;
        constraints.gridy++;

		//actionlistener added to createGame button
		buttonCreateGame.addActionListener(this);

		//button added to panel
        panel.add(buttonCreateGame,constraints);
		//--------------------------------------------------------------


		//--------------------------------------------------------------
		//layout set to (x=1 ; y=1)
        constraints.gridx++;

		//actionlistener added to JoinGame button
		buttonJoinGame.addActionListener(this);

		//button added to panel
        panel.add(buttonJoinGame,constraints);
		//--------------------------------------------------------------


		//--------------------------------------------------------------
		//layout set to (x=2 ; y=1)
		constraints.gridx++;

		//actionlistener added to rules button
		buttonRules.addActionListener(this);

		//add button to panel and set layout to it
        panel.add(buttonRules,constraints);
		//--------------------------------------------------------------


		//--------------------------------------------------------------
		//layout set to (x=0 ; y=0)
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;

		//actionlistener added to rules button
		about.addActionListener(this);

		//add button to panel and set layout to it
		panel.add(about,constraints);
		//--------------------------------------------------------------
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

	public void actionPerformed (ActionEvent ae){
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, wird der Text des JLabels entsprechend geändert
		if(ae.getSource() == this.buttonRules){
			rules gameRules = new rules();
			gameRules.createGui();
			frame.dispose();
		}
		else if(ae.getSource() == this.buttonCreateGame){
			createNewGame newGame = new createNewGame();
			newGame.createGui();
			frame.dispose();
		}
		else if(ae.getSource() == this.buttonJoinGame){
			joinGame joinGame = new joinGame();
			joinGame.createGui();
			frame.dispose();
		}
		else if(ae.getSource() == this.about){
			About about = new About();
			about.createGui();
			frame.dispose();
		}
	}
}
