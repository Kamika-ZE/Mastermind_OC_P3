package main.java.fr.mickael.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    /*public static int nbDigit = 6; // nb chiffres utilisables mastermind
    public static int codeLength = 4;
    public static int maxRound = 10;
    public static int codeIndex = 0;*/

    private final static String CONFIG_FILE = "./src/main/resources/config/config.properties";

    private static Properties properties = new Properties();

    private static int codeLength1 = 0;
    private static int maxRound1 = 0;
    private static int nbDigit1 = 0;
    private static int codeIndex1 = 0;
    static {
        loadingProperties();
    }

    public static int getCodeLength() {
        codeLength1 = Integer.parseInt((properties.getProperty("codeLength")));
        return codeLength1;
    }

    public static int getMaxRound() {
        maxRound1 = Integer.parseInt((properties.getProperty("maxRound")));
        return maxRound1;
    }

    public static int getNbDigit() {
        nbDigit1 = Integer.parseInt((properties.getProperty("nbDigit")));
        return nbDigit1;
    }

    public static int getCodeIndex() {
        codeIndex1 = Integer.parseInt((properties.getProperty("codeIndex")));
        return codeIndex1;
    }

    private static void loadingProperties() {
        try {
            InputStream inputStream = new FileInputStream(CONFIG_FILE);
            properties.load( inputStream);
            inputStream.close( );
        }
        catch ( IOException e ) {
            e.printStackTrace( );
        }
    }


}
