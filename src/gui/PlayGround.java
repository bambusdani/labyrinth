package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

import network.*;
import gameLogic.*;

import java.util.ArrayList;

public class PlayGround implements ActionListener {

    //------------------------
    //PlayerManagement
    private int playerID;
    ArrayList<Boolean> playersTurn = new ArrayList<Boolean>();
    private Boolean tileInsertionAllowed = true;
    //------------------------


    private int fontSize = 20;
    private int boxSizeX = 175;
    private int boxSizeY = 50;
    private Color colorBlack = new Color(0, 0, 0);
    private int stoneSize = 75;

    //all buttons for actionListener
    private JButton buttonNewGame;
    private JButton buttonEndGame;
    private	JButton buttonRotate;
    public JButton[][] boardSquares = new JButton[7][7];
    private JFrame frame;

    //Nächstes Teil das eingefügt wird
    public Tiles tmpStorageTile;
    public JLabel labelNextStoneSymbol;
    public int rotationAngle = 0 ;


    // Images for the Arrrow buttons
    private ImageIcon imageArrowDown = new ImageIcon("src/resources/arrows/downArrow.png");
    private ImageIcon imageArrowLeft = new ImageIcon("src/resources/arrows/leftArrow.png");
    private ImageIcon imageArrowUp	 = new ImageIcon("src/resources/arrows/upArrow.png");
    private ImageIcon imageArrowRight= new ImageIcon("src/resources/arrows/rightArrow.png");
    private ImageIcon imageRotate	 = new ImageIcon("src/resources/arrows/rotateArrow.png");

    //Buttons for the arrows to place the next stone
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

    // Erstellen der Klasse mit wichtigen Funktionen

    //TODO muss weg
    //private GameFunctions gameFunctions = new GameFunctions();

    //TODO des muss auch wieder weg
    public void nextPlayersTurn(){
        for(int index = 0; index < playersTurn.size(); index++) {
            if (playersTurn.get(index) && index < playersTurn.size()-1) {
                playersTurn.set(index, false);
                playersTurn.set(index + 1, true);
                break;

            }
            else if(playersTurn.get(index)){
                playersTurn.set(index, false);
                playersTurn.set(0, true);
                break;
            }
        }
        //playerID hochzählen
        if(playerID < 3){ playerID++;}else{	playerID = 0; }
        tileInsertionAllowed = true;
        System.out.println("PlayerID:" + playerID);
    }
    //TODO weg mit dem Drüber :D



    public PlayGround(String hostName, String screenName, int playerID) {
        // connect to server
        try {
            socket = new Socket(hostName, 4444);
            out    = new Out(socket);
            in     = new In(socket);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        this.screenName = "[" + screenName + "]: ";
        this.initName = screenName;
        out.println("initName " + initName);

        //TODO Muss nacher wieder weg ist nur zum testen :)
        this.playerID = playerID;
        System.out.println("PlayerID: " + playerID);
        playersTurn.add(true);
        playersTurn.add(false);
        playersTurn.add(false);
        playersTurn.add(false);
        //TODO Bis hier


        //TODO ---------------------------------------------------------------------------------------------------------


        //TODO _________________________________________________________________________________________________________

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

        //--------------------------------------------------------------------------------
        // symbols left
        constraintsPlayeroverview.gridx = 0;
        constraintsPlayeroverview.gridy = 0;
        JLabel labelSymbolsLeft = setLabel("Fehlende Symbole: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        panelPlayeroverview.add(labelSymbolsLeft, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 0
        constraintsPlayeroverview.gridx = 1;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer0 = setLabel(" " , fontSize,boxSizeX, boxSizeY, colorBlack);
        panelPlayeroverview.add(labelPlayer0, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 1
        constraintsPlayeroverview.gridx = 2;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer1 = setLabel(" " , fontSize,boxSizeX, boxSizeY, colorBlack);
        panelPlayeroverview.add(labelPlayer1, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 2
        constraintsPlayeroverview.gridx = 3;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer2 = setLabel(" " , fontSize,boxSizeX, boxSizeY, colorBlack);
        panelPlayeroverview.add(labelPlayer2, constraintsPlayeroverview);
        //---------------------------------------------------------------------------------
        // Player 3
        constraintsPlayeroverview.gridx = 4;
        constraintsPlayeroverview.gridy = 0;
        labelPlayer3 = setLabel(" " , fontSize,boxSizeX, boxSizeY, colorBlack);
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

        constraintsInformation.anchor = GridBagConstraints.NORTHWEST;
        constraintsInformation.weightx = 1;
        constraintsInformation.weighty = 1;
        constraintsInformation.gridwidth = 1;
        constraintsInformation.insets = new Insets(0, 0, 0, 0);

        //-----------------------------------------------------------------------------------
        // reached goals
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 0;
        JLabel labelReachedGoals = setLabel("Erreichte Ziele: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        panelInformation.add(labelReachedGoals, constraintsInformation);

        //-----------------------------------------------------------------------------------
        //TODO entfernen????
        // last reached goal symbol
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 1;
        // instead of Dragon it should use an image
        JLabel labelReachedGoalsSymbol = setLabel("",fontSize, stoneSize, stoneSize, colorBlack );
        //gibt nur das ziel des Players 0 aus sowie das erste ziel gibt ebenfalls falschen wert aus!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //labelReachedGoalsSymbol.setIcon(board.getAllPlayers()[1].getCreaturesNeeded().get(0).getSymbolImage());
        labelReachedGoalsSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        panelInformation.add(labelReachedGoalsSymbol, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next goal
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 2;
        JLabel labelNextGoal = setLabel("Nächstes Ziel: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        panelInformation.add(labelNextGoal, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next goal symbol
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 3;
        labelNextGoalSymbol= setLabel("", fontSize, stoneSize,stoneSize,colorBlack);
        labelNextGoalSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        panelInformation.add(labelNextGoalSymbol, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next stone
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 4;
        JLabel labelNextStone = setLabel("Nächster Stein: ",fontSize, boxSizeX, boxSizeY, colorBlack );
        panelInformation.add(labelNextStone, constraintsInformation);

        //-----------------------------------------------------------------------------------
        // next stone symbol
        constraintsInformation.gridx = 0;
        constraintsInformation.gridy = 5;
        // instead of "T" it should use an image



        this.labelNextStoneSymbol = setLabel("",fontSize, stoneSize, stoneSize, colorBlack );
        this.labelNextStoneSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
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

        //===================================================================================
        // panel network
        //===================================================================================
        JPanel panelChat = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsChat = new GridBagConstraints();

        constraintsChat.anchor = GridBagConstraints.SOUTHWEST;
        constraintsChat.weightx = 1;
        constraintsChat.weighty = 1;
        constraintsChat.gridwidth = 1;
        constraintsChat.insets = new Insets(0, 5, 5, 5);

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

                //-------------
            }


        }


        //====================================================================================
        // creating frame
        // sets the position on the panelContent
        //====================================================================================

        JPanel panelContent = new JPanel(new GridBagLayout());
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
        constraintsContent.insets = new Insets(0, 50, 0, 50);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 1;
        panelContent.add(panelGameField, constraintsContent);
        //---------
        //Information
        constraintsContent.anchor = GridBagConstraints.WEST;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 2;
        constraintsContent.gridheight= 4;
        constraintsContent.insets = new Insets(0, 10, 0, 0);
        constraintsContent.gridx = 4;
        constraintsContent.gridy = 1;
        panelContent.add(panelInformation, constraintsContent);
        //---------
        //Chat
        constraintsContent.anchor = GridBagConstraints.SOUTHWEST;
        constraintsContent.weightx = 1;
        constraintsContent.weighty = 1;
        constraintsContent.gridwidth = 1;
        constraintsContent.gridheight= 2;
        constraintsContent.insets = new Insets(0, 0, 0, 0);
        constraintsContent.gridx = 0;
        constraintsContent.gridy = 5;
        panelContent.add(panelChat, constraintsContent);
        //---------
        //adding to frame
        this.frame = createFrame();
        this.frame.add(panelContent);
    }

    //====================================================================
    // Functions
    //====================================================================
    public JFrame createFrame(){
        JFrame frame = new JFrame("Join Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1200, 900);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLocation(300, 200);
        return frame;
    }

    public JLabel setLabel(String labelString, int fontSize, int sizeX, int sizeY, Color fontColor){
        JLabel label = new JLabel(labelString);
        label.setMinimumSize(new Dimension(sizeX, sizeY));
        label.setPreferredSize(new Dimension(sizeX, sizeY));
        //label.setMaximumSize(new Dimension(sizeX, sizeY));
        label.setFont(new Font("Serif", Font.PLAIN, fontSize));
        label.setForeground(fontColor);
        return label;
    }

    public JButton setButtons(String text, int fontSize, int sizeX, int sizeY){
        JButton button = new JButton(text);

        //makes a fix size
        button.setMinimumSize(new Dimension(sizeX, sizeY));
        button.setPreferredSize(new Dimension(sizeX, sizeY));
        //button.setMaximumSize(new Dimension(sizeX, sizeY));
        button.setFont(new Font("Serif", Font.PLAIN, fontSize));
        return button;
    }

    public JTextField setTextField(int width, int height) {
        JTextField textField = new JTextField(width);
        textField.setMinimumSize(new Dimension(width, height));
        textField.setPreferredSize(new Dimension(width, height));
        return textField;
    }

    public JTextArea setTextArea(int width , int height) {
        JTextArea textArea = new JTextArea(height, width);
        textArea.setMinimumSize(new Dimension(width, height));
        textArea.setPreferredSize(new Dimension(width, height));
        return textArea;
    }

    //=================================================================================
    //actionListener
    //=================================================================================

    public void actionPerformed(ActionEvent e) {

        //Abfrage ob Spieler dran ist

        //network text field
        if(textField == e.getSource()) {
            out.println(screenName + "chat " + textField.getText());

            textField.setText("");
            textField.requestFocusInWindow();
        }

        if(buttonEndGame == e.getSource()){
            out.println(screenName + "leave");
            System.exit(0);
        }

        if(buttonNewGame == e.getSource()){
            frame.dispose();
            CreateNewGame newGame = new CreateNewGame();
            newGame.createGui();
        }
        if(buttonRotate == e.getSource()){

            int nextTileRotation = board.getNextTile().getRotation();

            if(nextTileRotation==270){
                nextTileRotation=0;
            }else{
                nextTileRotation += 90;
            }

            board.getNextTile().setRotation(nextTileRotation);

            labelNextStoneSymbol.setIcon(board.getNextTile().getShape().rotateImage(nextTileRotation));
            board.getNextTile().getShape().setImage(board.getNextTile().getShape().rotateImage(nextTileRotation));
            board.getNextTile().getShape().setRotatedPossiblePath(board.getNextTile().getShape().getPossiblePaths());


        }


		//------------------------------------------------------------
		// checks which button on the gameField is pressed
		//if(!tileInsertionAllowed){

		/*for(int i = 0; i < boardSquares.length; i++){
			for(int j = 0; j< boardSquares[i].length; j++){

				if( e.getActionCommand().equals("gameField: "+j+" "+i)){


					//writes the command of the button
					//System.out.println("Button j: "+j +", i: "+ i +" pressed");


					//=====================================================
					// Aufruf ob der Zug möglich ist

					// erstellt aus j und i eine neue Position
					Position buttonPositionPressed = new Position(j, i);

					//TODO wurde bereits ein Stein reingeschoben???

					// Ist der Zug möglich, falls ja ändere die Ränder
					gameFunctions.movePlayerIfMoveIsPossible(playerID, buttonPositionPressed);
					if(gameFunctions.isMovePossible(buttonPositionPressed,board.getPlayer(playerID).getAcutalPosition().getX(),board.getPlayer(playerID).getAcutalPosition().getY())){
						nextPlayersTurn();
					}
					else{
						nextPlayersTurn();
					}


					board = gameFunctions.movePlayerIfMoveIsPossible(playerID,buttonPositionPressed);
					drawGameField(board);*/
					//==================================================================================================
					/**
					 * Zeichnen der Punkte
					 */
        /*switch (gameFunctions.isPlayerGettingPoints(board , playerID)){
				protocol.isPlayerGettingPoints(board, playerID);
					switch (protocol.getPlayerPoints()){
						case 0:
							//System.out.println("kein Punkt");
							break;
						case 1:
							// neu zeichnen der Punkte
							labelPlayer0.setText(board.getPlayer(0).getNameOfPlayer() + ": " + board.getPlayer(0).getScore() );
							labelPlayer1.setText(board.getPlayer(1).getNameOfPlayer() + ": " + board.getPlayer(1).getScore() );
							labelPlayer2.setText(board.getPlayer(2).getNameOfPlayer() + ": " + board.getPlayer(2).getScore() );
							labelPlayer3.setText(board.getPlayer(3).getNameOfPlayer() + ": " + board.getPlayer(3).getScore() );
							//neu zeichnen des NextNeededSymbol
							labelNextGoalSymbol.setIcon(board.getPlayer(playerID).getCreaturesNeeded().get(0).getSymbolImage());
							break;
						case 2:
							//frame.dispose();
							GameEnd GameEnd = new GameEnd();
							GameEnd.createGui();
							break;
					}
				}
			}
		}
		}



		//-------------------------------------------------------------
			//TODO ID Abfrage übern server usw...

		// checks which button was pressed  to place the next stone
		// buttonArrow_1_0 means line j:1 i:0 on the field
		// topArrowButtons
		{*/

        //buttonArrow1_0 pressed -> an Server übergeben
		if(buttonArrow_1_0 == e.getSource()){
			out.println("insertTile 0 "+ playerID);

		/*}





		if(buttonArrow_3_0 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(1,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;

		}
		if(buttonArrow_5_0 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(2,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		//right arrows
		if(buttonArrow_6_1 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(3,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_6_3 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(4,board);
			//Todo sende an server den button und id
			// Todo -> bekomme ein board zurück

			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_6_5 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(5,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		//bottom arrows
		if(buttonArrow_5_6 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(6,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_3_6 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(7,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_1_6 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(8,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		// left arrows
		if(buttonArrow_0_5 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(9,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_0_3 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(10,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
		}
		if(buttonArrow_0_1 == e.getSource()){
			//übergibt die ButtonID + Board und bekommt ein neues zurück
			board = gameFunctions.placeNextStoneInMaze(11,board);
			//zeichnet das komplette Spielfeld neu
			drawGameField(board);

			//TODO nacher ändern
			System.out.println("Tile insertion isnt allowed -> This player already inserted tile");
			tileInsertionAllowed = false;
			}
		}
	}
	*/
    }}



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
        String s;
        while ((s = in.readLine()) != null) {
            //tileID
            if(s.startsWith("tileID")) {

                saveTileIDStingInBoard(s);


            }
            //tileNextID
            else if(s.startsWith("tileNextID")){

                saveNextTileIDInBoard(s);


            }
            //ileRot
            else if(s.startsWith("tileRot")){
                textArea.insert(s + "\n", textArea.getText().length());
                textArea.setCaretPosition(textArea.getText().length());

                String[] tmpRot = s.split("\\s+");

                int counter = 1;
                for(int j=0; j< 7;j++ ){
                    for(int i=0; i<7;i++){
                        board.getTile(j,i).setRotation(Integer.parseInt(tmpRot[counter]));
                    }
                }
                //TODO setzt die Rotation muss aber noch überprüft werden ob das richtige Tile erwischt wird
                System.out.println(board.getTile(0,0).getRotation());


            }
            else if(s.startsWith("tileX")){
                //TODO initialisierung passt nicht von shuffel
                textArea.insert(s + "\n", textArea.getText().length());
                textArea.setCaretPosition(textArea.getText().length());

            }
            else if(s.startsWith("tileY")){
                //TODO initialisierung von shuffel passt nicht
                textArea.insert(s + "\n", textArea.getText().length());
                textArea.setCaretPosition(textArea.getText().length());

            }
            else if(s.startsWith("deal")){

                String[] dealID = s.split("\\s+");
                for(int first = 1; first < dealID.length; first++){
                    for(int second = 0; second < board.getAllGoalCards().length; second ++){
                        if(Integer.parseInt(dealID[first]) == board.getAllGoalCards()[second].getGoalCardID()){
                            board.getCreaturesNeeded().add(board.getAllGoalCards()[second]);
                        }
                    }
                }

                //drawGameField(board);
            }


            else if(s.startsWith("draw")){
                drawGameField(board);
            }


            //initName

            else if(s.startsWith("initName")) {
                String[] tmpPlayer = s.split("\\s+");

                for (int i = 1; i < tmpPlayer.length; i++) {
                    board.getPlayer(i-1).setNameOfPlayer(tmpPlayer[i]);
                    //System.out.println(tmpPlayer[i]);
                }
                //drawGameField(board);
            }
            else {
                textArea.insert(s + "\n", textArea.getText().length());
                textArea.setCaretPosition(textArea.getText().length());
            }

        }
        out.close();
        in.close();
        try                 { socket.close();      }
        catch (Exception e) { e.printStackTrace(); }
        System.err.println("Closed client socket");
    }

    /**
     * setname
     */


    /*******************************************************************************************************************
     * drawGameField()
     * @param board
     * draws the whole gameField
     */

    //TODO Important!!!
    //TODO drawGameField darf erst aufgerufen werden wenn alle daten verteilt wurden!
    public void drawGameField(BoardFromClient board){

        //Spielfeld wird komplett neu gezeichnet
        for(int j = 0; j < boardSquares.length; j++) {
            for (int i = 0; i < boardSquares[j].length; i++) {
                //draw images on the buttons
                boardSquares[j][i].setIcon(board.getTile(j,i).getShape().getImage());
                //set border from buttons on default
                //TODO default color / border is different
                boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack));

                // checking if the players are on the the spot if yes draw a colored border
                if((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(0).getColor()));
                }
                if((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(1).getColor()));
                }
                if((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(2).getColor()));
                }
                if((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i)){
                    boardSquares[j][i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(3).getColor()));
                }
            }
        }
        //draw the NextStone image
        labelNextStoneSymbol.setIcon(board.getNextTile().getShape().getImage());

        //draw points of player
        labelPlayer0.setText(board.getPlayer(0).getNameOfPlayer() + ": " + board.getPlayer(0).getScore());
        labelPlayer1.setText(board.getPlayer(1).getNameOfPlayer() + ": " + board.getPlayer(1).getScore());
        labelPlayer2.setText(board.getPlayer(2).getNameOfPlayer() + ": " + board.getPlayer(2).getScore());
        labelPlayer3.setText(board.getPlayer(3).getNameOfPlayer() + ": " + board.getPlayer(3).getScore());

        labelNextGoalSymbol.setIcon(board.getAllPlayers()[playerID].getCreaturesNeeded().get(0).getSymbolImage());

        // draw a border around the player which turn it is
        if(board.getPlayer(0).getTurn()){
            labelPlayer0.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        }
        else if(board.getPlayer(1).getTurn()){
            labelPlayer1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        }
        else if(board.getPlayer(2).getTurn()){
            labelPlayer2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        }
        else if(board.getPlayer(3).getTurn()){
            labelPlayer3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
        }
    }

    public void saveTileIDStingInBoard(String s){
        String[] tmpTileID = s.split("\\s+");
        Tiles[] tmpTiles = new Tiles[board.getAllTilesInOneArray().length-1];

        //vergleicht jeden mit jedem in der liste und speichert es in
        for(int first= 1 ; first < tmpTileID.length; first++){
            for(int second = 0; second < board.getAllTilesInOneArray().length; second ++){
                if(Integer.parseInt(tmpTileID[first]) == board.getAllTilesInOneArray()[second].getId()){
                    tmpTiles[first-1] = board.getAllTilesInOneArray()[second];
                }
            }
        }
        //macht aus einer Liste ein 2d Array und speichert es in board
        int counter=0;
        for(int j = 0; j< 7; j++){
            for(int i = 0; i< 7; i++){
                board.setTiles(j,i,tmpTiles[counter]);
                counter++;
            }
        }
    }

    public void saveNextTileIDInBoard(String s){
        for (int index = 0 ; index < board.getAllTilesInOneArray().length ; index ++ ){
            if(Integer.parseInt(s.substring(11)) == board.getAllTilesInOneArray()[index].getId()){
                board.setNextTile(board.getAllTilesInOneArray()[index]);
            }
        }
    }
}




