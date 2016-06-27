package chat;

public class Protocol {
    private boolean validMove;
    private boolean madeMove;
    private String message;

    public Protocol() {
        this.validMove = false;
        this.madeMove = false;
    }

    public boolean isValidMove() {
        return validMove;
    }

    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }

    public boolean isMadeMove() {
        return madeMove;
    }

    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        System.out.println(message);
    }
}
