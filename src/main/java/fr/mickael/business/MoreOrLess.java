package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;

import java.util.Arrays;
import java.util.Collections;

public class MoreOrLess extends Game{

    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

    @Override
    public void play() {
        int round = 0;
        boolean asWon = false;
        int[] secretCode;
        int[] guessCode;
        String compareCode = "";

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

        if (!defender.getClass().getSimpleName().equals("Human")) {
            System.out.println("The computer's secret code has been defined !\n");
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
