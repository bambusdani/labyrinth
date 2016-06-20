package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import gameLogic.*;

public class playGround implements ActionListener {

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
	private JFrame 	frame;

	//Nächstes Teil das eingefügt wird
	public Tiles tmpStorageTile;
	public JLabel labelNextStoneSymbol;

	//Im Uhrzeigersinn den Buttons(einschub/pfeilbuttons) zugewiesen
	public boolean[] possibleInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};


	//Buttons for the arrows to place the next stone
	//top
	public JButton buttonArrow_1_0;
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


	//chat stuff
	private JTextArea textArea;
	private JTextField textField;

	public Board board ;



	public playGround(Board board) {

		this.board = board;
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
		JLabel labelPlayer0 = setLabel(board.getPlayer(0).getNameOfPlayer()+ ": " + board.getPlayer(0).getScore() ,fontSize, boxSizeX, boxSizeY, board.getPlayer(0).getColor());
		//shows which players turn it is
		if(board.getPlayer(0).getTurn() == true){
			labelPlayer0.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		}
		panelPlayeroverview.add(labelPlayer0, constraintsPlayeroverview);
		//---------------------------------------------------------------------------------
		// Player 1
		constraintsPlayeroverview.gridx = 2;
		constraintsPlayeroverview.gridy = 0;
		JLabel labelPlayer1 = setLabel(board.getPlayer(1).getNameOfPlayer()+ ": " + board.getPlayer(1).getScore()  ,fontSize, boxSizeX, boxSizeY , board.getPlayer(1).getColor());
		//shows which players turn it is
		if(board.getPlayer(1).getTurn() == true){
			labelPlayer1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		}
		panelPlayeroverview.add(labelPlayer1, constraintsPlayeroverview);
		//---------------------------------------------------------------------------------
		// Player 2
		constraintsPlayeroverview.gridx = 3;
		constraintsPlayeroverview.gridy = 0;
		JLabel labelPlayer2 = setLabel(board.getPlayer(2).getNameOfPlayer() + ": " + board.getPlayer(2).getScore() ,fontSize, boxSizeX, boxSizeY , board.getPlayer(2).getColor());
		//shows which players turn it is
		if(board.getPlayer(2).getTurn() == true){
			labelPlayer2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		}
		panelPlayeroverview.add(labelPlayer2, constraintsPlayeroverview);
		//---------------------------------------------------------------------------------
		// Player 3
		constraintsPlayeroverview.gridx = 4;
		constraintsPlayeroverview.gridy = 0;
		JLabel labelPlayer3 = setLabel(board.getPlayer(3).getNameOfPlayer()+": " + board.getPlayer(3).getScore() ,fontSize, boxSizeX, boxSizeY , board.getPlayer(3).getColor() );
		//shows which players turn it is
		if(board.getPlayer(3).getTurn() == true){
			labelPlayer3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		}
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
		// last reached goal symbol
		constraintsInformation.gridx = 0;
		constraintsInformation.gridy = 1;
		// instead of Dragon it should use an image
		JLabel labelReachedGoalsSymbol = setLabel("Dragon",fontSize, stoneSize, stoneSize, colorBlack );
		labelReachedGoalsSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		panelInformation.add(labelReachedGoalsSymbol, constraintsInformation);

		//-----------------------------------------------------------------------------------
		// next goal
		constraintsInformation.gridx = 0;
		constraintsInformation.gridy = 2;
		JLabel labelNextGoal = setLabel("N�chstes Ziele: ",fontSize, boxSizeX, boxSizeY, colorBlack );
		panelInformation.add(labelNextGoal, constraintsInformation);

		//-----------------------------------------------------------------------------------
		// next goal symbol
		constraintsInformation.gridx = 0;
		constraintsInformation.gridy = 3;
		// instead of Dragon it should use an image
		JLabel labelNextGoalSymbol = setLabel("Dragon",fontSize, stoneSize, stoneSize, colorBlack );
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


		this.labelNextStoneSymbol = setLabel(board.getNextTile().getShape(),fontSize, stoneSize, stoneSize, colorBlack );

		this.labelNextStoneSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		panelInformation.add(this.labelNextStoneSymbol, constraintsInformation);

		//-----------------------------------------------------------------------------------
		// Button rotate
		constraintsInformation.insets = new Insets(20, 0, 0, 0);
		constraintsInformation.gridx = 0;
		constraintsInformation.gridy = 6;
		this.buttonRotate = setButtons("Rotate", fontSize, stoneSize, stoneSize);
		//add ActionListener
		this.buttonRotate.addActionListener(this);
		panelInformation.add(this.buttonRotate, constraintsInformation);

		//===================================================================================
		// panel chat
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
		this.buttonArrow_1_0 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_1_0.addActionListener(this);
		panelGameField.add(this.buttonArrow_1_0, constraintsGameField);
		// #2 top
		constraintsGameField.gridx = 4;
		constraintsGameField.gridy = 0;
		this.buttonArrow_3_0 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_3_0.addActionListener(this);
		panelGameField.add(this.buttonArrow_3_0, constraintsGameField);
		// #3 top
		constraintsGameField.gridx = 6;
		constraintsGameField.gridy = 0;
		this.buttonArrow_5_0 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_5_0.addActionListener(this);
		panelGameField.add(this.buttonArrow_5_0, constraintsGameField);
		//-----------------------------
		// #1 bottom
		constraintsGameField.gridx = 2;
		constraintsGameField.gridy = 8;
		this.buttonArrow_1_6 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_1_6.addActionListener(this);
		panelGameField.add(this.buttonArrow_1_6, constraintsGameField);
		// #2 bottom
		constraintsGameField.gridx = 4;
		constraintsGameField.gridy = 8;
		this.buttonArrow_3_6 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_3_6.addActionListener(this);
		panelGameField.add(this.buttonArrow_3_6, constraintsGameField);
		// #3 bottom
		constraintsGameField.gridx = 6;
		constraintsGameField.gridy = 8;
		this.buttonArrow_5_6 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_5_6.addActionListener(this);
		panelGameField.add(this.buttonArrow_5_6, constraintsGameField);
		//-----------------------------
		// #1 left
		constraintsGameField.gridx = 0;
		constraintsGameField.gridy = 2;
		this.buttonArrow_0_1 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_0_1.addActionListener(this);
		panelGameField.add(this.buttonArrow_0_1, constraintsGameField);
		// #2 left
		constraintsGameField.gridx = 0;
		constraintsGameField.gridy = 4;
		this.buttonArrow_0_3 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_0_3.addActionListener(this);
		panelGameField.add(this.buttonArrow_0_3, constraintsGameField);
		// #3 left
		constraintsGameField.gridx = 0;
		constraintsGameField.gridy = 6;
		this.buttonArrow_0_5 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_0_5.addActionListener(this);
		panelGameField.add(this.buttonArrow_0_5, constraintsGameField);
		//-----------------------------
		// #1 right
		constraintsGameField.gridx = 8;
		constraintsGameField.gridy = 2;
		this.buttonArrow_6_1 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_6_1.addActionListener(this);
		panelGameField.add(this.buttonArrow_6_1, constraintsGameField);
		// #2 right
		constraintsGameField.gridx = 8;
		constraintsGameField.gridy = 4;
		this.buttonArrow_6_3 = setButtons("->", fontSize, stoneSize, stoneSize);
		//adding ActionListener
		this.buttonArrow_6_3.addActionListener(this);
		panelGameField.add(this.buttonArrow_6_3, constraintsGameField);
		// #3 right
		constraintsGameField.gridx = 8;
		constraintsGameField.gridy = 6;
		this.buttonArrow_6_5 = setButtons("->", fontSize, stoneSize, stoneSize);
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

				JButton buttonStone = setButtons(board.getTile(j, i).getShape(), fontSize, stoneSize, stoneSize);
				//------------------------
				// is placing the symbols on the tiles
				if(board.getTile(j, i).getSymbol() == null){
					buttonStone.setText(board.getTile(j, i).getShape());
				}
				else{
					buttonStone.setText(board.getTile(j, i).getShape() + " " + board.getTile(j, i).getSymbol());
				}
				//-------------------------
				// checking if the players are on the the spot if yes draw a colored border
				if((board.getPlayer(0).getAcutalPosition().getX() == j) && (board.getPlayer(0).getAcutalPosition().getY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(0).getColor()));
				}
				if((board.getPlayer(1).getAcutalPosition().getX() == j) && (board.getPlayer(1).getAcutalPosition().getY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(1).getColor()));
				}
				if((board.getPlayer(2).getAcutalPosition().getX() == j) && (board.getPlayer(2).getAcutalPosition().getY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(2).getColor()));
				}
				if((board.getPlayer(3).getAcutalPosition().getX() == j) && (board.getPlayer(3).getAcutalPosition().getY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(3).getColor()));
				}
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
		System.out.println("j 0 i 0: "+ board.getTile(0,0).getShape());

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

		if(buttonEndGame == e.getSource()){
			frame.dispose();
		}
		if(buttonNewGame == e.getSource()){
			frame.dispose();
			createNewGame newGame = new createNewGame();
			newGame.createGui();
		}
		if(buttonRotate == e.getSource()){


			int newRotation = board.getNextTile().getRotation()+90;
			board.getNextTile().setRotation(newRotation);

			System.out.println("Rotation betr�gt: " + newRotation);

		}


		//------------------------------------------------------------
		// checks which button on the gameField is pressed
		for(int i = 0; i < boardSquares.length; i++){
			for(int j = 0; j< boardSquares[i].length; j++){

				if( e.getActionCommand().equals("gameField: "+j+" "+i)){
					//writes the command of the button
					System.out.println("Button j: "+j +", i: "+ i +" pressed");
				}
			}
		}
		//-------------------------------------------------------------

		// checks which button was pressed  to place the next stone
		// buttonArrow_1_0 means line j:1 i:0 on the field
		// topArrowButtons
		if(buttonArrow_1_0 == e.getSource()){
			System.out.println("ArrowButton j: 1 i: 0");

			//ist zug möglich?
			if(possibleInsertions[0]) {
				for(int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[8]=false;

				tmpStorageTile = board.getTile(1, 6);

				for (int index = 6; index > 0; index--) {
					board.setTiles(1, index, board.getTile(1, index - 1));
					boardSquares[1][index].setText(board.getTile(1, index).getShape());
				}

				board.setTiles(1, 0, board.getNextTile());
				boardSquares[1][0].setText(board.getTile(1, 0).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{System.err.print("Invalid -> ArrowButton j: 1 i: 0");}

		}
		if(buttonArrow_3_0 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[1]) {
				System.out.println("ArrowButton j: 3 i: 0");
				//gegenüber auf false
				for(int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				possibleInsertions[7] = false;

				tmpStorageTile = board.getTile(3, 6);

				for (int index = 6; index > 0; index--) {
					board.setTiles(3, index, board.getTile(3, index - 1));
					boardSquares[3][index].setText(board.getTile(3, index).getShape());
				}

				board.setTiles(3, 0, board.getNextTile());
				boardSquares[3][0].setText(board.getTile(3, 0).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{System.err.print("Invalid -> ArrowButton j: 3 i: 0");}

		}
		if(buttonArrow_5_0 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[2]) {
				System.out.println("ArrowButton j: 5 i: 0");

				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[6] = false;

				tmpStorageTile = board.getTile(5, 6);

				for (int index = 6; index > 0; index--) {
					board.setTiles(5, index, board.getTile(5, index - 1));
					boardSquares[5][index].setText(board.getTile(5, index).getShape());
				}

				board.setTiles(5, 0, board.getNextTile());
				boardSquares[5][0].setText(board.getTile(5, 0).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 5 i: 0");}

		}
		// bottomArrowButtons
		if(buttonArrow_1_6 == e.getSource()){
			//ist zug möglich?
			if(possibleInsertions[8]) {
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[0] = false;

				System.out.println("ArrowButton j: 1 i: 6");

				tmpStorageTile = board.getTile(1, 0);

				for (int index = 0; index < 6; index++) {
					board.setTiles(1, index, board.getTile(1, index + 1));
					boardSquares[1][index].setText(board.getTile(1, index).getShape());
				}

				board.setTiles(1, 6, board.getNextTile());
				boardSquares[1][6].setText(board.getTile(1, 6).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 1 i: 6");}

		}
		if(buttonArrow_3_6 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[7]) {
				System.out.println("ArrowButton j: 3 i: 6");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[1] = false;

				tmpStorageTile = board.getTile(3, 0);

				for (int index = 0; index < 6; index++) {
					board.setTiles(3, index, board.getTile(3, index + 1));
					boardSquares[3][index].setText(board.getTile(3, index).getShape());
				}

				board.setTiles(3, 6, board.getNextTile());
				boardSquares[3][6].setText(board.getTile(3, 6).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 3 i: 6");}

		}
		if(buttonArrow_5_6 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[6]) {
				System.out.println("ArrowButton j: 5 i: 6");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[2] = false;

				tmpStorageTile = board.getTile(5, 0);

				for (int index = 0; index < 6; index++) {
					board.setTiles(5, index, board.getTile(5, index + 1));
					boardSquares[5][index].setText(board.getTile(5, index).getShape());
				}

				board.setTiles(5, 6, board.getNextTile());
				boardSquares[5][6].setText(board.getTile(5, 6).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 5 i: 6");
			}

		}
		//leftArrowButton
		if(buttonArrow_0_1 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[11]) {
				System.out.println("ArrowButton j: 0 i: 1");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[3] = false;


				tmpStorageTile = board.getTile(6, 1);

				for (int index = 6; index > 0; index--) {
					board.setTiles(index, 1, board.getTile(index - 1, 1));
					boardSquares[index][1].setText(board.getTile(index, 1).getShape());
				}

				board.setTiles(0, 1, board.getNextTile());
				boardSquares[0][1].setText(board.getTile(0, 1).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 0 i: 1");
			}

		}
		if(buttonArrow_0_3 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[10]) {
				System.out.println("ArrowButton j: 0 i: 3");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[4] = false;

				tmpStorageTile = board.getTile(6, 3);

				for (int index = 6; index > 0; index--) {
					board.setTiles(index, 3, board.getTile(index - 1, 3));
					boardSquares[index][3].setText(board.getTile(index, 3).getShape());
				}

				board.setTiles(0, 3, board.getNextTile());
				boardSquares[0][3].setText(board.getTile(0, 3).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 0 i: 3");
			}

		}
		if(buttonArrow_0_5 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[9]) {
				System.out.println("ArrowButton j: 0 i: 5");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[5] = false;


				tmpStorageTile = board.getTile(6, 5);

				for (int index = 6; index > 0; index--) {
					board.setTiles(index, 5, board.getTile(index - 1, 5));
					boardSquares[index][5].setText(board.getTile(index, 5).getShape());
				}

				board.setTiles(0, 5, board.getNextTile());
				boardSquares[0][5].setText(board.getTile(0, 5).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 0 i: 5");
			}

		}
		//rightArrowButton
		if(buttonArrow_6_1 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[3]) {
				System.out.println("ArrowButton j: 6 i: 1");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[11] = false;

				tmpStorageTile = board.getTile(0, 1);

				for (int index = 0; index < 6; index++) {
					board.setTiles(index, 1, board.getTile(index + 1, 1));
					boardSquares[index][1].setText(board.getTile(index, 1).getShape());
				}

				board.setTiles(6, 1, board.getNextTile());
				boardSquares[6][1].setText(board.getTile(6, 1).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 6 i: 1");
			}

		}
		if(buttonArrow_6_3 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[4]) {
				System.out.println("ArrowButton j: 6 i: 3");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[10] = false;

				tmpStorageTile = board.getTile(0, 3);

				for (int index = 0; index < 6; index++) {
					board.setTiles(index, 3, board.getTile(index + 1, 3));
					boardSquares[index][3].setText(board.getTile(index, 3).getShape());
				}

				board.setTiles(6, 3, board.getNextTile());
				boardSquares[6][3].setText(board.getTile(6, 3).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 6 i: 3");
			}

		}
		if(buttonArrow_6_5 == e.getSource()){


			//ist zug möglich?
			if(possibleInsertions[5]) {
				System.out.println("ArrowButton j: 6 i: 5");
				for (int index = 0; index < possibleInsertions.length; index++) {
					possibleInsertions[index] = true;
				}
				//gegenüber auf false
				possibleInsertions[9] = false;

				tmpStorageTile = board.getTile(0, 5);

				for (int index = 0; index < 6; index++) {
					board.setTiles(index, 5, board.getTile(index + 1, 5));
					boardSquares[index][5].setText(board.getTile(index, 5).getShape());
				}

				board.setTiles(6, 5, board.getNextTile());
				boardSquares[6][5].setText(board.getTile(6, 5).getShape());

				board.setNextTile(tmpStorageTile);
				labelNextStoneSymbol.setText(board.getNextTile().getShape());
			}
			else{
				System.err.println("Invalid -> ArrowButton j: 6 i: 5");
			}
		}

	}

	public void setBoard(Board newBoard){
		this.board = newBoard;
	}

	//for chat
	public JTextArea getTextArea() {
		return this.textArea;
	}
	public JTextField getTextField() {
		return this.textField;
	}

}

//test 17.06.2016
//h
