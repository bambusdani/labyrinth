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

import gameLogic.Board;
import chat.ChatClient;

public class playGround2 implements ActionListener {

	public playGround2() {
		// TODO Auto-generated constructor stub
	}

	private int fontSize = 20;
	private int boxSizeX = 175;
	private int boxSizeY = 50;
	private Color colorBlack = new Color(0, 0, 0);
	private int stoneSize = 75;
	
	//all buttons for actionListener
	private JButton buttonNewGame;
	private JButton buttonEndGame;
	private JButton buttonRotate;
	private JButton[][] buttonsToPlaceStone = new JButton[7][7];
	private JButton[][] boardSquares = new JButton[7][7];
	private JFrame 	frame;

	//chat stuff
	private JTextArea textArea;
	private JTextField textField;


	
	//test 
	Board givenBoard = new Board();
	
	

	public void createGui(Board board) {
		
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
		JLabel labelNextStone = setLabel("N�chster Stein: ",fontSize, boxSizeX, boxSizeY, colorBlack );
		panelInformation.add(labelNextStone, constraintsInformation);
		
		//-----------------------------------------------------------------------------------
		// next stone symbol
		constraintsInformation.gridx = 0;
		constraintsInformation.gridy = 5;
		// instead of "T" it should use an image

		
		JLabel labelNextStoneSymbol = setLabel(board.getNextTile().getShape(),fontSize, stoneSize, stoneSize, colorBlack );

		labelNextStoneSymbol.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colorBlack));
		panelInformation.add(labelNextStoneSymbol, constraintsInformation);
		
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
		constraintsChat.insets = new Insets(5, 5, 5, 5);

        //-----------------------------------------------------------------------------------
        // text area
		constraintsChat.gridx = 0;
		constraintsChat.gridy = 0;
        textArea = setTextArea(7,32);
        textArea.setEditable(false);
        panelChat.add(textArea, constraintsChat);

        //-----------------------------------------------------------------------------------
        // input field
        constraintsChat.gridx = 0;
        constraintsChat.gridy = 1;
		textField = setTextField(32);
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
		
		//-----------------------------------------------------------------------------------------
		//creating the buttons where to place the new stone
		//global
		//JButton[][] buttonsToPlaceStone = new JButton[7][7];
		
		for(int i = 0; i < buttonsToPlaceStone.length; i++){
			for(int j = 0; j< buttonsToPlaceStone[i].length; j++){
				
				//top horizontal line
				if((j % 2 == 0) && (i == 0) && (j > 0)){
					constraintsGameField.gridx = j;
					constraintsGameField.gridy = 0;
					JButton buttonStone = setButtons("->", fontSize, stoneSize, stoneSize);
					this.buttonsToPlaceStone[j][i] = buttonStone;
					//add ActionListener
					this.buttonsToPlaceStone[j][i].setActionCommand("ArrowButton: "+ j + " " +i);
					this.buttonsToPlaceStone[j][i].addActionListener(this);
					panelGameField.add(this.buttonsToPlaceStone[j][i], constraintsGameField);
					
			
					
				}
				//bottom horizontal line
				if((j % 2 == 0) && (i == 6) && (j > 0)){
					constraintsGameField.gridx = j;
					constraintsGameField.gridy = 8;
					JButton buttonStone = setButtons("->", fontSize, stoneSize, stoneSize);
					buttonsToPlaceStone[j][i] = buttonStone;
					//add ActionListener
					this.buttonsToPlaceStone[j][i].setActionCommand("ArrowButton: "+ j + " " +i);
					this.buttonsToPlaceStone[j][i].addActionListener(this);
					panelGameField.add(this.buttonsToPlaceStone[j][i], constraintsGameField);
				}
				// left vertical line
				if((i % 2 == 0) && (j == 0) && (i > 0)){
					constraintsGameField.gridx = 0;
					constraintsGameField.gridy = i;
					JButton buttonStone = setButtons("->", fontSize, stoneSize, stoneSize);
					buttonsToPlaceStone[j][i] = buttonStone;
					//add ActionListener
					this.buttonsToPlaceStone[j][i].setActionCommand("ArrowButton: "+ j + " " +i);
					this.buttonsToPlaceStone[j][i].addActionListener(this);
					panelGameField.add(this.buttonsToPlaceStone[j][i], constraintsGameField);
				}
				// right vertical line
				if((i % 2 == 0) && (j == 6) && (i > 0)){
					constraintsGameField.gridx = 8;
					constraintsGameField.gridy = i;
					JButton buttonStone = setButtons("->", fontSize, stoneSize, stoneSize);
					buttonsToPlaceStone[j][i] = buttonStone;
					//add ActionListener
					this.buttonsToPlaceStone[j][i].setActionCommand("ArrowButton: "+ j + " " +i);
					this.buttonsToPlaceStone[j][i].addActionListener(this);
					panelGameField.add(this.buttonsToPlaceStone[j][i], constraintsGameField);
				}
				// rest is filled with null
				else{
					buttonsToPlaceStone[j][i] = null;
				}
			}
		}
		
			
		//-----------------------------------------------------------------------------------------
		// creates 7 X 7 Buttons
		//global
		//JButton[][] boardSquares = new JButton[7][7];
		for(int i = 0; i < boardSquares.length; i++){
			for(int j = 0; j< boardSquares[i].length; j++){
				
				//creating button
				constraintsGameField.gridx = i+1;
				constraintsGameField.gridy = j+1;
				
				JButton buttonStone = setButtons(board.getTile(i, j).getShape(), fontSize, stoneSize, stoneSize);
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
				if((board.getPlayer(0).getPositionX() == j) && (board.getPlayer(0).getPostitonY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(0).getColor()));
				}
				if((board.getPlayer(1).getPositionX() == j) && (board.getPlayer(1).getPostitonY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(1).getColor()));
				}
				if((board.getPlayer(2).getPositionX() == j) && (board.getPlayer(2).getPostitonY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(2).getColor()));
				}
				if((board.getPlayer(3).getPositionX() == j) && (board.getPlayer(3).getPostitonY() == i)){
					buttonStone.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(3).getColor()));
				}
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

    public JTextField setTextField(int length) {
        JTextField textField = new JTextField(length);
		textField.setMinimumSize(new Dimension(100, 32));
		textField.setPreferredSize(new Dimension(100, 32));
        return textField;
    }

    public JTextArea setTextArea(int height, int width) {
        JTextArea textArea = new JTextArea(height, width);
		textArea.setMinimumSize(new Dimension(200, 100));
		textArea.setPreferredSize(new Dimension(200, 100));
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

		
		
		
		
		
			int newRotation = givenBoard.getNextTile().getRotation()+90;
			givenBoard.getNextTile().setRotation(newRotation);
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
		// checks which button to place the next stone is pressed
		for(int i = 0; i < buttonsToPlaceStone.length; i++){
			for(int j = 0; j< buttonsToPlaceStone[i].length; j++){
				
				if( e.getActionCommand().equals("ArrowButton: "+ j + " " +i)){
					//writes the command of the button
					System.out.println("ArrowButton j: "+j +", i: "+ i +" pressed");
				}
			}
		}		
	}
	

	
	
	// make it local in this class so it can be used by all methods
	// only for test
	public void setBoard(Board newBoard){
		this.givenBoard = newBoard;
	}

	//for chat
	public JTextArea getTextArea() { return this.textArea; }
	public JTextField getTextField() { return this.textField; }

}
