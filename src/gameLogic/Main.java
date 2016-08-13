package gameLogic;

/**
 * runs the game
 * if you press host at the lobby it will create hostGame
 * if you press join at the lobby it will create joinGame
 */
public class Main {


    public static void main(String[] args) {

        Board board = new Board();
        HostGame hostGame = new HostGame();

        JoinGame joinGame = new JoinGame();
    }


}
