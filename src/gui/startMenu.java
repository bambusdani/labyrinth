package gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class startMenu {
	JFrame frame = new JFrame("DVL2 Menu");
	JPanel buttonContainer = new JPanel();
	
	public void createGui(){
		setFrame(700, 300, 700, 300);
		buttonContainer.add(addButton(50,50,200,50,"Spiel erstellen"),BorderLayout.SOUTH);
		buttonContainer.add(addButton(50,50,200,50,"Spiel beitretten"),BorderLayout.WEST);
		buttonContainer.add(addButton(50,50,200,50,"Spielregeln"),BorderLayout.EAST);
		
		frame.add(setLabel("Das verrückte Labyrinth"),BorderLayout.PAGE_START);
		frame.add(buttonContainer,BorderLayout.PAGE_END);
	}
	
	public JPanel addButton(int locationX, int locationY, int sizeX, int sizeY, String label){
		JPanel btnPanel = new JPanel();
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(sizeX, sizeY));
		btnPanel.add(button);
		return btnPanel;
	}
	
	public void setFrame(int sizeX, int sizeY, int locationX, int locationY){
		//frame.getContentPane().setLayout(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(sizeX, sizeY);
		frame.setLocation(locationX, locationY);
	}
	
	public JPanel setLabel(String labelText){
		JPanel labelPanel = new JPanel();
		JLabel label = new JLabel(labelText);
		labelPanel.add(label);
		return labelPanel;
	}
	
}
