package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class GradientPanel extends JPanel{

    /**
     * Constructor
     */
    public GradientPanel() {
        super();
        setOpaque(true);
    }

    /**
     * new color is created
     */
    Color deepSkyBlue = new Color(152,245,255);

    /**
     * class creates a gradient
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        GradientPaint gp = new GradientPaint(getWidth(), 0, Color.LIGHT_GRAY, 0,
                getHeight(), deepSkyBlue);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

