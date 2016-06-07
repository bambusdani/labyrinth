/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package chat;

import java.util.Vector;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;
    }

    // check for incoming messages and broadcast
    public void run() {
        while (true) {
            for (int i = 0; i < connections.size(); i++) {
                Connection ith = connections.get(i);

                // if connection terminated, remove from list of active connections
                if (!ith.isAlive())
                    connections.remove(i);

                // broadcast to everyone
                //message contains username + message
                //e.g [Darth Vader]: I am your father!

                String message = ith.getMessage();
                if (message != null)
                    for (Connection jth : connections) {

                        //split the message into username + message
                        /*
                        METHOD TO SPLIT THE STRING:
                            String[] tmp = message.split(": "); <-- There needs to be a blank char, otherwise
                            the printed server message begins with a blank.

                            tmp[0] contains the username e.g "[Darth Vader]:"
                            tmp[1] contains ": "
                            tmp[2] contains the acuatal message e.g " I am your farther"
                            jth.println(tmp[tmp.length-1]); //<-- prints the actual message
                        */
                        String[] tmp = message.split(": ");
                        String tmpUsername = tmp[0];
                        String tmpMessage = tmp[tmp.length-1];

                        /*
                        BEGIN WITH PARAMETERS
                        maybe we should make a separate class for
                        all the parameters...

                        TODO:
                        -get player color
                        -move parameter
                        -deal etc...
                         */

                        //'chat' parameter
                        //check the first 4 characters of the message.
                        //If it equals "chat" then print the whole message
                        try {
                            if (tmpMessage.substring(0, 4).equalsIgnoreCase("chat")) {
                                jth.println(message);
                            } else {
                                //if the message doens't make sense at all...
                                jth.println("What the **** did you type?");
                            }
                        }
                        catch (Exception e) {
                            jth.println("error");
                        }

                        //split string so we can work with messagge only and
                        //not the username+message
                        //method 1

                        //default
                        //jth.println(message);
                    }
            }

            // don't monopolize processor
            try                 { Thread.sleep(100);   }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

}