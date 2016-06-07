package gui;

import javax.swing.*;
import java.awt.*;

import gameLogic.Board;
import chat.*;

public class playGround {
	
	private int windowWidth=1000;
	private int windowHeight=700;
	private int gridButtonSize = 60;

	private ChatClient chatClient;
	

	public void createGui(Board board) {
		JFrame frame = new JFrame("DVL2 Spielfeld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);

		//Label "Fehlende Symbole"
		JLabel label = new JLabel("Fehlende Symbole:");
		label.setSize(label.getPreferredSize());	//Die gr��e wird je nach Text L�nge gesetzt
		label.setLocation(5, 10);					//setLocation(x, y)
		
		//Player1 TextField
		JTextField player1 = new JTextField(board.getPlayer(0).getNameOfPlayer()+ " : " + board.getPlayer(0).getScore());
		player1.setSize(100, 20);
		player1.setEditable(false);					//Damit man nicht rein schreiben kann...
		player1.setLocation(label.getPreferredSize().width+15, label.getLocation().y);
		
		//Player2 TextField
		JTextField player2 = new JTextField(board.getPlayer(1).getNameOfPlayer()+ " : " + board.getPlayer(1).getScore());
		player2.setSize(100, 20);
		player2.setEditable(false);					//Damit man nicht rein schreiben kann...
		player2.setLocation(player1.getLocation().x+player1.getWidth()+15, label.getLocation().y);
		
		//Player3 TextField
		JTextField player3 = new JTextField(board.getPlayer(2).getNameOfPlayer()+ " : " + board.getPlayer(2).getScore());
		player3.setSize(100, 20);
		player3.setEditable(false);					//Damit man nicht rein schreiben kann...
		player3.setLocation(player2.getLocation().x+player2.getWidth()+15, label.getLocation().y);
		
		//Player4 TextField
		JTextField player4 = new JTextField(board.getPlayer(3).getNameOfPlayer()+ " : " + board.getPlayer(3).getScore());
		player4.setSize(100, 20);
		player4.setEditable(false);					//Damit man nicht rein schreiben kann...
		player4.setLocation(player3.getLocation().x+player3.getWidth()+15, label.getLocation().y);
		
		//Grid
		JButton[][] boardSquares = new JButton[7][7];
		
		for(int i = 0; i < boardSquares.length; i++) {
			for(int j = 0; j < boardSquares[i].length; j++) {
				JButton b = new JButton();
				b.setBackground(Color.WHITE);
				b.setSize(gridButtonSize, gridButtonSize);
				
				//draws the Shape of the tile
				//checks if the tile has a symbol and adds it to the tile
				if(board.getTile(j, i).getSymbol() == null){
					b.setText(board.getTile(j, i).getShape());
				}
				else{
					b.setText(board.getTile(j, i).getShape() +" \n "+ board.getTile(j, i).getSymbol());					
				}
				
				
	
				// checking if the first player is on the the spot if yes draw a colored border
				if((board.getPlayer(0).getPositionX() == j) && (board.getPlayer(0).getPostitonY() == i)){
					
					b.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, board.getPlayer(0).getColor()));
				}
				
                /* Das ist f�r sp�ter, um icons zu benutzen
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);*/
				
				boardSquares[j][i] = b;
				//Setting location with start location 350,150
				boardSquares[j][i].setLocation(350+i*gridButtonSize, 150+j*gridButtonSize);
				contentPane.add(boardSquares[j][i]);
			}
		}
		
		//Label "Erreichte Ziele"
		JLabel achieved = new JLabel("Erreichte Ziele:");
		achieved.setSize(achieved.getPreferredSize());	//Die gr��e wird je nach Text L�nge gesetzt
		achieved.setLocation(800, 150);					//setLocation(x, y)
		
		//Label "N�chstes Ziele"
		JLabel next = new JLabel("N�chstes Ziele:");
		next.setSize(next.getPreferredSize());	//Die gr��e wird je nach Text L�nge gesetzt
		next.setLocation(800, 250);				//setLocation(x, y)
		
		//Label "Stein"
		JLabel stone = new JLabel("Stone:");
		stone.setSize(stone.getPreferredSize());	//Die gr��e wird je nach Text L�nge gesetzt
		stone.setLocation(800, 350);				//setLocation(x, y)

		//Moveable Stone
		JButton moveableStone = new JButton("M");
		moveableStone.setBackground(Color.WHITE);
		moveableStone.setSize(gridButtonSize, gridButtonSize);
		moveableStone.setLocation(boardSquares[2][0].getX()-70, boardSquares[2][0].getY());

		//Chat
		//chatClient = new ChatClient("Hjorleif", "localhost");
		//chatClient.listen();
		JTextField chat = new JTextField("Chat");
		JTextArea  enteredText = new JTextArea(10, 32);
		JTextField typedText   = new JTextField(32);
		enteredText.setSize(250,200);
		enteredText.setLocation(0,windowHeight-220);
		typedText.setSize(250,20);
		typedText.setLocation(0,windowHeight-20);
		//chatClient = new ChatClient("Hjorleif", "localhost", enteredText, typedText);
		//chatClient.listen();
		//chat.setSize(250, 200);
		//chat.setEditable(false);					//Damit man nicht rein schreiben kann...
		//chat.setLocation(0, windowHeight-200);
		
		contentPane.add(label);
		contentPane.add(player1);
		contentPane.add(player2);
		contentPane.add(player3);
		contentPane.add(player4);
		
		contentPane.add(achieved);
		contentPane.add(next);
		contentPane.add(stone);
		
		contentPane.add(enteredText);
		contentPane.add(typedText);
		
		contentPane.add(moveableStone);

		
		frame.setContentPane(contentPane);
		frame.setSize(windowWidth, windowHeight);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setLayout(null);
	}
}
