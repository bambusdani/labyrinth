package gameLogic;

import gui.PlayGround;

import javax.swing.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 *
 * runs the game
 * if you press host at the lobby it will create hostGame
 * if you press join at the lobby it will create joinGame
 *
 */
public class Main {
    public static void main(String[] args) {

        /**
         * Set a better appearance -> Nimbus
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("LAF not possible");
        }

        BoardFromClient board = new BoardFromClient();

        PlayGround test = new PlayGround("localhost", "Daniel");

        test.listen();
    }
}

