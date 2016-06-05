package gui;

import gameLogic.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class createNewGame implements ActionListener{

	JButton start = addButtons("Spiel starten");
	JButton back = addButtons("Zurück");
	JFrame frame = createFrame();

	public void createGui(){
		JPanel panel = new JPanel(new GridBagLayout());
	   //--------------------------------------------------------------
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		constraints.insets = new Insets(30,0,0,0);
		constraints.gridx = 0;
		constraints.gridx++;
        constraints.gridy = 0;
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
      //--------------------------------------------------------------
        constraints.gridy++;
        panel.add(setLabel("Spiel erstellen", 30), constraints);
      //--------------------------------------------------------------
        constraints.gridx= 0;
        constraints.gridy++;
        panel.add(addTextFields(),constraints);
        constraints.gridx++;
        panel.add(addButtons("computer"),constraints);
        constraints.gridx++;
        panel.add(addButtons("spieler"),constraints);
      //--------------------------------------------------------------
        constraints.gridx= 0;
        constraints.gridy++;
        panel.add(addTextFields(),constraints);
        constraints.gridx++;
        panel.add(addButtons("computer"),constraints);
        constraints.gridx++;
        panel.add(addButtons("spieler"),constraints);
      //--------------------------------------------------------------
        constraints.gridx= 0;
        constraints.gridy++;
        panel.add(addTextFields(),constraints);
        constraints.gridx++;
        panel.add(addButtons("computer"),constraints);
        constraints.gridx++;
        panel.add(addButtons("spieler"),constraints);
      //--------------------------------------------------------------

        JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(30,0,0,10);
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 0;
		start.addActionListener(this);
        panel2.add(start,c);
        c.gridx++;
		back.addActionListener(this);
        panel2.add(back,c);
      //--------------------------------------------------------------

		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);
		
	}	
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("createNewGame");
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

	public void actionPerformed (ActionEvent ae){
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, wird der Text des JLabels entsprechend geändert
		if(ae.getSource() == this.back){
			startMenu startMenu = new startMenu();
			startMenu.createGui();
			frame.dispose();
		}
		else if(ae.getSource() == this.start){
			Board board = new Board();
			playGround boardview = new playGround();
			boardview.createGui(board);
			frame.dispose();
		}
	}
}
