package lobby;

import network.*;
import network.Connection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    public static void main(String[] args) throws Exception {
        Vector<network.Connection> connections        = new Vector<network.Connection>();
        ServerSocket serverSocket             = new ServerSocket(4444);
        network.ConnectionListener connectionListener = new network.ConnectionListener(connections);

        //thread that broadcasts messages to clients
        connectionListener.start();

        System.err.println("Chatserver started");

        while(true) {
            //wait for next client connection request
            Socket clientSocket = serverSocket.accept();
            System.err.println("Created socket with client");

            //listen to client in a separate thread
            network.Connection connection = new Connection(clientSocket);
            connections.add(connection);
            connection.start();
        }
    }
}
