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

        System.out.println("MORE OR LESS\n"
                + String.join("*", Collections.nCopies(30, "*")) + "\n");
        if (defender.getClass().getSimpleName().equals("Human")){
            System.out.println("MODE : DEFENDER\n"
                    + "The computer have " + maxRound + " round to find your code.\n"
                    + "Make it suffer !\n");
        } else {
            System.out.println("MODE : CHALLENGER\n"
                    + "You have " + maxRound + " round to find the computer code.\n"
                    + "Use your brain !\n");
        }

        secretCode = defender.generateSecretCode();
        if (!defender.getClass().getSimpleName().equals("Human")) {
            System.out.println("The computer's secret code has been defined !\n");
        }

        while(!asWon && (round < maxRound)) {
            round++;
            System.out.println(String.join("*", Collections.nCopies(30, "*")) + "\n"
                    + "ROUND : " + round);
            if(attacker.getClass().getSimpleName().equals("Human")) {
                System.out.println("Enter your code\r");
                guessCode = attacker.guessTheCode();
                System.out.println("You play " + Arrays.toString(guessCode) + "\n\n");
            } else {
                guessCode = attacker.guessTheCode();
                System.out.println("The computer play " + Arrays.toString(guessCode) + "\n\n");
            }
            compareCode = compareCode(guessCode, secretCode);
            asWon = isAsWon(compareCode);
            if (!asWon) {
                attacker.getClues(compareCode.toCharArray());
            }
        }
        attacker.sendScore(asWon);
        System.out.println("The secret code was : " + Arrays.toString(secretCode) + "\n"
                + String.join("#", Collections.nCopies(30, "#")) + "\n");
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
