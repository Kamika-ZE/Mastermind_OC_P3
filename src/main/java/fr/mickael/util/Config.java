package main.java.fr.mickael.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that contains the configuration of the game.
 * @author M. COZ
 *
 */
public class Config {


    private static Logger logger = LogManager.getLogger();
    private final static String CONFIG_FILE = "./src/main/resources/config/config.properties";

    private static Properties properties = new Properties();

    private static int codeLength = 0;
    private static int maxRound = 0;
    private static int nbDigit = 0;
    private static boolean modeDev = false;

    static {
        loadingProperties();
    }

    //-------------------DEFAULT PROPERTIES-------------------

    public static int getDefaultCodeLength() {
        codeLength = Integer.parseInt((properties.getProperty("codeLength")));
        return codeLength;
    }

    public static int getDefaultMaxRound() {
        maxRound = Integer.parseInt((properties.getProperty("maxRound")));
        return maxRound;
    }

    public static int getDefaultNbDigit() {
        nbDigit = Integer.parseInt((properties.getProperty("nbDigit")));
        return nbDigit;
    }

    public static boolean isDefaultModeDev() {
        modeDev = Boolean.parseBoolean((properties.getProperty("modeDev")));
        return modeDev;
    }

    //---------------GETTERS AND SETTERS-------------------

    public static int getCodeLength() {
        return codeLength;
    }

    public static void setCodeLength(int codeLength) {
        Config.codeLength = codeLength;
    }

    public static int getMaxRound() {
        return maxRound;
    }

    public static void setMaxRound(int maxRound) {
        Config.maxRound = maxRound;
    }

    public static int getNbDigit() {
        return nbDigit;
    }

    public static void setNbDigit(int nbDigit) {
        Config.nbDigit = nbDigit;
    }

    public static boolean isModeDev() {
        return modeDev;
    }

    public static void setModeDev(boolean modeDev) {
        Config.modeDev = modeDev;
    }

    //--------------METHOD LOADING PROPERTIES------------------

    private static void loadingProperties() {
        try {
            InputStream inputStream = new FileInputStream(CONFIG_FILE);
            properties.load(inputStream);
            inputStream.close( );
        }
        catch ( IOException e ) {
            logger.error("General IO Exception");
            e.printStackTrace( );
        }
    }





}
