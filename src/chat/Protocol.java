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
        pass();
        push();
        leave();
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
    // 'PASS'
    // 'PUSH tileID rotation x y'
    // 'LEAVE'
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

    //================================================================================
    // parameter 'PASS'
    //================================================================================
    public void pass() {
        splitMessage();

        if (message.substring(0,4).equalsIgnoreCase("PASS")) {

            //--------------------------------------------------------------------------------
            // set player turn false
            board.getPlayer(playerID).setTurn(false);

            //--------------------------------------------------------------------------------
            // player id's go from 1-4
            // set turn for next player true
            if(playerID+1 > 4) {
                // next player is '1'
                board.getPlayer(1).setTurn(true);
                setMessage("Player " + playerID +
                        " has passed.\n" + "It's Player's 1 turn.");
            } else {
                board.getPlayer(playerID+1).setTurn(true);
                setMessage("Player " + playerID +
                        " has passed.\n" + "It's Player's " + (playerID+1) + " turn.");
            }
        }
    }

    //================================================================================
    // parameter 'PUSH'
    //================================================================================
    public void push() {
        splitMessage();


        if (message.substring(0,4).equalsIgnoreCase("PUSH")) {
            //--------------------------------------------------------------------------------
            // extract tileID, rotation and x y from e.g 'push 1 2 3 5'
            // tileID = 6th character
            // rotation = 8th character
            // x position = 10th character
            // y position = 12th character
            int tmpTileID = Integer.parseInt(message.substring(5,6));
            int tmpRotation = Integer.parseInt(message.substring(7,8));
            int tmpX = Integer.parseInt(message.substring(9,10));
            int tmpY = Integer.parseInt(message.substring(11,12));

            board.pushTile(tmpTileID, tmpRotation, tmpX, tmpY);

            setMessage("Player " + playerID + " pushed tile " + tmpTileID +
                    " with rotation " + tmpRotation +
                    " to " + tmpX + " " + tmpY + ".");
        }
    }

    //================================================================================
    // parameter 'LEAVE'
    //================================================================================
    public void leave() {
        splitMessage();

        if (message.substring(0,5).equalsIgnoreCase("LEAVE")) {
            //broadcast
            setMessage("Player " + playerID + " left the game.");
        }
    }
}
