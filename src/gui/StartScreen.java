package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class StartScreen extends JPanel implements ActionListener {

    /**
     * Attributes
     */
    private ImageIcon titleimage   = new ImageIcon("src/resources/titel/titelImage.jpg");
    private ImageIcon background = new ImageIcon("src/resources/backgroundImages/farbverlaufBig.png");
    private JButton buttonSubmit   = new JButton();
    private JLabel labelText       = new JLabel();
    private JLabel labelIP         = new JLabel();
    private JTextArea textAreaIP   = new JTextArea();
    private JTextArea textAreaName = new JTextArea();
    JFrame frame = new JFrame("Das Verrückte Labyrinth");
    private int textSize = 20;

    /**
     * Constructor
     */
    public StartScreen(){
        titleimage.setImage(titleimage.getImage().getScaledInstance(480,351 ,Image.SCALE_DEFAULT));
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new GridBagLayout());
        panelContent.setOpaque(false);
        GridBagConstraints constraintsContent = new GridBagConstraints();

        /***************************************************************************************************************
         * logo
         */
        JLabel labelLogo = new JLabel();
        labelLogo.setIcon(titleimage);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 5;
        constraintsContent.gridheight = 2;
        constraintsContent.insets = new Insets(15,10,20,10);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelContent.add(labelLogo, constraintsContent);

        /***************************************************************************************************************
         * player name insert
         */
        labelText.setText("Bitte geben Sie ihren Namen ein.");
        labelText.setFont(new Font("Serif",Font.PLAIN,textSize));
        labelText.setHorizontalAlignment(SwingConstants.CENTER);
        labelText.setVerticalAlignment(SwingConstants.CENTER);

        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 5;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 5, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(labelText, constraintsContent);

        textAreaName.setMinimumSize(new Dimension(200,50));
        textAreaName.setPreferredSize(new Dimension(250,50));
        textAreaName.setFont(new Font("Serif",Font.PLAIN,textSize + 10));
        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 5;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        panelContent.add(textAreaName, constraintsContent);

        /***************************************************************************************************************
         * IP address insert
         */
        labelIP.setText("Bitte geben Sie die Server-IP an.");
        labelIP.setFont(new Font("Serif",Font.PLAIN,textSize));
        labelIP.setHorizontalAlignment(SwingConstants.CENTER);
        labelIP.setVerticalAlignment(SwingConstants.CENTER);

        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 5;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 5, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 6;
        panelContent.add(labelIP, constraintsContent);

        textAreaIP.setMinimumSize(new Dimension(200,50));
        textAreaIP.setPreferredSize(new Dimension(250,50));
        textAreaIP.setFont(new Font("Serif",Font.PLAIN,textSize + 10));
        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 5;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 7;
        panelContent.add(textAreaIP, constraintsContent);

        /***************************************************************************************************************
         * Submit Button
         */
        buttonSubmit.setText("Submit");
        buttonSubmit.setMinimumSize(new Dimension(100, 50));
        buttonSubmit.setPreferredSize(new Dimension(100,50));
        buttonSubmit.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonSubmit.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.SOUTH;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 3;
        constraintsContent.gridheight = 1; //<-
        constraintsContent.insets = new Insets(20, 0, 20, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 8;
        panelContent.add(buttonSubmit, constraintsContent);

        /***************************************************************************************************************
         * frame
         */
        GradientPanel panelBackground = new GradientPanel();
        panelBackground.add(panelContent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(panelContent,BorderLayout.NORTH);
        frame.add(panelBackground);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(900, 700));
        frame.setLocation(300, 200);
}

    /**
     * action Listener
     * @param e
     */
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == buttonSubmit){
            if((!textAreaName.getText().isEmpty())){
                if(!textAreaIP.getText().isEmpty()){
                    Lobby lobby = new Lobby(textAreaIP.getText() , textAreaName.getText());
                    frame.dispose();
                }
                else{
                    labelIP.setForeground(Color.red);
                }
            }
            else{
                labelText.setForeground(Color.red);
            }
        }
    }

    /**
     * Main class to create a StartScreen
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Style
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Not possible to set Style");
        }

        /**
         * new startScreen created
         */
        StartScreen startScreen = new StartScreen();
    }

}





