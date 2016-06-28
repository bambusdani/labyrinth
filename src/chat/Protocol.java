package chat;

import gameLogic.Board;

public class Protocol extends Thread {
    private Board board;

    private String[] fullMessage;
    private String username;
    private String message;
    private int playerID;

    public Protocol() {
        board = new Board();
        playerID = 0;
    }

    public void validate() {
        chat();
        move();
    }

    public void splitMessage() {
        fullMessage = message.split(": ");
        username = "[" + fullMessage[0].substring(1, fullMessage[0].length()-1) + "]: ";
        message = fullMessage[fullMessage.length-1];

        assignUsername();
    }

    public void assignUsername() {
        // assign playername to work with the board
        for (int j = 0; j < board.getAllPlayers().length; j++) {
            if(username.equalsIgnoreCase(board.getAllPlayers()[j].getNameOfPlayer())) {
                playerID = board.getAllPlayers()[j].getPlayerID();
            }
        }
    }

    public synchronized String getMessage() {
        validate();
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
    }

    //================================================================================
    // Beginning with chat Protocol. Avaiable is
    // 'CHAT message'
    // 'MOVE x y'
    //================================================================================


    //================================================================================
    // parameter 'CHAT'
    //================================================================================
    public void chat() {
        splitMessage();

        if (message.substring(0, 4).equalsIgnoreCase("CHAT")) {
            //--------------------------------------------------------------------------------
            // get actual chat message:
            // message sent to server could look like:
            // "CHAT hello"
            // so we broadcast message from substring 5 (counting from 0)
            String tmpChat = message.substring(5);

            // print username + message
            setMessage(username + tmpChat);
        }
    }

    //================================================================================
    // parameter 'MOVE'
    //================================================================================
    public void move() {
        splitMessage();

        if (message.substring(0, 4).equalsIgnoreCase("MOVE")) {
            //--------------------------------------------------------------------------------
            // extract x and y potistion from message e.g 'move 1 1' with substring
            // x position = 6th character
            // y position = 8th character
            int tmpX = Integer.parseInt(message.substring(5, 6));
            int tmpY = Integer.parseInt(message.substring(7, 8));

            //--------------------------------------------------------------------------------
            // set new position
            //board.getPlayer(playerID).setPositionX(board.getPlayer(playerID).getPositionX()+tmpX); (alte Version)
            board.getPlayer(playerID).getAcutalPosition().setX(board.getPlayer(playerID).getAcutalPosition().getX() + tmpX);
            //board.getPlayer(playerID).setPositionY(board.getPlayer(playerID).getPositionY()+tmpY);
            board.getPlayer(playerID).getAcutalPosition().setY(board.getPlayer(playerID).getAcutalPosition().getY() + tmpY);

            //--------------------------------------------------------------------------------
            // broadcast to all players
            setMessage("Player " + board.getPlayer(playerID).getPlayerID() +
                    " moved to: " + board.getPlayer(playerID).getAcutalPosition().getX() + ", " + board.getPlayer(playerID).getAcutalPosition().getY());
        }
    }
}
