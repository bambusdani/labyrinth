package gameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * Includes all the Functions which are used in the GUI
 */
public class GameFunctions {
    //wird für checkMazeIfMoveISPossible benötigt
    boolean[][] visited = new boolean[7][7];

    //placeStoneWithArrows
    //Im Uhrzeigersinn den Buttons(einschub/pfeilbuttons) zugewiesen
    public boolean[] possibleArrowInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};
    Tiles tmpStorageTile;


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
     * TODO Überschreibt bisher noch die anderen Ränder -> gehört eigentlich zu GUI nicht Server
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
            // Für Pfeil  6 7 8 bottom
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
            // Für Pfeil  3 4 5 right
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
            // Für Pfeil  9 10 11 right
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


    /**
     * IsPlayerGettingPoints
     * Funktion, welche überprüft, ob der Spieler auf dem gesuchten Symbol steht. Falls ja wird ein Punkt dazu addiert
     * return 0 = kein Punkt
     * Return 1 = Punkt
     * return 2 = spiel beenden
     */
    public int isPlayerGettingPoints(Board board, int playerID){

        if(board.getPlayer(playerID).getCreaturesNeeded().get(0).getCreature() == board.getTile(board.getPlayer(playerID).getAcutalPosition().getX(),board.getPlayer(playerID).getAcutalPosition().getY()).getShape().getCreature()){
            //falls sie identisch sind
            // Punkt für den Spieler

            //erstes Element wird entfernt
            board.getPlayer(playerID).getCreaturesNeeded().remove(0);

            if(board.getPlayer(playerID).getCreaturesNeeded().isEmpty()){
                //falls die Liste der NeededCreatures leer ist -> Spieler hat gewonnen
                return 2;
            }
            else{
                System.out.println(board.getPlayer(playerID).getCreaturesNeeded().get(0).getCreature());
                //Wert beim Spieler um 1 erhöhen
                board.getPlayer(playerID).setScore(board.getPlayer(playerID).getScore() + 1);
                return 1;
            }
        }
        else{
           // kein Punkt erziehlt
            return 0;
        }
    }
    //==================================================================================================================

    /**
     * isArrowMoveAllowed
     * looks if it is allowed to place a new stone on the field.
     * When one turn before a stone was placed on the opposite side it´s not allowed to place it
     * @param buttonID
     * @return boolean
     */



    public boolean isArrowMoveAllowed (int buttonID){

        switch (buttonID){
            case 0:
                if(possibleArrowInsertions[8]){
                    System.err.println("is allowed");
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[0]= false;
                    return true;
                }
                break;
            case 1:
                if(possibleArrowInsertions[7]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[1]= false;
                    return true;
                }
                break;
            case 2:
                if(possibleArrowInsertions[6]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[2]= false;
                    return true;
                }
                break;
            case 3:
                if(possibleArrowInsertions[11]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[3]= false;
                    return true;
                }
                break;
            case 4:
                if(possibleArrowInsertions[10]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[4]= false;
                    return true;
                }
                break;
            case 5:
                if(possibleArrowInsertions[9]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[5]= false;
                    return true;
                }
                break;
            case 6:
                if(possibleArrowInsertions[2]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[6]= false;
                    return true;
                }
                break;
            case 7:
                if(possibleArrowInsertions[1]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[7]= false;
                    return true;
                }
                break;
            case 8:
                if(possibleArrowInsertions[0]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[8]= false;
                    return true;
                }
                break;
            case 9:
                if(possibleArrowInsertions[5]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[9]= false;
                    return true;
                }
                break;
            case 10:
                if(possibleArrowInsertions[4]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[10]= false;
                    return true;
                }
                break;
            case 11:
                if(possibleArrowInsertions[3]){
                    resetAllPossibleArrowInsertrions();
                    possibleArrowInsertions[11]= false;
                    return true;
                }
                break;
            default:
                System.err.println("Fehler beim Arrow auswählen.");
                break;
        }
        return false;
    }

    // is used in isArrowMoveAllowed
    public void resetAllPossibleArrowInsertrions(){
        for(int index = 0; index < possibleArrowInsertions.length; index++) {
            possibleArrowInsertions[index] = true;
        }
    }

    /**
     *
     * @param arrowButtonID
     * @param boardFromClient
     * @return
     */

    //board from client -> later from server
    public Board placeNextStoneInMaze(int arrowButtonID, Board boardFromClient){

        if(isArrowMoveAllowed(arrowButtonID)){

            switch (arrowButtonID){

                case 0:
                    tmpStorageTile = boardFromClient.getTile(1, 6);
                    //move all tiles one forward
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(1, index, boardFromClient.getTile(1, index - 1));
                        //Ausgabe -> muss in GUI
                        //boardSquares[1][index].setIcon(board.getTile(1, index).getShape().getImage());
                    }
                    // TODO move all Players on the line if you place a stone

                    //place on the first spot the next tile
                    boardFromClient.setTiles(1, 0, boardFromClient.getNextTile());
                    //place the tmpStorageTile
                    boardFromClient.setNextTile(tmpStorageTile);

                    break;


            }












        }

            return boardFromClient;

    }

}
