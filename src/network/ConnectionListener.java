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

    private String tileID="", tileNextID="", tileRot="", tileX="", tileY="", player="", playerPosX="", playerPosY="", goal0="", goal1="", goal2="", goal3="" , playerPoints =" 0 0 0 0", init="";
    private ServerFunctions serverFunctions = new ServerFunctions();
    private Board initBoard = new Board();
    //PlayerTurn
    private int playersTurnID=0;
    private int playersTurnCounter = 0;
    private boolean[] leftPlayers = {false,false,false,false};

    private boolean gameEnd = false;
    private String gameEndPlayerName = "";
    private boolean isPushAllowed;
    public Logger LOGGER = Logger.getLogger(Connection.class.getName());


    private String disabledButtonID;

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        //init Logger
        try {
            FileHandler fileHandler = new FileHandler("gameServer.log");
            LOGGER.addHandler(fileHandler);
            LOGGER.info("*****STARTING*****");
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
        // initialize board strings
        for (int i = 0; i < initBoard.getallTiles().length; i++) {
            for (int j = 0; j < initBoard.getallTiles()[0].length; j++) {
                tileID  += initBoard.getTile(j, i).getId() + " ";
                tileRot += initBoard.getTile(j, i).getRotation() + " ";
                tileX   += initBoard.getTile(j, i).getPosition().getX() + " ";
                tileY   += initBoard.getTile(j, i).getPosition().getY() + " ";
            }
        }
        tileNextID = initBoard.getNextTile().getId()+"";

        //--------------------------------------------------------------------------------
        // assemble an init string for logging
        String[] tmpTileID = tileID.split("\\s+");
        String[] tmpTileRot = tileRot.split("\\s+");
        String[] tmpTileX = tileX.split("\\s+");
        String[] tmpTileY = tileY.split("\\s+");
        for (int j = 0; j < tmpTileID.length; j++) {
            init += tmpTileID[j] + " " + tmpTileRot[j] + " " + tmpTileX[j] + " " + tmpTileY[j] + " ";
        }

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

                    // send init board for logging
                    ith.println("init " + init);
                    // log outgoing init message
                    LOGGER.info("OUTGOING init " + init);

                    if (ith.getpId() == 0) {
                        ith.println("deal " + goal0);
                        LOGGER.info("OUTGOING deal " + goal0);
                    }
                    if (ith.getpId() == 1) {
                        ith.println("deal " + goal1);
                        LOGGER.info("OUTGOING deal " + goal1);
                    }
                    if (ith.getpId() == 2) {
                        ith.println("deal " + goal2);
                        LOGGER.info("OUTGOING deal " + goal2);
                    }
                    if (ith.getpId() == 3) {
                        ith.println("deal " + goal3);
                        LOGGER.info("OUTGOING deal " + goal3);
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

                        //testet ob der Push erlaubt ist
                        isPushAllowed = serverFunctions.isArrowMoveAllowed(buttonID);

                        disabledButtonID = serverFunctions.disabledArrowID(buttonID)+"";



                        // log incoming push message
                        LOGGER.info("INCOMING push " + tileID + " " + rotation + " " + x + " " + y);
                        // send movevalid message to client
                        ith.println("pushAllowed " + isPushAllowed);
                        // log outgoing movevalid mesage
                        LOGGER.info("OUTGOING movevalid " + isPushAllowed);
                        // calculate
                        serverFunctions.insertTile(buttonID, initBoard);
                        if (serverFunctions.isArrowMoveAllowed(buttonID)) {
                            // send pushed message to all clients
                            broadcast("pushed " + tileID + " " + rotation + " " + x + " " + y);
                            // log outgoing pushed message
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
                            // log incoming pass message
                            LOGGER.info("INCOMING pass");
                            // send pass message to all clients
                            broadcast("passed");
                            // log outgoing pass message
                            LOGGER.info("OUTGOING passed");
                        }

                        // changes the player -> if a person leaves the game goes on
                        // Todo if it´s players turn and he leaves we have a problem
                        // bei leave muss playersTurnCounter um 1 erhöht werden und pass ausgeführt werden
                        // sowie ein stein random plaziert werden oder übersprungen werden
                        if(serverFunctions.checkMazeIfMoveIsPossible(initBoard,buttonPositionPressed,playerID)){
                            serverFunctions.movePlayerIfMoveIsPossible(initBoard,playerID,buttonPositionPressed);
                            // send movevalid message to client
                            ith.println("moveValid " + true);
                            // log movevalid message
                            LOGGER.info("OUTGOING movevalid " + true);
                            // send move message to all clients
                            broadcast("move player_0" + playerID + " " + x + " " + y);
                            // log outgoing move message
                            LOGGER.info("OUTGOING move player_0" + playerID + " " + x + " " + y);

                            if(playersTurnID == connections.get(connections.size()-1).getpId()){
                                playersTurnID = connections.get(0).getpId();
                                playersTurnCounter = 0;
                            }
                            /*else if(connections.get(playersTurnCounter) == null){
                                //need it if the third person is leaving
                                //not shure if we need it -> had a error alert
                                playersTurnCounter = 0;
                                playersTurnID = connections.get(playersTurnID).getpId();
                            }*/
                            else{
                                playersTurnCounter+=1;
                                playersTurnID = connections.get(playersTurnCounter).getpId();
                            }
                        }
                        else{
                            // send movevalid message to client
                            ith.println("moveValid " + false);
                            // log movevalid message
                            LOGGER.info("OUTGOING movevalid " + false);
                        }

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


                                if (ith.getpId() == 0) {
                                    ith.println("deal " + goalListToString(goal0,playerID));
                                    // send goal message to all clients
                                    broadcast("goal player_0" + playerID + " " + x + " " + y );
                                    // log outgoing goal message
                                    LOGGER.info("OUTGOING goal player_0" + playerID + " " + x + " " + y);
                                }
                                if (ith.getpId() == 1) {
                                    ith.println("deal " + goalListToString(goal1,playerID));                                        //ith.LOGGER.info("deal " + goal1);
                                    // send goal message to all clients
                                    broadcast("goal player_0" + playerID + " " + x + " " + y );
                                    // log outgoing goal message
                                    LOGGER.info("OUTGOING goal player_0" + playerID + " " + x + " " + y);
                                }
                                if (ith.getpId() == 2) {
                                    ith.println("deal " + goalListToString(goal2,playerID));                                        //ith.LOGGER.info("deal " + goal2);
                                    // send goal message to all clients
                                    broadcast("goal player_0" + playerID + " " + x + " " + y );
                                    // log outgoing goal message
                                    LOGGER.info("OUTGOING goal player_0" + playerID + " " + x + " " + y);
                                }
                                if (ith.getpId() == 3) {
                                    ith.println("deal " + goalListToString(goal3,playerID));                                        //ith.LOGGER.info("deal " + goal3);
                                    // send goal message to all clients
                                    broadcast("goal player_0" + playerID + " " + x + " " + y );
                                    // log outgoing goal message
                                    LOGGER.info("OUTGOING goal player_0" + playerID + " " + x + " " + y);
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
                        //wurde davor bereits gemacht
                        //serverFunctions.movePlayerIfMoveIsPossible(initBoard,playerID,buttonPositionPressed);
                        // log
                        // LOGGER.info("movevalid " + serverFunctions.checkMazeIfMoveIsPossible(initBoard, buttonPositionPressed, playerID));
                        playerPosToString(initBoard);


                    }
                    else if(message.startsWith("leave ")){
                        String[] leaveString = message.split("\\s+");
                        int idLeftPlayer = Integer.parseInt(leaveString[1]);

                        //wenn der spieler geht der gerade dran ist und es mehr als ein spieler ist
                        if((connections.get(playersTurnCounter).getpId() == idLeftPlayer) && (connections.size() > 1)){

                            if(idLeftPlayer == connections.get(connections.size()-1).getpId()){
                                playersTurnID =connections.get(0).getpId();
                                playersTurnCounter = 0;
                                broadcast("playersTurnID " + playersTurnID);
                                broadcast("draw ");
                            }else{
                                System.out.println("else");
                                //wenn es nicht der lezte ist -> liste verkürzt sich um eins -> wie eins weitergehen
                                //hole aber die nächste spieler ID -> +1
                                playersTurnID = connections.get(playersTurnCounter+1).getpId();
                                broadcast("playersTurnID " + playersTurnID);
                                broadcast("draw ");
                            }
                        }
                        //wenn ein spieler geht der nicht am zug ist und vor dem jetzigen spieler ist
                        //Bsp. spieler 3 ist dran und spieler 1 verlässt das spiel
                        else if(idLeftPlayer < connections.get(playersTurnCounter).getpId()){
                            System.out.println("previous person left");
                            playersTurnCounter --;
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
                                jth.println("disabledButtonID " + disabledButtonID);
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
                                }

                                jth.println("draw ");

                            }
                            else if (message.startsWith("leave")) {
                                String[] tmpLeave = message.split("\\s+");
                                // log incoming leave message
                                LOGGER.info("INCOMING leave");
                                // send disconnect to all clients
                                jth.println("disconnect player_0" + tmpLeave[1]);
                                // log outgoing disconnect message
                                LOGGER.info("OUTGOING disconnect player_0" + tmpLeave[1]);

                                //jth.println("playersTurnID " + playersTurnID);
                                //jth.println("draw ");

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

    public void broadcast(String s) {
        for (Connection jth : connections) {
            jth.println(s);
        }
    }

}