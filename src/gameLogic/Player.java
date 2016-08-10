package gameLogic;

import java.awt.*;
import java.util.List;

public class Player {

    /** ATTRIBUTE */
    private Position startPosition;
    private Position acutalPosition;
    private int playerID;
    //private GoalCard[] cardSymbolNeeded;
    private List<GoalCard> creaturesNeeded;
    private Color color;
    private boolean turn;
    private int score;
    private String nameOfPlayer;
    private String message;

    /** KONSTRUKTOR */
    public Player(Position startPosition, Position actualPosition, int playerID, Color color, boolean turn, int score, String nameOfPlayer, List<GoalCard> creaturesNeeded) {
        this.startPosition = startPosition;
        this.acutalPosition = actualPosition;
        this.playerID = playerID;
        //this.cardSymbolNeeded = cardSymbolNeeded;
        this.creaturesNeeded = creaturesNeeded;
        this.color = color;
        this.turn = turn;
        this.score = score;
        this.nameOfPlayer = nameOfPlayer;


    }

    /** GETTER                                                              SETTER */
    public Position getStartPosition(){return startPosition;}
    public Position getAcutalPosition(){return acutalPosition;}             public void setActualPosition(Position position){this.acutalPosition = position;}
    public int getPlayerID(){return playerID;}
    public List<GoalCard> getCreaturesNeeded(){return creaturesNeeded;}     public void setCreaturesNeeded(List<GoalCard> creaturesNeeded){this.creaturesNeeded=creaturesNeeded;}
    public Color getColor(){return color;}
    public boolean getTurn(){return turn;}                                  public void setTurn(boolean turn){this.turn = turn;}
    public int getScore(){return score;}                                    public void setScore(int score){this.score = score;}
    public String getNameOfPlayer(){return nameOfPlayer;}
    public String getMessage() {return this.message;}                       public void setMessage(String message) {this.message = message;}

}
