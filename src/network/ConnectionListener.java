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

    private String tileID="", tileNextID="", tileRot="", tileX="", tileY="", goal="", player="", playerPosX="", playerPosY="", goal0="", goal1="", goal2="", goal3="";
    private ServerFunctions serverFunctions = new ServerFunctions();
    private Board initBoard = new Board();
    private Tiles tmpTile;



    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //--------------------------------------------------------------------------------
        // create an init board
        //Board initBoard = new Board();

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
                tileID  += initBoard.getTile(j, i).getId() + " ";
                tileRot += initBoard.getTile(j, i).getRotation() + " ";
                tileX   += initBoard.getTile(j, i).getPosition().getX() + " ";
                tileY   += initBoard.getTile(j, i).getPosition().getY() + " ";
            }
        }
        //TODO
                tileNextID = initBoard.getNextTile().getId()+"";

        //--------------------------------------------------------------------------------
        // goal
        for (int i = 0; i < initBoard.getPlayer(0).getCreaturesNeeded().size(); i++) {
            goal0 += initBoard.getPlayer(0).getCreaturesNeeded().get(i).getGoalCardID() + " ";
            goal1 += initBoard.getPlayer(1).getCreaturesNeeded().get(i).getGoalCardID() + " ";
            goal2 += initBoard.getPlayer(2).getCreaturesNeeded().get(i).getGoalCardID() + " ";
            goal3 += initBoard.getPlayer(3).getCreaturesNeeded().get(i).getGoalCardID() + " ";
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
                if(ith.isAlive() && message != null && !connections.get(i).isInit()) {
                    connections.get(i).setpId(i);

                    // set connection specific player name
                    if (message.startsWith("initName")) {
                        connections.get(i).setPlayerName(message.substring(9));
                        if (!player.contains(connections.get(i).getPlayerName())) {
                            player += message.substring(9) + " ";
                        }
                    }

                    ith.println("tileID " + tileID);
                    //TODO rotation nextTileID
                    ith.println("tileNextID " + tileNextID);

                    ith.println("tileRot " + tileRot);
                    ith.println("tileX " + tileX);
                    ith.println("tileY " + tileY);

                    // start logging for earch client
                    ith.LOGGER.info("*****STARTING*****");
                    ith.LOGGER.info("init " + tileID);

                    if (ith.getpId() == 0) {
                        ith.println("deal " + goal0);
                        ith.LOGGER.info("deal " + goal0);
                    }
                    if (ith.getpId() == 1) {
                        ith.println("deal " + goal1);
                        ith.LOGGER.info("deal " + goal1);
                    }
                    if (ith.getpId() == 2) {
                        ith.println("deal " + goal2);
                        ith.LOGGER.info("deal " + goal2);
                    }
                    if (ith.getpId() == 3) {
                        ith.println("deal " + goal3);
                        ith.LOGGER.info("deal " + goal3);
                    }
                    //move to draw the field should be the last thing at init
                    ith.println("draw ");
                    connections.get(i).setInit(true);
                }

                if(ith.isAlive() && message != null) {
                    //test vl ist es besser hier
                    if (message.startsWith("insertTile ")) {
                        //TODO hier wird berechnet
                        String[] tmpInsertTile = message.split("\\s+");
                        int buttonID = Integer.parseInt(tmpInsertTile[1]);
                        int clientID = Integer.parseInt(tmpInsertTile[2]);

                        //Calculation
                        Board newBoard = serverFunctions.insertTile(buttonID, initBoard);
                        boardToString(newBoard);
                        playerPosToString(newBoard);

                    }

                    else if(message.startsWith("rotateTile ")){
                        String[] tmpRotateTile = message.split("\\s+");
                        int nextTileRot = Integer.parseInt(tmpRotateTile[1]);
                        int clientID = Integer.parseInt(tmpRotateTile[2]);

                        //Calculation
                        Board newBoard = serverFunctions.rotNextTile(nextTileRot,initBoard);
                        boardToString(newBoard);
                    }
                }
                //--------------------------------------------------------------------------------
                // begin with server broadcasting to all clients
                // begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            // send playernames to all clients
                            if (message.startsWith("initName")) {
                                jth.println("initName " + player);
                            }
                            /**
                             * TODO hier werden logik funktionien aufgerufen
                             * hier wird das board überprüft und wieder gesenet
                             */

                            //

                            else if (message.startsWith("insertTile ")) {

                                jth.println("tileID " + tileID );
                                jth.println("tileNextID " + tileNextID);
                                jth.println("tileRot " + tileRot);
                                jth.println("tileX " + tileX);
                                jth.println("tileY " + tileY);
                                jth.println("playerPosX " + playerPosX);
                                jth.println("playerPosY " + playerPosY);
                                jth.println("draw ");


                            }

                            else if(message.startsWith("rotateTile ")){

                                jth.println("tileID " + tileID );
                                jth.println("tileNextID " + tileNextID);
                                jth.println("tileRot " + tileRot);
                                jth.println("tileX " + tileX);
                                jth.println("tileY " + tileY);




                                jth.println("rotateTile ");

                                jth.println("draw ");
                            }
                            else if (message.contains("leave")) {
                                ith.LOGGER.info("disconnect player_0" + ith.getpId());
                            }
                            else {
                                // sendet alles was nicht über ifs abgefangen wird weiter (chat)
                                jth.println(message);
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

    public void boardToString(Board initBoard){

        tileID  = "";
        tileRot = initBoard.getNextTile().getRotation() + " " ;
        tileX   = "";
        tileY   = "";
        for (int i = 0; i < initBoard.getallTiles().length; i++) {
            for (int j = 0; j < initBoard.getallTiles()[0].length; j++) {
                tileID  += initBoard.getTile(j, i).getId() + " ";
                tileRot += initBoard.getTile(j, i).getRotation() + " ";
                tileX   += initBoard.getTile(j, i).getPosition().getX() + " ";
                tileY   += initBoard.getTile(j, i).getPosition().getY() + " ";
            }
        }
        tileNextID = initBoard.getNextTile().getId()+"";
    }

    public void playerPosToString(Board board){
        playerPosX="";
        playerPosY="";
        for (int i = 0; i < board.getAllPlayers().length; i++) {

            playerPosX += board.getPlayer(i).getAcutalPosition().getX() + " ";
            playerPosY += board.getPlayer(i).getAcutalPosition().getY() + " ";
        }

    }

}