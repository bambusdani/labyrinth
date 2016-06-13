package gameLogic;

import chat.ChatClient;
import gui.playGround2;

public class mainGame2 {

	public mainGame2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Board board2 = new Board();

		playGround2 boardview2 = new playGround2();
 		boardview2.createGui(board2);

		ChatClient client2 = new ChatClient("Yoda", "localhost", boardview2.getTextArea(), boardview2.getTextField());
		client2.listen();
	}

}
