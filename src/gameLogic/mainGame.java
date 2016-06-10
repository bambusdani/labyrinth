package gameLogic;

import chat.ChatClient;
import chat.ChatServer;
import gui.playGround;
import gui.playGround2;

public class mainGame {

	public mainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Board board = new Board();

		playGround2 boardview = new playGround2();
 		boardview.createGui(board);

		ChatClient client = new ChatClient("Darth Vader", "localhost", boardview.getTextArea(), boardview.getTextField());
		client.listen();
	}

}
