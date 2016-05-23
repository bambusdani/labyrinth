package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class createNewGame {
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
        panel2.add(addButtons("Spiel starten"),c);
        c.gridx++;
        panel2.add(addButtons("Zurück"),c);
      //--------------------------------------------------------------
        JFrame frame = createFrame();
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);
		
	}	
	
	public JFrame createFrame(){
		JFrame frame = new JFrame("Test Frame");
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
}
