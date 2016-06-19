package gui;

import gameLogic.Board;
import gameLogic.MyBoard;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


/*
* THIS CLASS CREATES THE GUI, BY CREATING A NEW ELEMENT OF A GUI CLASS AND CALL IT'S createGui() FUNCTION
* */
public class guiCreator extends JPanel {
	public static void main(String[] args) {
		/*createNewGame createNewGame = new createNewGame();
		createNewGame.createGui();*/

		/*startMenu startMenu = new startMenu();
		startMenu.createGui();*/

		MyBoard board = new MyBoard();
		MyPlayGround playGround = new MyPlayGround(board);



	}
}