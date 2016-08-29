package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class CreateNewGame implements ActionListener{

    /**
     * Attributes
     */
    JButton start = addButtons("Spiel starten");
    JButton back = addButtons("Zurück");
    JFrame frame = createFrame();

    /**
     * Total GUI is created
     */
    public void createGui(){
        //new Object of class Panel is created
        JPanel panel = new JPanel(new GridBagLayout());

        //---------------------------------------------------------------
        //Layout for Label is set (x=1 ; y=0)
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridx++;
        constraints.gridy = 0;
        //New label created and added to panel
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
        //-----------------------------------------------------------------

        //--------------------------------------------------------
        //Layout for Label is set (x=1 ; y=1)
        constraints.gridy++;
        //New Label created and added to panel
        panel.add(setLabel("Spiel erstellen", 30), constraints);
        //--------------------------------------------------------

        //-----------------------------------------------
        //Layout for textfield is set (x=0 ; y=2)
        constraints.gridx= 0;
        constraints.gridy++;
        //Textfield added to Panel
        panel.add(addTextFields(),constraints);
        //-----------------------------------------------

        //-----------------------------------------------
        //Layout for button is set (x=1 ; y=2)
        constraints.gridx++;
        //New button is added to Panel
        panel.add(addButtons("computer"),constraints);
        //-----------------------------------------------

        //-----------------------------------------------
        //Layout for button is set (x=2 ; y=2)
        constraints.gridx++;
        //New button is added to Panel
        panel.add(addButtons("spieler"),constraints);
        //-----------------------------------------------

        //----------------------------------------------
        //Layout for textfield is set (x=0 ; y=3)
        constraints.gridx= 0;
        constraints.gridy++;
        //New button is added to Panel
        panel.add(addTextFields(),constraints);
        //----------------------------------------------

        //----------------------------------------------
        //Layout for buton is set (x=1 ; y=3)
        constraints.gridx++;
        //New button is added to Panel
        panel.add(addButtons("computer"),constraints);
        //----------------------------------------------

        //----------------------------------------------
        //Layout for button is set (x=2 ; y=3)
        constraints.gridx++;
        //New button is added to Panel
        panel.add(addButtons("spieler"),constraints);
        //----------------------------------------------

        //----------------------------------------------
        //Layout for textfield is set (x=0 ; y=4)
        constraints.gridx= 0;
        constraints.gridy++;
        //New button is added to Panel
        panel.add(addTextFields(),constraints);
        //----------------------------------------------

        //----------------------------------------------
        //Layout for button is set (x=1 ; y=4)
        constraints.gridx++;
        //New button is added to Panel
        panel.add(addButtons("computer"),constraints);
        //----------------------------------------------

        //----------------------------------------------
        //Layout for button is set (x=2 ; y=4)
        constraints.gridx++;
        panel.add(addButtons("spieler"),constraints);
        //----------------------------------------------

        //------------------------------------------------
        //Create a new Panel with GridBagLayout
        JPanel panel2 = new JPanel(new GridBagLayout());
        //set Gridbag Layout (x=0 ; y=0)
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,0,0,10);
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 0;
        //set ActionListener to start Button
        start.addActionListener(this);
        //add StartButton to Panel2
        panel2.add(start,c);
        //------------------------------------------------

        //---------------------------------------
        //Layout for button is set (x=1 ; y=0)
        c.gridx++;
        back.addActionListener(this);
        panel2.add(back,c);
        //---------------------------------------

        //--------------------------------------------------
        //Both panel are set to Frame with a BorderLayout
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        //--------------------------------------------------
    }


    /**
     * creates spezialized Frame
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("CreateNewGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1200, 500);
        frame.setLocation(300, 200);
        return frame;
    }

    /**
     * crates a spezialized Label gets as input the text and the size
     * @param labelString
     * @param size
     * @return
     */
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        return label;
    }

    /**
     * creates a spezialized TextField
     * @return
     */
    public JTextField addTextFields(){
        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(10, 50));
        Font font = new Font("SansSerif", Font.BOLD, 20);
        textField.setFont(font);
        return textField;
    }

    /**
     * creates a spezialized Button and gets as input its text
     * @param text
     * @return
     */
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }

    /**
     * Function creates an ActionListener for the Buttons
     * @param ae
     */
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.back){
            StartMenu StartMenu = new StartMenu();
            StartMenu.createGui();
            frame.dispose();
        }
        else if(ae.getSource() == this.start){
            frame.dispose();
        }
    }
}
