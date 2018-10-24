package main.java.fr.mickael.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

/**
 * Class that contains constants used
 * in multiple classes.
 * @author M. COZ
 */
public class Constants {

    private static Logger logger = LogManager.getLogger();
    private static int nbDigit = Config.getNbDigit();
    private static int codeLength = Config.getCodeLength();

    public static int getNbPresent(int[] guessCode, int[] secretCode, int nbWellPlaced) {

        // comme on doit enlever les pions bien placés, on initialise nbPresent à - nbWellPlaced
        int nbPresent = - nbWellPlaced;
        for (int i = 0; i < nbDigit; i++){
            int presentSecretCode = 0;
            int presentGuessCode = 0;
            for (int j = 0; j < codeLength; j++){
                if (secretCode[j] == i){
                    presentSecretCode++;
                }
                if (guessCode[j] == i){
                    presentGuessCode++;
                }
            }
            if (presentSecretCode < presentGuessCode){
                nbPresent = presentSecretCode + nbPresent;
            } else {
                nbPresent = presentGuessCode + nbPresent;
            }
        }
        return nbPresent;
    }

    /**
     * Static method that print a view separator
     */
    public static final String SEPARATOR = "\n\n" + String.join("*", Collections.nCopies(15, "*"))
            +  "  %s  " + String.join("*", Collections.nCopies(15, "*")) + "\n\n";
}
