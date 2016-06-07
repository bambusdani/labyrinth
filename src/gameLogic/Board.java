package gameLogic;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

public class Board {

	public Board() {
		// TODO Auto-generated constructor stub
		
		//----------------------------
		// creates four empty Player
		for (int i = 0; i < allPlayers.length; i++) {
			allPlayers[i] = new Player(0, 0, "empty", new Color(0, 255, 0), null,0, i, false);	
		}
		//add one player with data
		addPlayer();
		
		
		//----------------------------------------------------------------------
		// creates 49 tiles
		// all should get directly the right attribute like the not moveable 
		
		//moveable tiles
		for (int indexY = 0; indexY < tiles.length; indexY++) {
			for (int indexX = 0; indexX < tiles[indexY].length; indexX++) {
				tiles[indexX][indexY] = new Tiles(true, indexX,indexY,"I",null,0); 
				
			}
		}
		
		// not moveable tiles
		tiles[0][0] = new Tiles(false,0,0,"L",null,0);
		tiles[2][0] = new Tiles(false,2,0,"L",null,0);
		tiles[4][0] = new Tiles(false,4,0,"T","dragon",0);
		tiles[6][0] = new Tiles(false,6,0,"L",null,0);
		tiles[0][2] = new Tiles(false,0,2,"I",null,0);
		tiles[2][2] = new Tiles(false,2,2,"I","fish",0);
		tiles[4][2] = new Tiles(false,4,2,"T","lamp",0);
		tiles[6][2] = new Tiles(false,6,2,"I",null,0);
		tiles[0][4] = new Tiles(false,0,4,"L",null,0);
		tiles[2][4] = new Tiles(false,2,4,"L","cat",0);
		tiles[4][4] = new Tiles(false,4,4,"L",null,0);
		tiles[6][4] = new Tiles(false,6,4,"T",null,0);
		tiles[0][6] = new Tiles(false,0,6,"L",null,0);
		tiles[2][6] = new Tiles(false,2,6,"I","rat",0);
		tiles[4][6] = new Tiles(false,4,6,"L",null,0);
		tiles[6][6] = new Tiles(false,6,6,"L",null,0);
		
		//-----------------------------------------------------------------------
	
	}
	
	private Player[] allPlayers = new Player[4];
	private Tiles[][] tiles  = new Tiles[7][7];
	
	//creates the first nextTile
	//null not allowed as position so 99
	private Tiles nextTile = new Tiles(true, 99, 99, "L", "Mouse", 0);
	
	
	
	
	public void addPlayer(){
		//needs to get the information from creating game
		Player player = new Player(3, 5, "Rehan", new Color(255, 0, 0), null,5, 0,true);
		allPlayers[player.getPlayerID()]= player;
	}
	
	public Player getPlayer(int indexOfPlayer){
		return allPlayers[indexOfPlayer];
	}
	
	public Tiles getTile(int x, int y){
		return tiles[x][y];
	}
	public Tiles getNextTile(){
		return nextTile;
	}
	public void setNextTile(Tiles tile){
		this.nextTile = tile;
	}
	
}
