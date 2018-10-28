package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class of the game More or Less
 * Implementation of the game for the mode challenger and defender
 * @author M. COZ
 *
 */
public class MoreOrLess extends Game{

    private static Logger logger = LogManager.getLogger();
    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

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
        String compareCode;

        System.out.println("\nMORE OR LESS\n"
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
            System.out.println("nbDigit : " + Config.getNbDigit());
            System.out.println("codeLength : " + Config.getCodeLength());
            System.out.println("maxRound : " + Config.getMaxRound());
        }

        while(!asWon && (round < maxRound)) {
            round++;
            System.out.println("\n" + String.join("*", Collections.nCopies(40, "*"))
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
        logger.debug("run method compareCode().\n"
                + "Parameters : int[] guessCode = " + Arrays.toString(guessCode) + "\n"
                + "int[] secretCode = " + Arrays.toString(secretCode));
        StringBuffer strB = new StringBuffer();
        for (int i = 0; i < codeLength; i++) {
            if (guessCode[i] == secretCode[i]) {
                strB.append("=");
            } else if (guessCode[i] < secretCode[i]) {
                strB.append("+");
            } else {
                strB.append("-");
            }
        }
        return strB.toString();
    }

    /**
     * Private method that take the answer in parameter
     * and return a boolean depending of the value of the parameter
     * @param strB      the String return by the method compareCode()
     * @return boolean
     */
    protected static boolean isAsWon(String strB) {
        if (strB.contains("-") || strB.contains("+")){
            return false;
        }
        return true;
    }
}
