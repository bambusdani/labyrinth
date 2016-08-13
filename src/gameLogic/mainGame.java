package gameLogic;

import network.Protocol;

public class MainGame {

	public MainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		

		Board board = new Board();
		HostGame hostGame = new HostGame(board);

		JoinGame joinGame = new JoinGame(board);




	}

}
