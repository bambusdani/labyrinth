package gameLogic;

import java.util.ArrayList;

public class MyBoard {

    /** -> ATTRIBUTE */
    //liste aller player (maximal 4)
    private ArrayList<MyPlayer> players = new ArrayList<MyPlayer>();
    private MyTiles[][] tiles = new MyTiles[7][7];

    /** -> KONSTRUKTOR */
    public MyBoard(){

        //Alle tiles extra gesetzt, dadurch kann man jedes tile genau festlegen...
        //erste Reihe                                                   //fünfte Reihe
        tiles[0][0] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][0] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][1] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][1] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][2] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][2] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][3] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][3] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][4] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][4] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][5] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][5] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[0][6] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[4][6] = new MyTiles(false,new MyPosition(0,0),null,null);
        //zweite Reihe                                                  //sechste Reihe
        tiles[1][0] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][0] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][1] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][1] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][2] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][2] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][3] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][3] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][4] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][4] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][5] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][5] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[1][6] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[5][6] = new MyTiles(false,new MyPosition(0,0),null,null);
        //dritte Reihe                                                  //siebte Reihe
        tiles[2][0] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][0] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][1] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][1] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][2] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][2] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][3] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][3] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][4] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][4] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][5] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][5] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[2][6] = new MyTiles(false,new MyPosition(0,0),null,null); tiles[6][6] = new MyTiles(false,new MyPosition(0,0),null,null);
        //vierte Reihe
        tiles[3][0] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][1] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][2] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][3] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][4] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][5] = new MyTiles(false,new MyPosition(0,0),null,null);
        tiles[3][6] = new MyTiles(false,new MyPosition(0,0),null,null);
    }

    /** -> SPIELER VERWALTUNG */

    //Neuer Spieler wird erzeugt solange momentane Anzahl kleiner als 4 ist
    public void addPlayer(MyPlayer player){
        if(players.size() < 4){players.add(player);}
        else{System.out.println("Es kann kein weiterer Spieler hinzugefügt werden!");}
    }

    //Spieler wird gelöscht solange momentane Anzahl größer als 0 ist
    //TODO delete player by PID erstellen


    /** -> RANDOMIZE BOARD */
    //TODO randomize array
}
