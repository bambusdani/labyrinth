package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by m on 23.08.2016.
 */

public class Lobby implements ActionListener{

    private ImageIcon titleimage         = new ImageIcon("src/resources/titel/titelImage.jpg");
    private JButton buttonHost           = new JButton();
    private JButton buttonJoin           = new JButton();
    private JButton buttonRules          = new JButton();
    private JTextField textFieldChat     = new JTextField();
    private JTextArea  textAreaChatText  = new JTextArea();
    private JTextArea  textAreaOpenGames = new JTextArea();
    private JTextArea  textAreaPlayer    = new JTextArea();

    private String nameOfPlayer;
    JFrame frame = new JFrame("Das Verrückte Labyrinth");


    public Lobby(String name){

        nameOfPlayer= name;
        titleimage.setImage(titleimage.getImage().getScaledInstance(480,350 , Image.SCALE_DEFAULT));

        JPanel panelContent = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsContent = new GridBagConstraints();

        //==============================================================================================================
        JLabel labelLogo = new JLabel();
        //labelLogo.setText("Das Verrückte Labyrinth später ein Bild" );
        labelLogo.setMinimumSize(new Dimension(480, 350));
        labelLogo.setPreferredSize(new Dimension(480, 350));
        labelLogo.setIcon(titleimage);
        labelLogo.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 3;
        constraintsContent.insets = new Insets(10,0,0,0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelContent.add(labelLogo, constraintsContent);

        //==============================================================================================================
        JPanel panelButtons = new JPanel();
        panelButtons.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );
        panelButtons.setMinimumSize(new Dimension(480,450));
        panelButtons.setPreferredSize(new Dimension(480,450));

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelButtons, constraintsContent);
        //==============================================================================================================

        textAreaChatText.setFont(new Font("Serif",Font.PLAIN,15));
        textAreaChatText.setMinimumSize(new Dimension(480,700));
        textAreaChatText.setPreferredSize(new Dimension(480,700));
        textAreaChatText.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 7;
        constraintsContent.insets = new Insets(10, 0, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 0;
        panelContent.add(textAreaChatText, constraintsContent);

        //==============================================================================================================
        textFieldChat.setFont(new Font("Serif",Font.PLAIN,15));
        textFieldChat.setMinimumSize(new Dimension(480,100));
        textFieldChat.setPreferredSize(new Dimension(480,100));
        textFieldChat.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 8;
        panelContent.add(textFieldChat, constraintsContent);

        //==============================================================================================================
        textAreaOpenGames.setText("open games");
        textAreaOpenGames.setFont(new Font("Serif",Font.PLAIN,15));
        textAreaOpenGames.setMinimumSize(new Dimension(280,350));
        textAreaOpenGames.setPreferredSize(new Dimension(280,350));
        textAreaOpenGames.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 3;
        constraintsContent.insets = new Insets(10, 0, 0, 0);
        constraintsContent.gridx = 2;
        constraintsContent.gridy = 0;
        panelContent.add(textAreaOpenGames, constraintsContent);

        //==============================================================================================================
        textAreaPlayer.setText("player games");
        textAreaPlayer.setFont(new Font("Serif",Font.PLAIN,15));
        textAreaPlayer.setMinimumSize(new Dimension(280,450));
        textAreaPlayer.setPreferredSize(new Dimension(280,450));
        textAreaPlayer.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );

        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 2;
        constraintsContent.gridy = 4;
        panelContent.add(textAreaPlayer, constraintsContent);

        //==============================================================================================================
        // JButton buttonSubmit = new JButton();
       /* buttonSubmit.setText("Submit");
        buttonSubmit.setMinimumSize(new Dimension(100, 50));
        buttonSubmit.setPreferredSize(new Dimension(100,50));
        buttonSubmit.addActionListener(this);

        constraintsContent.anchor = GridBagConstraints.SOUTH;
        constraintsContent.weightx = 2;
        constraintsContent.weighty = 2;
        constraintsContent.gridwidth = 3;
        constraintsContent.gridheight = 1; //<-
        constraintsContent.insets = new Insets(20, 0, 20, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 6;
        panelContent.add(buttonSubmit, constraintsContent);
*/
        //==============================================================================================================
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelContent,BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocation(300, 200);
    }
    public void actionPerformed (ActionEvent e){

       /* if(e.getSource() == buttonSubmit){
            if(!textAreaName.getText().isEmpty()){
                Lobby lobby = new Lobby(textAreaName.getText());
                frame.dispose();
            }
            else{
                textAreaName.setText("Name wird benötigt.");
            }
        }*/
    }

    public static void main(String[] args) {

        //StartScreen startScreen = new StartScreen();
        Lobby lobby = new Lobby("marvin");
    }

}
