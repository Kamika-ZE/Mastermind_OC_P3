package main.java.fr.mickael.util;

public class Util {

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

    public static final String SEPARATOR = "\n\n********************  %s  **********************\n\n";
}
