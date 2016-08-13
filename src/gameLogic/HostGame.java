package gameLogic;

import gui.PlayGround;
import network.Protocol;
import network.Server;


public class HostGame {

    // constructor
    public HostGame(){

        Board board         = new Board();

        //TODO server muss im konstruktor gestartet werden
        //Server server       = new Server();
        //Protocol protocol   = new Protocol();

        Broker broker       = new Broker();



        //TODO sachen aus dem Fenster davor auslesen
        PlayGround playGroundPlayer0 = new PlayGround(board,"local host", "Marvin", 0);

    }


}
