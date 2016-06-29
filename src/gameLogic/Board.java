package gameLogic;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Board {

    //=============================================================
    //Save Images in Variables
    // Tiles
    //"I"
    private ImageIcon tile1Image   = new ImageIcon("src/resources/tiles/tile1.jpg");
    //"L"
    private ImageIcon tile2Image   = new ImageIcon("src/resources/tiles/tile2.jpg");
    //movable
    private ImageIcon tile3Image   = new ImageIcon("src/resources/tiles/tile3.jpg");
    private ImageIcon tile4Image   = new ImageIcon("src/resources/tiles/tile4.jpg");
    private ImageIcon tile5Image   = new ImageIcon("src/resources/tiles/tile5.jpg");
    private ImageIcon tile6Image   = new ImageIcon("src/resources/tiles/tile6.jpg");
    private ImageIcon tile7Image   = new ImageIcon("src/resources/tiles/tile7.jpg");
    private ImageIcon tile8Image   = new ImageIcon("src/resources/tiles/tile8.jpg");
    private ImageIcon tile9Image   = new ImageIcon("src/resources/tiles/tile9.jpg");
    private ImageIcon tile10Image  = new ImageIcon("src/resources/tiles/tile10.jpg");
    private ImageIcon tile11Image  = new ImageIcon("src/resources/tiles/tile11.jpg");
    private ImageIcon tile12Image  = new ImageIcon("src/resources/tiles/tile12.jpg");
    private ImageIcon tile13Image  = new ImageIcon("src/resources/tiles/tile13.jpg");
    private ImageIcon tile14Image  = new ImageIcon("src/resources/tiles/tile14.jpg");
    //not moveable
    private ImageIcon tile15Image  = new ImageIcon("src/resources/tiles/tile15.jpg");
    private ImageIcon tile16Image  = new ImageIcon("src/resources/tiles/tile16.jpg");
    private ImageIcon tile17Image  = new ImageIcon("src/resources/tiles/tile17.jpg");
    private ImageIcon tile18Image  = new ImageIcon("src/resources/tiles/tile18.jpg");
    private ImageIcon tile19Image  = new ImageIcon("src/resources/tiles/tile19.jpg");
    private ImageIcon tile20Image  = new ImageIcon("src/resources/tiles/tile20.jpg");
    private ImageIcon tile21Image  = new ImageIcon("src/resources/tiles/tile21.jpg");
    private ImageIcon tile22Image  = new ImageIcon("src/resources/tiles/tile22.jpg");
    private ImageIcon tile23Image  = new ImageIcon("src/resources/tiles/tile23.jpg");
    private ImageIcon tile24Image  = new ImageIcon("src/resources/tiles/tile24.jpg");
    private ImageIcon tile25Image  = new ImageIcon("src/resources/tiles/tile25.jpg");
    private ImageIcon tile26Image  = new ImageIcon("src/resources/tiles/tile26.jpg");
    //start position
    private ImageIcon tile27Image  = new ImageIcon("src/resources/tiles/tile27.jpg");
    private ImageIcon tile28Image  = new ImageIcon("src/resources/tiles/tile28.jpg");
    private ImageIcon tile29Image  = new ImageIcon("src/resources/tiles/tile29.jpg");
    private ImageIcon tile30Image  = new ImageIcon("src/resources/tiles/tile30.jpg");

    // Creatures
    private ImageIcon creatureImage1 = new ImageIcon("src/resources/creatures/creature1.jpg");
    private ImageIcon creatureImage2 = new ImageIcon("src/resources/creatures/creature2.jpg");
    //should be all!!!!!!!!!!!!!!!!


    //==============================================================
    /** -> ATTRIBUTE */
    //liste aller player (maximal 4)
    private Player[] allPlayers = new Player[4];
    private Tiles[][] tiles = new Tiles[7][7];

    private Tiles nextTile = new Tiles(0 ,true, null, new Shape(tile2Image, "L", new boolean[] {true,false,false,true} , null ), 0);

    //=============================================================


    //============================
    /** -> KONSTRUKTOR */
    public Board(){


        //--------------------------------------------
        //set size of all images
        tile1Image.setImage(tile1Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile2Image.setImage(tile2Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile3Image.setImage(tile3Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile4Image.setImage(tile4Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile5Image.setImage(tile5Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile6Image.setImage(tile6Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile7Image.setImage(tile7Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile8Image.setImage(tile8Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile9Image.setImage(tile9Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile10Image.setImage(tile10Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile11Image.setImage(tile11Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile12Image.setImage(tile12Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile13Image.setImage(tile13Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile14Image.setImage(tile14Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile15Image.setImage(tile15Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile16Image.setImage(tile16Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile17Image.setImage(tile17Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile18Image.setImage(tile18Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile19Image.setImage(tile19Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile20Image.setImage(tile20Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile21Image.setImage(tile21Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile22Image.setImage(tile22Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile23Image.setImage(tile23Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile24Image.setImage(tile24Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile25Image.setImage(tile25Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile26Image.setImage(tile26Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile27Image.setImage(tile27Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile28Image.setImage(tile28Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile29Image.setImage(tile29Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        tile30Image.setImage(tile30Image.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));

        //creatures
        creatureImage1.setImage(creatureImage1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        creatureImage2.setImage(creatureImage2.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));





        //--------------------------------------------

        //Tiles erzeugen
        createTiles(this.tiles);

        //Tiles vermischen
        shuffleTiles(this.tiles);

        String[] creaturesNeeded = {"creature1", "creature2"};

        //Liste mit allen Creaturen zu finden
        // vl liste besser geeignet als array
        // alle einzeln erstellen in einer liste mischen und dann auf spieler aufteilen
        GoalCard mouse = new GoalCard("mouse", creatureImage1);
        GoalCard crown = new GoalCard("crown", creatureImage2);
        GoalCard[] allCreaturesNeeded = { mouse, crown };
        // vl eine liste und nur immer eine bestimmte anzahl raus nehmen
        GoalCard[] creaturesNeededPlayer1 = {mouse};
        GoalCard[] creaturesNeededPlayer2 = {crown};
        GoalCard[] creaturesNeededPlayer3 = {null};
        GoalCard[] creaturesNeededPlayer4 = {null};



                                  //startPosition   , actualPosition   , ID, Color                      ,turn,score, name, cardsymbolNeeded
        allPlayers[0] = new Player(new Position(0,0), new Position(4,5), 1 , new Color(255, 0 ,0),      true,   5, "Rehan",    creaturesNeededPlayer1);
        allPlayers[1] = new Player(new Position(0,6), new Position(2,5), 2 , new Color(0, 255 ,0),      false,  6, "Marvin",   creaturesNeededPlayer2);
        allPlayers[2] = new Player(new Position(6,0), new Position(6,3), 3 , new Color(0, 255 ,255),    false,  6, "Daniel",   creaturesNeededPlayer3);
        allPlayers[3] = new Player(new Position(6,6), new Position(2,5), 4 , new Color(255, 255 ,0),    false,  6, null ,      creaturesNeededPlayer4);
    }
    //===========================

    /** -> GETTER                                                    SETTER */
    public Player getPlayer(int id){return this.allPlayers[id];}
    public Tiles[][] getallTiles(){return this.tiles;}
    public Tiles getTile(int x, int y){return this.tiles[x][y];}     public void setTiles(int x, int y, Tiles tile){this.tiles[x][y] = tile;}
    public Tiles getNextTile(){return nextTile;}                     public void setNextTile(Tiles nextTile){this.nextTile = nextTile;}
    public Player[] getAllPlayers(){return  this.allPlayers;}

    //=================================================================================
    /** -> SPIELER VERWALTUNG */

    //=================================================================================


    //======================================================
    /** -> RANDOMIZE BOARD */
    //Tiles werden zufällig vertauscht und zurückgegeben.
    public Tiles[][] shuffleTiles(Tiles[][] tiles) {
        //saves 2d Array to an ArrayList

        List<Tiles> listOfMovableTiles = new ArrayList<Tiles>();
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].getMoveable()) {
                    listOfMovableTiles.add(tiles[i][j]);
                }
            }
        }

        //shuffle ArrayList
        Collections.shuffle(listOfMovableTiles);


        int x = 0; int y = 0; int listCounter = 0;
        for(int index = 0; index < 49; index++){
            if( x % 2 != 0 && y % 2 == 0 ){
                tiles[x][y] = listOfMovableTiles.get(listCounter);
                x++;
                listCounter++;
            }
            else if( y % 2 != 0 ){
                if(x==6) {
                    tiles[x][y] = listOfMovableTiles.get(listCounter);
                    x = 0;
                    y++;
                    listCounter++;
                }else{
                    tiles[x][y] = listOfMovableTiles.get(listCounter);
                    x++;
                    listCounter++;
                }
            }
            else{
                if(x==6) {
                    x = 0;
                    y++;
                }else{
                    x++;
                }
            }
        }
        return tiles;

    }
    //======================================================


    //=================================================================================================================================
    /** -> Creates Tiles */
    public void createTiles(Tiles[][] tiles){
        //Alle tiles extra gesetzt, dadurch kann man jedes tile genau festlegen...
        //new Tiless(id, moveable, position(x,y), shape(image,shapeOfImage, possibleWaysToMove, Symbol), rotation)
        //erste Reihe                                                                                                                               //fünfte Reihe

        tiles[0][0] = new Tiles(1,false,new Position(0,0), new Shape(tile30Image, "L" , new boolean[] {false,true,true,false},"green"),0);          tiles[4][0] = new Tiles(29,false,new Position(4,0),new Shape(tile16Image, "T" , new boolean[] {false,true,true,true},"map"),0);
        tiles[0][1] = new Tiles(2,true,new  Position(0,1),  new Shape(tile8Image, "L" ,  new boolean[] {true,true,false,false},"motte"),0);         tiles[4][1] = new Tiles(30,true,new  Position(4,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][2] = new Tiles(3,false,new Position(0,2), new Shape(tile17Image, "T" , new boolean[] {true,true,true,false},"kerze"),0);           tiles[4][2] = new Tiles(31,false,new Position(4,2),new Shape(tile19Image, "T" , new boolean[] {false,true,true,true},"krone"),0);
        tiles[0][3] = new Tiles(4,true,new  Position(0,3),  new Shape(tile9Image, "T" ,  new boolean[] {true,true,false,true}, "drache"),0);        tiles[4][3] = new Tiles(32,true,new  Position(4,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][4] = new Tiles(5,false,new Position(0,4), new Shape(tile21Image, "T" , new boolean[] {true,true,true,false},"helm"),0);            tiles[4][4] = new Tiles(33,false,new Position(4,4),new Shape(tile23Image, "T" , new boolean[] {true,false,true,true},"key"),0);
        tiles[0][5] = new Tiles(6,true,new  Position(0,5),  new Shape(tile3Image, "L" ,  new boolean[] {false,true,true,false},"eule"),0);          tiles[4][5] = new Tiles(34,true,new  Position(4,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][6] = new Tiles(7,false,new Position(0,6), new Shape(tile28Image, "L" , new boolean[] {true,true,false,false},"blue"),0);           tiles[4][6] = new Tiles(35,false,new Position(4,6),new Shape(tile26Image, "T" , new boolean[] {true,true,false,true},"skul"),0);
        //zweite Reihe                                                                                                                              //sechste Reihe
        tiles[1][0] = new Tiles(8,true,new  Position(1,0),  new Shape(tile4Image, "T" ,  new boolean[] {true,true,false,true},"zauberer"),0);       tiles[5][0] = new Tiles(36,true,new Position(5,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][1] = new Tiles(9,true,new  Position(1,1),  new Shape(tile5Image, "L" ,  new boolean[] {true,true,false,false},"käfer"),0);         tiles[5][1] = new Tiles(37,true,new Position(5,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][2] = new Tiles(10,true,new Position(1,2), new Shape(tile6Image, "T" ,  new boolean[] {true,true,false,true},"Fgeist"),0);          tiles[5][2] = new Tiles(38,true,new Position(5,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][3] = new Tiles(11,true,new Position(1,3), new Shape(tile7Image, "L" ,  new boolean[] {false,true,true,false},"salamander"),0);     tiles[5][3] = new Tiles(39,true,new Position(5,3),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][4] = new Tiles(12,true,new Position(1,4), new Shape(tile10Image, "T" , new boolean[] {true,true,false,true},"troll"),0);           tiles[5][4] = new Tiles(40,true,new Position(5,4),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][5] = new Tiles(13,true,new Position(1,5), new Shape(tile11Image, "L" , new boolean[] {false,false,true,true},"mouse"),0);          tiles[5][5] = new Tiles(41,true,new Position(5,5),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][6] = new Tiles(14,true,new Position(1,6), new Shape(tile12Image, "T" , new boolean[] {true,true,false,true},"fledermaus"),0);      tiles[5][6] = new Tiles(42,true,new Position(5,6),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        //dritte Reihe                                                                                                                              //siebte Reihe
        tiles[2][0] = new Tiles(15,false,new Position(2,0),new Shape(tile15Image, "T" , new boolean[] {false,true,true,true},"ring"),0);            tiles[6][0] = new Tiles(43,false,new Position(6,0),new Shape(tile29Image, "L" , new boolean[] {false,true,false,true},"yellow"),0);
        tiles[2][1] = new Tiles(16,true,new  Position(2,1), new Shape(tile13Image, "T" , new boolean[] {true,true,false,true},"geist"),0);          tiles[6][1] = new Tiles(44,true,new  Position(6,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][2] = new Tiles(17,false,new Position(2,2),new Shape(tile18Image, "T" , new boolean[] {true,true,true,false},"schatz"),0);          tiles[6][2] = new Tiles(45,false,new Position(6,2),new Shape(tile20Image, "T" , new boolean[] {true,false,true,true},"book"),0);
        tiles[2][3] = new Tiles(18,true,new  Position(2,3), new Shape(tile14Image, "L" , new boolean[] {true,true,false,false},"spinne"),0);        tiles[6][3] = new Tiles(46,true,new  Position(6,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][4] = new Tiles(19,false,new Position(2,4),new Shape(tile22Image, "T" , new boolean[] {true,true,false,true},"rubin"),0);           tiles[6][4] = new Tiles(47,false,new Position(6,4),new Shape(tile24Image, "T" , new boolean[] {true,false,true,true},"coins"),0);
        tiles[2][5] = new Tiles(20,true,new  Position(2,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);            tiles[6][5] = new Tiles(48,true,new  Position(6,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][6] = new Tiles(21,false,new Position(2,6),new Shape(tile25Image, "T" , new boolean[] {true,true,false,true},"schwert"),0);         tiles[6][6] = new Tiles(49,false,new Position(6,6),new Shape(tile27Image, "L" , new boolean[] {true,false,false,true},"red"),0);
        //vierte Reihe
        tiles[3][0] = new Tiles(22,true,new Position(3,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][1] = new Tiles(23,true,new Position(3,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][2] = new Tiles(24,true,new Position(3,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][3] = new Tiles(25,true,new Position(3,3),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][4] = new Tiles(26,true,new Position(3,4),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][5] = new Tiles(27,true,new Position(3,5),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][6] = new Tiles(28,true,new Position(3,6),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);

    }
    //===================================================================================================================================

    //=================================================================================
    /** PUSH TILE */
    public void pushTile(int pID, int pRotation, int pX, int pY) {
        for (int i = 0; i < getallTiles().length; i++) {
            for (int j = 0; j < getallTiles()[0].length; j++) {
                if(pID == getallTiles()[i][j].getId()) {
                    getallTiles()[i][j].push(pRotation, pX, pY);
                }
            }
        }
    }
    //=================================================================================
}
