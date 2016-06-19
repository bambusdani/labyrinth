package gameLogic;

public class Tiles {

    /** ATTRIBUTE */
    private boolean moveable;
    private Position position;
    private String shape;
    private String symbol;
    private int rotation;

    /** KONSTRUKTOR */
    public Tiles(boolean moveable, Position position, String shape, String symbol, int rotation){
        this.moveable = moveable;
        this.position = position;
        this.shape = shape;
        this.symbol = symbol;
        this.rotation = rotation;
    }

    /** GETTER                                         SETTER */
    public boolean getMoveable(){return moveable;}
    public Position getPosition(){return position;}  public void setPosition(Position position){this.position = position;}
    public String getShape(){return shape;}
    public String getSymbol(){return symbol;}
    public int getRotation(){return rotation;}       public void setRotation(int rotation){this.rotation = rotation;}
}
