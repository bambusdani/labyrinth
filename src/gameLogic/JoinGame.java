package gameLogic;

import gui.PlayGround;


public class JoinGame {



    public JoinGame(){

        Broker broker = new Broker();

        //TODO get Board from Host
        PlayGround playGroundPlayer1 = new PlayGround(new Board(),"local host", "Marvin" , 1);
    }

}
