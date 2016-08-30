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
    private int portNumber = 4445;
    private int playerCounter = 1;

    public Logger LOGGER = Logger.getLogger(Connection.class.getName());

    private String players = "", readyPlayers = "", rooms = "", roomCounter = "", hosts = "";

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

                        //check if player Name is already in use
                        System.out.println("players: " + players);
                        if(players.contains(tmpMessage[1])){
                            connections.get(i).setPlayerName(tmpMessage[1]+playerCounter);
                            playerCounter++;
                        }else{
                            // set player name from message
                            connections.get(i).setPlayerName(tmpMessage[1]);
                        }

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

                    // send rooms to players
                    broadcast("rooms " + rooms);
                    // log outgoing message
                    LOGGER.info("OUTGOING rooms " + rooms);

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
                        rooms += tmpHost[1] + " ";
                        // send rooms to all clients
                        broadcast("rooms " + rooms);
                        // log outgoing rooms message
                        LOGGER.info("OUTGOING rooms " + rooms);

                        // hosts
                        hosts += tmpHost[1] + " " + connections.get(i).getpId() + " ";
                        // send hosts too all clients
                        broadcast("hosts " + hosts);
                        // log outgoing hosts message
                        LOGGER.info("OUTGOING hosts " + hosts);

                        // append to roomCounter
                        roomCounter += tmpHost[1] + " " + "1" + " ";
                    }
                    // 'join' parameter (join room name)
                    else if (message.startsWith("join")) {
                        String[] tmpJoin = message.split("\\s+");
                        // log incoming message
                        LOGGER.info("INCOMING " + message);

                        // if join is vaiable
                        String[] tmpRoomCounter = roomCounter.split("\\s+");
                        for (int j = 0; j < tmpRoomCounter.length; j++) {
                            if (tmpRoomCounter[i].equalsIgnoreCase(tmpJoin[1])) {
                                int tmpCounter = Integer.parseInt(tmpRoomCounter[i+1]);
                                // counting 1 to 4
                                if (tmpCounter <= 4) {
                                    // set room counter + 1
                                    roomCounter = roomCounter.replace(tmpRoomCounter[i]+ " " + tmpCounter, tmpRoomCounter[i] + " " + tmpCounter+1 + "");

                                    //set client room name
                                    connections.get(i).setRoom(tmpJoin[1]);
                                    // send client join is viable
                                    ith.println("joinValid true");
                                    // log outgoing message
                                    LOGGER.info("OUTGOING joinValid true");
                                }
                            }
                        }

                        // send rooms to all clients
                        broadcast("rooms " + rooms);
                        // log outgoing message
                        LOGGER.info("OUTGOING rooms " + rooms);
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


                        if (ith.isHost()) {
                            // send kick message with room name
                            broadcast("kick " + ith.getRoom());
                            // log outgoing message
                            LOGGER.info("OUTGOING kick " + ith.getRoom());
                            // reset host status
                            ith.setHost(false);

                            // reset room name of clients in game room
                            for (Connection lth : connections) {
                                if (ith.getRoom().equalsIgnoreCase(lth.getRoom())) {
                                    lth.setRoom("");
                                }
                            }
                        }
                    }
                    // 'start' parameter (starting the game)
                    else if (message.startsWith("start")) {
                        // log incoming start message
                        LOGGER.info("INCOMING start");

                        // assembly 'gamestart GameRoomName PlayerID1 ... PlayerIDN'
                        // append GameRoomName
                        String tmpGameStart = ith.getRoom() + " ";
                        // get playerID's where gameRoom equals
                        //send port to all clients
                        broadcast("portNumber " + portNumber);

                        // remove game room from string
                        rooms = rooms.replace(tmpGameStart + " ", "");
                        // broadcast rooms to all players
                        broadcast("rooms " + rooms);
                        // log outgoing message
                        LOGGER.info("OUTGOING rooms " + rooms);

                        // broadcast gameStart to all clients
                        broadcast("gamestart " + tmpGameStart);
                        // log outgoing message
                        LOGGER.info("OUTGOING gamestart " + tmpGameStart);

                        // start the game with players who are ready
                        System.out.println("startGameServer " + portNumber);
                        ith.startGameServer(portNumber+"");
                        portNumber++;

                        // TODO
                        // remove user von lobby wenn er ein spiel startet
                        // delete gameRoom if game started

                    }
                    // 'playerName' delete playerName form players string
                    else if (message.startsWith("playerName")) {
                        String[] tmpPlayerName = message.split("\\s+");

                        // remove playerID
                        players = players.replace(connections.get(i).getpId() + "", "");
                        // remove player name
                        players = players.replace(connections.get(i).getPlayerName(), "");
                        // broadcast new players string to all clients
                        broadcast("players " + players);
                        // log outgoing message
                        LOGGER.info("OUTGOING players " + players);
                    }
                    // 'quitLobby'
                    else {
                        if (message.startsWith("quitLobby")) {
                        /* remove player from players string */
                            // remove playerID
                            players = players.replace(connections.get(i).getpId() + "", "");
                            // remove player name
                            players = players.replace(connections.get(i).getPlayerName(), "");
                            // broadcast new players string to all clients
                            broadcast("players " + players);

                        /* remove game room from rooms and hosts (only when host is sending) */
                            if (ith.isHost()) {
                                // remove game room from rooms
                                rooms = rooms.replace(connections.get(i).getRoom(), "");
                                // broadcast rooms to all clients
                                broadcast("rooms " + rooms);
                                // log outgoing message
                                LOGGER.info("OUTGOING rooms " + rooms);

                            /* remove game room and hostID from hosts */
                                // remove game room form hosts
                                hosts = hosts.replace(connections.get(i).getRoom(), "");
                                // remove hostID from hosts
                                hosts = hosts.replace(connections.get(i).getpId() + "", "");
                                // broadcast hosts too all clients
                                broadcast("hosts " + hosts);
                                // log outgoing message
                                LOGGER.info("hosts " + hosts);
                            }
                            // remove player from lobby
                            connections.remove(i);
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