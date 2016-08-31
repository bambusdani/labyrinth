package gameLogic;

import java.util.ArrayList;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class ServerFunctions {

    /**
     * Attributes
     */
    private ArrayList<Boolean> activePlayers = new ArrayList<Boolean>();
    private ArrayList<Boolean> playersTurn = new ArrayList<Boolean>();
    private boolean[][] visited = new boolean[7][7];
    private Tiles tmpTile;
    private boolean[] possibleArrowInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};

    /**********************
     * Rotates the nextTile
     * @param rotation
     * @param board
     */
    public void rotNextTile (int rotation, Board board){
        //Set rotation of the tile -> rotation > 360° -> set to 0°
        if(board.getNextTile().getRotation()+rotation<=270){
            board.getNextTile().setRotation(board.getNextTile().getRotation()+rotation);
        }else{
            board.getNextTile().setRotation(0);
        }
        board.getNextTile().getShape().setImage(board.getNextTile().getShape().rotateImage(90));
        board.getNextTile().getShape().setRotatedPossiblePath(board.getNextTile().getShape().getPossiblePaths());
    }

    /****************+++++***********************************************************
     * Insert(push) function, nextTile is inserted to gameField + new nextTile is set
     * @param buttonID
     * @param board
     */
    public void insertTile (int buttonID, Board board){
        if(isArrowMoveAllowed(buttonID)){
            switch (buttonID){
                case 0:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(1,6));
                    for (int i = 6; i > 0 ; i--) {
                        board.setTiles(1,i,board.getTile(1,i-1));
                    }
                    board.setTiles(1,0,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 1:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(3,6));
                    for (int i = 6; i > 0 ; i--) {
                        board.setTiles(3,i,board.getTile(3,i-1));
                    }
                    board.setTiles(3,0,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 2:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(5,6));
                    for (int i = 6; i > 0 ; i--) {
                        board.setTiles(5,i,board.getTile(5,i-1));
                    }
                    board.setTiles(5,0,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 3:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(0,1));
                    for (int i = 0; i < 6; i++) {
                        board.setTiles(i,1,board.getTile(i+1,1));
                    }
                    board.setTiles(6,1,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 4:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(0,3));
                    for (int i = 0; i < 6; i++) {
                        board.setTiles(i,3,board.getTile(i+1,3));
                    }
                    board.setTiles(6,3,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 5:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(0,5));
                    for (int i = 0; i < 6; i++) {
                        board.setTiles(i,5,board.getTile(i+1,5));
                    }
                    board.setTiles(6,5,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 6:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(5,0));
                    for (int i = 0; i < 6 ; i++) {
                        board.setTiles(5, i, board.getTile(5,i+1));
                    }
                    board.setTiles(5,6,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 7:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(3,0));
                    for (int i = 0; i < 6 ; i++) {
                        board.setTiles(3, i, board.getTile(3,i+1));
                    }
                    board.setTiles(3,6,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 8:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(1,0));
                    for (int i = 0; i < 6 ; i++) {
                        board.setTiles(1, i, board.getTile(1,i+1));
                    }
                    board.setTiles(1,6,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 9:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(6,5));
                    for (int i = 6; i > 0; i--) {
                        board.setTiles(i,5,board.getTile(i-1,5));
                    }
                    board.setTiles(0,5,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 10:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(6,3));
                    for (int i = 6; i > 0; i--) {
                        board.setTiles(i,3,board.getTile(i-1,3));
                    }
                    board.setTiles(0,3,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;

                case 11:
                    tmpTile = board.getNextTile();
                    board.setNextTile(board.getTile(6,1));
                    for (int i = 6; i > 0; i--) {
                        board.setTiles(i,1,board.getTile(i-1,1));
                    }
                    board.setTiles(0,1,tmpTile);
                    movePlayerIfStoneIsPlacedInMaze(board,  buttonID);
                    break;
                default:
                    System.err.println("Button ID ist nicht Korrekt");
                    break;
            }
        }else{
            System.out.println("This insertion is not allowed");
        }
    }

    /**
     * Checks if the insertion(push) is allowed
     * @param buttonID
     * @return
     */
    public boolean isArrowMoveAllowed (int buttonID){

        switch (buttonID){
            case 0:
                if(possibleArrowInsertions[8]){
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

    /**
     * Players is moved if the move is valid
     * @param board
     * @param playerID
     * @param buttonPositionPressed
     * @return
     */
    public Board movePlayerIfMoveIsPossible(Board board, int playerID, Position buttonPositionPressed){
        if(checkMazeIfMoveIsPossible(board, buttonPositionPressed, playerID)){
            board.getPlayer(playerID).setActualPosition(buttonPositionPressed);
        }
        else{
            System.err.println("move not possible");
        }
        return board;
    }

    /**
     * checks wether move to button pressed position is possible
     * Visited is set to remember already visited tiles -> no endless loop
     * @param board
     * @param buttonPositionPressed
     * @param playerID
     * @return
     */
    public boolean checkMazeIfMoveIsPossible(Board board, Position buttonPositionPressed, int playerID){
        // setzt alle Werte auf false -> wurde noch nicht besucht
        for (int p=0; p<7;p++){
            for (int q= 0;q<7;q++){
                this.visited[p][q]= false;
            }
        }
        int actualPosX = board.getPlayer(playerID).getAcutalPosition().getX();
        int actualPosY = board.getPlayer(playerID).getAcutalPosition().getY();
        return isMovePossible(board, buttonPositionPressed, actualPosX,actualPosY);
    }

    /**
     * isMovePossible
     * goes trough the maze
     * places booleans in visited and walks on the maze
     * @param board
     * @param buttonPositionPressed
     * @param tilePositionX -> actual x-position of the player on the tiles
     * @param tilePositionY -> actual y-position of the player on the tiles
     * @return
     */
    public boolean isMovePossible (Board board, Position buttonPositionPressed,int tilePositionX , int tilePositionY){

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
            if(isMovePossible(board ,buttonPositionPressed,tilePositionX - 1 , tilePositionY)){
                return true;
            }
        }
        // nach rechts gehen
        if((tilePositionX != 6) && (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[1]) && (board.getallTiles()[tilePositionX + 1][tilePositionY].getShape().getPossiblePaths()[3]) ){
            if(isMovePossible(board,buttonPositionPressed,tilePositionX + 1 , tilePositionY)){
                return true;
            }
        }
        // nach oben gehen
        if( (tilePositionY != 0)&& (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[0]) && (board.getallTiles()[tilePositionX][tilePositionY - 1].getShape().getPossiblePaths()[2]) ){
            if(isMovePossible(board,buttonPositionPressed,tilePositionX , tilePositionY - 1)){
                return true;
            }
        }
        // nach unten gehen
        if((tilePositionY != 6) && (board.getallTiles()[tilePositionX][tilePositionY].getShape().getPossiblePaths()[2]) && (board.getallTiles()[tilePositionX][tilePositionY + 1].getShape().getPossiblePaths()[0]) ){
            if(isMovePossible(board,buttonPositionPressed,tilePositionX , tilePositionY + 1)){
                return true;
            }
        }
        // Wenn kein Fall zutrift wird false zurückgegeben
        return false;
    }

    /**
     * players are moved with tile by insertion
     * @param board
     * @param arrowNumber
     * @return
     */
    public Board movePlayerIfStoneIsPlacedInMaze(Board board, int arrowNumber){
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

            // for arrow 0 1 2 top
            if (((arrowNumber==0) || (arrowNumber == 1) || (arrowNumber == 2)) && (board.getPlayer(playerID).getAcutalPosition().getX() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getY() == 6) {
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, 0));
                } else {
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, board.getPlayer(playerID).getAcutalPosition().getY() + 1));
                }
            }
            //----------------------------
            // for arrow  6 7 8 bottom
            if (((arrowNumber == 6) || (arrowNumber == 7) || (arrowNumber == 8)) && (board.getPlayer(playerID).getAcutalPosition().getX() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getY() == 0) {
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, 6));
                } else {
                    board.getPlayer(playerID).setActualPosition(new Position(rownumber, board.getPlayer(playerID).getAcutalPosition().getY() - 1));
                }
            }
            //----------------------------
            // for arrow  3 4 5 right
            if (((arrowNumber == 3) || (arrowNumber == 4) || (arrowNumber == 5)) && (board.getPlayer(playerID).getAcutalPosition().getY() == rownumber)) {
                if (board.getPlayer(playerID).getAcutalPosition().getX() == 0) {
                    board.getPlayer(playerID).setActualPosition(new Position(6,rownumber));
                } else {
                    board.getPlayer(playerID).setActualPosition(new Position(board.getPlayer(playerID).getAcutalPosition().getX() - 1, rownumber));
                }
            }
            //----------------------------
            // for arrow  9 10 11 right
            if (((arrowNumber == 9) || (arrowNumber == 10) || (arrowNumber == 11)) && (board.getPlayer(playerID).getAcutalPosition().getY() == rownumber)) {
                //reset color
                if (board.getPlayer(playerID).getAcutalPosition().getX() == 6) {
                    board.getPlayer(playerID).setActualPosition(new Position(0,rownumber));
                } else {
                    board.getPlayer(playerID).setActualPosition(new Position(board.getPlayer(playerID).getAcutalPosition().getX() + 1, rownumber));
                }
            }
        }
        return board;
    }

    /**
     * IsPlayerGettingPoints
     * Funktion, welche überprüft, ob der Spieler auf dem gesuchten Symbol steht. Falls ja wird ein Punkt dazu addiert
     * return 0 = kein Punkt
     * Return 1 = Punkt
     * return 2 = spiel beenden
     */
    public int isPlayerGettingPoints(Board board, int playerID){
        if(board.getPlayer(playerID).getCreaturesNeeded().get(0).getCreature() == board.getTile(board.getPlayer(playerID).getAcutalPosition().getX(),board.getPlayer(playerID).getAcutalPosition().getY()).getShape().getCreature()){
            //if they are equal -> first element is deketed
            board.getPlayer(playerID).getCreaturesNeeded().remove(0);
            if(board.getPlayer(playerID).getCreaturesNeeded().isEmpty()){
                //if list of NeededCreatures is empty -> player has won
                return 2;
            }
            else{
                //increase value of player
                board.getPlayer(playerID).setScore(board.getPlayer(playerID).getScore() + 1);
                return 1;
            }
        }
        else{
            //no points
            return 0;
        }
    }

    /**
     * ID is given back, to check which button must be disabled
     * @param buttonID
     * @return
     */
    public int disabledArrowID(int buttonID){
        switch (buttonID){
            case 0:
                return 8;
            case 1:
                return 7;
            case 2:
                return 6;
            case 3:
                return 11;
            case 4:
                return 10;
            case 5:
                return 9;
            case 6:
                return 2;
            case 7:
                return 1;
            case 8:
                return 0;
            case 9:
                return 5;
            case 10:
                return 4;
            case 11:
                return 3;
            default:
                System.err.println("FEHLER BEIM ERKENNEN DES BUTTONS");
                return 0;
        }
    }
}