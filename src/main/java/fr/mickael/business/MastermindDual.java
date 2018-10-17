package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;
import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MastermindDual extends Game {

    private static Logger logger = LogManager.getLogger();
    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

    @Override
    public void play() {

        logger.debug("initialisation des variables");
        int round = 0;
        boolean asWon = false;
        int[] playerOneSecretCode;
        int[] playerTwoSecretCode;
        int[] attackerGuessCode;
        String compareCodeAttacker = "";

        System.out.println("MASTERMIND\n"
                + String.join("*", Collections.nCopies(30, "*")) + "\n"
                + "MODE : DUAL\n"
                + "You have " + maxRound + " round to find\n"
                + "the computer's code before it find yours !\n"
                + "Let's the fight begin !\n");


        playerOneSecretCode = attacker.generateSecretCode();
        System.out.println("Your secret code has been defined.\n");
        playerTwoSecretCode = defender.generateSecretCode();
        System.out.println("The computer's secret code has been defined !\n"
                + String.join("*", Collections.nCopies(30, "*")) + "\n");


        List<Player> players = new LinkedList<>();
        players.add(attacker);
        players.add(defender);

        List<int[]> playersSecretCodes = new LinkedList<>();
        playersSecretCodes.add(playerTwoSecretCode);
        playersSecretCodes.add(playerOneSecretCode);


        while (!asWon && (round < maxRound)) {
            round++;
            System.out.println(String.join("*", Collections.nCopies(30, "*")) + "\n"
                    + "ROUND : " + round);
            int test = 0;
            while( test != 2) {
                if (attacker.getClass().getSimpleName().equals("Human")) {
                    System.out.println("Player " + (test + 1) + " enter your code");
                    attackerGuessCode = players.get(test).guessTheCode();
                    System.out.println("Player " + (test + 1) + " you play " + Arrays.toString(attackerGuessCode) + "\n");
                } else {
                    attackerGuessCode = players.get(test).guessTheCode();
                    System.out.println("The computer play (aka player " + (test + 1) + ") "+ Arrays.toString(attackerGuessCode));
                }
                compareCodeAttacker = compareCode(attackerGuessCode, playersSecretCodes.get(test));
                asWon = isAsWon(compareCodeAttacker);
                if (!asWon) {
                    players.get(test).getClues(compareCodeAttacker.toCharArray());
                    test++;
                } else if (asWon){
                    test = 2;
                }
            }
        }
        attacker.sendScore(asWon);
        if (attacker.getClass().getSimpleName().equals("Human")) {
            System.out.println("The secret code was : "
                    + Arrays.toString(playersSecretCodes.get(0)) + "\n"
                    + String.join("#", Collections.nCopies(30, "#")) + "\n");
        } else {
            System.out.println("The secret code was : "
                    + Arrays.toString(playersSecretCodes.get(1)) + "\n"
                    + String.join("#", Collections.nCopies(30, "#")) + "\n");
        }
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
        strB.append(nbWellPlaced + " Well placed " + nbPresent + " Present\n");
        return strB.toString();
    }

    private static boolean isAsWon(String strB) {
        return strB.equals(Config.getCodeLength() + " well placed | 0 present");
    }
}
