/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package chat;

import java.util.Vector;
import java.io.IOException;
import java.util.logging.*;

import gameLogic.*;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;


    private final static Logger LOGGER = Logger.getLogger(ConnectionListener.class.getName());
    static private int playerID;
    private boolean mError, mException;
    private String sException;
    private Protocol protocol;

    //--------------------------------------------------------------------------------
    // create a new board so we can get the player information needed
    private Board board = new Board();

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;
        this.protocol = new Protocol();

        //================================================================================
        // setup the logger
        //================================================================================
        try {
            //================================================================================
            // set log level

            LOGGER.info("*****STARTING*****");
        } catch (Exception e) {
            //================================================================================
            // catch error
            LOGGER.warning(e.toString());

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


                //--------------------------------------------------------------------------------
                // Broadcast to specific clients goes here before the jth-loop!
                if(mError) {
                    ith.println("What the **** have you typed in?");
                    mError = false;
                }
                if(mException) {
                    ith.println(sException);
                    mException = false;
                }


                //TEST
                if(board.getPlayer(0).getMessage() != null) {
                    ith.println(board.getPlayer(0).getMessage());
                }

                //--------------------------------------------------------------------------------
                // Begin with server broadcasting to all clients
                // Begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {

                        //================================================================================
                        // split the message into username + message
                        // TODO get individual player id from tmpUsername
                        String[] tmpFullMessage = message.split(": ");
                        String tmpUsername = tmpFullMessage[0].substring(1, tmpFullMessage[0].length()-1);
                        String tmpMessage = tmpFullMessage[tmpFullMessage.length-1];

                        //================================================================================
                        // assign playername to work with the board
                        for (int j = 0; j < board.getAllPlayers().length; j++) {
                            if(tmpUsername.equalsIgnoreCase(board.getAllPlayers()[j].getNameOfPlayer())) {
                                playerID = board.getAllPlayers()[j].getPlayerID();
                            }
                        }

                        //================================================================================
                        // add to message log
                        LOGGER.info("INCOMING " + message);

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
                                jth.println("Player " + board.getPlayer(playerID).getPlayerID() +
                                        " current Position: "  + board.getPlayer(playerID).getAcutalPosition().getX() + ", " + board.getPlayer(playerID).getAcutalPosition().getY());

                                //--------------------------------------------------------------------------------
                                // set new position
                                //board.getPlayer(playerID).setPositionX(board.getPlayer(playerID).getPositionX()+tmpX); (alte Version)
                                board.getPlayer(playerID).getAcutalPosition().setX(board.getPlayer(playerID).getAcutalPosition().getX()+tmpX);
                                //board.getPlayer(playerID).setPositionY(board.getPlayer(playerID).getPositionY()+tmpY);
                                board.getPlayer(playerID).getAcutalPosition().setY(board.getPlayer(playerID).getAcutalPosition().getY()+tmpY);

                                //--------------------------------------------------------------------------------
                                // broadcast to all players
                                jth.println("Player " + board.getPlayer(playerID).getPlayerID() +
                                        " moved to: " + board.getPlayer(playerID).getAcutalPosition().getX() + ", " + board.getPlayer(playerID).getAcutalPosition().getY());
                            }

                            //================================================================================
                            // parameter 'PASS'
                            //================================================================================
                            else if (tmpMessage.substring(0,4).equalsIgnoreCase("pass")) {

                                //--------------------------------------------------------------------------------
                                // set player turn false
                                board.getPlayer(playerID).setTurn(false);

                                //--------------------------------------------------------------------------------
                                // send to server that player has passed
                                jth.println("Player " + playerID +
                                        " has passed.");

                                //--------------------------------------------------------------------------------
                                // player id's go from 1-4
                                // set turn for next player true
                                if(playerID+1 > 4) {
                                    // next player is '1'
                                    board.getPlayer(1).setTurn(true);
                                    jth.println("It's Player's 1 turn.");
                                } else {
                                    board.getPlayer(playerID+1).setTurn(true);
                                    jth.println("It's Player's " + (playerID+1) + " turn.");
                                }
                            }

                            //================================================================================
                            // parameter 'PUSH'
                            //================================================================================
                            else if (tmpMessage.substring(0,4).equalsIgnoreCase("push")) {
                                //--------------------------------------------------------------------------------
                                // extract tileID, rotation and x y from e.g 'push 1 2 3 5'
                                // tileID = 6th character
                                // rotation = 8th character
                                // x position = 10th character
                                // y position = 12th character
                                int tmpTileID = Integer.parseInt(tmpMessage.substring(5,6));
                                int tmpRotation = Integer.parseInt(tmpMessage.substring(7,8));
                                int tmpX = Integer.parseInt(tmpMessage.substring(9,10));
                                int tmpY = Integer.parseInt(tmpMessage.substring(11,12));

                                board.pushTile(tmpTileID, tmpRotation, tmpX, tmpY);

                                jth.println("Player " + playerID + " pushed tile " + tmpTileID +
                                        " with rotation " + tmpRotation +
                                        " to " + tmpX + " " + tmpY + ".");
                            }

                            else {
                                //--------------------------------------------------------------------------------
                                // if the message doens't make sense at all...
                                // will only be display in the client
                                mError = true;
                            }
                        }
                        catch (Exception e) {
                            //--------------------------------------------------------------------------------
                            // error will only be displayed in the client
                            mException = true;
                            sException = e.getMessage();
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