package chat;

import gameLogic.Board;

public class Protocol {
    private Board board;

    private String[] incMessage;
    private String username;
    private String message;
    private String outMessage;
    private int playerID;

    public Protocol() {
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
        } else if(this.message.substring(0, 4).equalsIgnoreCase("pass")) {
            pass();
        } else if(this.message.substring(0, 4).equalsIgnoreCase("push")) {
            push();
        } else if(this.message.substring(0, 5).equalsIgnoreCase("leave")) {
            leave();
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

    //================================================================================
    // parameter 'PASS'
    //================================================================================
    public void pass() {
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
                this.outMessage = "Player " + playerID +
                        " has passed.\n" + "It's Player's 1 turn.";
            } else {
                board.getPlayer(playerID+1).setTurn(true);
                this.outMessage = "Player " + playerID +
                        " has passed.\n" + "It's Player's " + (playerID+1) + " turn.";
            }
        }
    }

    //================================================================================
    // parameter 'PUSH'
    //================================================================================
    public void push() {
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

        this.outMessage = "Player " + playerID + " pushed tile " + tmpTileID +
                " with rotation " + tmpRotation +
                " to " + tmpX + " " + tmpY + ".";
    }

    //================================================================================
    // parameter 'LEAVE'
    //================================================================================
    public void leave() {
        if (message.substring(0,5).equalsIgnoreCase("LEAVE")) {
            //broadcast
            this.outMessage = "Player " + playerID + " left the game.";
        }
    }
}