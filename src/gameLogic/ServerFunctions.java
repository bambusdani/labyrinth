package gameLogic;

import java.util.ArrayList;

/**
 * Created by rehan on 19.08.16.
 */
public class ServerFunctions {

    private ArrayList<Boolean> activePlayers = new ArrayList<Boolean>();
    private ArrayList<Boolean> playersTurn = new ArrayList<Boolean>();
    private boolean[][] visited = new boolean[7][7];
    private Tiles tmpTile;

    /**=================================================================================
     * Rotation of next Tile by Pressing the Rotate Button
     * */
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

    /**==================================================================================
     * PUSH Functions
     * */
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
    /**========================================================================================*/



    /*******************************************************************************************************************
     *Is the insertion(PUSH) allowed
     */

    public boolean[] possibleArrowInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};
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






    public Board movePlayerIfMoveIsPossible(Board board, int playerID, Position buttonPositionPressed){
        if(checkMazeIfMoveIsPossible(board, buttonPositionPressed, playerID)){
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
    //==================================================================================================================

    /**
     * isMovePossible
     * @param board
     * @param buttonPositionPressed
     * @param tilePositionX -> actual x-position of the player on the tiles
     * @param tilePositionY -> actual y-position of the player on the tiles
     * @return
     * goes trough the maze
     * places booleans in visited and walks on the maze
     *
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







    /*******************************************************************************************************************
     * //TODO
     * IsPlayerGettingPoints
     * Funktion, welche überprüft, ob der Spieler auf dem gesuchten Symbol steht. Falls ja wird ein Punkt dazu addiert
     * return 0 = kein Punkt
     * Return 1 = Punkt
     * return 2 = spiel beenden
     */
    public int isPlayerGettingPoints(Board board, int playerID){

        System.out.println("player is getting points");

        if(board.getPlayer(playerID).getCreaturesNeeded().get(0).getCreature() == board.getTile(board.getPlayer(playerID).getAcutalPosition().getX(),board.getPlayer(playerID).getAcutalPosition().getY()).getShape().getCreature()){
            //falls sie identisch sind
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



}