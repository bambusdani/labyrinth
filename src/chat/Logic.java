package chat;

import gameLogic.Board;
import gameLogic.GameFunctions;

public class Logic {
    private GameFunctions gameFunctions;

    private int playerPoints;

    public Logic() {
        this.gameFunctions = new GameFunctions();
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(Board board, int playerID) {
        this.playerPoints = gameFunctions.isPlayerGettingPoints(board, playerID);
    }
}
