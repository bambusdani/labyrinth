package gameLogic;


import javax.swing.*;

public class GoalCard {

    private String creature;
    private ImageIcon symbolImage;
    private int goalCardID;

    public GoalCard(String symbol, ImageIcon symbolImage, int goalCardID) {
        this.creature = symbol;
        this.symbolImage = symbolImage;
        this.goalCardID = goalCardID;

    }



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
