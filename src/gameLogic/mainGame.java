package gameLogic;

import chat.ChatClient;
import gui.playGround;

public class mainGame {

	public mainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Board board = new Board();

		playGround boardview = new playGround(board);


		ChatClient client = new ChatClient("Darth Vader", "localhost", boardview.getTextArea(), boardview.getTextField());
		client.listen();
	}

}
