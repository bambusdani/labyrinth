package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import network.*;
import gameLogic.*;

import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class PlayGround implements ActionListener {

    /**
     * Attributes
     */
    //player
    private int playerID;
    ArrayList<Boolean> playersTurn = new ArrayList<Boolean>();
    private Boolean tileInsertionAllowed = true;
    private int playersTurnID = 0;
    private boolean tileInserted = false;

    //to check if turns are valid
    private boolean moveValid;
    private boolean isPushAllowed;
    private int disabledButtonID;

    //style settings
    private int fontSize = 20;
    private int boxSizeX = 175;
    private int boxSizeY = 50;
    private Color colorBlack = new Color(0, 0, 0);
    private int stoneSize = 75;

    //gameField buttons
    private JButton buttonNewGame;
    private JButton buttonEndGame;
    private	JButton buttonRotate;
    public JButton[][] boardSquares = new JButton[7][7];

    //Frame
    private JFrame frame;

    //Label of the nextStone
    public JLabel labelNextStoneSymbol;

    // Images for the Arrrow buttons
    private ImageIcon imageArrowDown = new ImageIcon("src/resources/arrows/downArrow.png");
    private ImageIcon imageArrowLeft = new ImageIcon("src/resources/arrows/leftArrow.png");
    private ImageIcon imageArrowUp	 = new ImageIcon("src/resources/arrows/upArrow.png");
    private ImageIcon imageArrowRight= new ImageIcon("src/resources/arrows/rightArrow.png");
    private ImageIcon imageRotate	 = new ImageIcon("src/resources/arrows/rotateArrow.png");

    //Buttons for the arrows to place the next stone
    private JButton[] buttonArrow_Array = new JButton[12];
    //top
    private JButton buttonArrow_1_0;
    private JButton buttonArrow_3_0;
    private JButton buttonArrow_5_0;
    //bottom
    private JButton buttonArrow_1_6;
    private JButton buttonArrow_3_6;
    private JButton buttonArrow_5_6;
    //left
    private JButton buttonArrow_0_1;
    private JButton buttonArrow_0_3;
    private JButton buttonArrow_0_5;
    //right
    private JButton buttonArrow_6_1;
    private JButton buttonArrow_6_3;
    private JButton buttonArrow_6_5;

    //label Player information with points
    private JLabel labelPlayer0;
    private JLabel labelPlayer1;
    private JLabel labelPlayer2;
    private JLabel labelPlayer3;
    //to draw borders
    private Border border0 ;
    private Border border1;
    private Border border2;
    private Border border3;
    //two colors
    private Border border01;
    private Border border02;
    private Border border03;
    private Border border12;
    private Border border13;
    private Border border23;
    //three colors
    private Border border012;
    private Border border123;
    private Border border230;
    private Border border013;
    //four colors
    private Border border0123;

    //label für das Nachste Ziel
    private JLabel labelNextGoalSymbol;

    //network stuff
    private JTextArea textArea;
    private JTextField textField;

    public BoardFromClient board = new BoardFromClient();

    // socket for connection to network server
    private Socket socket;

    // for writing to and reading from the server
    private Out out;
    private In in;
    private String screenName;
    private String initName;
    private final Logger LOGGER = Logger.getLogger(PlayGround.class.getName());


    /**
     * Constructor
     * @param hostName
     * @param screenName
     */
    public PlayGround(String hostName, int port, String screenName) {
        // connect to server
        try {
            socket = new Socket(hostName, port);
            out    = new Out(socket);
            in     = new In(socket);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        this.screenName = "[" + screenName + "]: ";
        this.initName = screenName;
        out.println("initName " + initName);

        playersTurn.add(true);
        playersTurn.add(false);
        playersTurn.add(false);
        playersTurn.add(false);

        //to draw the borders
        border0 = BorderFactory.createLineBorder(board.getAllPlayers()[0].getColor(), 3);
        border1 = BorderFactory.createLineBorder(board.getAllPlayers()[1].getColor(), 3);
        border2 = BorderFactory.createLineBorder(board.getAllPlayers()[2].getColor(), 3);
        border3 = BorderFactory.createLineBorder(board.getAllPlayers()[3].getColor(), 3);
        //two colors
        border01 = new CompoundBorder(border0, border1);
        border02 = new CompoundBorder(border0, border2);
        border03 = new CompoundBorder(border0, border3);
        border12 = new CompoundBorder(border1, border2);
        border13 = new CompoundBorder(border1, border3);
        border23 = new CompoundBorder(border2, border3);
        //three colors
        border012 = new CompoundBorder(border01,border2);
        border123 = new CompoundBorder(border12,border3);
        border230 = new CompoundBorder(border23,border0);
        border013 = new CompoundBorder(border01,border3);
        //four colors
        border0123 = new CompoundBorder(border01,border23);

        //--------------------------------------------------------------------------------------------------
        //set size of images
        imageArrowDown.setImage(imageArrowDown.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        imageArrowLeft.setImage(imageArrowLeft.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        imageArrowUp.setImage(imageArrowUp.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        imageArrowRight.setImage(imageArrowRight.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        imageRotate.setImage(imageRotate.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));

        //================================================================================
        // panel Player overview
        //================================================================================
        JPanel panelPlayeroverview = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsPlayeroverview = new GridBagConstraints();

        constraintsPlayeroverview.anchor = GridBagConstraints.CENTER;
        constraintsPlayeroverview.weightx = 1;
        constraintsPlayeroverview.weighty = 1;
        constraintsPlayeroverview.gridwidth = 1;
        constraintsPlayeroverview.insets = new Insets(15, 10, 10, 10);

        //---------------------------------------------------------------------------------
        // Player 0
        constraintsPlayeroverview.gridx = 1;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer0 = setLabel("" , fontSize,boxSizeX, boxSizeY, colorBlack);
        labelPlayer0.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer0.setVerticalAlignment(SwingConstants.CENTER);
        panelPlayeroverview.add(labelPlayer0, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 1
        constraintsPlayeroverview.gridx = 2;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer1 = setLabel("" , fontSize,boxSizeX, boxSizeY, colorBlack);
        labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer1.setVerticalAlignment(SwingConstants.CENTER);
        panelPlayeroverview.add(labelPlayer1, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 2
        constraintsPlayeroverview.gridx = 3;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer2 = setLabel("" , fontSize,boxSizeX, boxSizeY, colorBlack);
        labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer2.setVerticalAlignment(SwingConstants.CENTER);
        panelPlayeroverview.add(labelPlayer2, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 3
        constraintsPlayeroverview.gridx = 4;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer3 = setLabel("" , fontSize,boxSizeX, boxSizeY, colorBlack);
        labelPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer3.setVerticalAlignment(SwingConstants.CENTER);
        panelPlayeroverview.add(labelPlayer3, constraintsPlayeroverview);

        //---------------------------------------------------------------------------------
        // Button new Game
        constraintsPlayeroverview.gridx = 5;
        constraintsPlayeroverview.gridy = 0;
        this.buttonNewGame = setButtons("Neues Spiel", fontSize, boxSizeX, boxSizeY);
        //adding ActionListener
        this.buttonNewGame.addActionListener(this);
        panelPlayeroverview.add(this.buttonNewGame, constraintsPlayeroverview);

        //---------------------------------------------------------------------------------
        // Button end Game
        constraintsPlayeroverview.gridx = 6;
        constraintsPlayeroverview.gridy = 0;
        this.buttonEndGame = setButtons("Spiel beenden", fontSize, boxSizeX, boxSizeY);
        //adding ActionListener
        this.buttonEndGame.addActionListener(this);
        panelPlayeroverview.add(this.buttonEndGame, constraintsPlayeroverview);

        //===================================================================================
        // panel information
        //===================================================================================
        JPanel panelInformation = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsInformation = new GridBagConstraints();

        constraintsInformation.anchor = GridBagConstraints.CENTER;
        constraintsInformation.weightx = 1;
        constraintsInformation.weighty = 1;
        constraintsInformation.gridwidth = 1;
        constraintsInformation.insets = new Insets(75, 0, 0, 0);

        //-----------------------------------------------------------------------------------
        // next goal
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 0;
        JLabel labelNextGoal = setLabel("Nächstes Ziel: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        labelNextGoal.setHorizontalAlignment(SwingConstants.CENTER);
        labelNextGoal.setVerticalAlignment(SwingConstants.CENTER);
        panelInformation.add(labelNextGoal, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next goal symbol
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 1;
        constraintsInformation.insets = new Insets(0, 0, 0, 0);
        labelNextGoalSymbol= setLabel("", fontSize, stoneSize,stoneSize,colorBlack);
        //labelNextGoalSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        panelInformation.add(labelNextGoalSymbol, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next stone
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 2;
        JLabel labelNextStone = setLabel("Nächster Stein: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        labelNextStone.setHorizontalAlignment(SwingConstants.CENTER);
        labelNextStone.setVerticalAlignment(SwingConstants.CENTER);
        panelInformation.add(labelNextStone, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next stone symbol
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 3;

        this.labelNextStoneSymbol = setLabel("",fontSize, stoneSize, stoneSize, colorBlack );
        //this.labelNextStoneSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        panelInformation.add(this.labelNextStoneSymbol, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // Button rotate
        constraintsInformation.insets = new Insets(20, 0, 0, 0);
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 6;
        this.buttonRotate = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonRotate.setIcon(imageRotate);
        //add ActionListener
        this.buttonRotate.addActionListener(this);
        panelInformation.add(this.buttonRotate, constraintsInformation);

        //chat zur info hinzufügen
        GridBagConstraints constraintsChat = new GridBagConstraints();

        constraintsChat.anchor = GridBagConstraints.SOUTHEAST;
        constraintsChat.weightx = 1;
        constraintsChat.weighty = 1;
        constraintsChat.gridwidth = 1;
        constraintsChat.gridheight = 5;
        constraintsChat.insets = new Insets(50, 0, 0, 0);

        //-----------------------------------------------------------------------------------
        // text area
        constraintsChat.gridx = 0;
        constraintsChat.gridy = 7;
        textArea = setTextArea(350 , 125);
        textArea.setEditable(false);

        // creating scroll panel
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setMinimumSize(new Dimension(350,125));
        scroll.setPreferredSize(new Dimension(350,125));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelInformation.add(scroll, constraintsChat);

        // input field
        constraintsChat.gridheight=1;
        constraintsChat.gridx = 0;
        constraintsChat.gridy = 13;
        constraintsChat.insets = new Insets(0, 0, 0, 0);
        textField = setTextField(350 , 32);
        textField.addActionListener(this);
        panelInformation.add(textField, constraintsChat);



        //===================================================================================
        // panel network
        //===================================================================================
        /*JPanel panelChat = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsChat = new GridBagConstraints();

        constraintsChat.anchor = GridBagConstraints.SOUTHWEST;
        constraintsChat.weightx = 1;
        constraintsChat.weighty = 1;
        constraintsChat.gridwidth = 1;
        constraintsChat.insets = new Insets(0, 5, 0, 5);

        //-----------------------------------------------------------------------------------
        // text area
        constraintsChat.gridx = 0;
        constraintsChat.gridy = 0;

        textArea = setTextArea(300 , 125);
        textArea.setEditable(false);

        // creating scroll panel
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setMinimumSize(new Dimension(300,125));
        scroll.setPreferredSize(new Dimension(300,125));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelChat.add(scroll, constraintsChat);

        //-----------------------------------------------------------------------------------
        // input field
        constraintsChat.gridx = 0;
        constraintsChat.gridy = 1;
        textField = setTextField(300 , 32);
        textField.addActionListener(this);
        panelChat.add(textField, constraintsChat);
*/
        //===================================================================================
        // panel board
        //===================================================================================
        JPanel panelGameField = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsGameField = new GridBagConstraints();

        constraintsGameField.anchor = GridBagConstraints.CENTER;
        constraintsGameField.weightx = 1;
        constraintsGameField.weighty = 1;
        constraintsGameField.gridwidth = 1;
        constraintsGameField.insets = new Insets(0, 0, 0, 0);

        //--------------------------------------------------
        // Buttons to place the next stone
        //--------------------------------------------------
        // #1 top
        constraintsGameField.gridx = 2;
        constraintsGameField.gridy = 0;
        this.buttonArrow_1_0 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_1_0.setIcon(imageArrowDown);
        //adding ActionListener
        this.buttonArrow_1_0.addActionListener(this);
        panelGameField.add(this.buttonArrow_1_0, constraintsGameField);

        // #2 top
        constraintsGameField.gridx = 4;
        constraintsGameField.gridy = 0;
        this.buttonArrow_3_0 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_3_0.setIcon(imageArrowDown);
        //adding ActionListener
        this.buttonArrow_3_0.addActionListener(this);
        panelGameField.add(this.buttonArrow_3_0, constraintsGameField);

        // #3 top
        constraintsGameField.gridx = 6;
        constraintsGameField.gridy = 0;
        this.buttonArrow_5_0 = setButtons("", fontSize, stoneSize, stoneSize);
        //adding ActionListener
        this.buttonArrow_5_0.addActionListener(this);
        this.buttonArrow_5_0.setIcon(imageArrowDown);
        panelGameField.add(this.buttonArrow_5_0, constraintsGameField);

        //-----------------------------
        // #1 bottom
        constraintsGameField.gridx = 2;
        constraintsGameField.gridy = 8;
        this.buttonArrow_1_6 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_1_6.setIcon(imageArrowUp);
        //adding ActionListener
        this.buttonArrow_1_6.addActionListener(this);
        panelGameField.add(this.buttonArrow_1_6, constraintsGameField);

        // #2 bottom
        constraintsGameField.gridx = 4;
        constraintsGameField.gridy = 8;
        this.buttonArrow_3_6 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_3_6.setIcon(imageArrowUp);
        //adding ActionListener
        this.buttonArrow_3_6.addActionListener(this);
        panelGameField.add(this.buttonArrow_3_6, constraintsGameField);

        // #3 bottom
        constraintsGameField.gridx = 6;
        constraintsGameField.gridy = 8;
        this.buttonArrow_5_6 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_5_6.setIcon(imageArrowUp);
        //adding ActionListener
        this.buttonArrow_5_6.addActionListener(this);
        panelGameField.add(this.buttonArrow_5_6, constraintsGameField);

        //-----------------------------
        // #1 left
        constraintsGameField.gridx = 0;
        constraintsGameField.gridy = 2;
        this.buttonArrow_0_1 = setButtons("", fontSize, stoneSize, stoneSize);
        //adding ActionListener
        this.buttonArrow_0_1.addActionListener(this);
        this.buttonArrow_0_1.setIcon(imageArrowRight);
        panelGameField.add(this.buttonArrow_0_1, constraintsGameField);

        // #2 left
        constraintsGameField.gridx = 0;
        constraintsGameField.gridy = 4;
        this.buttonArrow_0_3 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_0_3.setIcon(imageArrowRight);
        //adding ActionListener
        this.buttonArrow_0_3.addActionListener(this);
        panelGameField.add(this.buttonArrow_0_3, constraintsGameField);

        // #3 left
        constraintsGameField.gridx = 0;
        constraintsGameField.gridy = 6;
        this.buttonArrow_0_5 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_0_5.setIcon(imageArrowRight);
        //adding ActionListener
        this.buttonArrow_0_5.addActionListener(this);
        panelGameField.add(this.buttonArrow_0_5, constraintsGameField);

        //-----------------------------
        // #1 right
        constraintsGameField.gridx = 8;
        constraintsGameField.gridy = 2;
        this.buttonArrow_6_1 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_6_1.setIcon(imageArrowLeft);
        //adding ActionListener
        this.buttonArrow_6_1.addActionListener(this);
        panelGameField.add(this.buttonArrow_6_1, constraintsGameField);

        // #2 right
        constraintsGameField.gridx = 8;
        constraintsGameField.gridy = 4;
        this.buttonArrow_6_3 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_6_3.setIcon(imageArrowLeft);
        //adding ActionListener
        this.buttonArrow_6_3.addActionListener(this);
        panelGameField.add(this.buttonArrow_6_3, constraintsGameField);

        // #3 right
        constraintsGameField.gridx = 8;
        constraintsGameField.gridy = 6;
        this.buttonArrow_6_5 = setButtons("", fontSize, stoneSize, stoneSize);
        this.buttonArrow_6_5.setIcon(imageArrowLeft);
        //adding ActionListener
        this.buttonArrow_6_5.addActionListener(this);
        panelGameField.add(this.buttonArrow_6_5, constraintsGameField);

        //-----------------------------------------------------------------------------------------
        // creates 7 X 7 Buttons
        //global
        //JButton[][] boardSquares = new JButton[7][7];
        for(int j = 0; j < boardSquares.length; j++){
            for(int i = 0; i< boardSquares[j].length; i++){
                //creating button
                constraintsGameField.gridx = j+1;
                constraintsGameField.gridy = i+1;
                JButton buttonStone = setButtons("", fontSize, stoneSize, stoneSize);

                //-------------------------
                // saves the field in a 2d array
                boardSquares[j][i]= buttonStone;
                //adding actionListener
                boardSquares[j][i].setActionCommand("gameField: "+j+" "+i);
                boardSquares[j][i].addActionListener(this);
                panelGameField.add(boardSquares[j][i], constraintsGameField);
            }
        }

        //====================================================================================
        // creating frame
        // sets the position on the panelContent
        JPanel panelContent = new JPanel(new GridBagLayout());
        //GradientPanel panelContent = new GradientPanel();
        //panelContent.setLayout(new GridBagLayout());
        GridBagConstraints constraintsContent = new GridBagConstraints();

        //Play overview
        constraintsContent.anchor = GridBagConstraints.NORTH;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 7;
        constraintsContent.gridheight= 1;
        constraintsContent.insets = new Insets(0, 0, 5, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 0;
        panelContent.add(panelPlayeroverview, constraintsContent);

        //--------------------
        //GameField
        constraintsContent.anchor = GridBagConstraints.EAST;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 2;
        constraintsContent.gridheight= 4;
        constraintsContent.insets = new Insets(5, 50, 0, 50);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelContent.add(panelGameField, constraintsContent);

        //-----------
        //Information
        constraintsContent.anchor = GridBagConstraints.SOUTHWEST;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 2;
        constraintsContent.gridheight= 4;
        constraintsContent.insets = new Insets(0, 10, 0, 0);
        constraintsContent.gridx = 4;
        constraintsContent.gridy = 1;
        panelContent.add(panelInformation, constraintsContent);

        //----
        //Chat
        /*
        constraintsContent.anchor = GridBagConstraints.SOUTHWEST;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 2;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        */
        //panelContent.add(panelChat, constraintsContent);
        //---------

        //adding to frame
        this.frame = createFrame();

        // sets the Opaque
        panelPlayeroverview.setOpaque(false);
        panelInformation.setOpaque(false);
        panelGameField.setOpaque(false);
        //panelChat.setOpaque(true);
        panelContent.setOpaque(false);

        //places the panelContent on the panelBackground
        GradientPanel panelBackground = new GradientPanel();
        panelBackground.setLayout(new BorderLayout());
        panelBackground.add(panelContent,BorderLayout.NORTH);


        this.frame.add(panelBackground);

        //buttons are clockwise written to array
        buttonArrow_Array[0]  = buttonArrow_1_0;
        buttonArrow_Array[1]  = buttonArrow_3_0;
        buttonArrow_Array[2]  = buttonArrow_5_0;
        buttonArrow_Array[3]  = buttonArrow_6_1;
        buttonArrow_Array[4]  = buttonArrow_6_3;
        buttonArrow_Array[5]  = buttonArrow_6_5;
        buttonArrow_Array[6]  = buttonArrow_5_6;
        buttonArrow_Array[7]  = buttonArrow_3_6;
        buttonArrow_Array[8]  = buttonArrow_1_6;
        buttonArrow_Array[9]  = buttonArrow_0_5;
        buttonArrow_Array[10] = buttonArrow_0_3;
        buttonArrow_Array[11] = buttonArrow_0_1;
    }

    /**
     * creates specialized frame for playGround
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("Join Game");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //entfernt die obere leiste
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //frame.setUndecorated(true);
        //frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setVisible(true);
        frame.setSize(1200, 900);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLocation(300, 200);
        return frame;
    }

    /**
     * creates specialized label for playGround
     * @param labelString
     * @param fontSize
     * @param sizeX
     * @param sizeY
     * @param fontColor
     * @return
     */
    public JLabel setLabel(String labelString, int fontSize, int sizeX, int sizeY, Color fontColor){
        JLabel label = new JLabel(labelString);
        label.setMinimumSize(new Dimension(sizeX, sizeY));
        label.setPreferredSize(new Dimension(sizeX, sizeY));
        //label.setMaximumSize(new Dimension(sizeX, sizeY));
        label.setFont(new Font("Serif", Font.PLAIN, fontSize));
        label.setForeground(fontColor);
        return label;
    }

    /**
     * creates button with different size
     * @param text
     * @param fontSize
     * @param sizeX
     * @param sizeY
     * @return
     */
    public JButton setButtons(String text, int fontSize, int sizeX, int sizeY){
        JButton button = new JButton(text);

        //makes a fix size
        button.setMinimumSize(new Dimension(sizeX, sizeY));
        button.setPreferredSize(new Dimension(sizeX, sizeY));
        //button.setMaximumSize(new Dimension(sizeX, sizeY));
        button.setFont(new Font("Serif", Font.PLAIN, fontSize));
        return button;
    }

    /**
     * creates textfield -> width and height can directly be set
     * @param width
     * @param height
     * @return
     */
    public JTextField setTextField(int width, int height) {
        JTextField textField = new JTextField(width);
        textField.setMinimumSize(new Dimension(width, height));
        textField.setPreferredSize(new Dimension(width, height));
        return textField;
    }

    /**
     * creates TextArea for playGround -> width and hight can directly be set
     * @param width
     * @param height
     * @return
     */
    public JTextArea setTextArea(int width , int height) {
        JTextArea textArea = new JTextArea(height, width);
        textArea.setMinimumSize(new Dimension(width, height));
        textArea.setPreferredSize(new Dimension(width, height));
        return textArea;
    }

    /**
     * ActionListener
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        //network text field
        if (textField == e.getSource()) {
            // send to server
            out.println("chat " + screenName + textField.getText());
            // log
            LOGGER.info("OUTGOING chat " + textField.getText());
            textField.setText("");
            textField.requestFocusInWindow();
        }
        if (buttonEndGame == e.getSource()) {
            // send to server
            out.println("leave " + playerID);
            // log
            LOGGER.info("OUTGOING leave");
            System.exit(0);
        }
        if (buttonNewGame == e.getSource()) {
            frame.dispose();
            CreateNewGame newGame = new CreateNewGame();
            newGame.createGui();
        }
        if (playersTurnID == playerID) {
            if (buttonRotate == e.getSource()) {
                out.println("rotateTile 90 " + playerID);
                //todo was soll hier gesendet werden??????? -> "nextTileID" gibts nicht
            }

            if(tileInserted){
                for (int i = 0; i < boardSquares.length; i++) {
                    for (int j = 0; j < boardSquares[i].length; j++) {
                        if (e.getActionCommand().equals("gameField: " + j + " " + i)) {
                            if (j == board.getPlayer(playerID).getAcutalPosition().getX() && i == board.getPlayer(playerID).getAcutalPosition().getY()) {
                                // send to server
                                out.println("pass " + j + " " + i + " " + playerID);
                                // log
                                LOGGER.info("OUTGOING pass");
                            } else {
                                // send to server
                                out.println("move " + j + " " + i + " " + playerID);
                                // log
                                LOGGER.info("OUTGOING move " + j + " " + i);
                            }
                        }
                    }
                }
            }

		/* checks which button was pressed  to place the next stone
		 buttonArrow_1_0 means line j:1 i:0 on the field
		 topArrowButtons*/
        System.out.println();
        if(!tileInserted){
                if (buttonArrow_1_0 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 0 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 1 0");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 1 0");
                }
                if (buttonArrow_3_0 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 1 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 3 0");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 3 0");
                }
                if (buttonArrow_5_0 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 2 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 5 0");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 5 0");
                }
                if (buttonArrow_6_1 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 3 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 1");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 1");
                }
                if (buttonArrow_6_3 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 4 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 3");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 3");
                }
                if (buttonArrow_6_5 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 5 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 5");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 6 5");
                }
                if (buttonArrow_5_6 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 6 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 5 6");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 5 6");
                }
                if (buttonArrow_3_6 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 7 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 3 6");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 3 6");
                }
                if (buttonArrow_1_6 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 8 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 1 6");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 1 6");
                }
                if (buttonArrow_0_5 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 9 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 5");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 5");
                }
                if (buttonArrow_0_3 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 10 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 3");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 3");
                }
                if (buttonArrow_0_1 == e.getSource()) {
                    tileInserted = true;
                    buttonRotate.setEnabled(false);
                    // send to server
                    out.println("insertTile 11 " + playerID + " " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 1");
                    // log
                    LOGGER.info("OUTGOING push " + board.getNextTile().getId() + " " + board.getNextTile().getRotation() + " 0 1");
                }
            }
            else{
                System.err.println("A TILE WAS ALREADY INSERTED! NO MORE INSERTION ALLOWED");
            }
        }
    }

    //================================================================================
    // listen to socket and print everything that server broadcasts
    //================================================================================
    //================================================================================
    // +Listen to socket and print everything (chat) that server broadcasts
    // +Converting String(s) -> Board
    // Available incoming Strings:
    // -tileID
    // -tileRot
    // -tileX
    // -tileY
    // -player
    //================================================================================
    public void listen() {
        System.out.println(playersTurnID+" playersTurnID");
        String s;
        while ((s = in.readLine()) != null) {
            //tileID
            if (s.startsWith("tileID")) {
                System.out.println(s);
                saveTileIDStingInBoard(s);
            } else if (s.startsWith("disabledButtonID ")) {
                disabledButtonID = Integer.parseInt(s.substring(17));

                for (int i = 0; i < buttonArrow_Array.length; i++) {
                    buttonArrow_Array[i].setEnabled(true);
                }

                buttonArrow_Array[disabledButtonID].setEnabled(false);

            } else if (s.startsWith("pushAllowed ")) {
                isPushAllowed = Boolean.parseBoolean(s.substring(12));
                System.out.println("push allowed: " + isPushAllowed);
                if (isPushAllowed) {
                    tileInserted = true;
                    // log incoming movevalid message
                    LOGGER.info("INCOMING movevalid true");
                } else {
                    tileInserted = false;
                    // log incoming movevalid message
                    LOGGER.info("INCOMING movevalid false");
                }
            } else if (s.startsWith("moveValid ")) {
                moveValid = Boolean.parseBoolean(s.substring(10));
                if (moveValid) {
                    tileInserted = false;
                    buttonRotate.setEnabled(true);
                    // log
                    LOGGER.info("INCOMING movevalid true");
                } else {
                    tileInserted = true;
                    // log
                    LOGGER.info("INCOMING movevalid false");
                }
            }
            //acutal playersTurnID
            else if (s.startsWith("playersTurnID")) {
                String[] tmpTurnID = s.split("\\s+");
                playersTurnID = Integer.parseInt(tmpTurnID[1]);
            }
            //tileNextID
            else if (s.startsWith("tileNextID")) {

                saveNextTileIDInBoard(s);
            }
            //TileRot
            else if (s.startsWith("tileRot")) {
                //textArea.insert(s + "\n", textArea.getText().length());
                //textArea.setCaretPosition(textArea.getText().length());

                String[] tmpRot = s.split("\\s+");
                //get nextTileRotation
                board.getNextTile().setRotation(Integer.parseInt(tmpRot[1]));

                int counter = 2;
                for (int j = 0; j < 7; j++) {
                    for (int i = 0; i < 7; i++) {
                        board.getTile(j, i).setRotation(Integer.parseInt(tmpRot[counter]));
                    }
                }
                board.getNextTile().getShape().rotateImage(Integer.parseInt(tmpRot[1]));
            } else if (s.startsWith("rotateTile ")) {
                board.getNextTile().getShape().setImage(board.getNextTile().getShape().rotateImage(90));
                board.getNextTile().getShape().setRotatedPossiblePath(board.getNextTile().getShape().getPossiblePaths());
            }else if (s.startsWith("playerPosX")) {
                String[] playerPosX = s.split("\\s+");

                for (int i = 0; i < board.getAllPlayers().length; i++) {
                    board.getPlayer(i).setActualPosition(new Position(Integer.parseInt(playerPosX[i + 1]), board.getPlayer(i).getAcutalPosition().getY()));
                }
            } else if (s.startsWith("playerPosY")) {
                String[] playerPosY = s.split("\\s+");
                for (int i = 0; i < board.getAllPlayers().length; i++) {
                    board.getPlayer(i).setActualPosition(new Position(board.getPlayer(i).getAcutalPosition().getX(), Integer.parseInt(playerPosY[i + 1])));
                }
            } else if (s.startsWith("deal")) {
                board.getCreaturesNeeded().clear();
                String[] dealID = s.split("\\s+");
                for (int first = 1; first < dealID.length; first++) {
                    for (int second = 0; second < board.getAllGoalCards().length; second++) {
                        if (Integer.parseInt(dealID[first]) == board.getAllGoalCards()[second].getGoalCardID()) {
                            board.getCreaturesNeeded().add(board.getAllGoalCards()[second]);
                        }
                    }
                }
                // log incoming deal message
                LOGGER.info("INCOMING deal " + s.substring(5));
            } else if (s.startsWith("points ")) {
                String[] points = s.split("\\s+");
                System.out.println(points.length);
                System.out.println("points: " + points[0] + " " + points[1] + " " + points[2] + " " + points[3] + " " + points[4]);
                for (int i = 1; i < board.getAllPlayers().length+1; i++) {
                    board.getPlayer(i - 1).setScore(Integer.parseInt(points[i]));
                }
            } else if (s.startsWith("draw")) {
                drawGameField(board);
            }

            //initName
            else if (s.startsWith("initName")) {
                String[] tmpPlayer = s.split("\\s+");
                for (int i = 1; i < tmpPlayer.length; i++) {
                    board.getPlayer(i - 1).setNameOfPlayer(tmpPlayer[i]);
                    //System.out.println(tmpPlayer[i]);
                }
                drawGameField(board);

            } else if (s.startsWith("initPlayerID")) {
                String[] tmpPlayerID = s.split("\\s+");
                // set playerID
                playerID = Integer.parseInt(tmpPlayerID[1]);
                // init logger
                try {
                    FileHandler fileHandler = new FileHandler("player_0" + playerID + ".log");
                    LOGGER.addHandler(fileHandler);
                    LOGGER.info("*****STARTING*****");
                } catch (Exception e) {
                    System.err.println(e);
                }
            } else if (s.startsWith("gameEnd")) {
                //-> 100 is never used so nobody can press anything
                playersTurnID = 100;
                GameEnd gameEnd = new GameEnd(this.frame);
                gameEnd.createGui(s.substring(7));
            }
            // incoming init message
            else if (s.startsWith("init")) {
                // log incoming init message
                LOGGER.info("INCOMING " + s);
            }
            // incoming pushed message
            else if (s.startsWith("pushed")) {
                LOGGER.info("INCOMING " + s);
            }
            // incoming pass message
            else if (s.startsWith("passed")) {
                LOGGER.info("INCOMING passed");
            }
            // incoming move message
            else if (s.startsWith("move")) {
                LOGGER.info("INCOMING " + s);
            }
            // incoming goal message
            else if (s.startsWith("goal")) {
                LOGGER.info("INCOMING " + s);
            }
            // incoming disconnect message
            else if (s.startsWith("disconnect")) {
                // log incoming disconnect message
                LOGGER.info("INCOMING " + s);
            }
            else {
                // in case of chat
                textArea.insert(s + "\n", textArea.getText().length());
                textArea.setCaretPosition(textArea.getText().length());
                // log incoming chat message
                LOGGER.info("INCOMING " + s);
            }
        }
        out.close();
        in.close();
        try                 { socket.close();      }
        catch (Exception e) { e.printStackTrace(); }
        System.err.println("Closed client socket");
    }

    /*******************************************************************************************************************
     * drawGameField()
     * @param board
     * draws the whole gameField
     */
    public void drawGameField(BoardFromClient board){

        //Spielfeld wird komplett neu gezeichnet
        for(int j = 0; j < boardSquares.length; j++) {
            for (int i = 0; i < boardSquares[j].length; i++) {
                //draw images on the buttons
                boardSquares[j][i].setIcon(board.getTile(j,i).getShape().getImage());
                //set border from buttons on default
                boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));
                //check how many player are on the field and draw the borders
                if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i))       &&
                        ((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border0123);
                }
                //three on one field
                else if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border012);                }
                else if(((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border123);
                }
                else if(((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border230);
                }
                else if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i))  &&
                        ((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border013);
                }
                //two on one field
                else if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border01);
                }
                else if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border02);
                }
                else if(((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border03);
                }
                else if(((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border12);
                }
                else if(((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border13);
                }
                else if(((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i)) &&
                        ((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i))){
                    boardSquares[j][i].setBorder(border23);
                }
                else if((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(0).getColor()));
                }
                else if((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(1).getColor()));
                }
                else if((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(2).getColor()));
                }
                else if((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(3).getColor()));
                }
            }
        }
        //draw the NextStone image
        labelNextStoneSymbol.setIcon(board.getNextTile().getShape().getImage());

        //draw points of player with color
        if(!board.getPlayer(0).getNameOfPlayer().isEmpty()){
            labelPlayer0.setText(board.getPlayer(0).getNameOfPlayer() + ": " + board.getPlayer(0).getScore() + " / 6");
            labelPlayer0.setForeground(board.getPlayer(0).getColor());
            labelPlayer0.setOpaque(true);
            labelPlayer0.setBackground(new Color(154, 192, 205));
        }
        if(!board.getPlayer(1).getNameOfPlayer().isEmpty()) {
            labelPlayer1.setText(board.getPlayer(1).getNameOfPlayer() + ": " + board.getPlayer(1).getScore() + " / 6");
            labelPlayer1.setForeground(board.getPlayer(1).getColor());
            labelPlayer1.setOpaque(true);
            labelPlayer1.setBackground(new Color(154, 192, 205));
        }
        if(!board.getPlayer(2).getNameOfPlayer().isEmpty()) {
            labelPlayer2.setText(board.getPlayer(2).getNameOfPlayer() + ": " + board.getPlayer(2).getScore() + " / 6");
            labelPlayer2.setForeground(board.getPlayer(2).getColor());
            labelPlayer2.setOpaque(true);
            labelPlayer2.setBackground(new Color(154, 192, 205));
        }
        if(!board.getPlayer(3).getNameOfPlayer().isEmpty()) {
            labelPlayer3.setText(board.getPlayer(3).getNameOfPlayer() + ": " + board.getPlayer(3).getScore() + " / 6");
            labelPlayer3.setForeground(board.getPlayer(3).getColor());
            labelPlayer3.setOpaque(true);
            labelPlayer3.setBackground(new Color(154, 192, 205));
        }

        labelNextGoalSymbol.setIcon(board.getAllPlayers()[playerID].getCreaturesNeeded().get(0).getSymbolImage());

        //resets the borders
        labelPlayer0.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));
        labelPlayer1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));
        labelPlayer2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));
        labelPlayer3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));
        // draw a border around the player which turn it is
        if(board.getPlayer(0).getPlayerID() == playersTurnID){
            labelPlayer0.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorBlack));
        }
        else if(board.getPlayer(1).getPlayerID() == playersTurnID){
            labelPlayer1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorBlack));
        }
        else if(board.getPlayer(2).getPlayerID() == playersTurnID){
            labelPlayer2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorBlack));
        }
        else if(board.getPlayer(3).getPlayerID() == playersTurnID){
            labelPlayer3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorBlack));
        }
    }

    /**
     * Information of the String is saved in the Board
     * @param s
     */
    public void saveTileIDStingInBoard(String s){
        String[] tmpTileID = s.split("\\s+");
        Tiles[] tmpTiles = new Tiles[board.getAllTilesInOneArray().length-1];

        //all are compared and saved to tmpTiles
        for(int first= 1 ; first < tmpTileID.length; first++){
            for(int second = 0; second < board.getAllTilesInOneArray().length; second ++){
                if(Integer.parseInt(tmpTileID[first]) == board.getAllTilesInOneArray()[second].getId()){
                    tmpTiles[first-1] = board.getAllTilesInOneArray()[second];
                }
            }
        }

        //convertes list to 2d Array and set it to the Board
        int counter=0;
        for(int j = 0; j< 7; j++){
            for(int i = 0; i< 7; i++){
                board.setTiles(i,j,tmpTiles[counter]);
                counter++;
            }
        }
    }

    /**
     * searches nextTile from the list and saves it to the nextTile
     * @param s
     */
    public void saveNextTileIDInBoard(String s){
        for (int index = 0 ; index < board.getAllTilesInOneArray().length ; index ++ ){
            if(Integer.parseInt(s.substring(11)) == board.getAllTilesInOneArray()[index].getId()){
                board.setNextTile(board.getAllTilesInOneArray()[index]);
            }
        }
    }
}



