/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package lobby;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import gameLogic.*;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;

    public Logger LOGGER = Logger.getLogger(Connection.class.getName());

    private String players="";

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //init Logger
        try {
            FileHandler fileHandler = new FileHandler("lobbyLog.log");
            LOGGER.addHandler(fileHandler);
            LOGGER.info("*****STARTING LOBBY*****");
        } catch (Exception e) {
            System.err.println(e);
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

                //send init board strings to clients (specifically)
                if(ith.isAlive() && message != null && connections.get(i).isInit()) {
                    // set unique playerID
                    connections.get(i).setpId(i);
                    // set unique playerName
                    if (message.startsWith("connect")) {
                        // split message @space
                        String[] tmpMessage = message.split("\\s+");
                        // set player name from message
                        connections.get(i).setPlayerName(tmpMessage[1]);
                        // log incoming connection
                        LOGGER.info("INCOMING connection " + tmpMessage[1]);
                        // send welcome message to client
                        ith.println("Welcome " + connections.get(i).getpId() + " " + connections.get(i).getPlayerName());
                        // log outgoing welcome message
                        LOGGER.info("welcome " + connections.get(i).getpId() + " " + connections.get(i).getPlayerName());
                    }

                    // append to player var. and broadcast to all clients
                    if (!players.contains(connections.get(i).getpId()+"")) {
                        players += connections.get(i).getpId() + " " + connections.get(i).getPlayerName() + " ";
                        broadcast("players " + players);
                    }

                    // set connection init false
                    connections.get(i).setInit(false);
                }

                //--------------------------------------------------------------------------------
                // begin with server broadcasting to all clients
                // begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            // in case of chat
                            String[] tmpMessage = message.split(": ");
                            LOGGER.info("INCOMING chat " + tmpMessage[1]);
                            jth.println(message.substring(5));
                            LOGGER.info("OUTGOING chat player_0" + connections.get(i).getpId() + " " + tmpMessage[1]);
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

    public void broadcast(String s) {
        for (Connection jth : connections) {
            jth.println(s);
        }
    }
}