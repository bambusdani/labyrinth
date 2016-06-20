package gameLogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Board {
    //==============================================================
    /** -> ATTRIBUTE */
    //liste aller player (maximal 4)
    private Player[] allPlayers = new Player[4];
    private Tiles[][] tiles = new Tiles[7][7];
    private Tiles nextTile = new Tiles(true, null, "T", null, 0);
    //=============================================================


    //============================
    /** -> KONSTRUKTOR */
    public Board(){

        //Tiles erzeugen
        createTiles(this.tiles);

        //Tiles vermischen
        shuffleTiles(this.tiles);

        String[] creaturesNeeded = {"creature1", "creature2"};
        allPlayers[0] = new Player(new Position(0,0), new Position(1,1), 1 , new Color(255, 0 ,0), true, 5, "Rehan", creaturesNeeded);
        allPlayers[1] = new Player(new Position(0,6), new Position(2,5), 2 , new Color(0, 255 ,0), true, 6, "Marvin", creaturesNeeded);
        allPlayers[2] = new Player(new Position(6,0), new Position(6,3), 3 , new Color(0, 255 ,255), true, 6, "Daniel", creaturesNeeded);
        allPlayers[3] = new Player(new Position(6,6), new Position(2,5), 4 , new Color(255, 255 ,0), true, 6, null , creaturesNeeded);
    }
    //===========================

    /** -> GETTER                                                    SETTER */
    public Player getPlayer(int x){return this.allPlayers[x];}
    public Tiles getTile(int x, int y){return this.tiles[x][y];}     public void setTiles(int x, int y, Tiles tile){this.tiles[x][y] = tile;}
    public Tiles getNextTile(){return nextTile;}                     public void setNextTile(Tiles nextTile){this.nextTile = nextTile;}
    public Player[] getAllPlayers(){return  this.allPlayers;}


    //=================================================================================
    /** -> SPIELER VERWALTUNG */

    //=================================================================================


    //======================================================
    /** -> RANDOMIZE BOARD */
    //Tiles werden zufällig vertauscht und zurückgegeben.
    public Tiles[][] shuffleTiles(Tiles[][] tiles) {
        //saves 2d Array to an ArrayList

        List<Tiles> listOfMovableTiles = new ArrayList<Tiles>();
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].getMoveable()) {
                    listOfMovableTiles.add(tiles[i][j]);
                }
            }
        }

        //shuffle ArrayList
        Collections.shuffle(listOfMovableTiles);


        int x = 0; int y = 0; int listCounter = 0;
        for(int index = 0; index < 49; index++){
            if( x % 2 != 0 && y % 2 == 0 ){
                tiles[x][y] = listOfMovableTiles.get(listCounter);
                x++;
                listCounter++;
            }
            else if( y % 2 != 0 ){
                if(x==6) {
                    tiles[x][y] = listOfMovableTiles.get(listCounter);
                    x = 0;
                    y++;
                    listCounter++;
                }else{
                    tiles[x][y] = listOfMovableTiles.get(listCounter);
                    x++;
                    listCounter++;
                }
            }
            else{
                if(x==6) {
                    x = 0;
                    y++;
                }else{
                    x++;
                }
            }
        }
        return tiles;

    }
    //======================================================


    //=================================================================================================================================
    /** -> Creates Tiles */
    public void createTiles(Tiles[][] tiles){
        //Alle tiles extra gesetzt, dadurch kann man jedes tile genau festlegen...
        //erste Reihe                                                   //fünfte Reihe

        tiles[0][0] = new Tiles(false,new Position(0,0),"!",null,0); tiles[4][0] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[0][1] = new Tiles(true,new Position(0,0),"A",null,0);  tiles[4][1] = new Tiles(true,new Position(0,0),"Z",null,0);
        tiles[0][2] = new Tiles(false,new Position(0,0),"!",null,0); tiles[4][2] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[0][3] = new Tiles(true,new Position(0,0),"B",null,0);  tiles[4][3] = new Tiles(true,new Position(0,0),"A",null,0);
        tiles[0][4] = new Tiles(false,new Position(0,0),"!",null,0); tiles[4][4] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[0][5] = new Tiles(true,new Position(0,0),"C",null,0);  tiles[4][5] = new Tiles(true,new Position(0,0),"B",null,0);
        tiles[0][6] = new Tiles(false,new Position(0,0),"!",null,0); tiles[4][6] = new Tiles(false,new Position(0,0),"!",null,0);
        //zweite Reihe                                                  //sechste Reihe
        tiles[1][0] = new Tiles(true,new Position(0,0),"D",null,0);  tiles[5][0] = new Tiles(true,new Position(0,0),"C",null,0);
        tiles[1][1] = new Tiles(true,new Position(0,0),"E",null,0);  tiles[5][1] = new Tiles(true,new Position(0,0),"D",null,0);
        tiles[1][2] = new Tiles(true,new Position(0,0),"F",null,0);  tiles[5][2] = new Tiles(true,new Position(0,0),"E",null,0);
        tiles[1][3] = new Tiles(true,new Position(0,0),"G",null,0);  tiles[5][3] = new Tiles(true,new Position(0,0),"F",null,0);
        tiles[1][4] = new Tiles(true,new Position(0,0),"H",null,0);  tiles[5][4] = new Tiles(true,new Position(0,0),"G",null,0);
        tiles[1][5] = new Tiles(true,new Position(0,0),"I",null,0);  tiles[5][5] = new Tiles(true,new Position(0,0),"H",null,0);
        tiles[1][6] = new Tiles(true,new Position(0,0),"J",null,0);  tiles[5][6] = new Tiles(true,new Position(0,0),"I",null,0);
        //dritte Reihe                                                  //siebte Reihe
        tiles[2][0] = new Tiles(false,new Position(0,0),"!",null,0); tiles[6][0] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[2][1] = new Tiles(true,new Position(0,0),"K",null,0);  tiles[6][1] = new Tiles(true,new Position(0,0),"J",null,0);
        tiles[2][2] = new Tiles(false,new Position(0,0),"!",null,0); tiles[6][2] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[2][3] = new Tiles(true,new Position(0,0),"L",null,0);  tiles[6][3] = new Tiles(true,new Position(0,0),"K",null,0);
        tiles[2][4] = new Tiles(false,new Position(0,0),"!",null,0); tiles[6][4] = new Tiles(false,new Position(0,0),"!",null,0);
        tiles[2][5] = new Tiles(true,new Position(0,0),"M",null,0);  tiles[6][5] = new Tiles(true,new Position(0,0),"L",null,0);
        tiles[2][6] = new Tiles(false,new Position(0,0),"!",null,0); tiles[6][6] = new Tiles(false,new Position(0,0),"!",null,0);
        //vierte Reihe
        tiles[3][0] = new Tiles(true,new Position(0,0),"N",null,0);
        tiles[3][1] = new Tiles(true,new Position(0,0),"O",null,0);
        tiles[3][2] = new Tiles(true,new Position(0,0),"P",null,0);
        tiles[3][3] = new Tiles(true,new Position(0,0),"Q",null,0);
        tiles[3][4] = new Tiles(true,new Position(0,0),"W",null,0);
        tiles[3][5] = new Tiles(true,new Position(0,0),"X",null,0);
        tiles[3][6] = new Tiles(true,new Position(0,0),"Y",null,0);

    }
    //===================================================================================================================================
}
