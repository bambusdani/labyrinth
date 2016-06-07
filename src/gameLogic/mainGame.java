package gameLogic;

import gui.playGround;
import gui.playGround2;

public class mainGame {

	public mainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board board = new Board();
		
		
		
		
		
		
		playGround2 boardview = new playGround2();
 		boardview.createGui(board);
		
		

	}

}
