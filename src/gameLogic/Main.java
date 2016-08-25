package gameLogic;

import gui.PlayGround;

import javax.swing.*;

/**
 * runs the game
 * if you press host at the lobby it will create hostGame
 * if you press join at the lobby it will create joinGame
 */
public class Main {
    public static void main(String[] args) {



        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }



            BoardFromClient board = new BoardFromClient();



        PlayGround test = new PlayGround("localhost", "ppo");
        test.listen();
    }
}

