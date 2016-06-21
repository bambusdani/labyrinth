package gameLogic;

public class Tiles {

    /** ATTRIBUTE */
    private int id;
    private boolean moveable;
    private Position position;
    private Shape shape;
    private int rotation;

    //Symbol entfernt ist jetzt in Shape drin

    /** KONSTRUKTOR */
    public Tiles(int id, boolean moveable, Position position, Shape shape , int rotation){
        this.id = id;
        this.moveable = moveable;
        this.position = position;
        this.shape = shape;
        this.rotation = rotation;
    }

    /** GETTER                                         SETTER */
    public boolean getMoveable(){return moveable;}
    public Position getPosition(){return position;}  public void setPosition(Position position){this.position = position;}
    public Shape getShape(){return shape;}           public void setShape(Shape shape){this.shape = shape;}
    public int getRotation(){return rotation;}       public void setRotation(int rotation){this.rotation = rotation;}
    public int getId(){return this.id;}              public void setID(int id){this.id = id;}

    /** PUSH */
    public void push(int pRotation, int pX, int pY) {
        setRotation(pRotation);
        setPosition(new Position(pX,pY));
    }
}
