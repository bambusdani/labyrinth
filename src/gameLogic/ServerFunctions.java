package gameLogic;

/**
 * Created by rehan on 19.08.16.
 */
public class ServerFunctions {


    //todo noch nicht buttonIdabhängig muss in switch rein
    public Board insertTile (int buttonID, Board board){

        Tiles tmpTile = board.getNextTile();
        board.setNextTile(board.getTile(1,6));

        for (int i = 6; i > 0 ; i--) {

            board.setTiles(1,i,board.getTile(1,i-1));
        }
        board.setTiles(1,0,tmpTile);





        return board;
    }



    /*******************************************************************************************************************
     *
     */
    //from old gameFunctions
    public boolean[] possibleArrowInsertions = {true, true, true, true, true, true, true, true, true, true, true, true};


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
    public void placeNextStoneInMaze(int arrowButtonID, Board boardFromClient, Tiles tmpStorageTile){

        if(isArrowMoveAllowed(arrowButtonID)){
        //if(true){
            System.out.println("ffffffffff");
            switch (arrowButtonID){
                case 0:
                    System.out.println("id=0");
                    tmpStorageTile = boardFromClient.getTile(1, 6);
                    //move all tiles one forward
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(1, index, boardFromClient.getTile(1, index - 1));
                    }
                    //place on the first spot the next tile
                    boardFromClient.setTiles(1, 0, boardFromClient.getNextTile());
                    //place the tmpStorageTile
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 1:
                    tmpStorageTile = boardFromClient.getTile(3, 6);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(3, index, boardFromClient.getTile(3, index - 1));
                    }
                    boardFromClient.setTiles(3, 0, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 2:
                    tmpStorageTile = boardFromClient.getTile(5, 6);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(5, index, boardFromClient.getTile(5, index - 1));
                    }
                    boardFromClient.setTiles(5, 0, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);


                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 8:
                    tmpStorageTile = boardFromClient.getTile(1, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(1, index, boardFromClient.getTile(1, index + 1));
                    }
                    boardFromClient.setTiles(1, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 7:
                    tmpStorageTile = boardFromClient.getTile(3, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(3, index, boardFromClient.getTile(3, index + 1));
                    }
                    boardFromClient.setTiles(3, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 6:
                    tmpStorageTile = boardFromClient.getTile(5, 0);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(5, index, boardFromClient.getTile(5, index + 1));
                    }
                    boardFromClient.setTiles(5, 6, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 11:
                    tmpStorageTile = boardFromClient.getTile(6, 1);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 1, boardFromClient.getTile(index - 1, 1));
                    }
                    boardFromClient.setTiles(0, 1, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 10:
                    tmpStorageTile = boardFromClient.getTile(6, 3);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 3, boardFromClient.getTile(index - 1, 3));
                    }
                    boardFromClient.setTiles(0, 3, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 9:

                    tmpStorageTile = boardFromClient.getTile(6, 5);
                    for (int index = 6; index > 0; index--) {
                        boardFromClient.setTiles(index, 5, boardFromClient.getTile(index - 1, 5));
                    }
                    boardFromClient.setTiles(0, 5, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 3:
                    tmpStorageTile = boardFromClient.getTile(0, 1);

                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 1, boardFromClient.getTile(index + 1, 1));
                    }

                    boardFromClient.setTiles(6, 1, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 4:
                    tmpStorageTile = boardFromClient.getTile(0, 3);

                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 3, boardFromClient.getTile(index + 1, 3));
                    }
                    boardFromClient.setTiles(6, 3, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;

                case 5:
                    tmpStorageTile = boardFromClient.getTile(0, 5);
                    for (int index = 0; index < 6; index++) {
                        boardFromClient.setTiles(index, 5, boardFromClient.getTile(index + 1, 5));
                    }
                    boardFromClient.setTiles(6, 5, boardFromClient.getNextTile());
                    boardFromClient.setNextTile(tmpStorageTile);

                    //boardFromClient = movePlayerIfStoneIsPlacedInMaze(boardFromClient,  arrowButtonID);

                    break;
            }
        }

    }
    //==================================================================================================================


    public void setRotation(int rot, Board board){
        board.getTile(0,0).setRotation(rot);
    }


    public void setSpecialTile (Board board){

        board.setTiles(1,1,board.getTile(0,0));



    }

}
