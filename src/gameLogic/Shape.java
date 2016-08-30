package gameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Shape {

    /**
     * Attributes
     */
    private ImageIcon buImg;
    private String shape;
    private boolean[] possiblePaths = new boolean[4]; //top right bottom left???
    private String creature;

    /**
     * Constructor
     * @param image
     * @param shape
     * @param possiblePaths
     * @param creature
     */
    public Shape(ImageIcon image, String shape, boolean[] possiblePaths, String creature){
        this.buImg = image;
        this.shape = shape;
        this.possiblePaths = possiblePaths;
        this.creature = creature;
    }

    /**
     * Getter Setter
     * */
    public ImageIcon getImage(){return this.buImg;}
    public boolean[] getPossiblePaths(){return this.possiblePaths;}
    public String getCreature(){return creature;}

    public void setImage(ImageIcon image){this.buImg = image;}

    /**
     * Rotates an image by 90°
     * @param degrees
     * @return
     */
    public ImageIcon rotateImage(double degrees){
        ImageIcon imageIcon = this.buImg;
        BufferedImage buImg = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        buImg.getGraphics().drawImage(imageIcon.getImage(), 0,0, imageIcon.getImageObserver());

        AffineTransform tx = new AffineTransform();
        tx.rotate((Math.PI / 2), buImg.getWidth() / 2, buImg.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        buImg = op.filter(buImg, null);

        ImageIcon imageI = new ImageIcon(buImg);
        
        return imageI;
    }

    /**
     * Sets the new paths for the tiles after rotation
     * @param possiblePaths
     */
    public void setRotatedPossiblePath(boolean[] possiblePaths){
        boolean oben = possiblePaths[0];
        boolean rechts = possiblePaths[1];
        boolean unten = possiblePaths[2];
        boolean links = possiblePaths[3];

        possiblePaths[0] = links;
        possiblePaths[1] = oben;
        possiblePaths[2] = rechts;
        possiblePaths[3] = unten;

        this.possiblePaths = possiblePaths;
    }
}
