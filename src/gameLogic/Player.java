package gameLogic;

import java.awt.Color;

public class Player {

	public Player(int startPositionX, int startPositionY, String nameOfPlayer,Color color, String[] cardSymbosNeeded, int score, int playerID, boolean turn) {
		// TODO Auto-generated constructor stub
		this.color = color;
		this.startPositionX = startPositionX;
		this.startPositionY = startPositionY;
		this.positionX = startPositionX;
		this.positionY = startPositionY;
		
		this.nameOfPlayer 	= nameOfPlayer;
		this.cardSymbolsNeeded = cardSymbosNeeded;
		this.playerID = playerID;
		this.score = score;
		this.turn = turn;
		
		
	}
	
	private int positionX;
	private int positionY;
	private int score;
	private Color color;
	private int startPositionX;
	private int startPositionY;
	private String nameOfPlayer;
	private String[] cardSymbolsNeeded;
	private int playerID;
	private boolean turn;
	
	
	
	
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() { return positionY; }
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Color getColor() {
		return color;
	}
	
	public int getStartPositionX() {
		return startPositionX;
	}
	
	public int getStartPositionY() {
		return startPositionY;
	}
	
	public String getNameOfPlayer() {
		return nameOfPlayer;
	}
	
	public String[] getCardSymbolsNeeded() {
		return cardSymbolsNeeded;
	}
	public int getPlayerID (){
		return playerID;
	}
	public boolean getTurn(){
		return turn;
	}
	public void setTurn(boolean turn){
		this.turn = turn;
	}
	


}
