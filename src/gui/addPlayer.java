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


/*
* THIS CLASS CREATES A GUI TO ADD PLAYERS TO THE GAME
* */
public class AddPlayer {

	//===============================================================================================================
	//Method creates GUI to add players to the Game
	public void createGui(){

		//-------------------------------------------------------------------
		//Panel is container of the GUI Elements (-> a GridBagLayout is used)
		JPanel panel = new JPanel(new GridBagLayout());
		//-------------------------------------------------------------------


		//--------------------------------------------------------------------------------------
		//Layout for Header is set (x=0 ; y=0 ; width=2columns ; marginRight=30)
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(30,0,0,0);
		constraints.gridx = 0;
	    constraints.gridy = 0;

		//Label is created and set to the defined Layout
	    panel.add(setLabel("Geben sie die IP-Adresse ihres Mitspielers ein.", 30), constraints);
	    //--------------------------------------------------------------------------------------


		//----------------------------------------------
	    //Layout label is set (x=0 ; y=1)
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.anchor = GridBagConstraints.CENTER;

		//Label is created and set to the defined Layout
	    panel.add(setLabel("Name: ", 20),constraints);
		//----------------------------------------------


		//----------------------------------------------
		//Layout for textfield is set (x=1 ; y=1)
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.anchor = GridBagConstraints.CENTER;

		//textfield is added to Pael by defined Layout
	    panel.add(addTextFields(),constraints);
	    //----------------------------------------------


		//--------------------------------------------------
	    //Layout for label is set (x=0 ; y=2)
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.CENTER;

		//label is created and set to the defined Layout
	    panel.add(setLabel("IP-Adresse: ", 20),constraints);
		//--------------------------------------------------


		//----------------------------------------------
		//Layout for textfield is set (x=1 ; y2)
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.CENTER;

		//Textfield is added to Panel by defined Layout
	    panel.add(addTextFields(),constraints);
	    //----------------------------------------------


		//------------------------------------------------------
	    //Layout for button is set (x=0 ; y=3)
	    constraints.insets = new Insets(30,5,5,0);
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.anchor = GridBagConstraints.WEST;

		//Button is created and set to its defined Layout
	    panel.add(addButtons("Spieler hinzufügen"),constraints);
		//------------------------------------------------------


		//-------------------------------------------
		//Layout for button is set (x=1 ; y=3)
	    constraints.gridx = 1;
	    constraints.gridy = 3;
	    constraints.anchor = GridBagConstraints.EAST;

		//Button is created and set to its defined Layout
	    panel.add(addButtons("zurück"),constraints);
		//-------------------------------------------


	    //-------------------------------------------------------------------------------------------------------------------
        //Element of class JFrame is created by method createFrame() (-> Method create frame with special defined parameters)
	    JFrame frame = createFrame();

		//The whole Panel, which contains the GUI Elements on its Layout is added to the frame with a Borderlayout
		frame.add(panel, BorderLayout.NORTH);
		//-------------------------------------------------------------------------------------------------------------------
	}
	//=======================================================================================================================
	
	
	//=========================================================
	// Creates a specialized frame for the class AddPlayer
	public JFrame createFrame(){
		JFrame frame = new JFrame("DvL Spieler hinzuf�gen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setLocation(300, 200);
		return frame;
	}
	//=========================================================


	//=====================================================
	//Creates a Label, gets the name and the size as input
	public JLabel setLabel(String labelString, int size){
		JLabel label = new JLabel(labelString);
		label.setFont(new Font("Serif", Font.PLAIN, size));
		return label;
	}
	//=====================================================


	//=====================================================
	//Creates a Button gets the text of it as input
	public JButton addButtons(String text){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(250, 50));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
	//=====================================================


	//=====================================================
	//Creates a textfield with spezialized size
	public JTextField addTextFields() {
		JTextField textField = new JTextField(10);
		textField.setPreferredSize(new Dimension(10, 50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		textField.setFont(font);
		return textField;
	}
	//=====================================================
}
