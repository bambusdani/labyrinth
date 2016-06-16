package gameLogic;

import chat.ChatClient;
import gui.MyPlayGround;
import gui.playGround;

import java.util.ArrayList;
import java.util.Properties;

public class mainGame {

	public mainGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		MyBoard board = new MyBoard();

		MyPlayGround boardview = new MyPlayGround(board);


		/*ChatClient client = new ChatClient("Darth Vader", "localhost", boardview.getTextArea(), boardview.getTextField());
		client.listen();*/


	}

}
