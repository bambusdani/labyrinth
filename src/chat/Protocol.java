package chat;

public class Protocol {
    private boolean validMove;
    private boolean madeMove;

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
}
