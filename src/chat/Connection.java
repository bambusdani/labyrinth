/******************************************************************************
 *  Compilation:  javac Connection.java
 * 
 ******************************************************************************/

package chat;

import java.net.Socket;
import gameLogic.*;

public class Connection extends Thread {
    private Socket socket;
    private Out out;
    private In in;
    private String message;     // one line buffer
    private Protocol protocol;

    public Connection(Socket socket) {
        in  = new In(socket);
        out = new Out(socket);
        this.socket = socket;
        protocol = new Protocol();
    }

    public void println(String s) { out.println(s); }

    public void run() {
        String s;
        while ((s = in.readLine()) != null) {
            setMessage(s);
        }
        out.close();
        in.close();
        try                 { socket.close();      }
        catch (Exception e) { e.printStackTrace(); }
        System.err.println("closing socket");
    };

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
            try                  { wait();               }
            catch (Exception ex) { ex.printStackTrace(); }
        }
        message = s;
    }

    public synchronized void setPlayerPoints(int playerPoints) {
        protocol.setPlayerPoints(playerPoints);
    }

    public synchronized void isPlayerGettingPoints(Board board, int playerID) {
        protocol.isPlayerGettingPoints(board, playerID);
    }

    public synchronized int getPlayerPoints() {
        return protocol.getPlayerPoints();
    }

    public void setDummy(boolean x) {
        protocol.setDummy(x);
    }

    public boolean isDummy() {
        return protocol.isDummy();
    }
}