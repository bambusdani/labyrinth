package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import network.In;
import network.Out;
import lobby.Server;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Lobby implements ActionListener{

    /**
     * Attributes
     */
    private ImageIcon titleimage         = new ImageIcon("src/resources/titel/titelImage.jpg");

    //buttons
    private JButton buttonHost           = new JButton();
    private JButton buttonJoin           = new JButton();
    private JButton buttonRules          = new JButton();
    private JButton buttonReady          = new JButton();
    private JButton buttonStart          = new JButton();
    private JButton buttonback           = new JButton();
    private JButton buttonback2           = new JButton();
    private JButton buttonAbout          = new JButton();

    //textFields - textAreas
    private JTextField textFieldChat     = new JTextField();
    private JTextArea  textAreaChatText  = new JTextArea();
    private JTextArea  textAreaOpenGames = new JTextArea();
    private JTextArea  textAreaPlayer    = new JTextArea();
    private JTextArea  textAreaHostName  = new JTextArea();
    private JTextArea  textAreaJoinNumber = new JTextArea();
    private JTextArea  hostPlayer0       = new JTextArea();
    private JTextArea  hostPlayer1       = new JTextArea();
    private JTextArea  hostPlayer2       = new JTextArea();
    private JTextArea  hostPlayer3       = new JTextArea();
    private JTextArea  joinPlayer0       = new JTextArea();
    private JTextArea  joinPlayer1       = new JTextArea();
    private JTextArea  joinPlayer2       = new JTextArea();
    private JTextArea  joinPlayer3       = new JTextArea();
    private int textSize = 20;

    //panels
    private JPanel panelButtons          = new JPanel(new GridBagLayout());
    private JPanel panelJoinGame         = new JPanel(new GridBagLayout());
    private JPanel panelHostGame         = new JPanel(new GridBagLayout());

    //socket
    private Socket socket;
    private In in;
    private Out out;

    //logger
    private final Logger LOGGER = Logger.getLogger(Lobby.class.getName());

    //players
    private String playerName;
    private String hostName;
    private String tmpName, nameOfPlayer;
    private String playerID;
    private String room="";
    private String gameRooms="";
    private boolean ready = false;
    private boolean host = false;

    //Frame
    JFrame frame = new JFrame("Das Verrückte Labyrinth");

    private int portNumber = 0;


    public void connectToServer(String hostName, String name){

        this.hostName = hostName;
        this.playerName = name;

        /***************************************************************************************************************
         * create connection with lobbyServer
         */
        try {
            socket = new Socket(hostName, 4444);
            out = new Out(socket);
            in = new In(socket);

            out.println("connect " + name);

        } catch (Exception e) {
            textAreaChatText.setText("Keine Verbindung zum Server, bitte Spiel neu starten!");
        }
        nameOfPlayer = "[" + name + "]: ";
        tmpName = name;
    }

    public void createLobby() {
        titleimage.setImage(titleimage.getImage().getScaledInstance(480, 350, Image.SCALE_DEFAULT));
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
        labelLogo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 3;
        constraintsContent.insets = new Insets(10, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelContent.add(labelLogo, constraintsContent);

        /***************************************************************************************************************
         * panelButtons
         */
        panelButtons.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        panelButtons.setMinimumSize(new Dimension(480, 450));
        panelButtons.setPreferredSize(new Dimension(480, 450));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelButtons, constraintsContent);

        panelJoinGame.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        panelJoinGame.setMinimumSize(new Dimension(480, 450));
        panelJoinGame.setPreferredSize(new Dimension(480, 450));
        panelJoinGame.setVisible(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelJoinGame, constraintsContent);

        panelHostGame.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        panelHostGame.setMinimumSize(new Dimension(480, 450));
        panelHostGame.setPreferredSize(new Dimension(480, 450));
        panelHostGame.setVisible(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelContent.add(panelHostGame, constraintsContent);

        /***************************************************************************************************************
         * Chatwindow
         */
        textAreaChatText.setFont(new Font("Serif", Font.PLAIN, textSize));
        textAreaChatText.setMinimumSize(new Dimension(480, 700));
        textAreaChatText.setPreferredSize(new Dimension(480, 700));
        textAreaChatText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        textAreaChatText.setEditable(false);
        textAreaChatText.setLineWrap(true);
        textAreaChatText.setWrapStyleWord(true);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 7;
        constraintsContent.insets = new Insets(10, 0, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 0;
        panelContent.add(textAreaChatText, constraintsContent);

        /***************************************************************************************************************
         * chatTextField
         */
        textFieldChat.setFont(new Font("Serif", Font.PLAIN, textSize));
        textFieldChat.setMinimumSize(new Dimension(480, 100));
        textFieldChat.setPreferredSize(new Dimension(480, 100));
        textFieldChat.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        textFieldChat.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 8;
        panelContent.add(textFieldChat, constraintsContent);

        /***************************************************************************************************************
         * textAreaOpenGames
         */
        textAreaOpenGames.setText("Open Game Rooms:\n");
        textAreaOpenGames.setFont(new Font("Serif", Font.PLAIN, textSize));
        textAreaOpenGames.setMinimumSize(new Dimension(280, 350));
        textAreaOpenGames.setPreferredSize(new Dimension(280, 350));
        textAreaOpenGames.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        textAreaOpenGames.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 3;
        constraintsContent.insets = new Insets(10, 0, 0, 0);
        constraintsContent.gridx = 2;
        constraintsContent.gridy = 0;
        panelContent.add(textAreaOpenGames, constraintsContent);

        /***************************************************************************************************************
         * player names
         */
        textAreaPlayer.setText("Players in Lobby:");
        textAreaPlayer.setFont(new Font("Serif", Font.PLAIN, textSize));
        textAreaPlayer.setMinimumSize(new Dimension(280, 450));
        textAreaPlayer.setPreferredSize(new Dimension(280, 450));
        textAreaPlayer.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        textAreaPlayer.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 5;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 2;
        constraintsContent.gridy = 4;
        panelContent.add(textAreaPlayer, constraintsContent);

        /***************************************************************************************************************
         * panelButton
         */
        buttonHost.setText("Host Game");
        buttonHost.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonHost.setMinimumSize(new Dimension(150, 50));
        buttonHost.setPreferredSize(new Dimension(150, 50));
        buttonHost.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelButtons.add(buttonHost, constraintsContent);

        textAreaHostName.setFont(new Font("Serif", Font.PLAIN, textSize));
        textAreaHostName.setMinimumSize(new Dimension(150, 50));
        textAreaHostName.setPreferredSize(new Dimension(150, 50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 10, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 0;
        panelButtons.add(textAreaHostName, constraintsContent);

        buttonJoin.setText("Join Game");
        buttonJoin.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonJoin.setMinimumSize(new Dimension(150, 50));
        buttonJoin.setPreferredSize(new Dimension(150, 50));
        buttonJoin.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelButtons.add(buttonJoin, constraintsContent);

        textAreaJoinNumber.setFont(new Font("Serif", Font.PLAIN, textSize));
        textAreaJoinNumber.setMinimumSize(new Dimension(150, 50));
        textAreaJoinNumber.setPreferredSize(new Dimension(150, 50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 10, 0, 0);
        constraintsContent.gridx = 1;
        constraintsContent.gridy = 1;
        panelButtons.add(textAreaJoinNumber, constraintsContent);


        buttonRules.setText("Game Rules");
        buttonRules.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonRules.setMinimumSize(new Dimension(150, 50));
        buttonRules.setPreferredSize(new Dimension(150, 50));
        buttonRules.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelButtons.add(buttonRules, constraintsContent);

        /*************************************************************************
         * About
         */
        buttonAbout.setText("About");
        buttonAbout.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonAbout.setMinimumSize(new Dimension(150, 50));
        buttonAbout.setPreferredSize(new Dimension(150, 50));
        buttonAbout.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 3;
        panelButtons.add(buttonAbout, constraintsContent);

        /***************************************************************************************************************
         * panelJoinGame
         */
        JLabel labelReady = new JLabel();
        labelReady.setText("Join Game");
        labelReady.setFont(new Font("Serif", Font.PLAIN, textSize));
        labelReady.setHorizontalAlignment(SwingConstants.CENTER);
        labelReady.setVerticalAlignment(SwingConstants.CENTER);
        labelReady.setMinimumSize(new Dimension(150, 50));
        labelReady.setPreferredSize(new Dimension(150, 50));
        constraintsContent.anchor = GridBagConstraints.CENTER;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelJoinGame.add(labelReady, constraintsContent);

        joinPlayer0.setText("");
        joinPlayer0.setFont(new Font("Serif", Font.PLAIN, textSize));
        joinPlayer0.setMinimumSize(new Dimension(150, 50));
        joinPlayer0.setPreferredSize(new Dimension(150, 50));
        joinPlayer0.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelJoinGame.add(joinPlayer0, constraintsContent);

        joinPlayer1.setText("");
        joinPlayer1.setFont(new Font("Serif", Font.PLAIN, textSize));
        joinPlayer1.setMinimumSize(new Dimension(150, 50));
        joinPlayer1.setPreferredSize(new Dimension(150, 50));
        joinPlayer1.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelJoinGame.add(joinPlayer1, constraintsContent);

        joinPlayer2.setText("");
        joinPlayer2.setFont(new Font("Serif", Font.PLAIN, textSize));
        joinPlayer2.setMinimumSize(new Dimension(150, 50));
        joinPlayer2.setPreferredSize(new Dimension(150, 50));
        joinPlayer2.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 3;
        panelJoinGame.add(joinPlayer2, constraintsContent);

        joinPlayer3.setText("");
        joinPlayer3.setFont(new Font("Serif", Font.PLAIN, textSize));
        joinPlayer3.setMinimumSize(new Dimension(150, 50));
        joinPlayer3.setPreferredSize(new Dimension(150, 50));
        joinPlayer3.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelJoinGame.add(joinPlayer3, constraintsContent);

        buttonReady.setText("Ready?");
        buttonReady.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonReady.setMinimumSize(new Dimension(150, 50));
        buttonReady.setPreferredSize(new Dimension(150, 50));
        buttonReady.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        panelJoinGame.add(buttonReady, constraintsContent);

        buttonback2.setText("Leave " + room);
        buttonback2.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonback2.setMinimumSize(new Dimension(150, 50));
        buttonback2.setPreferredSize(new Dimension(150, 50));
        buttonback2.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 6;
        panelJoinGame.add(buttonback2, constraintsContent);

        /***************************************************************************************************************
         * panelHostGame
         */
        JLabel labelHost = new JLabel();
        labelHost.setText("Host Game");
        labelHost.setFont(new Font("Serif", Font.PLAIN, textSize));
        labelHost.setHorizontalAlignment(SwingConstants.CENTER);
        labelHost.setVerticalAlignment(SwingConstants.CENTER);
        labelHost.setMinimumSize(new Dimension(150, 50));
        labelHost.setPreferredSize(new Dimension(150, 50));
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelHostGame.add(labelHost, constraintsContent);

        hostPlayer0.setText("");
        hostPlayer0.setFont(new Font("Serif", Font.PLAIN, textSize));
        hostPlayer0.setMinimumSize(new Dimension(150, 50));
        hostPlayer0.setPreferredSize(new Dimension(150, 50));
        hostPlayer0.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelHostGame.add(hostPlayer0, constraintsContent);

        hostPlayer1.setText("");
        hostPlayer1.setFont(new Font("Serif", Font.PLAIN, textSize));
        hostPlayer1.setMinimumSize(new Dimension(150, 50));
        hostPlayer1.setPreferredSize(new Dimension(150, 50));
        hostPlayer1.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 2;
        panelHostGame.add(hostPlayer1, constraintsContent);

        hostPlayer2.setText("");
        hostPlayer2.setFont(new Font("Serif", Font.PLAIN, textSize));
        hostPlayer2.setMinimumSize(new Dimension(150, 50));
        hostPlayer2.setPreferredSize(new Dimension(150, 50));
        hostPlayer2.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 3;
        panelHostGame.add(hostPlayer2, constraintsContent);

        hostPlayer3.setText("");
        hostPlayer3.setFont(new Font("Serif", Font.PLAIN, textSize));
        hostPlayer3.setMinimumSize(new Dimension(150, 50));
        hostPlayer3.setPreferredSize(new Dimension(150, 50));
        hostPlayer3.setEditable(false);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 4;
        panelHostGame.add(hostPlayer3, constraintsContent);

        buttonStart.setText("Start Game");
        buttonStart.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonStart.setMinimumSize(new Dimension(150, 50));
        buttonStart.setPreferredSize(new Dimension(150, 50));
        buttonStart.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        panelHostGame.add(buttonStart, constraintsContent);

        buttonback.setText("Leave " + room);
        buttonback.setFont(new Font("Serif", Font.PLAIN, textSize));
        buttonback.setMinimumSize(new Dimension(150, 50));
        buttonback.setPreferredSize(new Dimension(150, 50));
        buttonback.addActionListener(this);
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 0;
        constraintsContent.weighty = 0;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight = 1;
        constraintsContent.insets = new Insets(15, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 6;
        panelHostGame.add(buttonback, constraintsContent);

        /***************************************************************************************************************
         * frame
         */
        GradientPanel panelBackground = new GradientPanel();
        panelContent.setOpaque(false);
        panelBackground.add(panelContent);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelBackground, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(1300, 900));
        frame.setLocation(300, 10);
    }

    /**
     * ActionListener
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textFieldChat) {
            // send message to server
            out.println("chat " + nameOfPlayer + textFieldChat.getText());
            // log outgoing chat message
            LOGGER.info("OUTGOING chat " + textFieldChat.getText());

            // clear textField
            textFieldChat.setText("");
            textFieldChat.requestFocusInWindow();
        } else if (e.getSource() == buttonHost) {
            if (!textAreaHostName.getText().isEmpty()) {
                // indicate to server that this player is host
                out.println("host " + textAreaHostName.getText());
                // log outgoing message
                LOGGER.info("OUTGOING host " + textAreaHostName.getText());
                // set room
                room = textAreaHostName.getText();

                // host is always ready, send to server
                out.println("ready");
                // log outgoing message
                LOGGER.info("OUTGOING ready");
                // set back button text
                buttonback.setText("Leave " + room);

                panelButtons.setVisible(false);
                panelHostGame.setVisible(true);

            } else {
                textAreaHostName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
            }

        } else if (e.getSource() == buttonJoin) {
            // send join request to server
            if (!textAreaJoinNumber.getText().isEmpty()) {
                // set room
                room = textAreaJoinNumber.getText();
                // count the players in a gameRoom by counting
                // the strings of the same room
                int tmpCounter = 0;
                // split gameRooms string
                String[] tmpRooms = gameRooms.split("\\s+");
                for (int j = 0; j < tmpRooms.length; j = j+2) {
                    if (tmpRooms[j].equalsIgnoreCase(textAreaJoinNumber.getText())) {
                        tmpCounter++;
                    }
                }

                // if tmpCounter is greater 0 and less than 4 then there
                // is room for more players in the gameRoom
                if (tmpCounter > 0 && tmpCounter < 4) {
                    // send join message to server
                    out.println("join " + textAreaJoinNumber.getText());
                    // log outgoing message
                    LOGGER.info("OUTGOING join " + textAreaJoinNumber.getText());


                    // set back button text
                    buttonback2.setText("Leave " + room);

                    panelButtons.setVisible(false);
                    panelJoinGame.setVisible(true);
                } else {
                    textAreaChatText.append(textAreaJoinNumber.getText() + " ist voll oder existiert nicht!\n");
                }
            } else {
                textAreaJoinNumber.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
            }
        } else if (e.getSource() == buttonRules) {
            Rules rules = new Rules();
            rules.createGui();

        } else if (e.getSource() == buttonAbout) {
            About about = new About();
            about.createGui();

        } else if (e.getSource() == buttonback) {
            panelJoinGame.setVisible(false);
            panelHostGame.setVisible(false);
            panelButtons.setVisible(true);
            textAreaHostName.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
            textAreaHostName.setText("");
            textAreaJoinNumber.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
            textAreaJoinNumber.setText("");

            // send leave message to server
            out.println("leave " + room);
            // log outgoing message
            LOGGER.info("OUTGOING leave " + room);

        } else if (e.getSource() == buttonback2) {
            panelJoinGame.setVisible(false);
            panelHostGame.setVisible(false);
            panelButtons.setVisible(true);
            textAreaHostName.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
            textAreaHostName.setText("");
            textAreaJoinNumber.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
            textAreaJoinNumber.setText("");

            // send leave message to server
            out.println("leave " + room);
            // log outgoing message
            LOGGER.info("OUTGOING leave " + room);

        } else if (e.getSource() == buttonReady) {
            // send 'ready playerID' to server
            out.println("ready");
            // log outgoing message
            LOGGER.info("OUTGOING ready");

        } else if (e.getSource() == buttonStart) {
            // send start to server
            out.println("start");
            // log outgoing message
            LOGGER.info("OUTGOING start");
        }
    }

    /*******************************************************************************************************************
     * incoming messages from server
     */
    public void listen() {
        String s;
        while ((s = in.readLine()) != null) {
            // init playerID
            if (s.startsWith("initPlayerID")) {
                String[] tmpPlayerID = s.split("\\s+");

                // set playerID
                playerID = tmpPlayerID[1];

                // init logger
                try {
                    FileHandler fileHandler = new FileHandler("lobby_0" + tmpPlayerID[1] + ".log");
                    LOGGER.addHandler(fileHandler);
                    LOGGER.info("*****STARTING*****");
                    // log outgoing connect message
                    LOGGER.info("OUTGOING connect " + tmpName);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            // player names and id's (sent on with every new connection)
            else if (s.startsWith("players")) {
                /*****************************************
                 * after split:
                 * tmpMessage[0] = "players"
                 * tmpMessage[uneven number] = playerID
                 * tmpMessage[even number] = player name
                 *****************************************/
                String[] tmpMessage = s.split("\\s+");
                String tmpNames = "";

                for (int i = 2; i < tmpMessage.length; i = i + 2) {
                    tmpNames += tmpMessage[i] + " (id: " + tmpMessage[i-1] + ")" + "\n";
                }

                textAreaPlayer.setText("Players in Lobby:\n" + tmpNames);

                // log players on new connection
                LOGGER.info("INCOMING " + s);

            }
            // 'welcome' parameter
            else if (s.startsWith("welcome")) {
                // log incoming welcome message
                LOGGER.info("INCOMING " + s);
            }
            // 'hosts' parameter
            else if (s.startsWith("hosts")) {
                // log incoming message
                LOGGER.info("INCOMING " + s);
            }
            // 'rooms' parameter
            else if (s.startsWith("rooms")) {
                textAreaOpenGames.setText("Open Games:\n");
                // log incoming rooms message
                LOGGER.info("INCOMING " + s);
                // assign rooms to gameRooms
                gameRooms = s.substring(6);

                String[] tmpRooms = s.split("\\s+");
                for (int i = 1; i < tmpRooms.length; i = i+2) {
                    if (!textAreaOpenGames.getText().contains(tmpRooms[i])) {
                        textAreaOpenGames.append(tmpRooms[i] + "\n");
                    }
                }
            }
            // 'kick' (kick roomName)
            else if (s.startsWith("kick")) {
                String[] tmpKick = s.split("\\s+");
                // log incoming message
                LOGGER.info("INCOMING " + s);

                // leave game
                if (tmpKick[1].equalsIgnoreCase(room)) {
                    buttonback.doClick();
                    buttonback2.doClick();
                }
            }
            // 'ready playerID' parameter
            else {
                if (s.startsWith("ready")) {
                    // log incoming ready message
                    LOGGER.info("INCOMING " + s);
                    // set player to ready
                    String[] tmpReady = s.split("\\s+");
                    if (playerID.equalsIgnoreCase(tmpReady[1])) {
                        this.ready = true;
                    }
                }
                // readyPlayers
                else if (s.startsWith("drawReadyPlayers")) {
                    String[] tmpReadyPlayers = s.split("\\s+");

                    // draw readyPlayers
                    for (int i = 1; i < tmpReadyPlayers.length; i = i+2) {
                        if (hostPlayer0.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                hostPlayer1.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                hostPlayer2.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                hostPlayer3.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                joinPlayer0.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                joinPlayer1.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                joinPlayer2.getText().equalsIgnoreCase(tmpReadyPlayers[i+1]) ||
                                joinPlayer3.getText().equalsIgnoreCase(tmpReadyPlayers[i+1])) {
                        }
                        else if (tmpReadyPlayers[i].equalsIgnoreCase(room)) {
                            // player 0
                            if (hostPlayer0.getText().equalsIgnoreCase("") && joinPlayer0.getText().equalsIgnoreCase("")) {
                                hostPlayer0.setText(tmpReadyPlayers[i+1]);
                                joinPlayer0.setText(tmpReadyPlayers[i+1]);
                            }
                            // player 1
                            else if (hostPlayer1.getText().equalsIgnoreCase("") && joinPlayer1.getText().equalsIgnoreCase("")) {
                                hostPlayer1.setText(tmpReadyPlayers[i+1]);
                                joinPlayer1.setText(tmpReadyPlayers[i+1]);
                            }
                            // player 2
                            else if (hostPlayer2.getText().equalsIgnoreCase("") && joinPlayer2.getText().equalsIgnoreCase("")) {
                                hostPlayer2.setText(tmpReadyPlayers[i+1]);
                                joinPlayer2.setText(tmpReadyPlayers[i+1]);
                            }
                            // player 3
                            else if (hostPlayer3.getText().equalsIgnoreCase("") && joinPlayer3.getText().equalsIgnoreCase("")) {
                                hostPlayer3.setText(tmpReadyPlayers[i+1]);
                                joinPlayer3.setText(tmpReadyPlayers[i+1]);
                            }
                        }
                    }
                }
                // 'gamestart' parameter
                else if (s.startsWith("gamestart")) {
                    String[] tmpGameStart = s.split("\\s+");

                    // log incoming game start message
                    LOGGER.info("INCOMING " + s);

                    if (tmpGameStart[1].equals(room)) {
                        // send player name to server so we can update players string
                        out.println("playerName " + playerName);
                        frame.dispose();
                        PlayGround playGround = new PlayGround(this.hostName, portNumber, this.playerName);
                        playGround.listen();
                    }
                }
                //portNummer
                else if (s.startsWith("portNumber ")) {
                    String[] tmpPortNumber = s.split("\\s+");
                    portNumber = Integer.parseInt(tmpPortNumber[1]);

                }
                // gameRoom
                else if (s.startsWith("gameRoom")) {
                    String[] tmpGameRoom = s.split("\\s+");
                    this.room = tmpGameRoom[1];
                }
                // 'chat' parameter
                else {
                    // write incoming chat message in textArea
                    textAreaChatText.insert(s + "\n", textAreaChatText.getText().length());
                    textAreaChatText.setCaretPosition(textAreaChatText.getText().length());
                    // log incoming chat message
                    LOGGER.info("INCOMING " + s);
                }
            }
        }

        out.close();
        in.close();
        try                 { socket.close();      }
        catch (Exception e) { e.printStackTrace(); }
        System.err.println("Closed client socket");
    }

    /**
     * main -> starts StartScreen
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        StartScreen startScreen = new StartScreen();
        boolean endWhile = true;
        do{
            System.out.println("");
            if(startScreen.getSubmitPressed()){
                String ip =startScreen.getIP();
                String name = startScreen.getName();
                //StartScreen startScreen = new StartScreen();
                Lobby lobby = new Lobby();
                lobby.createLobby();
                lobby.connectToServer(ip,name);
                //startScreen.closeStartScreen();
                lobby.listen();
                endWhile = false;
            }
        }while (endWhile);
    }
}

