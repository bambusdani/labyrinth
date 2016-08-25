package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rehan on 25.08.16.
 */
public class GradientPanel extends JPanel{

    public GradientPanel() {
        super();
        setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        GradientPaint gp = new GradientPaint(getWidth(), 0, Color.RED, 0,
                getHeight(), Color.ORANGE);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

