package gameLogic;

import gui.playGround;

public class mainGame {

	public mainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board board = new Board();
		
		playGround boardview = new playGround();
 		boardview.createGui(board);
		
		

	}

}
