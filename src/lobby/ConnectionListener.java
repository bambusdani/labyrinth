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

    private String players="", readyPlayers="", gameRooms="";
    private int gameRoomID=0;

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

                // send init board strings to clients (specifically)
                if(ith.isAlive() && message != null && connections.get(i).isInit()) {
                    // set unique playerID
                    connections.get(i).setpId(i);
                    // send playerID to client
                    ith.println("initPlayerID " + connections.get(i).getpId());
                    // set unique playerName
                    if (message.startsWith("connect")) {
                        // split message @space
                        String[] tmpMessage = message.split("\\s+");
                        // set player name from message
                        connections.get(i).setPlayerName(tmpMessage[1]);
                        // log incoming connection
                        LOGGER.info("INCOMING connection " + tmpMessage[1]);
                        // send welcome message to client
                        ith.println("welcome " + connections.get(i).getpId() + " " + connections.get(i).getPlayerName());
                        // log outgoing welcome message
                        LOGGER.info("OUTGOING welcome " + connections.get(i).getpId() + " " + connections.get(i).getPlayerName());
                    }

                    // append to player var. and broadcast to all clients
                    if (!players.contains(connections.get(i).getpId()+"")) {
                        players += connections.get(i).getpId() + " " + connections.get(i).getPlayerName() + " ";
                        // send players to all clients
                        broadcast("players " + players);
                        // log outgoing players message
                        LOGGER.info("OUTGOING players " + players);
                    }

                    // set connection init false
                    connections.get(i).setInit(false);
                }

                // not init
                if (ith.isAlive() && message != null) {
                    // 'host' parameter
                    // TODO it has to be 'host GameRoomName'
                    if (message.startsWith("host")) {
                        // log incoming message
                        LOGGER.info("INCOMING host");
                        // set client to host
                        connections.get(i).setHost(true);
                        // send players gameRommID
                        // gameRoomID+1
                        gameRoomID++;
                        broadcast("gameRoomID " + gameRoomID + "");
                    }
                    // 'ready' parameter (ready playerID)
                    else {
                        if (message.startsWith("ready")) {
                            // set players connection to ready
                            connections.get(i).setReady(true);
                            // log incoming message
                            LOGGER.info("INCOMING ready");
                            // send 'ready playerID' to all clients
                            broadcast("ready " + connections.get(i).getpId());
                            // log outgoing ready message
                            LOGGER.info("OUTGOING ready " + connections.get(i).getpId());
                            // append name to readyPlayers
                            if (!readyPlayers.contains(connections.get(i).getPlayerName())) {
                                readyPlayers += connections.get(i).getPlayerName() + " ";
                                // broadcast readyPlayers to all clients
                                broadcast("readyPlayers " + readyPlayers);
                            }
                        }
                        // 'start' parameter (starting the game)
                        else if (message.startsWith("start")) {
                            // log incoming start message
                            LOGGER.info("INCOMING start");
                            // start the game with players who are ready
                            for (Connection kth : connections) {
                                if (kth.isHost()) {
                                    kth.startGameServer();
                                    kth.connectToGame();
                                }
                            /*if (kth.isReady()) {
                                // send game start message to ready clients
                                kth.println("gamestart");
                                // log outgoing game start message
                                LOGGER.info("OUTGOING gamestart");
                            }*/
                            }
                        }
                    }
                }

                //--------------------------------------------------------------------------------
                // begin with server broadcasting to all clients
                // begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            // in case of chat
                            String[] tmpMessage = message.split(": ");
                            // log incoming chat message
                            LOGGER.info("INCOMING chat " + tmpMessage[1]);
                            // send chat message to all clients
                            jth.println(message.substring(5));
                            // log outgoing chat message
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