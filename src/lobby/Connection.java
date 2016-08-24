/******************************************************************************
 *  Compilation:  javac Connection.java
 *
 ******************************************************************************/

package lobby;

import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import gameLogic.*;

public class Connection extends Thread {
    private Socket socket;
    private Out out;
    private In in;
    private String message;         // one line buffer
    private boolean init = true;   // for init message
    private int pId;                 //connection ID (for logging)
    private String playerName;



    public Connection(Socket socket) {
        in = new In(socket);
        out = new Out(socket);
        this.socket = socket;
    }

    public void println(String s) {
        out.println(s);
    }

    public void run() {
        String s;
        while ((s = in.readLine()) != null) {
            setMessage(s);
        }
        out.close();
        in.close();
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("closing socket");
    }

    ;

    /***************************************************************************
     *  The methods getMessage() and setMessage() are synchronized
     *  so that the thread in Connection doesn't call setMessage()
     *  while the ConnectionListener thread is calling getMessage().
     ***************************************************************************/
    public synchronized String getMessage() {
        if (message == null) return null;
        String temp = message;
        message = null;
        notifyAll();
        return temp;
    }

    public synchronized void setMessage(String s) {
        if (message != null) {
            try {
                wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        message = s;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}