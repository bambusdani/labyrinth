package gameLogic;

import java.util.ArrayList;
import java.util.*;

public class MyBoard {
    //==============================================================
    /** -> ATTRIBUTE */
    //liste aller player (maximal 4)
    private ArrayList<MyPlayer> players = new ArrayList<MyPlayer>();
    private MyTiles[][] tiles = new MyTiles[7][7];
    //=============================================================


    //============================
    /** -> KONSTRUKTOR */
    public MyBoard(){

        //Tiles erzeugen
        createTiles(this.tiles);

        //Tiles vermischen
        shuffleTiles(this.tiles);

    }
    //===========================

    /** -> GETTER                                                    SETTER */
    public  MyPlayer getPlayer(int x){return this.players.get(x);}
    public MyTiles getTile(int x, int y){return this.tiles[x][y];}


    //=================================================================================
    /** -> SPIELER VERWALTUNG */
    //Neuer Spieler wird erzeugt solange momentane Anzahl kleiner als 4 ist
    public void addPlayer(MyPlayer player){
        if(players.size() < 4){players.add(player);}
        else{System.out.println("Es kann kein weiterer Spieler hinzugefügt werden!");}
    }
    //---------------------------------------------------------------------------------
    //Spieler wird gelöscht solange momentane Anzahl größer als 0 ist
    public void deletePlayer(MyPlayer player){
        for(int index = 0; index < players.size(); index ++){
            if(players.get(index).getPID()==player.getPID()){
                players.remove(index);
            }
        }
    }
    //=================================================================================


    //======================================================
    /** -> RANDOMIZE BOARD */
    //Tiles werden zufällig vertauscht und zurückgegeben.
    public MyTiles[][] shuffleTiles(MyTiles[][] tiles) {
        //saves 2d Array to an ArrayList
        List<MyTiles> listOfMovableTiles = new ArrayList<MyTiles>();
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
    public void createTiles(MyTiles[][] tiles){
        //Alle tiles extra gesetzt, dadurch kann man jedes tile genau festlegen...
        //erste Reihe                                                   //fünfte Reihe
        tiles[0][0] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[4][0] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[0][1] = new MyTiles(true,new MyPosition(0,0),"A",null);  tiles[4][1] = new MyTiles(true,new MyPosition(0,0),"Z",null);
        tiles[0][2] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[4][2] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[0][3] = new MyTiles(true,new MyPosition(0,0),"B",null);  tiles[4][3] = new MyTiles(true,new MyPosition(0,0),"A",null);
        tiles[0][4] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[4][4] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[0][5] = new MyTiles(true,new MyPosition(0,0),"C",null);  tiles[4][5] = new MyTiles(true,new MyPosition(0,0),"B",null);
        tiles[0][6] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[4][6] = new MyTiles(false,new MyPosition(0,0),"!",null);
        //zweite Reihe                                                  //sechste Reihe
        tiles[1][0] = new MyTiles(true,new MyPosition(0,0),"D",null); tiles[5][0] = new MyTiles(true,new MyPosition(0,0),"C",null);
        tiles[1][1] = new MyTiles(true,new MyPosition(0,0),"E",null);  tiles[5][1] = new MyTiles(true,new MyPosition(0,0),"D",null);
        tiles[1][2] = new MyTiles(true,new MyPosition(0,0),"F",null); tiles[5][2] = new MyTiles(true,new MyPosition(0,0),"E",null);
        tiles[1][3] = new MyTiles(true,new MyPosition(0,0),"G",null);  tiles[5][3] = new MyTiles(true,new MyPosition(0,0),"F",null);
        tiles[1][4] = new MyTiles(true,new MyPosition(0,0),"H",null); tiles[5][4] = new MyTiles(true,new MyPosition(0,0),"G",null);
        tiles[1][5] = new MyTiles(true,new MyPosition(0,0),"I",null);  tiles[5][5] = new MyTiles(true,new MyPosition(0,0),"H",null);
        tiles[1][6] = new MyTiles(true,new MyPosition(0,0),"J",null); tiles[5][6] = new MyTiles(true,new MyPosition(0,0),"I",null);
        //dritte Reihe                                                  //siebte Reihe
        tiles[2][0] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[6][0] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[2][1] = new MyTiles(true,new MyPosition(0,0),"K",null);  tiles[6][1] = new MyTiles(true,new MyPosition(0,0),"J",null);
        tiles[2][2] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[6][2] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[2][3] = new MyTiles(true,new MyPosition(0,0),"L",null);  tiles[6][3] = new MyTiles(true,new MyPosition(0,0),"K",null);
        tiles[2][4] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[6][4] = new MyTiles(false,new MyPosition(0,0),"!",null);
        tiles[2][5] = new MyTiles(true,new MyPosition(0,0),"M",null);  tiles[6][5] = new MyTiles(true,new MyPosition(0,0),"L",null);
        tiles[2][6] = new MyTiles(false,new MyPosition(0,0),"!",null); tiles[6][6] = new MyTiles(false,new MyPosition(0,0),"!",null);
        //vierte Reihe
        tiles[3][0] = new MyTiles(true,new MyPosition(0,0),"N",null);
        tiles[3][1] = new MyTiles(true,new MyPosition(0,0),"O",null);
        tiles[3][2] = new MyTiles(true,new MyPosition(0,0),"P",null);
        tiles[3][3] = new MyTiles(true,new MyPosition(0,0),"Q",null);
        tiles[3][4] = new MyTiles(true,new MyPosition(0,0),"W",null);
        tiles[3][5] = new MyTiles(true,new MyPosition(0,0),"X",null);
        tiles[3][6] = new MyTiles(true,new MyPosition(0,0),"Y",null);
    }
    //===================================================================================================================================
}
