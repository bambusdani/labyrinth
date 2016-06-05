package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class rules implements ActionListener{

	private String longText = "Test";
	JFrame frame = createFrame();
	JButton back = addButtons("zurück");
	
	public void createGui(){
		
		JPanel panel = new JPanel(new GridBagLayout());
		//--------------------------------------------------------------
		// header
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weighty = 1;
		constraints.insets = new Insets(30,0,0,0);
		constraints.gridx = 0;
		constraints.gridx++;
	    constraints.gridy = 0;
	    panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
	    //--------------------------------------------------------------
	    // second header
        constraints.gridy = 2;
        panel.add(setLabel("Die Spielregeln: ", 30), constraints);
        //--------------------------------------------------------------
        // scroll panel for the rules
        constraints.gridy = 3;
        panel.add(setScrollPane("Das sind die Spielregeln: " + createLongText() ,20),constraints);
	    //---------------------------------------------------------------
        // back button
        constraints.gridy = 4;
		back.addActionListener(this);
        panel.add(back,constraints);
        
        //--------------------------------------------------------------
        // creating the frame
		frame.add(panel, BorderLayout.NORTH);
			
	}
	
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("DvL Spielregeln");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1200, 700);
		frame.setLocation(300, 200);
		return frame;
	}
	
	public JLabel setLabel(String labelString, int size){
		JLabel label = new JLabel(labelString);
		label.setFont(new Font("Serif", Font.PLAIN, size));
		return label;
	}
	
	public JScrollPane setScrollPane(String text, int sizeOfText){
		
		JTextArea textArea = new JTextArea(text);
		textArea.setFont(new Font("Serif", Font.PLAIN, sizeOfText));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(600,400));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scroll;
	}
	
	
	
	public JButton addButtons(String text){
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(200, 50));
		button.setFont(new Font("Serif", Font.PLAIN, 25));
		return button;
	}
	
	public String createLongText(){
			for (int i = 0; i < 1000; i++) {
				longText = longText + " Test ";
			}
			return longText;
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
	}
}
