package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;

public class Mastermind extends Game{

    private static Logger logger = LogManager.getLogger();

    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

    @Override
    public void play() {
        logger.debug("play");
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
        if (!defender.getClass().getSimpleName().equals("Human")) {
            System.out.println("The computer's secret code has been defined !\n");
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

    @Override
    public String compareCode(int[] guessCode, int[] secretCode) {
        StringBuffer strB = new StringBuffer();
        int nbWellPlaced = 0;
        // calcul des bien placés
        for (int i = 0; i < codeLength; i++){
            if (guessCode[i] == secretCode[i]){
                nbWellPlaced++;
            }
        }
        // calcul des présents
        int nbPresent = Constants.getNbPresent(guessCode, secretCode, nbWellPlaced);
        strB.append(nbWellPlaced + " Well placed | " + nbPresent + " Present\n");
        return strB.toString();
    }

    private static boolean isAsWon(String strB) {
        return strB.equals(Config.getCodeLength()+ " Well placed | 0 Present\n");
    }
}
