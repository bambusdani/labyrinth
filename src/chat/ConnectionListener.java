/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package chat;

import java.util.Vector;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import gameLogic.Board;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;

    static private final Logger log = Logger.getLogger(ConnectionListener.class.getName());
    static private FileHandler fileTxt;
    static private SimpleFormatter formatText;

    //--------------------------------------------------------------------------------
    // create a new board so we can get the player information needed
    private Board board = new Board();

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //================================================================================
        // Create Logger
        // Log Levels are: SEVERE (highest), WARNING, INFO, CONFIG, FINE, FINER, FINEST
        //================================================================================
        try {
            //================================================================================
            // set log level
            this.log.setLevel(Level.INFO);

            //================================================================================
            // create file handler
            fileTxt = new FileHandler("chatLog.txt");

            //================================================================================
            // create a text formatter
            formatText = new SimpleFormatter();
            fileTxt.setFormatter(formatText);
            log.addHandler(fileTxt);

        } catch (IOException e) {
            //================================================================================
            // catch error
            System.err.print("Caught IOExceptin: " + e.getMessage());
        }
    }

    //--------------------------------------------------------------------------------
    // check for incoming messages and broadcast
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < connections.size(); i++) {
                Connection ith = connections.get(i);

                //--------------------------------------------------------------------------------
                // if connection terminated, remove from list of active connections
                if (!ith.isAlive())
                    connections.remove(i);

                //================================================================================
                // Broadcasts to all clients oder to one specific client
                // -broadcasting to all:
                //  jth.print...
                // -broadcast to specific client:
                //  ith.print...
                //================================================================================
                String message = ith.getMessage();

                if (message != null)
                    for (Connection jth : connections) {
                        //================================================================================
                        // split the message into username + message
                        // TODO get individual player id from tmpUsername
                        //================================================================================
                        String[] tmpFullMessage = message.split(": ");
                        String tmpUsername = tmpFullMessage[0];
                        String tmpMessage = tmpFullMessage[tmpFullMessage.length-1];

                        //================================================================================
                        // add to log
                        log.info(message);

                        //================================================================================
                        // Begin with parameters
                        // for testing purposes we use playerId(0)
                        //================================================================================
                        try {

                            //================================================================================
                            // parameter 'CHAT'
                            //================================================================================
                            if (tmpMessage.substring(0, 4).equalsIgnoreCase("chat")) {
                                //================================================================================
                                // print message
                                jth.println(message);
                            }

                            //================================================================================
                            // parameter 'MOVE'
                            //================================================================================
                            else if (tmpMessage.substring(0, 4).equalsIgnoreCase("move")) {
                                //--------------------------------------------------------------------------------
                                // extract x and y potistion from message e.g 'move 1 1' with substring
                                // x position = 6th character
                                // y position = 8th character
                                int tmpX = Integer.parseInt(tmpMessage.substring(5,6));
                                int tmpY = Integer.parseInt(tmpMessage.substring(7,8));

                                //--------------------------------------------------------------------------------
                                // for testing purposes uncomment or comment
                                jth.println("Player " + board.getPlayer(0).getPlayerID() +
                                        " current Position: "  + board.getPlayer(0).getPositionX() + ", " + board.getPlayer(0).getPositionY());

                                //--------------------------------------------------------------------------------
                                // set new position
                                board.getPlayer(0).setPositionX(board.getPlayer(0).getPositionX()+tmpX);
                                board.getPlayer(0).setPositionY(board.getPlayer(0).getPositionY()+tmpY);

                                //--------------------------------------------------------------------------------
                                // broadcast to all players
                                jth.println("Player " + board.getPlayer(0).getPlayerID() +
                                        " moved to : " + board.getPlayer(0).getPositionX() + ", " + board.getPlayer(0).getPositionY());
                            }

                            //================================================================================
                            // parameter 'PASS'
                            //================================================================================
                            if (tmpMessage.substring(0,4).equalsIgnoreCase("pass")) {
                                //send to server that player has passed
                            }

                            else {
                                //--------------------------------------------------------------------------------
                                // if the message doens't make sense at all...
                                // will only be display in the client
                                ith.println("What the **** have you typed in?");
                            }
                        }
                        catch (Exception e) {
                            //--------------------------------------------------------------------------------
                            // error will only be displayed in the client
                            ith.println(e.getMessage());
                        }
                    }
            }

            //--------------------------------------------------------------------------------
            // don't monopolize processor
            try                 { Thread.sleep(100);   }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

}