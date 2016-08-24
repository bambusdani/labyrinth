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

    private String players;

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //init Logger
        try {
            FileHandler fileHandler = new FileHandler("lobbyLog.log");
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {};
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
                if(ith.isAlive() && message != null && !connections.get(i).isInit()) {
                    // set unique playerID
                    connections.get(i).setpId(i);
                    // send welcome message
                    ith.println("welcome " + connections.get(i).getpId());

                    // append to player var. (TODO add player name)
                    if (!players.contains(connections.get(i).getpId()+"")) {
                        players += connections.get(i).getpId()+"";
                    }
                }

                //--------------------------------------------------------------------------------
                // begin with server broadcasting to all clients
                // begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            if (message.startsWith("connect")) {
                                jth.println(players);
                            }
                            else if (message.startsWith("connect") ||
                                     message.startsWith("host")    ||
                                     message.startsWith("join")    ||
                                     message.startsWith("leave")) {
                                jth.println("rooms");
                            }
                            else if (message.startsWith("ready")) {
                                String[] tmpMessage = message.split("\\s+");
                                // tmpMessage[1] contains playerID
                                jth.println("ready " + tmpMessage[1]);
                            }
                            // if chat
                            else {
                                String[] tmpMessage = message.split(": ");
                                LOGGER.info("INCOMING chat " + tmpMessage[1]);
                                jth.println(message.substring(5));
                                LOGGER.info("OUTGOING chat player_0" + connections.get(i).getpId() + " " + tmpMessage[1]);
                            }
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