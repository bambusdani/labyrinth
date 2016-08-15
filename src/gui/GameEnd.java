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

/*
* THIS CLASS CREATES THE GUI FOR THE GAME END
* */
public class GameEnd {

    //======================================================================
    //creates the GUI for the Class GameEnd
    public void createGui(){

        //creats new panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //--------------------------------------------------------------
        // Layout for label is set (x=0 ; y=0)
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridwidth = 10;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        //create new Label and set to defined Layout
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
        //-------------------------------------------------------------


        //-------------------------------------------------------------
        //Layout for label is set (x=1 ; y=1)
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;

        //create new Label and set to defined Layout
        panel.add(setLabel("Spieler 1 hat gewonnen!", 30), constraints);
        //-------------------------------------------------------------


        //-------------------------------------------------------------
        //Layout for button is set (x=0 ; y=2)
        constraints.insets = new Insets(30,5,5,0);
        constraints.gridx = 0;
        constraints.gridy = 2;

        //create new button and set to defined Layout
        panel.add(addButtons("erneut Spielen"), constraints);
        //-------------------------------------------------------------


        //-------------------------------------------------------------
        //Layout for button is set (x=1 ; y=2)
        constraints.gridx = 1;
        constraints.gridy = 2;

        //create new button and set to defined Layout
        panel.add(addButtons("neues Spiel"), constraints);
        //-------------------------------------------------------------


        //-------------------------------------------------------------
        //Layout for button is set (x=3 ; y=2)
        constraints.gridx = 3;
        constraints.gridy = 2;

        //create new button and set to defined Layout
        panel.add(addButtons("Spiel beenden"), constraints);
        //-------------------------------------------------------------


        //--------------------------------------------------------------
        //create frame
        JFrame frame = createFrame();

        //add panel to frame and add a borderlayout
        frame.add(panel, BorderLayout.NORTH);
        //-------------------------------------------------------------
    }
    //=================================================================


    //=====================================================================
    //creates a specialized frame for this class
    public JFrame createFrame(){
        JFrame frame = new JFrame("DvL Spiel beendet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setLocation(300, 200);
        return frame;
    }
    //=====================================================================


    //=====================================================================
    //creates a specialized label and gets text and size as input
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        return label;
    }
    //=====================================================================


    //=====================================================================
    //creates a specialized button and gets text as input
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }
    //=====================================================================

}
