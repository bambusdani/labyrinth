package gui;

import oracle.jrockit.jfr.JFR;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
* THIS CLASS CREATES GUI TO JOIN A GAME
* */
public class joinGame implements ActionListener{

	//=====================================================================
	//create a new frame and a button
	JFrame frame = createFrame();
	JButton back = addButtons("Zurück");

	public void createGui(){
		//create new Panel and a new GirdBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		//--------------------------------------------------------------
		//create new gridbaglayout -> new Layout set to (x=0 ; y=0)
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.weighty = 1;
			constraints.insets = new Insets(0,-110,0,0); 
			constraints.gridx = 0;
			constraints.gridx++;
	        constraints.gridy = 0;

		//create new label and set to layout
	        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
		//--------------------------------------------------------------


		//--------------------------------------------------------------
		//Layout set to (x=0 ; y=1)
	        constraints.gridx = 0;
	        constraints.gridy++;

		//create new label and set to layout
	        panel.add(setLabel("Spiel beitreten", 25), constraints);
		//---------------------------------------------------------------


		//---------------------------------------------------------------
		//Layout set to (x=1 ; y=2)
	        constraints.gridx++;
	        constraints.gridy++;

		//create new label and set to layout
	        panel.add(setLabel("Ihre IP-Adresse:", 25), constraints);
		//---------------------------------------------------------------


		//---------------------------------------------------------------
		//Layout set to (x=1 ; y=3)
	        constraints.gridy++;

		//create new label and set to Layout
	        panel.add(setLabel("Übermitteln Sie die IP-Adresse dem Host", 25), constraints);
		//---------------------------------------------------------------


		//---------------------------------------------------------------
		//Layout set to (x=1 ; y=4)
	        constraints.gridy++;

		//create new label and set to layout
	        panel.add(setLabel("Warten Sie bis der Host das Spiel startet ", 25), constraints);
		//---------------------------------------------------------------


		//---------------------------------------------------------------
		//Layout set to (x=1 ; y=5)
	        constraints.gridy++;

		//add actionslistener to back button
			back.addActionListener(this);

		//add backbutton to panel by layout
			panel.add(back, constraints);
		//---------------------------------------------------------------


		//---------------------------------------------------------------
		//set panel on frame
	    frame.add(panel);
		//---------------------------------------------------------------
	}

	//=====================================================================
	//creates specialized frame for JoinGame
	public JFrame createFrame(){
		JFrame frame = new JFrame("Join Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200, 500);
		frame.setLocation(300, 200);
		return frame;
	}
	//=====================================================================


	//=====================================================================
	//creates specialized label and get text and size as input
	public JLabel setLabel(String labelString, int size){
		JLabel label = new JLabel(labelString);
		label.setFont(new Font("Serif", Font.PLAIN, size));
		return label;
	}
	//=====================================================================


	//=====================================================================
	//creates specialized textfield
	public JTextField addTextFields(){
		JTextField textField = new JTextField(10);
		textField.setPreferredSize(new Dimension(10, 50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		textField.setFont(font);
		return textField;
	}
	//=====================================================================


	//=====================================================================
	//creates specialized button and gets text as input
	public JButton addButtons(String text){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(200, 50));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
	//=====================================================================


	//=====================================================================
	//actionlistener method for the back button
	public void actionPerformed (ActionEvent ae){
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, wird der Text des JLabels entsprechend geändert
		if(ae.getSource() == this.back){
			startMenu startMenu = new startMenu();
			startMenu.createGui();
			frame.dispose();
		}
	}
	//=====================================================================
}
