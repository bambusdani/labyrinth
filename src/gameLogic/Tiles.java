package gameLogic;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Tiles {

    /**
     * Attributes
     */
    private int id;
    private boolean moveable;
    private Position position;
    private Shape shape;
    private int rotation;

    /**
     * Constructor
     * @param id
     * @param moveable
     * @param position
     * @param shape
     * @param rotation
     */
    public Tiles(int id, boolean moveable, Position position, Shape shape , int rotation){
        this.id = id;
        this.moveable = moveable;
        this.position = position;
        this.shape = shape;
        this.rotation = rotation;
    }

    /**
     * Getter Setter
     */
    public boolean getMoveable(){return moveable;}
    public Position getPosition(){return position;}
    public Shape getShape(){return shape;}
    public int getRotation(){return rotation;}
    public int getId(){return this.id;}

    public void setPosition(Position position){this.position = position;}
    public void setRotation(int rotation){this.rotation = rotation;}

    /**
     * PUSH
     */
    public void push(int pRotation, int pX, int pY) {
        setRotation(pRotation);
        setPosition(new Position(pX,pY));
    }
}
