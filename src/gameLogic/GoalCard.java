package gameLogic;


import javax.swing.*;

public class GoalCard {

    private String symbol;
    private ImageIcon symbolImage;

    public GoalCard(String symbol, ImageIcon symbolImage) {
        this.symbol = symbol;
        this.symbolImage = symbolImage;
    }



    public String getSymbol() {
        return symbol;
    }

    public ImageIcon getSymbolImage() {
        return symbolImage;
    }
}
