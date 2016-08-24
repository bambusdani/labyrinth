package lobby;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    public static void main(String[] args) throws Exception {
        Vector<Connection> connections        = new Vector<Connection>();
        ServerSocket serverSocket             = new ServerSocket(4444);
        ConnectionListener connectionListener = new ConnectionListener(connections);

        //thread that broadcasts messages to clients
        connectionListener.start();

        System.err.println("Lobbyserver started");

        while(true) {
            //wait for next client connection request
            Socket clientSocket = serverSocket.accept();
            System.err.println("Created socket with client");

            //listen to client in a separate thread
            Connection connection = new Connection(clientSocket);
            connections.add(connection);
            connection.start();
        }
    }
}
