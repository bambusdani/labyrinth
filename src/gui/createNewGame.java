package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class createNewGame {
	
	JFrame frame = new JFrame("create new Game");
	JPanel labelContainer = new JPanel();
	JPanel buttonContainer = new JPanel();
	JPanel textfieldContainer = new JPanel();
	
	
	public void createGui(){
		setFrame(700, 700, 500, 100);
		
		labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));
		labelContainer.add(setLabel("Das verrücke Labyrinth", 40));
		labelContainer.add(setLabel("Erstellen des Spiels", 25));
		
		buttonContainer.setLayout(new GridLayout(3, 3));
		buttonContainer.add(addTextFields(10,50, "abc"));
		buttonContainer.add(addButton(200, 50, "Computer Hinzufügen"));
		buttonContainer.add(addButton(175, 50, "Spieler Hinzufügen"));
		buttonContainer.add(addTextFields(10,50, "abc"));
		buttonContainer.add(addButton(200, 50, "Computer Hinzufügen"));
		buttonContainer.add(addButton(175, 50, "Spieler Hinzufügen"));
		buttonContainer.add(addTextFields(10,50, "abc"));
		buttonContainer.add(addButton(200, 50, "Computer Hinzufügen"));
		buttonContainer.add(addButton(175, 50, "Spieler Hinzufügen"));

		frame.setLayout(new BorderLayout());
		frame.add(labelContainer, BorderLayout.NORTH);
		frame.add(buttonContainer, BorderLayout.CENTER);
		frame.add(addButton(200, 50, "Back"), BorderLayout.SOUTH);
		
        
	}
	
	public void setFrame(int sizeX, int sizeY, int locationX, int locationY){
		//frame.getContentPane().setLayout(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(sizeX, sizeY);
		frame.setLocation(locationX, locationY);
	}
	
	public JPanel setLabel(String labelString, int size){
		JPanel panel = new JPanel();
		JLabel label = new JLabel(labelString);
		label.setFont(new Font("Serif", Font.PLAIN, size));
		panel.add(label);
		return panel;
	}
	
	public JPanel addButton(int sizeX, int sizeY, String label){
		JPanel btnPanel = new JPanel();
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(sizeX, sizeY));
		btnPanel.add(button);
		return btnPanel;
	}
	
	public JPanel addTextFields(int sizeX, int sizeY, String text){
		JPanel textPanel = new JPanel();
		JTextField textField = new JTextField(sizeX);
		textField.setPreferredSize(new Dimension(sizeX, sizeY));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		textField.setFont(font);
		textPanel.add(textField);
		return textPanel;
	}
}
