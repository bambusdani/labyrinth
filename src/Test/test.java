package Test;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class test{

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("no image");
        ImageIcon yourImage = new ImageIcon("src/resources/arrows/downArrow.png");

        JLabel imagelabel = new JLabel(rotate(yourImage,90));
        frame.setVisible(true);
        frame.add(imagelabel, BorderLayout.CENTER);


    }

    public static ImageIcon rotate(ImageIcon imageIcon, double degrees){
        //to Buffered Image
        BufferedImage buImg = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        buImg.getGraphics().drawImage(imageIcon.getImage(), 0,0, imageIcon.getImageObserver());

        //drehen
        AffineTransform affineTransform = AffineTransform.getRotateInstance(
                Math.toRadians(degrees),
                buImg.getWidth() / 2,
                buImg.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(buImg.getWidth(), buImg
                .getHeight(), buImg.getType());
        Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
        g.setTransform(affineTransform);
        g.drawImage(buImg, 0, 0, null);

        //to ImageIcon
        ImageIcon imageI = new ImageIcon(rotatedImage);

        return imageI;

    }

}