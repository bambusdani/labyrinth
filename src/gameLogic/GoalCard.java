package gameLogic;

import javax.swing.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class GoalCard {

    private String creature;
    private ImageIcon symbolImage;
    private int goalCardID;

    /**
     * Constructor
     * @param symbol
     * @param symbolImage
     * @param goalCardID
     */
    public GoalCard(String symbol, ImageIcon symbolImage, int goalCardID) {
        this.creature = symbol;
        this.symbolImage = symbolImage;
        this.goalCardID = goalCardID;

    }

    /**
     * Getter
     */
    public String getCreature() {
        return creature;
    }
    public ImageIcon getSymbolImage() {
        return symbolImage;
    }
    public int getGoalCardID(){
        return goalCardID;
    }
}
