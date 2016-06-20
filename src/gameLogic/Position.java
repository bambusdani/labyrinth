package gameLogic;

public class Position {

    /** ATTRIBUTE */
       private int x;
       private int y;

    /** KONSTRUKTOR */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /** GETTER                       SETTER */
    public int getX(){
        return x;
    }  public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }  public void setY(int y){
        this.y = y;
    }
}
