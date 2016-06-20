package gameLogic;

public class Tiles {

    /** ATTRIBUTE */
    private int id;
    private boolean moveable;
    private Position position;
    private String shape;
    private String symbol;
    private int rotation;

    /** KONSTRUKTOR */
    public Tiles(int id, boolean moveable, Position position, String shape, String symbol, int rotation){
        this.id = id;
        this.moveable = moveable;
        this.position = position;
        this.shape = shape;
        this.symbol = symbol;
        this.rotation = rotation;
    }

    /** GETTER                                         SETTER */
    public boolean getMoveable(){return moveable;}
    public Position getPosition(){return position;}  public void setPosition(Position position){this.position = position;}
    public String getShape(){return shape;}          public void setShape(String shape){this.shape = shape;}
    public String getSymbol(){return symbol;}
    public int getRotation(){return rotation;}       public void setRotation(int rotation){this.rotation = rotation;}
    public int getId(){return this.id;}              public void setID(int id){this.id = id;}

    /** PUSH */
    public void push(int pRotation, int pX, int pY) {
        setRotation(pRotation);
        setPosition(new Position(pX,pY));
    }
}
