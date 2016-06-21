package gameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Shape {

    private ImageIcon image;
    private String shape;
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
    public BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

}
