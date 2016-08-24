/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package network;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import gameLogic.*;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;
    private String playerID;

    private String tileID="", tileNextID="", tileRot="", tileX="", tileY="", player="", playerPosX="", playerPosY="", goal0="", goal1="", goal2="", goal3="" ,playerPoints =" 0 0 0 0";
    private ServerFunctions serverFunctions = new ServerFunctions();
    private Board initBoard = new Board();
    //PlayerTurn
    private int playersTurnID=0;

    private boolean gameEnd = false;
    private String gameEndPlayerName = "";

    public Logger LOGGER = Logger.getLogger(Connection.class.getName());

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //init Logger
        try {
            FileHandler fileHandler = new FileHandler("gameLog.log");
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {};

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
                    //set unique playerID
                    connections.get(i).setpId(i);
                    //send playerID to playGround
                    ith.println("initPlayerID " + connections.get(i).getpId());

                    // set connection specific player name
                    if (message.startsWith("initName")) {
                        //player name
                        connections.get(i).setPlayerName(message.substring(9));
                        initBoard.getPlayer(i).setNameOfPlayer(message.substring(9));
                        if (!player.contains(connections.get(i).getPlayerName())) {
                            player += message.substring(9) + " ";
                        }
                    }

                    ith.println("tileID " + tileID);
                    ith.println("tileNextID " + tileNextID);
                    ith.println("tileRot " + tileRot);
                    ith.println("tileX " + tileX);
                    ith.println("tileY " + tileY);

                    // start logging for each client
                    LOGGER.info("*****STARTING*****");
                    LOGGER.info("init " + tileID);

                    if (ith.getpId() == 0) {
                        ith.println("deal " + goal0);
                        LOGGER.info("Player_00 deal " + goal0);
                    }
                    if (ith.getpId() == 1) {
                        ith.println("deal " + goal1);
                        LOGGER.info("Player_01 deal " + goal1);
                    }
                    if (ith.getpId() == 2) {
                        ith.println("deal " + goal2);
                        LOGGER.info("Player_02 deal " + goal2);
                    }
                    if (ith.getpId() == 3) {
                        ith.println("deal " + goal3);
                        LOGGER.info("Player_03 deal " + goal3);
                    }
                    //move to draw the field should be the last thing at init
                    ith.println("draw ");
                    connections.get(i).setInit(true);
                }

                if(ith.isAlive() && message != null) {
                    if (message.startsWith("insertTile ")) {
                        // push buttonID clientID tileID rotation x y
                        String[] tmpInsertTile = message.split("\\s+");
                        int buttonID = Integer.parseInt(tmpInsertTile[1]);
                        int clientID = Integer.parseInt(tmpInsertTile[2]);
                        int tileID   = Integer.parseInt(tmpInsertTile[3]);
                        int rotation = Integer.parseInt(tmpInsertTile[4]);
                        int x        = Integer.parseInt(tmpInsertTile[5]);
                        int y        = Integer.parseInt(tmpInsertTile[6]);

                        //log
                        LOGGER.info("INCOMING push " + tileID + " " + rotation + " " + x + " " + y);
                        // calculate
                        serverFunctions.insertTile(buttonID, initBoard);
                        // log
                        LOGGER.info("OUTGOING movevalid " + serverFunctions.isArrowMoveAllowed(buttonID));
                        if (serverFunctions.isArrowMoveAllowed(buttonID)) {
                            LOGGER.info("OUTGOING pushed " + tileID + " " + rotation + " " + x + " " + y);
                        }

                        boardToString(initBoard);
                        playerPosToString(initBoard);

                    }

                    else if(message.startsWith("rotateTile ")){
                        String[] tmpRotateTile = message.split("\\s+");
                        int nextTileRot = Integer.parseInt(tmpRotateTile[1]);
                        int clientID = Integer.parseInt(tmpRotateTile[2]);

                        //Calculation
                        serverFunctions.rotNextTile(nextTileRot,initBoard);
                        boardToString(initBoard);
                    }

                    else if(message.startsWith("move ") || message.startsWith("pass ")){
                        // move x y playerID
                        String[] moveString = message.split("\\s+");

                        int playerID = Integer.parseInt(moveString[3]);


                        int x        = Integer.parseInt(moveString[1]);
                        int y        = Integer.parseInt(moveString[2]);
                        Position buttonPositionPressed = new Position(x, y);
                        // log
                        if (message.startsWith("move ")) {
                            LOGGER.info("INCOMING move " + x + " " + y);
                        } else {
                            LOGGER.info("OUTGOING pass player_0" + playerID);
                        }
                        LOGGER.info("OUTGOING movevalid " + serverFunctions.checkMazeIfMoveIsPossible(initBoard, buttonPositionPressed, playerID));
                        if (serverFunctions.checkMazeIfMoveIsPossible(initBoard, buttonPositionPressed, playerID)) {
                            LOGGER.info("OUTGOING move " + playerID + " " + x + " " + y);
                        }

                        serverFunctions.movePlayerIfMoveIsPossible(initBoard,playerID,buttonPositionPressed);
                        playerPosToString(initBoard);

                        switch (serverFunctions.isPlayerGettingPoints(initBoard , playerID)){

                                case 0:
                                    //System.out.println("kein Punkt");
                                    break;
                                case 1:
                                    playerPoints ="";
                                    // neu zeichnen der Punkte
                                    for (int j = 0; j < initBoard.getAllPlayers().length ; j++) {
                                        playerPoints += initBoard.getPlayer(j).getScore() +" ";
                                    }


                                    //TODO Daniel hier muss das protokol rein
                                    if (ith.getpId() == 0) {

                                        ith.println("deal " + goalListToString(goal0,playerID));
                                        LOGGER.info("Player_00 goal " + playerID + " " + x + " " + y);
                                    }
                                    if (ith.getpId() == 1) {
                                        ith.println("deal " + goalListToString(goal1,playerID));                                        //ith.LOGGER.info("deal " + goal1);
                                        LOGGER.info("Player_01 goal " + playerID + " " + x + " " + y);
                                    }
                                    if (ith.getpId() == 2) {
                                        ith.println("deal " + goalListToString(goal2,playerID));                                        //ith.LOGGER.info("deal " + goal2);
                                        LOGGER.info("Player_02 goal " + playerID + " " + x + " " + y);
                                    }
                                    if (ith.getpId() == 3) {
                                        ith.println("deal " + goalListToString(goal3,playerID));                                        //ith.LOGGER.info("deal " + goal3);
                                        LOGGER.info("Player_03 goal " + playerID + " " + x + " " + y);
                                    }
                                    break;
                                case 2:
                                   // spiel ist zu ende

                                    //todo sperre das komplette feld
                                    gameEnd = true;
                                    gameEndPlayerName = initBoard.getPlayer(playerID).getNameOfPlayer();
                                    break;
                        }

                        // calculate
                        serverFunctions.movePlayerIfMoveIsPossible(initBoard,playerID,buttonPositionPressed);

                        playerPosToString(initBoard);
                        if(serverFunctions.checkMazeIfMoveIsPossible(initBoard,buttonPositionPressed,playerID)){
                            if(playersTurnID == connections.size()-1){
                             playersTurnID = 0;

                            }
                            else{
                             playersTurnID++;
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
                            //Hier kommt die spielerbewegung noch dazu
                            else if (message.startsWith("move") || message.startsWith("pass")){
                                jth.println("playersTurnID " + playersTurnID);
                                jth.println("playerPosX " + playerPosX);
                                jth.println("playerPosY " + playerPosY);

                                jth.println("points " + playerPoints);

                                if(gameEnd){
                                    jth.println("gameEnd " + gameEndPlayerName );
                                    System.out.println(gameEndPlayerName +" gameEnde!!! server ");
                                }

                                jth.println("draw ");

                            }
                            else if (message.startsWith("leave")) {
                                String[] tmpLeave = message.split("\\s+");
                                LOGGER.info("INCOMING leave");
                                LOGGER.info("OUTGOING disconnect player_0" + tmpLeave[1]);
                            }
                            else {
                                // sendet alles was nicht über ifs abgefangen wird weiter (chat)
                                String[] tmpMessage = message.split(": ");
                                LOGGER.info("INCOMING chat " + tmpMessage[1]);
                                jth.println(message.substring(5));
                                LOGGER.info("OUTGOING chat " + connections.get(i).getpId() + " " + tmpMessage[1]);
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

    public String goalListToString (String goal,int playerID){
        goal = "";
        for (int j = 0; j < initBoard.getPlayer(playerID).getCreaturesNeeded().size(); j++) {
            goal += initBoard.getPlayer(playerID).getCreaturesNeeded().get(j).getGoalCardID() + " ";
        }
        return goal;
    }



}