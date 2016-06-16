package gameLogic;

import java.awt.*;

public class MyPlayer {

    /** ATTRIBUTE */
    private MyPosition startPosition;
    private MyPosition acutalPosition;
    private String pID;
    private String[] creaturesNeeded;
    private Color color;
    private boolean turn;
    private int score;

    /** KONSTRUKTOR */
    public MyPlayer(MyPosition startPosition, MyPosition actualPosition, String pID, String[] creaturesNeeded, Color color, boolean turn, int score) {
        this.startPosition = startPosition;
        this.acutalPosition = actualPosition;
        this.pID = pID;
        this.creaturesNeeded = creaturesNeeded;
        this.color = color;
        this.turn = turn;
        this.score = score;
    }

    /** GETTER                                                      SETTER */
    public MyPosition getStartPosition(){return startPosition;}
    public MyPosition getAcutalPosition(){return acutalPosition;}   public void setAcutalPosition(MyPosition position){this.acutalPosition = position;}
    public String getPID(){return pID;}                             public void setPID(String pID){this.pID = pID;}
    public String[] getCreaturesNeeded(){return creaturesNeeded;}   public void setCreaturesNeeded(String[] creaturesNeeded){this.creaturesNeeded=creaturesNeeded;}
    public Color getColor(){return color;}
    public boolean getTurn(){return turn;}                          public void setTurn(boolean turn){this.turn = turn;}
    public int getScore(){return score;}                            public void setScore(int score){this.score = score;}

}
