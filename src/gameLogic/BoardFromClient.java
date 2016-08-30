package gameLogic;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class BoardFromClient {

    /**
     * Attributes
     */
    //List of all players(max 4)
    private Player[] allPlayers = new Player[4];
    //List of all tiles 2dimensional -> sorted like playground
    private Tiles[][] tiles = new Tiles[7][7];
    private Tiles nextTile = null;
    //All tiles in 1dimensional array -> left to right then next row...
    private Tiles[] allTilesInOneArray = new Tiles[50];
    private List<GoalCard> creaturesNeeded = new ArrayList<>();
    private GoalCard[] allGoalCards = new GoalCard[28];

    private ImageIcon tile1Image   = new ImageIcon("src/resources/tiles/tile1.jpg");
    private ImageIcon tile2Image   = new ImageIcon("src/resources/tiles/tile2.jpg");

    /**
     * movable Tiles
     */
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

    /**
     * not movable tiles
     */
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
    private ImageIcon creatureImage1 = new ImageIcon("src/resources/creatures/creature1.png");
    private ImageIcon creatureImage2 = new ImageIcon("src/resources/creatures/creature2.png");
    private ImageIcon creatureImage3 = new ImageIcon("src/resources/creatures/creature3.png");
    private ImageIcon creatureImage4 = new ImageIcon("src/resources/creatures/creature4.png");
    private ImageIcon creatureImage5 = new ImageIcon("src/resources/creatures/creature5.png");
    private ImageIcon creatureImage6 = new ImageIcon("src/resources/creatures/creature6.png");
    private ImageIcon creatureImage7 = new ImageIcon("src/resources/creatures/creature7.png");
    private ImageIcon creatureImage8 = new ImageIcon("src/resources/creatures/creature8.png");
    private ImageIcon creatureImage9 = new ImageIcon("src/resources/creatures/creature9.png");
    private ImageIcon creatureImage10= new ImageIcon("src/resources/creatures/creature10.png");
    private ImageIcon creatureImage11= new ImageIcon("src/resources/creatures/creature11.png");
    private ImageIcon creatureImage12= new ImageIcon("src/resources/creatures/creature12.png");
    private ImageIcon creatureImage13= new ImageIcon("src/resources/creatures/creature13.png");
    private ImageIcon creatureImage14= new ImageIcon("src/resources/creatures/creature14.png");
    private ImageIcon creatureImage15= new ImageIcon("src/resources/creatures/creature15.png");
    private ImageIcon creatureImage16= new ImageIcon("src/resources/creatures/creature16.png");
    private ImageIcon creatureImage17= new ImageIcon("src/resources/creatures/creature17.png");
    private ImageIcon creatureImage18= new ImageIcon("src/resources/creatures/creature18.png");
    private ImageIcon creatureImage19= new ImageIcon("src/resources/creatures/creature19.png");
    private ImageIcon creatureImage20= new ImageIcon("src/resources/creatures/creature20.png");
    private ImageIcon creatureImage21= new ImageIcon("src/resources/creatures/creature21.png");
    private ImageIcon creatureImage22= new ImageIcon("src/resources/creatures/creature22.png");
    private ImageIcon creatureImage23= new ImageIcon("src/resources/creatures/creature23.png");
    private ImageIcon creatureImage24= new ImageIcon("src/resources/creatures/creature24.png");
    //startPointsCreature
    private ImageIcon creatureImageRed    = new ImageIcon("src/resources/creatures/red.png");
    private ImageIcon creatureImageBlue   = new ImageIcon("src/resources/creatures/blue.png");
    private ImageIcon creatureImageYellow = new ImageIcon("src/resources/creatures/yellow.png");
    private ImageIcon creatureImageGreen  = new ImageIcon("src/resources/creatures/green.png");

    /**
     * Constructor
     */
    public BoardFromClient(){
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
        creatureImage1.setImage(creatureImage1.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage2.setImage(creatureImage2.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage3.setImage(creatureImage3.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage4.setImage(creatureImage4.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage5.setImage(creatureImage5.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage6.setImage(creatureImage6.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage7.setImage(creatureImage7.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage8.setImage(creatureImage8.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage9.setImage(creatureImage9.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage10.setImage(creatureImage10.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage11.setImage(creatureImage11.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage12.setImage(creatureImage12.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage13.setImage(creatureImage13.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage14.setImage(creatureImage14.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage15.setImage(creatureImage15.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage16.setImage(creatureImage16.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage17.setImage(creatureImage17.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage18.setImage(creatureImage18.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage19.setImage(creatureImage19.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage20.setImage(creatureImage20.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage21.setImage(creatureImage21.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage22.setImage(creatureImage22.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage23.setImage(creatureImage23.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImage24.setImage(creatureImage24.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));

        //startPositionCreatures
        creatureImageGreen.setImage(creatureImageGreen.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImageYellow.setImage(creatureImageYellow.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImageBlue.setImage(creatureImageBlue.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));
        creatureImageRed.setImage(creatureImageRed.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT));


        /**
         * Creating all GoalCards
         * placeing all Cards into the list allCreaturesNeeded
         * shuffel all and place it in the player neededCreatures
         */
        allGoalCards[0]     = new GoalCard("mouse",     creatureImage1 , 0);
        allGoalCards[1]     = new GoalCard("krone",     creatureImage2 , 1);
        allGoalCards[2]     = new GoalCard("spinne",    creatureImage3 , 2);
        allGoalCards[3]     = new GoalCard("kerze",     creatureImage4 , 3);
        allGoalCards[4]     = new GoalCard("rubin",     creatureImage5 , 4);
        allGoalCards[5]     = new GoalCard("geist",     creatureImage6 , 5);
        allGoalCards[6]     = new GoalCard("eule",      creatureImage7 , 6);
        allGoalCards[7]     = new GoalCard("fledermaus",creatureImage8 , 7);
        allGoalCards[8]     = new GoalCard("drache",    creatureImage9 , 8);
        allGoalCards[9]     = new GoalCard("motte",     creatureImage10, 9);
        allGoalCards[10]    = new GoalCard("fgeist",    creatureImage11,10);
        allGoalCards[11]    = new GoalCard("key",       creatureImage12,11);
        allGoalCards[12]    = new GoalCard("map",       creatureImage13,12);
        allGoalCards[13]    = new GoalCard("zauberer",  creatureImage14,13);
        allGoalCards[14]    = new GoalCard("skull",     creatureImage15,14);
        allGoalCards[15]    = new GoalCard("coins",     creatureImage16,15);
        allGoalCards[16]    = new GoalCard("schatz",    creatureImage17,16);
        allGoalCards[17]    = new GoalCard("ring",      creatureImage18,17);
        allGoalCards[18]    = new GoalCard("helm",      creatureImage19,18);
        allGoalCards[19]    = new GoalCard("salamander",creatureImage20,19);
        allGoalCards[20]    = new GoalCard("troll",     creatureImage21,20);
        allGoalCards[21]    = new GoalCard("book",      creatureImage22,21);
        allGoalCards[22]    = new GoalCard("schwert",   creatureImage23,22);
        allGoalCards[23]    = new GoalCard("käfer",     creatureImage24,23);
        //Startpositionen
        allGoalCards[24]    = new GoalCard("red",       creatureImageRed,24);
        allGoalCards[25]    = new GoalCard("yellow",    creatureImageYellow,25);
        allGoalCards[26]    = new GoalCard("green",     creatureImageGreen,26);
        allGoalCards[27]    = new GoalCard("blue",      creatureImageBlue,27);

        /**
         * All Tiles in one array
         */
        nextTile= new Tiles(0 ,true, null, new Shape(tile2Image, "L", new boolean[] {true,false,false,true} , null ), 0);
        allTilesInOneArray[0]  = new Tiles(0 ,true, new Position(99,99), new Shape(tile2Image, "L", new boolean[] {true,false,false,true} , null ), 0);

        allTilesInOneArray[1]  = new Tiles(1,false,new Position(0,0), new Shape(tile30Image, "L" , new boolean[] {false,true,true,false},"green"),0);
        allTilesInOneArray[2]  = new Tiles(2,true,new  Position(0,1),  new Shape(tile8Image, "L" ,  new boolean[] {true,true,false,false},"motte"),0);
        allTilesInOneArray[3]  = new Tiles(3,false,new Position(0,2), new Shape(tile17Image, "T" , new boolean[] {true,true,true,false},"kerze"),0);
        allTilesInOneArray[4]  = new Tiles(4,true,new  Position(0,3),  new Shape(tile9Image, "T" ,  new boolean[] {true,true,false,true}, "drache"),0);
        allTilesInOneArray[5]  = new Tiles(5,false,new Position(0,4), new Shape(tile21Image, "T" , new boolean[] {true,true,true,false},"helm"),0);
        allTilesInOneArray[6]  = new Tiles(6,true,new  Position(0,5),  new Shape(tile3Image, "L" ,  new boolean[] {false,true,true,false},"eule"),0);
        allTilesInOneArray[7]  = new Tiles(7,false,new Position(0,6), new Shape(tile28Image, "L" , new boolean[] {true,true,false,false},"blue"),0);

        allTilesInOneArray[8]  = new Tiles(8,true,new  Position(1,0),  new Shape(tile4Image, "T" ,  new boolean[] {true,true,false,true},"zauberer"),0);
        allTilesInOneArray[9]  = new Tiles(9,true,new  Position(1,1),  new Shape(tile5Image, "L" ,  new boolean[] {true,true,false,false},"käfer"),0);
        allTilesInOneArray[10] = new Tiles(10,true,new Position(1,2), new Shape(tile6Image, "T" ,  new boolean[] {true,true,false,true},"fgeist"),0);
        allTilesInOneArray[11] = new Tiles(11,true,new Position(1,3), new Shape(tile7Image, "L" ,  new boolean[] {false,true,true,false},"salamander"),0);
        allTilesInOneArray[12] = new Tiles(12,true,new Position(1,4), new Shape(tile10Image, "T" , new boolean[] {true,true,false,true},"troll"),0);
        allTilesInOneArray[13] = new Tiles(13,true,new Position(1,5), new Shape(tile11Image, "L" , new boolean[] {false,false,true,true},"mouse"),0);
        allTilesInOneArray[14] = new Tiles(14,true,new Position(1,6), new Shape(tile12Image, "T" , new boolean[] {true,true,false,true},"fledermaus"),0);

        allTilesInOneArray[15] = new Tiles(15,false,new Position(2,0),new Shape(tile15Image, "T" , new boolean[] {false,true,true,true},"ring"),0);
        allTilesInOneArray[16] = new Tiles(16,true,new  Position(2,1), new Shape(tile13Image, "T" , new boolean[] {true,true,false,true},"geist"),0);
        allTilesInOneArray[17] = new Tiles(17,false,new Position(2,2),new Shape(tile18Image, "T" , new boolean[] {true,true,true,false},"schatz"),0);
        allTilesInOneArray[18] = new Tiles(18,true,new  Position(2,3), new Shape(tile14Image, "L" , new boolean[] {true,true,false,false},"spinne"),0);
        allTilesInOneArray[19] = new Tiles(19,false,new Position(2,4),new Shape(tile22Image, "T" , new boolean[] {true,true,false,true},"rubin"),0);
        allTilesInOneArray[20] = new Tiles(20,true,new  Position(2,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[21] = new Tiles(21,false,new Position(2,6),new Shape(tile25Image, "T" , new boolean[] {true,true,false,true},"schwert"),0);

        allTilesInOneArray[22] = new Tiles(22,true,new Position(3,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[23] = new Tiles(23,true,new Position(3,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[24] = new Tiles(24,true,new Position(3,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[25] = new Tiles(25,true,new Position(3,3),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[26] = new Tiles(26,true,new Position(3,4),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[27] = new Tiles(27,true,new Position(3,5),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[28] = new Tiles(28,true,new Position(3,6),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);

        allTilesInOneArray[29] = new Tiles(29,false,new Position(4,0),new Shape(tile16Image, "T" , new boolean[] {false,true,true,true},"map"),0);
        allTilesInOneArray[30] = new Tiles(30,true,new  Position(4,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[31] = new Tiles(31,false,new Position(4,2),new Shape(tile19Image, "T" , new boolean[] {false,true,true,true},"krone"),0);
        allTilesInOneArray[32] = new Tiles(32,true,new  Position(4,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[33] = new Tiles(33,false,new Position(4,4),new Shape(tile23Image, "T" , new boolean[] {true,false,true,true},"key"),0);
        allTilesInOneArray[34] = new Tiles(34,true,new  Position(4,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[35] = new Tiles(35,false,new Position(4,6),new Shape(tile26Image, "T" , new boolean[] {true,true,false,true},"skull"),0);

        allTilesInOneArray[36] = new Tiles(36,true,new Position(5,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[37] = new Tiles(37,true,new Position(5,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[38] = new Tiles(38,true,new Position(5,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        allTilesInOneArray[39] = new Tiles(39,true,new Position(5,3),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[40] = new Tiles(40,true,new Position(5,4),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[41] = new Tiles(41,true,new Position(5,5),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[42] = new Tiles(42,true,new Position(5,6),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);

        allTilesInOneArray[43] = new Tiles(43,false,new Position(6,0),new Shape(tile29Image, "L" , new boolean[] {false,false,true,true},"yellow"),0);
        allTilesInOneArray[44] = new Tiles(44,true,new  Position(6,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[45] = new Tiles(45,false,new Position(6,2),new Shape(tile20Image, "T" , new boolean[] {true,false,true,true},"book"),0);
        allTilesInOneArray[46] = new Tiles(46,true,new  Position(6,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[47] = new Tiles(47,false,new Position(6,4),new Shape(tile24Image, "T" , new boolean[] {true,false,true,true},"coins"),0);
        allTilesInOneArray[48] = new Tiles(48,true,new  Position(6,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        allTilesInOneArray[49] = new Tiles(49,false,new Position(6,6),new Shape(tile27Image, "L" , new boolean[] {true,false,false,true},"red"),0);

        /**
         * players
         */
                                  //startPosition   , actualPosition   , ID, Color                      ,turn,score, name, cardsymbolNeeded
        allPlayers[0] = new Player(new Position(6,6), new Position(6,6), 0 , new Color(255, 0 ,0),      true,   0, "",  creaturesNeeded );
        allPlayers[1] = new Player(new Position(0,6), new Position(0,6), 1 , new Color(0, 0 ,255),      false,  0, "",  creaturesNeeded );
        allPlayers[2] = new Player(new Position(6,0), new Position(6,0), 2 , new Color(252, 255, 0),    false,  0, "",  creaturesNeeded );
        allPlayers[3] = new Player(new Position(0,0), new Position(0,0), 3 , new Color(0, 255,0),     false,  0, "" , creaturesNeeded );
    }


    /**
     * getter
     */
    public Player getPlayer(int id){
        return this.allPlayers[id];
    }
    public Player[] getAllPlayers(){
        return  this.allPlayers;
    }
    public Tiles[][] getallTiles(){
        return this.tiles;
    }
    public Tiles getTile(int x, int y){
        return this.tiles[x][y];
    }
    public Tiles getNextTile(){
        return nextTile;
    }
    public Tiles[] getAllTilesInOneArray(){
        return allTilesInOneArray;
    }
    public List<GoalCard> getCreaturesNeeded(){
       return creaturesNeeded;
    }
    public GoalCard[] getAllGoalCards(){
        return allGoalCards;
    }

    /**
     * setter
     */
    public void setTiles(int x, int y, Tiles tile){
        this.tiles[x][y] = tile;
    }
    public void setNextTile(Tiles nextTile){
        this.nextTile = nextTile;
    }


    /**
     * Tiles are created
     * @param tiles
     * All tiles are seperately set -> each tile can be exactly determind
     * new Tiles(id, movable, position(x,y), shape(image, shapeOfImage, possibleWaysToMove, Symbol), rotation)
     */
    public void createTiles(Tiles[][] tiles){
        //first row
        tiles[0][0] = new Tiles(1,false,new Position(0,0), new Shape(tile30Image, "L" , new boolean[] {false,true,true,false},"green"),0);          tiles[4][0] = new Tiles(29,false,new Position(4,0),new Shape(tile16Image, "T" , new boolean[] {false,true,true,true},"map"),0);
        tiles[0][1] = new Tiles(2,true,new  Position(0,1),  new Shape(tile8Image, "L" ,  new boolean[] {true,true,false,false},"motte"),0);         tiles[4][1] = new Tiles(30,true,new  Position(4,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][2] = new Tiles(3,false,new Position(0,2), new Shape(tile17Image, "T" , new boolean[] {true,true,true,false},"kerze"),0);           tiles[4][2] = new Tiles(31,false,new Position(4,2),new Shape(tile19Image, "T" , new boolean[] {false,true,true,true},"krone"),0);
        tiles[0][3] = new Tiles(4,true,new  Position(0,3),  new Shape(tile9Image, "T" ,  new boolean[] {true,true,false,true}, "drache"),0);        tiles[4][3] = new Tiles(32,true,new  Position(4,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][4] = new Tiles(5,false,new Position(0,4), new Shape(tile21Image, "T" , new boolean[] {true,true,true,false},"helm"),0);            tiles[4][4] = new Tiles(33,false,new Position(4,4),new Shape(tile23Image, "T" , new boolean[] {true,false,true,true},"key"),0);
        tiles[0][5] = new Tiles(6,true,new  Position(0,5),  new Shape(tile3Image, "L" ,  new boolean[] {false,true,true,false},"eule"),0);          tiles[4][5] = new Tiles(34,true,new  Position(4,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[0][6] = new Tiles(7,false,new Position(0,6), new Shape(tile28Image, "L" , new boolean[] {true,true,false,false},"blue"),0);           tiles[4][6] = new Tiles(35,false,new Position(4,6),new Shape(tile26Image, "T" , new boolean[] {true,true,false,true},"skull"),0);
        //second row                                                                                                                         //sechste Reihe
        tiles[1][0] = new Tiles(8,true,new  Position(1,0),  new Shape(tile4Image, "T" ,  new boolean[] {true,true,false,true},"zauberer"),0);       tiles[5][0] = new Tiles(36,true,new Position(5,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][1] = new Tiles(9,true,new  Position(1,1),  new Shape(tile5Image, "L" ,  new boolean[] {true,true,false,false},"käfer"),0);         tiles[5][1] = new Tiles(37,true,new Position(5,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][2] = new Tiles(10,true,new Position(1,2), new Shape(tile6Image, "T" ,  new boolean[] {true,true,false,true},"fgeist"),0);          tiles[5][2] = new Tiles(38,true,new Position(5,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[1][3] = new Tiles(11,true,new Position(1,3), new Shape(tile7Image, "L" ,  new boolean[] {false,true,true,false},"salamander"),0);     tiles[5][3] = new Tiles(39,true,new Position(5,3),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][4] = new Tiles(12,true,new Position(1,4), new Shape(tile10Image, "T" , new boolean[] {true,true,false,true},"troll"),0);           tiles[5][4] = new Tiles(40,true,new Position(5,4),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][5] = new Tiles(13,true,new Position(1,5), new Shape(tile11Image, "L" , new boolean[] {false,false,true,true},"mouse"),0);          tiles[5][5] = new Tiles(41,true,new Position(5,5),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        tiles[1][6] = new Tiles(14,true,new Position(1,6), new Shape(tile12Image, "T" , new boolean[] {true,true,false,true},"fledermaus"),0);      tiles[5][6] = new Tiles(42,true,new Position(5,6),new Shape(tile1Image, "I" , new boolean[] {false,true,false,true},null),0);
        //third row                                                                                                                           //siebte Reihe
        tiles[2][0] = new Tiles(15,false,new Position(2,0),new Shape(tile15Image, "T" , new boolean[] {false,true,true,true},"ring"),0);            tiles[6][0] = new Tiles(43,false,new Position(6,0),new Shape(tile29Image, "L" , new boolean[] {false,false,true,true},"yellow"),0);
        tiles[2][1] = new Tiles(16,true,new  Position(2,1), new Shape(tile13Image, "T" , new boolean[] {true,true,false,true},"geist"),0);          tiles[6][1] = new Tiles(44,true,new  Position(6,1), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][2] = new Tiles(17,false,new Position(2,2),new Shape(tile18Image, "T" , new boolean[] {true,true,true,false},"schatz"),0);          tiles[6][2] = new Tiles(45,false,new Position(6,2),new Shape(tile20Image, "T" , new boolean[] {true,false,true,true},"book"),0);
        tiles[2][3] = new Tiles(18,true,new  Position(2,3), new Shape(tile14Image, "L" , new boolean[] {true,true,false,false},"spinne"),0);        tiles[6][3] = new Tiles(46,true,new  Position(6,3), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][4] = new Tiles(19,false,new Position(2,4),new Shape(tile22Image, "T" , new boolean[] {true,true,false,true},"rubin"),0);           tiles[6][4] = new Tiles(47,false,new Position(6,4),new Shape(tile24Image, "T" , new boolean[] {true,false,true,true},"coins"),0);
        tiles[2][5] = new Tiles(20,true,new  Position(2,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);            tiles[6][5] = new Tiles(48,true,new  Position(6,5), new Shape(tile1Image, "I" ,  new boolean[] {false,true,false,true},null),0);
        tiles[2][6] = new Tiles(21,false,new Position(2,6),new Shape(tile25Image, "T" , new boolean[] {true,true,false,true},"schwert"),0);         tiles[6][6] = new Tiles(49,false,new Position(6,6),new Shape(tile27Image, "L" , new boolean[] {true,false,false,true},"red"),0);
        //fourth row
        tiles[3][0] = new Tiles(22,true,new Position(3,0),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][1] = new Tiles(23,true,new Position(3,1),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][2] = new Tiles(24,true,new Position(3,2),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][3] = new Tiles(25,true,new Position(3,3),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][4] = new Tiles(26,true,new Position(3,4),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][5] = new Tiles(27,true,new Position(3,5),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
        tiles[3][6] = new Tiles(28,true,new Position(3,6),new Shape(tile2Image, "L" , new boolean[] {true,false,false,true},null),0);
    }
}



