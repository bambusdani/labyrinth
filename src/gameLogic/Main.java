package gameLogic;

import gui.PlayGround;

/**
 * runs the game
 * if you press host at the lobby it will create hostGame
 * if you press join at the lobby it will create joinGame
 */
public class Main {
    public static void main(String[] args) {

        Board board = new Board();

        PlayGround test = new PlayGround(board, "localhost", "Daniel", 1);
        test.listen();

    }
}

