package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class guiCreator extends JPanel {
	public static void main(String[] args) {
		startMenu startMenu = new startMenu();
		startMenu.createGui();
	}
}