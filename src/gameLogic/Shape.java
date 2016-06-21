package gameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Shape {

    private ImageIcon image;
    private String shape;
    //oben rechts unten links (Im Uhrzeigersinn)
    private boolean[] possiblePaths = new boolean[4]; //top right bottom left???
    private String creature;

    /** Konstrutkor */
    public Shape(ImageIcon image, String shape, boolean[] possiblePaths, String creature){
        this.image = image;
        this.shape = shape;
        this.possiblePaths = possiblePaths;
        this.creature = creature;
    }

    /** Getter                                      Setter */
    public ImageIcon getImage(){return this.image;}                         public void setImage(ImageIcon image){this.image = image;}
    public String getShape(){return this.shape;}
    public boolean[] getPossiblePaths(){return this.possiblePaths;}     public void setPossiblePaths(boolean[] possiblePaths){this.possiblePaths = possiblePaths;}
    public String getCreature(){return creature;}                         public void setCreature(String creature){this.creature = creature;}



    /** Rotate Image Function */

    public ImageIcon rotateImage(double degrees){
        ImageIcon imageIcon = this.image;

        //to Buffered Image
        BufferedImage buImg = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        buImg.getGraphics().drawImage(imageIcon.getImage(), 0,0, imageIcon.getImageObserver());

        //drehen
        AffineTransform affineTransform = AffineTransform.getRotateInstance(
                Math.toRadians(90),
                buImg.getWidth() / 2,
                buImg.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(buImg.getWidth(), buImg
                .getHeight(), buImg.getType());
        Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
        g.setTransform(affineTransform);
        g.drawImage(buImg, 0, 0, null);

        //to ImageIcon
        ImageIcon imageI = new ImageIcon(rotatedImage);

        boolean oben = this.possiblePaths[0];
        boolean rechts = this.possiblePaths[1];
        boolean unten = this.possiblePaths[2];
        boolean links = this.possiblePaths[3];

        possiblePaths[0] = links;
        possiblePaths[1] = oben;
        possiblePaths[2] = rechts;
        possiblePaths[3] = unten;

        return imageI;
    }

}
