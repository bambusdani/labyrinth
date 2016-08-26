package gameLogic;

import java.awt.*;
import java.util.List;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Player {

    /**
     * Attributes
     */
    private Position startPosition;
    private Position acutalPosition;
    private int playerID;
    private List<GoalCard> creaturesNeeded;
    private Color color;
    private boolean turn;
    private int score;
    private String nameOfPlayer;
    private String message;

    /**
     * Constructor
     * @param startPosition
     * @param actualPosition
     * @param playerID
     * @param color
     * @param turn
     * @param score
     * @param nameOfPlayer
     * @param creaturesNeeded
     */
    public Player(Position startPosition, Position actualPosition, int playerID, Color color, boolean turn, int score, String nameOfPlayer, List<GoalCard> creaturesNeeded) {
        this.startPosition = startPosition;
        this.acutalPosition = actualPosition;
        this.playerID = playerID;
        this.creaturesNeeded = creaturesNeeded;
        this.color = color;
        this.turn = turn;
        this.score = score;
        this.nameOfPlayer = nameOfPlayer;
    }

    /**
     * Getter Setter
     */
    public Position getAcutalPosition(){return acutalPosition;}
    public int getPlayerID(){return playerID;}
    public List<GoalCard> getCreaturesNeeded(){return creaturesNeeded;}
    public Color getColor(){return color;}
    public int getScore(){return score;}
    public String getNameOfPlayer(){return nameOfPlayer;}
    public String getMessage() {return this.message;}

    public void setActualPosition(Position position){this.acutalPosition = position;}
    public void setScore(int score){this.score = score;}
    public void setNameOfPlayer(String name){nameOfPlayer = name;}
    public void setMessage(String message) {this.message = message;}
}
