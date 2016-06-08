package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;



public class guiCreator extends JPanel {
	public static void main(String[] args) {
		startMenu startMenu = new startMenu();
		startMenu.createGui();
		
		createNewGame newLabyrinthGame = new createNewGame();
		newLabyrinthGame.createGui();
		
<<<<<<< HEAD
		playGround playGround = new playGround();
=======
		//playGround playGround = new playGround();
>>>>>>> 8ab92348815e4d2a0d2e74eb75e07745f96a4883
		//playGround.createGui();
		
		joinGame joinGame = new joinGame();
		joinGame.createGui();
	}
}