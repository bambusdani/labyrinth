package gui;


import properties.GameProperty;

import java.util.Properties;

public class About {

    //===========================================================
    public void createGui(){
        GameProperty gameProperty = new GameProperty();
        Properties properties = gameProperty.getProperties();
        String developer = properties.getProperty("developer");
        String url = properties.getProperty("url");
        String date = properties.getProperty("date");


    }
    //===========================================================
}
