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

    private String players = "", readyPlayers = "", rooms = "", hosts = "";

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
                if (ith.isAlive() && message != null && connections.get(i).isInit()) {
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
                    if (!players.contains(connections.get(i).getpId() + "")) {
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
                    if (message.startsWith("host")) {
                        String[] tmpHost = message.split("\\s+");
                        // log incoming message
                        LOGGER.info("INCOMING " + message);
                        // set client to host
                        connections.get(i).setHost(true);
                        // set clients room name
                        connections.get(i).setRoom(tmpHost[1]);

                        // rooms
                        // save room name
                        rooms += tmpHost[1];
                        // send rooms to all clients
                        broadcast("rooms " + tmpHost[1] + " ");
                        // log outgoing rooms message
                        LOGGER.info("OUTGOING rooms " + tmpHost[1]);

                        // hosts
                        hosts += tmpHost[1] + " " + connections.get(i).getpId() + " ";
                        // send hosts too all clients
                        broadcast("hosts " + hosts);
                        // log outgoing hosts message
                        LOGGER.info("OUTGOING hosts " + hosts);
                    }
                    // 'join' parameter (join room name)
                    else if (message.startsWith("join")) {
                        String[] tmpJoin = message.split("\\s+");
                        // log incoming message
                        LOGGER.info("INCOMING " + message);
                        // set client room name
                        connections.get(i).setRoom(tmpJoin[1]);
                        ith.println("gameRoom " + tmpJoin[1]);
                        // TODO send rooms and hosts to all cients
                    }
                    // 'ready' parameter (ready playerID)
                    else if (message.startsWith("ready")) {
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
                            broadcast("drawReadyPlayers " + readyPlayers);
                        }
                    }
                    // 'leave' parameter (leave GameRoomName)
                    else if (message.startsWith("leave")) {
                        // String[] tmpLeave = message.split("\\s+");

                        // log incoming message
                        LOGGER.info("INCOMING " + message);
                        // reset client game room
                        ith.setRoom("");
                    }
                    // 'start' parameter (starting the game)
                    else {
                        if (message.startsWith("start")) {
                            // log incoming start message
                            LOGGER.info("INCOMING start");
                            // start the game with players who are ready
                            String tmpRoom = "";
                            ith.startGameServer();

                            broadcast("chat gamestarting...");
                            // broadcast gameStart to all clients
                            broadcast("gamestart " + tmpRoom);
                            // log outgoing message
                            LOGGER.info("OUTGOING gamestart " + tmpRoom);

                            // TODO
                            // - ready players in textfelder werden nicht angezeigt
                            // port fort laufend
                            // remove user von lobby wenn er ein spiel startet
                            // delete gameRoom if game started

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
                        } catch (Exception e) {
                            // error displaying
                            System.err.println(e.getMessage());
                        }
                    }
            }
            // don't monopolize processor
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void broadcast(String s) {
        for (Connection jth : connections) {
            jth.println(s);
        }
    }
}