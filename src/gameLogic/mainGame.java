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





<<<<<<< HEAD
		ChatClient client = new ChatClient("Daniel", "localhost", boardview.getTextArea(), boardview.getTextField());
=======
		ChatClient client = new ChatClient("Rehan", "localhost", boardview.getTextArea(), boardview.getTextField());
>>>>>>> daniel

		client.listen();



	}

}
