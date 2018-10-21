package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;
import main.java.fr.mickael.util.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MoreOrLessDual extends Game{
    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

    @Override
    public void play() {

        int round = 0;
        boolean asWon = false;
        int[] playerOneSecretCode;
        int[] playerTwoSecretCode;
        int[] attackerGuessCode;
        String compareCodeAttacker = "";
        String numberPlayer = "";

        System.out.println("\nMORE OR LESS\n"
                + String.join("*", Collections.nCopies(40, "*")) + "\n"
                + "MODE : DUAL\n\n"
                + "You have " + maxRound + " round to find\n"
                + "the computer's code before it find yours !\n"
                + "The code size is " + codeLength
                + " and you can use any number between 0 and " + (Config.getNbDigit() - 1) + ".\n"
                + "Let's the fight begin !\n");


        playerOneSecretCode = attacker.generateSecretCode();
        System.out.println("Your secret code has been defined.\n");
        playerTwoSecretCode = defender.generateSecretCode();
        System.out.println("The computer's secret code has been defined !\n"
                + String.join("*", Collections.nCopies(40, "*")) + "\n");

        List<Player> players = new LinkedList<>();
        players.add(attacker);
        players.add(defender);

        List<int[]> playersSecretCodes = new LinkedList<>();
        playersSecretCodes.add(playerTwoSecretCode);
        playersSecretCodes.add(playerOneSecretCode);

        while (!asWon && (round < maxRound)) {
            round++;
            int test = 0;
            System.out.println("\n" + String.join("*", Collections.nCopies(40, "*"))
                    + "\nROUND : " + round);

            while( test != 2) {
                if (test == 0) {
                    System.out.println("\nPlayer " + (test + 1) + " (aka you) enter your code : ");
                }

                attackerGuessCode = players.get(test).guessTheCode();

                if (test == 0) {
                    System.out.println("\nPlayer " + (test + 1) + " you play : " + Arrays.toString(attackerGuessCode) + "\n");
                } else {
                    System.out.println("\nThe computer play (aka player " + (test + 1) + ") "+ Arrays.toString(attackerGuessCode) + "\n");
                }

                compareCodeAttacker = compareCode(attackerGuessCode, playersSecretCodes.get(test));
                asWon = isAsWon(compareCodeAttacker);

                if (!asWon) {
                    players.get(test).getClues(compareCodeAttacker.toCharArray());
                    test++;
                } else if (asWon){
                    numberPlayer = players.get(test).getClass().getSimpleName();
                    test = 2;
                }
            }
        }
        if (numberPlayer.equals("Human")) {
            attacker.sendScore(asWon);
            System.out.println("The computer's secret code was : "
                    + Arrays.toString(playersSecretCodes.get(0))
                    + "\n\n"
                    + String.join("#", Collections.nCopies(40, "#"))
                    + "\n\n");
        } else {
            defender.sendScore(asWon);
            System.out.println("Your secret code was : "
                    + Arrays.toString(playersSecretCodes.get(1))
                    + "\nFor your information, the computer's secret code was : "
                    + Arrays.toString(playersSecretCodes.get(0))
                    + "\n\n"
                    + String.join("#", Collections.nCopies(40, "#"))
                    + "\n\n");
        }
    }

    @Override
    public String compareCode(int[] guessCode, int[] secretCode) {
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

    private static boolean isAsWon(String strB) {
        if (strB.contains("-") || strB.contains("+")){
            return false;
        }
        return true;
    }
}

