package gameLogic;

public class playerManagement {

    private Board board = new Board();
    private boolean[] playersTurn = {true, false , false , false};
    private boolean isTurnValid = false;
    private Position position;

    public playerManagement(Board board, int positionI, int positionJ){
        board = this.board;
        Position position = new Position(positionI, positionJ);
        this.position = position;
    }

    public void  playerAction(){
        if(playersTurn[0] && isTurnValid){
            board.getAllPlayers()[0].setActualPosition(position);
        }
        else if(playersTurn[1] && isTurnValid){
            board.getAllPlayers()[1].setActualPosition(position);
        }
        else if(playersTurn[2] && isTurnValid){
            board.getAllPlayers()[2].setActualPosition(position);
        }
        else if(playersTurn[3] && isTurnValid){
            board.getAllPlayers()[3].setActualPosition(position);
        }

    }

}
