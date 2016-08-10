package gameLogic;


import javax.swing.*;

public class GoalCard {

    private String creature;
    private ImageIcon symbolImage;

    public GoalCard(String symbol, ImageIcon symbolImage) {
        this.creature = symbol;
        this.symbolImage = symbolImage;
    }



    public String getCreature() {
        return creature;
    }

    public ImageIcon getSymbolImage() {
        return symbolImage;
    }
}
