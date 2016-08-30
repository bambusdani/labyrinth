package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Marvin Röck, Daniel Deuscher, Rehan App
 * Programmierprojekt Sommersemester 2016
 * Das Verrückte Labyrinth
 */
public class Rules implements ActionListener{

    /**
     * Attributes
     */
    private String longText = "Test";
    JFrame frame = createFrame();
    JButton back = addButtons("close");

    /**
     * Creates total GUI for Rules
     */
    public void createGui(){
        //--------------------------------------------------------------
        //create new Panel and set GridBagLayout to it
        JPanel panel = new JPanel(new GridBagLayout());
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //create new GridBagLayout and set layout (x=1 ; y=0)
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1;
        constraints.insets = new Insets(30,0,0,0);
        constraints.gridx = 0;
        constraints.gridx++;
        constraints.gridy = 0;
        //create new label and set to layout
        panel.add(setLabel("Das verrückte Labyrinth", 40), constraints);
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=2)
        constraints.gridy = 2;
        //create new lable and set to layout
        panel.add(setLabel("Die Spielregeln: ", 30), constraints);
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=3)
        constraints.gridy = 3;
        //create new scrollPanel and set to layout
        panel.add(setScrollPane(gameRules() ,20),constraints);
        //---------------------------------------------------------------

        //--------------------------------------------------------------
        //layout set to (x=1 ; y=4)
        constraints.gridy = 4;
        //add actionlistener to back button and add it to panel
        back.addActionListener(this);
        panel.add(back,constraints);
        //--------------------------------------------------------------

        //set panel to frame and add borderlayout to frame
        frame.add(panel, BorderLayout.NORTH);
    }

    /**
     *creates a specialized frame for this class
     * @return
     */
    public JFrame createFrame(){
        JFrame frame = new JFrame("DvL Spielregeln");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1200, 800);
        frame.setLocation(300, 200);
        return frame;
    }

    /**
     * creates a specialized label and gets text and size as input
     * @param labelString
     * @param size
     * @return
     */
    public JLabel setLabel(String labelString, int size){
        JLabel label = new JLabel(labelString);
        label.setFont(new Font("Serif", Font.PLAIN, size));
        return label;
    }

    /**
     * creates a for Rule class specialized scrollable panel
     * @param text
     * @param sizeOfText
     * @return
     */
    public JScrollPane setScrollPane(String text, int sizeOfText){
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Serif", Font.PLAIN, sizeOfText));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(900,400));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scroll;
    }

    /**
     * specialized button for rules class is created
     * @param text
     * @return
     */
    public JButton addButtons(String text){
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 25));
        return button;
    }

    /**
     * creates the game rules
     * @return
     */
    public String gameRules(){

        String rules =  "Ziel des Spiels: \n" +
                        "In einem verwunschenen Labyrinth gehen die Spieler auf Suche nach geheimnisvollen Gegenständen und Lebewesen. Jeder Spieler verucht durch " +
                        "geschicktes Verschieben der Gänge zu ihnen zu gelangen." +
                        "Wer als erstes alle Geheimnisse lüftet und wieder auf seinem Startfeld landet, ist der Gewinner dieses spannenden Spieles. \n \n" +
                        "Vorbereitung: \n" +
                        "Die beweglichen Gänge-Karten werden zufällig auf das Spielfeld verteilt und jeder Spieler bekommt 6 Bildkarten, welche die anderen Spieler nicht sehen. \n \n" +
                        "Spielverlauf: \n" +
                        "Das Spiel läuft reihumm im Kreis. Ist ein Spieler an der Reihe, muss er versuchen auf das Feld zu gelangen, das dieselbe Figur zeigt, wie die oberste Karte seines Stapels." +
                        "Dazu verschiebt er immer zuerst das Labyrinth durch Einschieben einer Gänge-Karte und zieht danach mit seiner Spielfigur. \n \n" +
                        "Gänge verschieben: \n" +
                        "Entlang des Spielbrettes befinden sich 12 Pfeile. Der Spieler am Zug entscheidet sich für einen dieser Pfeile und schiebt hier die überzählige Gänge-Karte in das Spielfeld." +
                        "Dabei rutscht eine Gänge-Karte aus dem Labyrinth, diese muss der nächste Spieler an einer anderen Stelle wieder rein schieben. " +
                        "Eine Gänge-Karte darf also nicht an der gleichen Stelle, an der sie vom voherigen Spieler heraus geschoben wurde, wieder eingeschoben werden. " +
                        "Das Labyrinth muss in jedem Fall verschoben werden, auch wenn der Spieler die gewünschte Abbildung ohne Verschieben erreichen könnte.\n" +
                        "Wird beim Verschieben die eigene oder eine fremnde Spielfigur aus dem  Plan geschoben, so wird die Spielfigur auf der gegenüberliegenden Seite auf die gerade neu eingeschobene Gänge-Karte" +
                        "gesetz. Das Versetzen der Spielfigur gilt nicht als Zug.\n\n" +
                        "Spielfigur ziehen: \n" +
                        "Nach dem Verschieben des Labyrinths, zieht der Spieler mit seiner Figur.\n" +
                        "Der Spieler darf auf jedes Feld ziehen, das mit seiner Spielfigur durch einen ununterbrochenen Gang verbunden ist. Er darf so weit ziehen wie er möchte. " +
                        "Er kann seine Spielfigur auch auf ein Feld ziehen, auf dem schon eine andere Figur steht. \n\n" +
                        "Erreicht ein Spieler sein Ziel nicht auf direktem Wege, so kann er seine Figur so weit ziehen , dass er für seinen nächsten Zug eine günstigere Ausgangstellung besitzt. " +
                        "Da kein Zugzwang besteht, kann er seine Figur aber auch stehen lassen. \n\n" +
                        "Hat der Spieler das Ziel seiner Bildkarte erreicht, bekommt er seine nächste Karte, die er ab seinem nächsten Zug versuchen muss zu erreichen. \n\n" +
                        "Spielende: \n" +
                        "Es gewinnt der Spieler der zuerst alle seine Bildkarten aufgedeckt und seine Spielfigur danach wieder auf sein Startfeld gezogen hat.\n" ;
        return rules;
    }

    /**
     * action Listener
     * @param ae
     */
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.back){
            frame.dispose();
        }
    }
}
