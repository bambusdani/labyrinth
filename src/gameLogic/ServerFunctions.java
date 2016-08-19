package gameLogic;

/**
 * Created by rehan on 19.08.16.
 */
public class ServerFunctions {
    /**
     * DAS PROBLEM IST, DASS ES OHNE tmpTile FUNKTIONIERT, MAN JEDOCH DAS tmpTile BRAUCHT UM DIE FUNKTION RICHTIG UMZUSETZEN
     * -> MAN KANN IWIE NICHTS IN EINER VARIABLE ZWISCHENSPEICHERN UND DANN AUF DIESE ZUGREIFEN
     * */
    Tiles tmpTile;

    /**
     * Function inserts Next Tile and sets new Next Tile
     *
     *
     * Das problem ist , dass die Funktion jedes Tile in der Reihe Senkrecht eins nach unten schieben sollte,
     * und dann das nextTile an die erste Reihe in der Stelle...
     * Um das umzusetzen hab ich eine Variable tmpTile vorgesehen
     * */
    public void insertTile(int buttonID,Board board, int nextTileID) {
        System.out.println("buttonID: " + buttonID);
        if(buttonID==0){

            // das next tile wird in tmp gespeichert
            tmpTile = board.getNextTile();

            //das unterste teil wird herausgeschoben -> also in nextTile gesetzt
            board.setNextTile(board.getTile(1,6));

            //Sollte die Button nach unten schieben(fängt von letzen an und nimmt dann immer den vorherigen und setzt in darauf)
            for (int i = 6; i > 0 ; i--) {
                board.setTiles(1,i,board.getTile(1,i-1));
            }

            //das zwischengespeicherte NextTile wird oben reingeschoben
            board.setTiles(1,0,tmpTile);
        }
        else{
            System.out.println("Keine korrekte buttonID gefunden");
        }

        /**
         * DAS HIER WAR EIN TEST ZUM SCHAUEN OBS GEHT UND DAS FUNKTIONIERT SOLANGE ALLES BIS tmp verwendet wird
        board.setTiles(1,6,board.getTile(1,5));
        board.setTiles(1,5,board.getTile(1,4));
        board.setTiles(1,4,board.getTile(1,3));
         */
    }

}
