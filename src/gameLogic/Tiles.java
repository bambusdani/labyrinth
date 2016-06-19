package gameLogic;

public class Tiles {

    public Tiles(boolean moveable, int positionX, int positionY, String shape, String symbol, int rotation) {
        // TODO Auto-generated constructor stub
        this.moveable = moveable;
        this.positionX = positionX;
        this.positionY = positionY;
        this.shape = shape;
        this.symbol = symbol;
        this.rotation = rotation;
    }

    private boolean moveable;
    private int positionX;
    private int positionY;
    private String shape;
    private String symbol;
    private int rotation;


    public void setPositionX(int position){
        this.positionX = position;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public String getShape() {
        return shape;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setRotation(int rotation){
        this.rotation = rotation;
    }
    public int getRotation(){
        return rotation;
    }





}