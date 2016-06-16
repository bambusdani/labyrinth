package properties;

import java.io.*;
import java.util.Properties;

public class GameProperty{

    //=======================================================
    //Property Attributes
    String date = "";
    String developer = "";
    String url = "";

    Properties properties = new Properties();
    //=======================================================


    //=======================================================
    //Konstruktor reads out property file and stores the properties
    public GameProperty(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/properties/GameProperties.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.developer = prop.getProperty("developer");
            this.url = prop.getProperty("url");
            this.date = prop.getProperty("date");
            this.properties = prop;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //==========================================================================


    //==========================================================================
    //Save Data to the property file in right style
    public void writeToPropertyFile(String url, String developer, String date){

        Properties prop = new Properties();
        OutputStream output = null;

        //try to write properties to the file
        try {
            output = new FileOutputStream("src/properties/GameProperties.properties");

            // set the properties value
            prop.setProperty("url", url);
            prop.setProperty("developer", developer);
            prop.setProperty("date", date);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //===================================================================

    //---------------------------------
    //Properties getter
    public Properties getProperties(){
        return this.properties;
    }
    //---------------------------------
}
