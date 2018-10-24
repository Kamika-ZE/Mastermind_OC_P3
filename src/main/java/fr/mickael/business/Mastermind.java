package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class of the game Mastermind
 * Implementation of the game for the mode challenger and defender
 * @author M. COZ
 *
 */

public class Mastermind extends Game{

    private static Logger logger = LogManager.getLogger();

    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();
    private int nbDigit = Config.getNbDigit();

    /**
     * Method play.
     * Method that launch the board of the current game.
     * Implementation of the abstract class.
     */
    @Override
    public void play() {
        logger.debug("running play() of the " + getClass().getSimpleName() + " game.");
        logger.info("variables initializing");
        int round = 0;
        boolean asWon = false;
        int[] secretCode;
        int[] guessCode;
        String compareCode = "";

        System.out.println("\nMASTERMIND\n"
                + String.join("*", Collections.nCopies(40, "*")) + "\n");

        if (defender.getClass().getSimpleName().equals("Human")){
            System.out.println("MODE : DEFENDER\n\n"
                    + "The computer have " + maxRound + " round to find your code.\n"
                    + "The code size is " + codeLength
                    + " and you can use any number between 0 and " + (Config.getNbDigit() - 1) + ".\n"
                    + "Make it suffer !\n");
        } else {
            System.out.println("MODE : CHALLENGER\n\n"
                    + "You have " + maxRound + " round to find the computer code.\n"
                    + "The code size is " + codeLength
                    + " and you can use any number between 0 and " + (Config.getNbDigit() - 1) + ".\n"
                    + "Use your brain !\n");
        }

        secretCode = defender.generateSecretCode();
        logger.info("The secret code is " + Arrays.toString(secretCode));

        if (!defender.getClass().getSimpleName().equals("Human")) {
            System.out.println("The computer's secret code has been defined !\n");
        }

        //mode DEV
        if (Config.isModeDev()) {
            System.out.println("DEVELOPER MODE");
            System.out.println("The secret code is : " + Arrays.toString(secretCode));
        }

        while(!asWon && (round < maxRound)) {
            round++;
            System.out.println(String.join("*", Collections.nCopies(40, "*"))
                    + "\nROUND : " + round);

            if(attacker.getClass().getSimpleName().equals("Human")) {
                System.out.println("Enter your code\r");
                guessCode = attacker.guessTheCode();
                System.out.println("\nYou play " + Arrays.toString(guessCode) + "\n");
            } else {
                guessCode = attacker.guessTheCode();
                System.out.println("\nThe computer play " + Arrays.toString(guessCode) + "\n");
            }

            compareCode = compareCode(guessCode, secretCode);
            asWon = isAsWon(compareCode);

            if (!asWon) {
                attacker.getClues(compareCode.toCharArray());
            }
        }
        attacker.sendScore(asWon);
        System.out.println("The secret code was : " + Arrays.toString(secretCode) + "\n\n"
                + String.join("#", Collections.nCopies(40, "#")) + "\n");
    }

    /*
     * Method to compare the secret code and the guess code
     * @param guessCode		the code of the attacker
     * @param secretCode	the code of the defender
     * @return String 		the result of the comparison
     */
    @Override
    public String compareCode(int[] guessCode, int[] secretCode) {
        logger.debug("run method compareCode()");
        StringBuffer strB = new StringBuffer();
        int nbWellPlaced = 0;
        // number of well placed number in the code
        for (int i = 0; i < codeLength; i++){
            if (guessCode[i] == secretCode[i]){
                nbWellPlaced++;
            }
        }
        // number of present number in the code
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
        strB.append(nbWellPlaced + " Well placed | " + nbPresent + " Present\n");
        return strB.toString();
    }

    /**
     * Private method that take the answer in parameter
     * and return a boolean
     * @param strB
     * @return boolean
     */
    private static boolean isAsWon(String strB) {
        return strB.equals(Config.getCodeLength()+ " Well placed | 0 Present\n");
    }
}
