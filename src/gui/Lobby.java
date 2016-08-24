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
    private JButton buttonReady          = new JButton();
    private JButton buttonStart          = new JButton();
    private JButton buttonback           = new JButton();
    private JButton buttonback2           = new JButton();
    private JButton buttonAbout          = new JButton();
    private JTextField textFieldChat     = new JTextField();
    private JTextArea  textAreaChatText  = new JTextArea();
    private JTextArea  textAreaOpenGames = new JTextArea();
    private JTextArea  textAreaPlayer    = new JTextArea();
    private JTextArea  textAreaJoinNumber = new JTextArea();
    private JTextArea  hostPlayer0       = new JTextArea();
    private JTextArea  hostPlayer1       = new JTextArea();
    private JTextArea  hostPlayer2       = new JTextArea();
    private JTextArea  hostPlayer3       = new JTextArea();
    private JPanel panelButtons          = new JPanel(new GridBagLayout());
    private JPanel panelJoinGame         = new JPanel(new GridBagLayout());
    private JPanel panelHostGame         = new JPanel(new GridBagLayout());

    private int textSize = 20;

    private String nameOfPlayer;
    JFrame frame = new JFrame("Das Verrückte Labyrinth");


    public Lobby(String name){

        nameOfPlayer= name;
        titleimage.setImage(titleimage.getImage().getScaledInstance(480,350 , Image.SCALE_DEFAULT));

        JPanel panelContent = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsContent = new GridBagConstraints();

        /***************************************************************************************************************
         * labelLogo
         */
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

        /***************************************************************************************************************
         * panelButtons
         */
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

        panelJoinGame.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );
        panelJoinGame.setMinimumSize(new Dimension(480,450));
        panelJoinGame.setPreferredSize(new Dimension(480,450));
        panelJoinGame.setVisible(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelJoinGame, constraintsContent);

        panelHostGame.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black) );
        panelHostGame.setMinimumSize(new Dimension(480,450));
        panelHostGame.setPreferredSize(new Dimension(480,450));
        panelHostGame.setVisible(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelHostGame, constraintsContent);

        /***************************************************************************************************************
         * Chatwindow
         */
        textAreaChatText.setFont(new Font("Serif",Font.PLAIN,textSize));
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

        /***************************************************************************************************************
         * chatTextField
         */
        textFieldChat.setFont(new Font("Serif",Font.PLAIN,textSize));
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

        /***************************************************************************************************************
         * textAreaOpenGames
         */
        textAreaOpenGames.setText("open games");
        textAreaOpenGames.setFont(new Font("Serif",Font.PLAIN,textSize));
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

        /***************************************************************************************************************
         * player names
         */
        textAreaPlayer.setText("player games");
        textAreaPlayer.setFont(new Font("Serif",Font.PLAIN,textSize));
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

        /***************************************************************************************************************
         * panelButton
         */
        buttonHost.setText("Host Game");
        buttonHost.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonHost.setMinimumSize(new Dimension(150,50));
        buttonHost.setPreferredSize(new Dimension(150,50));
        buttonHost.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelButtons.add(buttonHost, constraintsContent);

        buttonJoin.setText("Join Game");
        buttonJoin.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonJoin.setMinimumSize(new Dimension(150,50));
        buttonJoin.setPreferredSize(new Dimension(150,50));
        buttonJoin.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelButtons.add(buttonJoin, constraintsContent);
        //
        textAreaJoinNumber.setFont(new Font("Serif",Font.PLAIN,textSize));
        textAreaJoinNumber.setMinimumSize(new Dimension(50,50));
        textAreaJoinNumber.setPreferredSize(new Dimension(50,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 1;
        panelButtons.add(textAreaJoinNumber, constraintsContent);
        //

        buttonRules.setText("Game Rules");
        buttonRules.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonRules.setMinimumSize(new Dimension(150,50));
        buttonRules.setPreferredSize(new Dimension(150,50));
        buttonRules.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelButtons.add(buttonRules, constraintsContent);

        //-----------------------------------------------
        //about
        buttonAbout.setText("About");
        buttonAbout.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonAbout.setMinimumSize(new Dimension(150,50));
        buttonAbout.setPreferredSize(new Dimension(150,50));
        buttonAbout.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 3;
        panelButtons.add(buttonAbout, constraintsContent);

        /***************************************************************************************************************
         * panelJoinGame
         */
        JLabel labelReady = new JLabel();
        labelReady.setText("Join Game");
        labelReady.setFont(new Font("Serif",Font.PLAIN,textSize));
        labelReady.setHorizontalAlignment(SwingConstants.CENTER);
        labelReady.setVerticalAlignment(SwingConstants.CENTER);
        labelReady.setMinimumSize(new Dimension(150,50));
        labelReady.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelJoinGame.add(labelReady, constraintsContent);

        buttonReady.setText("Ready?");
        buttonReady.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonReady.setMinimumSize(new Dimension(150,50));
        buttonReady.setPreferredSize(new Dimension(150,50));
        buttonReady.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelJoinGame.add(buttonReady, constraintsContent);

        buttonback2.setText("Back to Lobby");
        buttonback2.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonback2.setMinimumSize(new Dimension(150,50));
        buttonback2.setPreferredSize(new Dimension(150,50));
        buttonback2.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelJoinGame.add(buttonback2, constraintsContent);

        /***************************************************************************************************************
         * panelHostGame
         */
        JLabel labelHost = new JLabel();
        labelHost.setText("Host Game");
        labelHost.setFont(new Font("Serif",Font.PLAIN,textSize));
        labelHost.setHorizontalAlignment(SwingConstants.CENTER);
        labelHost.setVerticalAlignment(SwingConstants.CENTER);
        labelHost.setMinimumSize(new Dimension(150,50));
        labelHost.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelHostGame.add(labelHost, constraintsContent);

        hostPlayer0.setText("");
        hostPlayer0.setFont(new Font("Serif",Font.PLAIN,textSize));
        hostPlayer0.setMinimumSize(new Dimension(150,50));
        hostPlayer0.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelHostGame.add(hostPlayer0, constraintsContent);

        hostPlayer1.setText("");
        hostPlayer1.setFont(new Font("Serif",Font.PLAIN,textSize));
        hostPlayer1.setMinimumSize(new Dimension(150,50));
        hostPlayer1.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelHostGame.add(hostPlayer1, constraintsContent);

        hostPlayer2.setText("");
        hostPlayer2.setFont(new Font("Serif",Font.PLAIN,textSize));
        hostPlayer2.setMinimumSize(new Dimension(150,50));
        hostPlayer2.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 3;
        panelHostGame.add(hostPlayer2, constraintsContent);

        hostPlayer3.setText("");
        hostPlayer3.setFont(new Font("Serif",Font.PLAIN,textSize));
        hostPlayer3.setMinimumSize(new Dimension(150,50));
        hostPlayer3.setPreferredSize(new Dimension(150,50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelHostGame.add(hostPlayer3, constraintsContent);

        buttonStart.setText("Start Game");
        buttonStart.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonStart.setMinimumSize(new Dimension(150,50));
        buttonStart.setPreferredSize(new Dimension(150,50));
        buttonStart.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        panelHostGame.add(buttonStart, constraintsContent);

        buttonback.setText("Back to Lobby");
        buttonback.setFont(new Font("Serif",Font.PLAIN,textSize));
        buttonback.setMinimumSize(new Dimension(150,50));
        buttonback.setPreferredSize(new Dimension(150,50));
        buttonback.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 6;
        panelHostGame.add(buttonback, constraintsContent);

        /***************************************************************************************************************
         * frame
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelContent,BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(1300, 900));
        frame.setLocation(300, 10);
    }

    public void actionPerformed (ActionEvent e){

        if(e.getSource() == buttonHost){
            panelButtons.setVisible(false);
            panelHostGame.setVisible(true);
        }
        else if(e.getSource() == buttonJoin){

            if(!textAreaJoinNumber.getText().isEmpty()){
                panelButtons.setVisible(false);
                panelJoinGame.setVisible(true);
            }
            else{
                textAreaJoinNumber.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
            }


        }
        else if(e.getSource() == buttonRules){
            Rules rules = new Rules();
            rules.createGui();
        }
        else if(e.getSource() == buttonAbout){
            About about = new About();
            about.createGui();
        }
        else if(e.getSource() == buttonback){
            panelJoinGame.setVisible(false);
            panelHostGame.setVisible(false);
            panelButtons.setVisible(true);
        }
        else if(e.getSource() == buttonback2){
            panelJoinGame.setVisible(false);
            panelHostGame.setVisible(false);
            panelButtons.setVisible(true);
        }

        else if (e.getSource()== buttonReady){
            //TODO ready?
            System.out.println("ready");
        }

        else if(e.getSource()== buttonStart){
            //Todo start game
            System.out.println("start Game");
        }



    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        //StartScreen startScreen = new StartScreen();
        Lobby lobby = new Lobby("marvin");
    }

}
