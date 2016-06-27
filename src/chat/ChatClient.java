/******************************************************************************
 *  Compilation:  javac ChatClient.java
 *  Execution:    java ChatClient name host
 *  Dependencies: In.java Out.java
 *
 *  Connects to host server on port 4444, enables an interactive
 *  chat client.
 *  
 *  % java ChatClient alice localhost
 *
 *  % java ChatClient bob localhost
 *  
 ******************************************************************************/

package chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.Socket;

public class ChatClient extends JFrame implements ActionListener {

    //screen name will later be playername
    private String screenName="";

    // GUI stuff
    JTextArea enteredText;
    JTextField typedText;

    // socket for connection to chat server
    private Socket socket;

    // for writing to and reading from the server
    private Out out;
    private In in;

    //================================================================================
    // constructor creates a connection the the server
    // on socket = 4444
    //================================================================================
    public ChatClient(String screenName, String hostName, JTextArea textArea, JTextField textField) {

        this.enteredText = textArea;
        this.typedText = textField;

        // connect to server
        try {
            socket = new Socket(hostName, 4444);
            out    = new Out(socket);
            in     = new In(socket);
        }
        catch (Exception ex) { ex.printStackTrace(); }
        this.screenName = screenName;

        // close output stream  - this will cause listen() to stop and exit
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    out.close();
                }
            }
        );

        // gui action listener
        //typedText.addActionListener(this);
    }

    //================================================================================
    // proccess textField after user hits enter
    //================================================================================
    public void actionPerformed(ActionEvent e) {
        out.println("[" + screenName + "]: " + typedText.getText());
        typedText.setText("");
        typedText.requestFocusInWindow();
    }

    //================================================================================
    // listen to socket and print everything that server broadcasts
    //================================================================================
    /*public void listen() {
        String s;
        while ((s = in.readLine()) != null) {
            enteredText.insert(s + "\n", enteredText.getText().length());
            enteredText.setCaretPosition(enteredText.getText().length());
        }
        out.close();
        in.close();
        try                 { socket.close();      }
        catch (Exception e) { e.printStackTrace(); }
        System.err.println("Closed client socket");
    }*/

    //================================================================================
    // get screenName
    //================================================================================
    public String getScreenName() {
        return this.screenName;
    }

    //================================================================================
    // main method is for testing/debugging
    //================================================================================
    /*public static void main(String[] args)  {
    									  //ScreenName, Hostname
    	ChatClient client = new ChatClient("Hjorleif", "localhost");
        client.listen();
    }*/
}
