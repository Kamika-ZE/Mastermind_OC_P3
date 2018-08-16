package main.java.fr.mickael.util;

public class Config {

    public static int nbDigit = 10; // nb chiffres utilisables mastermind
    public static int codeLength = 4;
    public static int maxRound = 10;
    public static int scoreMax = 10 * codeLength;
    public static int codeIndex = 0;

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

}
