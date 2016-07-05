package gameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * Includes all the Functions which are used in the GUI
 */
public class GameFunctions {
    //wird für checkMazeIfMoveISPossible benötigt
    boolean[][] visited = new boolean[7][7];

    //konstruktor
    public GameFunctions(){

    }


    /**=================================================================================================================
     *
     * printNewPlayerPossition
     * JButton[][] allButtons , Player player, Positon buttonPositionPressed, int TilePositionX, int TilePositionY, Tiles[][] tiles
     * entfernt den bisherigen Rand und fügt an der neuen Stelle einen an
     * ruft die Funktion checkMazeIfMovePossible auf
     *
     * TODO Überschreibt bisher noch die anderen Ränder
     *
     * ==============================================================================================================**/
    public void movePlayerIfMovePossible(JButton[][] allButtons, Player[] allPlayer, int playerID, Position buttonPositionPressed, int tilePositionX, int tilePositionY, Tiles[][] tiles){
        if(checkMazeIfMoveIsPossible(tiles, buttonPositionPressed, tilePositionX,tilePositionY)){

            //setze Border auf default
            allButtons[allPlayer[playerID].getAcutalPosition().getX()][allPlayer[playerID].getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
            //setze neuen Rand
            allButtons[buttonPositionPressed.getX()][buttonPositionPressed.getY()].setBorder(BorderFactory.createMatteBorder(3,3,3,3, allPlayer[playerID].getColor()));
            // neue Koordinaten im Player setzen
            allPlayer[playerID].setActualPosition(buttonPositionPressed);
        }
        else{
            System.err.println("Zug ist nicht möglich");
        }
    }
    /**================================================================================
     * function checkMazeIfMoveIsPossible
     * tiles = alle Tiles
     * buttonPositionPressed = Position wo der Button gedrückt wurde
     * tilePositionX / Y = jetzige Position des Spielers
     * visited = merkt sich wo man bereits war sonst durchläuft es eine dauerschleife
     * ================================================================================**/

    public boolean checkMazeIfMoveIsPossible(Tiles[][] tiles, Position buttonPositionPressed, int tilePositionX, int tilePositionY){
        // setzt alle Werte auf false -> wurde noch nicht besucht
        for (int p=0; p<7;p++){
            for (int q= 0;q<7;q++){
                this.visited[p][q]= false;
            }
        }
        return isMovePossible(tiles, buttonPositionPressed, tilePositionX, tilePositionY);
    }

    public boolean isMovePossible (Tiles[][] tiles, Position buttonPositionPressed, int tilePositionX, int tilePositionY){

        //Falls der Punkt erreichbar ist
        if( buttonPositionPressed.getX() == tilePositionX && buttonPositionPressed.getY() == tilePositionY){
            return true;
        }
        // wenn man bereits an dem Ort war (already visited)
        if( this.visited[tilePositionX][tilePositionY] ){
            return false;
        }
        // Feld auf bereits besucht setzen
        this.visited[tilePositionX][tilePositionY]= true;

        // nach links gehen
        if((tilePositionX != 0) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[3]) && (tiles[tilePositionX - 1][tilePositionY].getShape().getPossiblePaths()[1]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX - 1 , tilePositionY)){
                return true;
            }
        }
        // nach rechts gehen
        if((tilePositionX != 6) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[1]) && (tiles[tilePositionX + 1][tilePositionY].getShape().getPossiblePaths()[3]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX + 1 , tilePositionY)){
                return true;
            }
        }
        // nach oben gehen
        if( (tilePositionY != 0)&& (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[0]) && (tiles[tilePositionX][tilePositionY - 1].getShape().getPossiblePaths()[2]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX , tilePositionY - 1)){
                return true;
            }
        }
        // nach unten gehen
        if((tilePositionY != 6) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[2]) && (tiles[tilePositionX][tilePositionY + 1].getShape().getPossiblePaths()[0]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX , tilePositionY + 1)){
                return true;
            }
        }
        // Wenn kein Fall zutrift wird false zurückgegeben
        return false;
    }


    /**
     *  Move player on the maze if a stone is placed in the same line
     *  int arrowNumber zählt im Uhrzeigersinn von 0 bis 11
     *  TODO Überschreibt noch andere Ränder -> Spieler sind noch da aber man sieht sie nicht
     */
    public void movePlayerIfMazeIsChanged(Player[] allPlayer, JButton allButtons[][], int arrowNumber){
        int rownumber = 0;
        switch (arrowNumber){
            //top and bottom
            case 0:
            case 8:
                rownumber = 1;
                break;
            case 1:
            case 7:
                rownumber = 3;
                break;
            case 2:
            case 6:
                rownumber = 5;
                break;
            //right and left
            case 3:
            case 11:
                rownumber = 1;
                break;
            case 4:
            case 10:
                rownumber = 3;
                break;
            case 5:
            case 9:
                rownumber = 5;
                break;


        }

        for (int playerID = 0 ; playerID < allPlayer.length ; playerID ++) {

            // Für Pfeil 0 1 2 top
            if (((arrowNumber==0) || (arrowNumber == 1) || (arrowNumber == 2)) && (allPlayer[playerID].getAcutalPosition().getX() == rownumber)) {
                //setze Farbe zurück
                allButtons[rownumber][allPlayer[playerID].getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                if (allPlayer[playerID].getAcutalPosition().getY() == 6) {
                    // aus dem spielfeld schieben
                    allPlayer[playerID].setActualPosition(new Position(rownumber, 0));
                    allButtons[rownumber][0].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                } else {
                    //eins weiter schieben
                    allPlayer[playerID].setActualPosition(new Position(rownumber, allPlayer[playerID].getAcutalPosition().getY() + 1));
                    allButtons[rownumber][allPlayer[playerID].getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                }
            }
            //----------------------------
            // Für Pfei  6 7 8 bottom
            if (((arrowNumber == 6) || (arrowNumber == 7) || (arrowNumber == 8)) && (allPlayer[playerID].getAcutalPosition().getX() == rownumber)) {
                //setze Farbe zurück
                allButtons[rownumber][allPlayer[playerID].getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                if (allPlayer[playerID].getAcutalPosition().getY() == 0) {
                    // aus dem spielfeld schieben
                    allPlayer[playerID].setActualPosition(new Position(rownumber, 6));
                    allButtons[rownumber][6].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                } else {
                    //eins weiter schieben
                    allPlayer[playerID].setActualPosition(new Position(rownumber, allPlayer[playerID].getAcutalPosition().getY() - 1));
                    allButtons[rownumber][allPlayer[playerID].getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                }
            }
            //----------------------------
            // Für Pfei  3 4 5 right
            if (((arrowNumber == 3) || (arrowNumber == 4) || (arrowNumber == 5)) && (allPlayer[playerID].getAcutalPosition().getY() == rownumber)) {
                //setze Farbe zurück
                allButtons[allPlayer[playerID].getAcutalPosition().getX()][rownumber].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                if (allPlayer[playerID].getAcutalPosition().getX() == 0) {
                    // aus dem spielfeld schieben
                    allPlayer[playerID].setActualPosition(new Position(6,rownumber));
                    allButtons[6][rownumber].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                } else {
                    //eins weiter schieben
                    allPlayer[playerID].setActualPosition(new Position(allPlayer[playerID].getAcutalPosition().getX() - 1, rownumber));
                    allButtons[allPlayer[playerID].getAcutalPosition().getX()][rownumber].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                }
            }
            //----------------------------
            // Für Pfei  9 10 11 right
            if (((arrowNumber == 9) || (arrowNumber == 10) || (arrowNumber == 11)) && (allPlayer[playerID].getAcutalPosition().getY() == rownumber)) {
                //setze Farbe zurück
                allButtons[allPlayer[playerID].getAcutalPosition().getX()][rownumber].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                if (allPlayer[playerID].getAcutalPosition().getX() == 6) {
                    // aus dem spielfeld schieben
                    allPlayer[playerID].setActualPosition(new Position(0,rownumber));
                    allButtons[0][rownumber].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                } else {
                    //eins weiter schieben
                    allPlayer[playerID].setActualPosition(new Position(allPlayer[playerID].getAcutalPosition().getX() + 1, rownumber));
                    allButtons[allPlayer[playerID].getAcutalPosition().getX()][rownumber].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, allPlayer[playerID].getColor()));
                }
            }

        }

    }
    //==================================================================================================================



}
