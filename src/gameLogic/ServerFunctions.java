package gameLogic;

/**
 * Created by rehan on 19.08.16.
 */
public class ServerFunctions {

    /**
     * Function inserts Next Tile and sets new Next Tile
     * */
    public void insertTile(int buttonID,Board board) {
        System.out.println("buttonID: " + buttonID);
        if(buttonID==0){

                //board.setTiles(6,6,board.getNextTile());

                board.setNextTile(board.getTile(1,6));
                board.setTiles(1,6,board.getTile(1,5));
                board.setTiles(1,5,board.getTile(1,4));
                board.setTiles(1,4,board.getTile(1,3));
                board.setTiles(1,3,board.getTile(1,2));
                board.setTiles(1,2,board.getTile(1,1));
                board.setTiles(1,1,board.getTile(1,0));

                board.setTiles(1,0,board.getNextTile());




        }else{
            System.out.println("Keine korrekte buttonID gefunden");
        }

        /*tmpTile = board.getTile(1,6);
        board.setTiles(1,6,board.getTile(1,5));
        board.setTiles(1,5,board.getTile(1,4));
        board.setTiles(1,4,board.getTile(1,3));
        board.setTiles(1,0,tmpTile);*/
    }

}
