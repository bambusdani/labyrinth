package gameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * Includes all the Functions which are used in the GUI
 */
public class GameFunctions {
    //wird für checkMazeIfMoveISPossible benötigt
    boolean[][] visited = new boolean[7][7];

    //konstruktor
    public GameFunctions(){

    }


    /**================================================================================
     * function checkMazeIfMoveIsPossible
     * tiles = alle Tiles
     * buttonPositionPressed = Position wo der Button gedrückt wurde
     * tilePositionX / Y = jetzige Position des Spielers
     * visited = merkt sich wo man bereits war sonst durchläuft es eine dauerschleife
     * ================================================================================**/

    public boolean checkMazeIfMoveIsPossible(Tiles[][] tiles, Position buttonPositionPressed, int tilePositionX, int tilePositionY){
        // setzt alle Werte auf false -> wurde noch nicht besucht
        for (int p=0; p<7;p++){
            for (int q= 0;q<7;q++){
                this.visited[p][q]= false;
            }
        }
        return isMovePossible(tiles, buttonPositionPressed, tilePositionX, tilePositionY);
    }

    public boolean isMovePossible (Tiles[][] tiles, Position buttonPositionPressed, int tilePositionX, int tilePositionY){

        //Falls der Punkt erreichbar ist
        if( buttonPositionPressed.getX() == tilePositionX && buttonPositionPressed.getY() == tilePositionY){
            return true;
        }
        // wenn man bereits an dem Ort war (already visited)
        if( this.visited[tilePositionX][tilePositionY] ){
            return false;
        }
        // Feld auf bereits besucht setzen
        this.visited[tilePositionX][tilePositionY]= true;

        // nach links gehen
        if((tilePositionX != 0) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[3]) && (tiles[tilePositionX - 1][tilePositionY].getShape().getPossiblePaths()[1]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX - 1 , tilePositionY)){
                return true;
            }
        }
        // nach rechts gehen
        if((tilePositionX != 6) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[1]) && (tiles[tilePositionX + 1][tilePositionY].getShape().getPossiblePaths()[3]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX + 1 , tilePositionY)){
                return true;
            }
        }
        // nach oben gehen
        if( (tilePositionY != 0)&& (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[0]) && (tiles[tilePositionX][tilePositionY - 1].getShape().getPossiblePaths()[2]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX , tilePositionY - 1)){
                return true;
            }
        }
        // nach unten gehen
        if((tilePositionY != 6) && (tiles[tilePositionX][tilePositionY].getShape().getPossiblePaths()[2]) && (tiles[tilePositionX][tilePositionY + 1].getShape().getPossiblePaths()[0]) ){
            if(isMovePossible(tiles,buttonPositionPressed,tilePositionX , tilePositionY + 1)){
                return true;
            }
        }
        // Wenn kein Fall zutrift wird false zurückgegeben
        return false;
    }

    /**=================================================================================================================
     *
     * printNewPlayerPossition
     * JButton[][] allButtons , Player player, Positon buttonPositionPressed, int TilePositionX, int TilePositionY, Tiles[][] tiles
     * entfernt den bisherigen Rand und fügt an der neuen Stelle einen an
     * ruft die Funktion checkMazeIfMovePossible auf
     *
     * TODO Überschreibt bisher noch die anderen Ränder 
     *
     * ==============================================================================================================**/
    public void movePlayerIfMovePossible(JButton[][] allButtons, Player player, Position buttonPositionPressed, int tilePositionX, int tilePositionY, Tiles[][] tiles){
        if(checkMazeIfMoveIsPossible(tiles, buttonPositionPressed, tilePositionX,tilePositionY)){

            //setze Border auf default
            allButtons[player.getAcutalPosition().getX()][player.getAcutalPosition().getY()].setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
            //setze neuen Rand
            allButtons[buttonPositionPressed.getX()][buttonPositionPressed.getY()].setBorder(BorderFactory.createMatteBorder(3,3,3,3, player.getColor()));
            // neue Koordinaten im Player setzen
            player.setActualPosition(buttonPositionPressed);
        }
        else{
            System.err.println("Zug ist nicht möglich");
        }







    }

}
