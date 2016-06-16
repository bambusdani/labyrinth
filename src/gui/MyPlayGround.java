package gui;

import java.awt.*;
import gameLogic.*;

import javax.swing.*;

public class MyPlayGround {

    //================================================
    /** -> ATTRIBUTE */
    private JButton[][] gameBoardButtons = new JButton[7][7];
    private JFrame frame;
    private JPanel panelGameBoardButtons = new JPanel(new GridBagLayout());
    private MyBoard givenBoard = new MyBoard();
    //================================================


    /** -> KONSTRUKTOR */
    public MyPlayGround(MyBoard board){
        GridBagConstraints constraintsGameField = new GridBagConstraints();

        for(int i = 0; i < gameBoardButtons.length; i++){
            for(int j = 0; j< gameBoardButtons[i].length; j++) {

                //creating button
                constraintsGameField.gridx = i + 1;
                constraintsGameField.gridy = j + 1;

                JButton StonesButton = setButtons(board.getTile(i, j).getShape(), 20, 75, 75);

                gameBoardButtons[j][i]= StonesButton;
                panelGameBoardButtons.add(gameBoardButtons[j][i], constraintsGameField);
            }
        }
        this.frame = createFrame();
        frame.add(panelGameBoardButtons);

    }

    //=======================================================
    /** -> Function to createFrame */
    public JFrame createFrame(){
        JFrame frame = new JFrame("Join Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1200, 900);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLocation(300, 200);
        return frame;
    }

    public JLabel setLabel(String labelString, int fontSize, int sizeX, int sizeY, Color fontColor){
        JLabel label = new JLabel(labelString);
        label.setMinimumSize(new Dimension(sizeX, sizeY));
        label.setPreferredSize(new Dimension(sizeX, sizeY));
        //label.setMaximumSize(new Dimension(sizeX, sizeY));
        label.setFont(new Font("Serif", Font.PLAIN, fontSize));
        label.setForeground(fontColor);
        return label;
    }

    public JButton setButtons(String text, int fontSize, int sizeX, int sizeY){
        JButton button = new JButton(text);

        //makes a fix size
        button.setMinimumSize(new Dimension(sizeX, sizeY));
        button.setPreferredSize(new Dimension(sizeX, sizeY));
        //button.setMaximumSize(new Dimension(sizeX, sizeY));
        button.setFont(new Font("Serif", Font.PLAIN, fontSize));
        return button;
    }

    public JTextField setTextField(int width, int height) {
        JTextField textField = new JTextField(width);
        textField.setMinimumSize(new Dimension(width, height));
        textField.setPreferredSize(new Dimension(width, height));
        return textField;
    }

    public JTextArea setTextArea(int width , int height) {
        JTextArea textArea = new JTextArea(height, width);
        textArea.setMinimumSize(new Dimension(width, height));
        textArea.setPreferredSize(new Dimension(width, height));
        return textArea;
    }
    //=======================================================


}
