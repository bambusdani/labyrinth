package chat;

import gameLogic.Board;

public class Prot {
    private Board board;

    private String[] incMessage;
    private String username;
    private String message;
    private String outMessage;
    private int playerID;

    public Prot() {
        board = new Board();
        this.playerID = 0;
    }

    public void setIncMessage(String[] incMessage) {
        //extract username + message
        this.username = "[" + incMessage[0].substring(1, incMessage[0].length()-1) + "]: ";
        this.message = incMessage[1];

        // assign player name to work with the board
        String tmpUsername = incMessage[0].substring(1, incMessage[0].length()-1);

        for (int j = 0; j < board.getAllPlayers().length; j++) {
            if(tmpUsername.equalsIgnoreCase(board.getAllPlayers()[j].getNameOfPlayer())) {
                playerID = board.getAllPlayers()[j].getPlayerID();
            }
        }
    }

    public String getOutMessage() {
        validate();
        return this.outMessage;
    }

    public void validate() {
        // Be aware! Length of substring may differ
        if(this.message.substring(0, 4).equalsIgnoreCase("chat")) {
            chat();
        } else if(this.message.substring(0, 4).equalsIgnoreCase("move")) {
            move();
        }
    }

    //================================================================================
    // Beginning with chat Protocol. Avaiable is
    // 'chat message'
    // 'move x y'
    // 'pass'
    // 'push tileID rotation x y'
    // 'leave'
    //================================================================================

    //================================================================================
    // parameter 'chat'
    //================================================================================
    public void chat() {
        //--------------------------------------------------------------------------------
        // get actual chat message:
        // message sent to server could look like:
        // "chat hello"
        // we broadcast message from substring 5 (counting from 0)
        this.outMessage = this.username + this.message.substring(5);
    }

    //================================================================================
    // parameter 'move'
    //================================================================================
    public void move() {
        //--------------------------------------------------------------------------------
        // extract x and y position from message e.g 'move 1 1' with substring
        // x position = 6th character
        // y position = 8th character
        int tmpX = Integer.parseInt(message.substring(5, 6));
        int tmpY = Integer.parseInt(message.substring(7, 8));

        //--------------------------------------------------------------------------------
        // set new position
        board.getPlayer(playerID).getAcutalPosition().setX(board.getPlayer(playerID).getAcutalPosition().getX() + tmpX);
        board.getPlayer(playerID).getAcutalPosition().setY(board.getPlayer(playerID).getAcutalPosition().getY() + tmpY);

        //--------------------------------------------------------------------------------
        // broadcast to all players
        this.outMessage = "Player " + board.getPlayer(playerID).getPlayerID() +
                " moved to: " + board.getPlayer(playerID).getAcutalPosition().getX() + ", " + board.getPlayer(playerID).getAcutalPosition().getY();
    }
}