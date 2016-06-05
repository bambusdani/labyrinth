package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startMenu implements ActionListener{
	JFrame frame = createFrame();
	JButton buttonRules = addButtons("Spielanleitung");
	JButton buttonCreateGame = addButtons("Spiel erstellen");
	JButton buttonJoinGame = addButtons("Spiel beitreten");

	public void createGui(){

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		frame.add(panel);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 1;
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(setLabel("Das verrückte Labyrinth", 50), constraints);
        //##############################################################
        constraints.gridx= 0;
        constraints.gridy++;

		buttonCreateGame.addActionListener(this);
        panel.add(buttonCreateGame,constraints);
        constraints.gridx++;

		buttonJoinGame.addActionListener(this);
        panel.add(buttonJoinGame,constraints);
		constraints.gridx++;

		buttonRules.addActionListener(this);
        panel.add(buttonRules,constraints);
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
	}
}
