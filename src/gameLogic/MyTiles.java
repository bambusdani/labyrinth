package gameLogic;

public class MyTiles {

    /** ATTRIBUTE */
    private boolean moveable;
    private MyPosition position;
    private String shape;
    private String symbol;

    /** KONSTRUKTOR */
    public MyTiles(boolean moveable, MyPosition position, String shape, String symbol){
        this.moveable = moveable;
        this.position = position;
        this.shape = shape;
        this.symbol = symbol;
    }

    /** GETTER                                         SETTER */
    public boolean getMoveable(){return moveable;}
    public MyPosition getPosition(){return position;}  public void setPosition(MyPosition position){this.position = position;}
    public String getShape(){return shape;}
    public String getSymbol(){return symbol;}
}
