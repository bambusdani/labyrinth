/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package network;

import java.util.Vector;
import java.util.logging.*;

import gameLogic.*;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;
    private String playerID;
    private String tileID="", tileRot="", tileX="", tileY="", goal="", player="";

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //--------------------------------------------------------------------------------
        // create an init board
        Board initBoard = new Board();

        //================================================================================
        // Converting Board -> String(s)
        // Available Strings:
        // -tileID
        // -tileRot
        // -tileX
        // -tileY
        // -goal
        // -player
        //================================================================================

        //--------------------------------------------------------------------------------
        // tileID
        for (int i = 0; i < initBoard.getallTiles().length; i++) {
            for (int j = 0; j < initBoard.getallTiles()[0].length; j++) {
                tileID  += initBoard.getTile(i, j).getId() + " ";
                tileRot += initBoard.getTile(i, j).getRotation() + " ";
                tileX   += initBoard.getTile(i, j).getPosition().getX() + " ";
                tileY   += initBoard.getTile(i, j).getPosition().getY() + " ";
            }
        }

        //--------------------------------------------------------------------------------
        // goal

        //--------------------------------------------------------------------------------
        // players
        for (int i = 0; i < initBoard.getAllPlayers().length; i++) {
            player += initBoard.getPlayer(i).getNameOfPlayer() + " ";
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

                //send init board strings to clients (speficially)
                if(ith.isAlive() && !connections.get(i).isInit()) {
                    ith.println("tileID " + tileID);
                    ith.println("tileRot " + tileRot);
                    ith.println("tileX " + tileX);
                    ith.println("tileY " + tileY);
                    ith.println("player " + player);

                    // start logging for earch client
                    ith.LOGGER.info("*****STARTING*****");
                    ith.LOGGER.info("init " + tileID);

                    connections.get(i).setInit(true);
                }

                //--------------------------------------------------------------------------------
                // begin with server broadcasting to all clients
                // begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            /**
                             * TODO hier werden logik funktionien aufgerufen
                             * hier wird das board überprüft und wieder gesenet
                             */
                            jth.println(message);
                        }
                        catch (Exception e) {
                            // error displaying
                            System.err.println(e.getMessage());
                        }
                    }
            }
            // don't monopolize processor
            try                 { Thread.sleep(100);   }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}