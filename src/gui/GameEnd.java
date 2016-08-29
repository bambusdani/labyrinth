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

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class GameEnd implements ActionListener{

    /**
     * Attributes
     */
    private JButton newGame = addButtons("neues Spiel");
    private JButton endGame = addButtons("Spiel beenden");
    private JFrame frame = createFrame();
    private JFrame playGroundFrame;

    public GameEnd(JFrame playGroundFrame){
        this.playGroundFrame = playGroundFrame;
    }

    /**
     * creates the GUI for the class GameEnd
     * @param nameOfPlayer
     */
    public void createGui(String nameOfPlayer){
        //creats new panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //--------------------------------------------------------------
        // Layout for label is set (x=0 ; y=0)
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        //create new Label and set to defined Layout
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
        //-------------------------------------------------------------

        //-------------------------------------------------------------
        //Layout for label is set (x=1 ; y=1)
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        //create new Label and set to defined Layout
        panel.add(setLabel(nameOfPlayer + " hat gewonnen!", 30), constraints);
        //-------------------------------------------------------------

        //-------------------------------------------------------------
        //Layout for button is set (x=1 ; y=2)
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(30,30,0,0);
        //create new button and set to defined Layout
        newGame.addActionListener(this);
        panel.add(newGame, constraints);
        //-------------------------------------------------------------

        //-------------------------------------------------------------
        //Layout for button is set (x=3 ; y=2)
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        //create new button and set to defined Layout
        endGame.addActionListener(this);
        panel.add(endGame, constraints);
        //-------------------------------------------------------------

        //--------------------------------------------------------------
        //add panel to frame and add a borderlayout
        this.frame.add(panel, BorderLayout.NORTH);
        //-------------------------------------------------------------
    }

    /**
     * creates a specialized frame for this class
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("DvL Spiel beendet");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setLocation(300, 200);
        return frame;
    }

    /**
     * creates a specialized label and gets text and size as input
     * @param labelString
     * @param size
     * @return
     */
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    /**
     * creates a specialized button and gets text as input
     * @param text
     * @return
     */
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }

    /**
     * action listener funktionen
     * @param ae
     */
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.endGame){
            System.exit(0);
        }
        else if(ae.getSource() == this.newGame){
            this.frame.dispose();
            this.playGroundFrame.dispose();
        }
    }
}
