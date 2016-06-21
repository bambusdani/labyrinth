package gameLogic;

import java.awt.*;

public class Player {

    /** ATTRIBUTE */
    private Position startPosition;
    private Position acutalPosition;
    private int playerID;
    private GoalCard[] cardSymbolNeeded;
    private Color color;
    private boolean turn;
    private int score;
    private String nameOfPlayer;

    /** KONSTRUKTOR */
    public Player(Position startPosition, Position actualPosition, int playerID, Color color, boolean turn, int score, String nameOfPlayer, GoalCard[] cardSymbolNeeded) {
        this.startPosition = startPosition;
        this.acutalPosition = actualPosition;
        this.playerID = playerID;
        this.cardSymbolNeeded = cardSymbolNeeded;
        this.color = color;
        this.turn = turn;
        this.score = score;
        this.nameOfPlayer = nameOfPlayer;
    }

    /** GETTER                                                      SETTER */
    public Position getStartPosition(){return startPosition;}
    public Position getAcutalPosition(){return acutalPosition;}     public void setActualPosition(Position position){this.acutalPosition = position;}
    public int getPlayerID(){return playerID;}
    public GoalCard[] getCreaturesNeeded(){return cardSymbolNeeded;}  public void setCreaturesNeeded(GoalCard[] creaturesNeeded){this.cardSymbolNeeded=creaturesNeeded;}
    public Color getColor(){return color;}
    public boolean getTurn(){return turn;}                          public void setTurn(boolean turn){this.turn = turn;}
    public int getScore(){return score;}                            public void setScore(int score){this.score = score;}
    public String getNameOfPlayer(){return nameOfPlayer;}


}
