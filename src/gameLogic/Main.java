package gameLogic;

import gui.PlayGround;

/**
 * runs the game
 * if you press host at the lobby it will create hostGame
 * if you press join at the lobby it will create joinGame
 */
public class Main {
    public static void main(String[] args) {

        BoardFromClient board = new BoardFromClient();



        PlayGround test = new PlayGround("localhost", "ppo");
        test.listen();
    }
}

