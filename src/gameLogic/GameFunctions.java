package gameLogic;

import gui.playGround;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Includes all the Functions which are used in the GUI
 */
public class GameFunctions {

    private ArrayList<Boolean> activePlayers = new ArrayList<Boolean>();
    private ArrayList<Boolean> playersTurn = new ArrayList<Boolean>();

    //wird für checkMazeIfMoveISPossible benötigt
    boolean[][] visited = new boolean[7][7];

    //placeStoneWithArrows
    //Im Uhrzeigersinn den Buttons(einschub/pfeilbuttons) zugewiesen
    public boolean[] possibleArrowInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};
    Tiles tmpStorageTile;

    private Board board;
    private playGround playGroundPlayer1;

    //konstruktor
    public GameFunctions(){
        board = new Board();
        playGroundPlayer1 = new playGround(board, "localhost", "Rehan", 0);
        playGroundPlayer1.listen();



    }

    /**
     * movePlayerIfMoveIsPossible
     * @param playerID
     * @param buttonPositionPressed
     * @return
     * checks if the move is possible and returns a whole board with changed positions
     * uses: checkMazeIfMoveIsPossible
     */
    public Board movePlayerIfMoveIsPossible(int playerID, Position buttonPositionPressed){
        if(checkMazeIfMoveIsPossible(buttonPositionPressed, playerID)){
            board.getPlayer(playerID).setActualPosition(buttonPositionPressed);
        }
        else{
            System.err.println("move not possible");
        }
        return board;
    }
    //------------------------------------------------------------------------------------------------------------------

    /**================================================================================
     * function checkMazeIfMoveIsPossible
     * buttonPositionPressed = Position wo der Button gedrückt wurde
     * visited = merkt sich wo man bereits war sonst durchläuft es eine dauerschleife
     * ================================================================================**/

    public boolean checkMazeIfMoveIsPossible(Position buttonPositionPressed, int playerID){
        // setzt alle Werte auf false -> wurde noch nicht besucht
        for (int p=0; p<7;p++){
            for (int q= 0;q<7;q++){
                this.visited[p][q]= false;
            }
        }
        int actualPosX = board.getPlayer(playerID).getAcutalPosition().getX();
        int actualPosY = board.getPlayer(playerID).getAcutalPosition().getY();
        return isMovePossible(buttonPositionPressed, actualPosX,actualPosY);
    }
    //==================================================================================================================

    /**
     * isMovePossible
     * @param buttonPositionPressed
     * @param tilePositionX -> actual x-position of the player on the tiles
     * @param tilePositionY -> actual y-position of the player on the tiles
     * @return
     * goes trough the maze
     * places booleans in visited and walks on the maze
     *
     */
    public boolean isMovePossible (Position buttonPositionPressed,int tilePositionX , int tilePositionY){

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
        if((tilePositionX != 0) && (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[3]) && (board.getallTiles()[tilePositionX - 1][tilePositionY].getShape().getPossiblePaths()[1]) ){
            if(isMovePossible(buttonPositionPressed,tilePositionX - 1 , tilePositionY)){
                return true;
            }
        }
        // nach rechts gehen
        if((tilePositionX != 6) && (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[1]) && (board.getallTiles()[tilePositionX + 1][tilePositionY].getShape().getPossiblePaths()[3]) ){
            if(isMovePossible(buttonPositionPressed,tilePositionX + 1 , tilePositionY)){
                return true;
            }
        }
        // nach oben gehen
        if( (tilePositionY != 0)&& (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[0]) && (board.getallTiles()[tilePositionX][tilePositionY - 1].getShape().getPossiblePaths()[2]) ){
            if(isMovePossible(buttonPositionPressed,tilePositionX , tilePositionY - 1)){
                return true;
            }
        }
        // nach unten gehen
        if((tilePositionY != 6) && (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[2]) && (board.getallTiles()[tilePositionX][tilePositionY + 1].getShape().getPossiblePaths()[0]) ){
            if(isMovePossible(buttonPositionPressed,tilePositionX , tilePositionY + 1)){
                return true;
            }
        }
        // Wenn kein Fall zutrift wird false zurückgegeben
        return false;
    }
    //==================================================================================================================


    /**
     * IsPlayerGettingPoints
     * Funktion, welche überprüft, ob der Spieler auf dem gesuchten Symbol steht. Falls ja wird ein Punkt dazu addiert
     * return 0 = kein Punkt
     * Return 1 = Punkt
     * return 2 = spiel beenden
     */
    public int isPlayerGettingPoints(int playerID){

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

    /**==========================================================================================
     * isArrowMoveAllowed
     * looks if it is allowed to place a new stone on the field.
     * When one turn before a stone was placed on the opposite side it´s not allowed to place it
     * @param buttonID
     * @return boolean
     * ==========================================================================================
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
    //==================================================================================================================

    /**
     * resetAllPossibleArrowInsertrions
     * resets the variable possibleArrowInsertions
     * is used in isArrowMoveAllowed
     */
    public void resetAllPossibleArrowInsertrions(){
        for(int index = 0; index < possibleArrowInsertions.length; index++) {
            possibleArrowInsertions[index] = true;
        }
    }
    //==================================================================================================================

    /**
     * placeNextStoneInMaze
     * Function places a stone in the maze if it´s possible
     * returns a whole board
     * @param arrowButtonID
     * @param boardFromClient
     * @return boardFromClient
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
                    }
                    //place on the first spot the next tile
                    boardFromClient.setTiles(1, 0, boardFromClient.getNextTile());
                    //place the tmpStorageTile
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 1:
                    tmpStorageTile = boardFromClient.getTile(3, 6);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(3, index, boardFromClient.getTile(3, index - 1));
                    }
                    boardFromClient.setTiles(3, 0, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 2:
                    tmpStorageTile = boardFromClient.getTile(5, 6);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(5, index, boardFromClient.getTile(5, index - 1));
                    }
                    boardFromClient.setTiles(5, 0, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);


                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 8:
                    tmpStorageTile = boardFromClient.getTile(1, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(1, index, boardFromClient.getTile(1, index + 1));
                    }
                    boardFromClient.setTiles(1, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 7:
                    tmpStorageTile = boardFromClient.getTile(3, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(3, index, boardFromClient.getTile(3, index + 1));
                    }
                    boardFromClient.setTiles(3, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 6:
                    tmpStorageTile = boardFromClient.getTile(5, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(5, index, boardFromClient.getTile(5, index + 1));
                    }
                    boardFromClient.setTiles(5, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 11:
                    tmpStorageTile = boardFromClient.getTile(6, 1);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 1, boardFromClient.getTile(index - 1, 1));
                    }
                    boardFromClient.setTiles(0, 1, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 10:
                    tmpStorageTile = boardFromClient.getTile(6, 3);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 3, boardFromClient.getTile(index - 1, 3));
                    }
                    boardFromClient.setTiles(0, 3, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 9:

                    tmpStorageTile = boardFromClient.getTile(6, 5);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 5, boardFromClient.getTile(index - 1, 5));
                    }
                    boardFromClient.setTiles(0, 5, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 3:
                    tmpStorageTile = boardFromClient.getTile(0, 1);

                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 1, boardFromClient.getTile(index + 1, 1));
                    }

                    boardFromClient.setTiles(6, 1, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 4:
                    tmpStorageTile = boardFromClient.getTile(0, 3);

                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 3, boardFromClient.getTile(index + 1, 3));
                    }
                    boardFromClient.setTiles(6, 3, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;

                case 5:
                    tmpStorageTile = boardFromClient.getTile(0, 5);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 5, boardFromClient.getTile(index + 1, 5));
                    }
                    boardFromClient.setTiles(6, 5, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    boardFromClient = movePlayerIfStoneIsPlacedInMaze(arrowButtonID);

                    break;
            }
        }
            return boardFromClient;
    }
    //==================================================================================================================


    /**
     *  Move player on the maze if a stone is placed in the same line
     *  int arrowNumber counts clockwise from 0 to 11
     */
    public Board movePlayerIfStoneIsPlacedInMaze(int arrowNumber){
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

        for (int playerID = 0 ; playerID < board.getAllPlayers().length ; playerID ++) {

            // Für Pfeil 0 1 2 top
            if (((arrowNumber==0) || (arrowNumber == 1) || (arrowNumber == 2)) && (board.getPlayer(playerID).getAcutalPosition().getX() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getY() == 6) {
                    // aus dem spielfeld schieben
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, 0));
                } else {
                    //eins weiter schieben
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, board.getPlayer(playerID).getAcutalPosition().getY() + 1));
                }
            }
            //----------------------------
            // Für Pfeil  6 7 8 bottom
            if (((arrowNumber == 6) || (arrowNumber == 7) || (arrowNumber == 8)) && (board.getPlayer(playerID).getAcutalPosition().getX() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getY() == 0) {
                    // aus dem spielfeld schieben
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, 6));
                } else {
                    //eins weiter schieben
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, board.getPlayer(playerID).getAcutalPosition().getY() - 1));
                }
            }
            //----------------------------
            // Für Pfeil  3 4 5 right
            if (((arrowNumber == 3) || (arrowNumber == 4) || (arrowNumber == 5)) && (board.getPlayer(playerID).getAcutalPosition().getY() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getX() == 0) {
                    // aus dem spielfeld schieben
                    board.getPlayer(playerID).setActualPosition(new Position(6,rownumber));
                } else {
                    //eins weiter schieben
                    board.getPlayer(playerID).setActualPosition(new Position(board.getPlayer(playerID).getAcutalPosition().getX() - 1, rownumber));
                }
            }
            //----------------------------
            // Für Pfeil  9 10 11 right
            if (((arrowNumber == 9) || (arrowNumber == 10) || (arrowNumber == 11)) && (board.getPlayer(playerID).getAcutalPosition().getY() == rownumber)) {
                //setze Farbe zurück
                if (board.getPlayer(playerID).getAcutalPosition().getX() == 6) {
                    // aus dem spielfeld schieben
                    board.getPlayer(playerID).setActualPosition(new Position(0,rownumber));
                } else {
                    //eins weiter schieben
                    board.getPlayer(playerID).setActualPosition(new Position(board.getPlayer(playerID).getAcutalPosition().getX() + 1, rownumber));
                }
            }
        }
        return board;
    }
    //==================================================================================================================


    /**
     * Player Management:
     *      List only filled with active Players
     *      Number of active players are counted
     *      Number of active players created
     * */

    //only active Players are added playersTurn list
    public void addActiveToPlayersTurn(){
        //Nur die aktiven Spieler werden ausgelsen -> nur diese können Züge Machen
        for(int index = 0; index < activePlayers.size(); index++){
            if(activePlayers.get(index)){
                playersTurn.add(true);
            }
        }
    }

    //Active players added to players list
    public int numberOfActivePlayers(){
        return(playersTurn.size());
    }


    public int playersTurn(){
        for(int index = 0; index < playersTurn.size(); index++){
            if(playersTurn.get(index)){
                int playerID = index + 1;
                return playerID;
            }
        }
        return 0;
    }

    public void nextPlayersTurn(){
        for(int index = 0; index < playersTurn.size(); index++) {
            if (playersTurn.get(index)) {

                //Zug des momentanen Spielers wird beendet
                playersTurn.set(index, false);

                //Nächster Spieler ist an der Reihe
                playersTurn.set(index + 1, true);
            }
        }
    }

    //Getter und Setter
    public ArrayList<Boolean> getPlayersTurn(){
        return playersTurn;
    }






}
