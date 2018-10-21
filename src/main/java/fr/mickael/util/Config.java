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
    private static boolean modeDev1 = false;

    static {
        loadingProperties();
    }

    //-------------------DEFAULT PROPERTIES-------------------

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

    public static boolean isModeDev() {
        modeDev1 = Boolean.parseBoolean((properties.getProperty("modeDev")));
        return modeDev1;
    }

    //---------------GETTERS AND SETTERS-------------------

    public static int getCodeLength1() {
        return codeLength1;
    }

    public static void setCodeLength1(int codeLength1) {
        Config.codeLength1 = codeLength1;
    }

    public static int getMaxRound1() {
        return maxRound1;
    }

    public static void setMaxRound1(int maxRound1) {
        Config.maxRound1 = maxRound1;
    }

    public static int getNbDigit1() {
        return nbDigit1;
    }

    public static void setNbDigit1(int nbDigit1) {
        Config.nbDigit1 = nbDigit1;
    }

    public static boolean isModeDev1() {
        return modeDev1;
    }

    public static void setModeDev(boolean modeDev) {
        Config.modeDev1 = modeDev;
    }

    //--------------METHOD LOADING PROPERTIES------------------

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
