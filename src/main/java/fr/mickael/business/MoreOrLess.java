package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;

import java.util.Arrays;

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

        secretCode = defender.generateSecretCode();

        while(!asWon && (round < maxRound)) {
            round++;
            System.out.println("tapez le code secret");
            guessCode = attacker.guessTheCode();
            System.out.println("Le joueur a joué " + Arrays.toString(guessCode));
            compareCode = compareCode(guessCode, secretCode);
            asWon = isAsWon(compareCode);
            if (!asWon) {
                attacker.getClues(compareCode.toCharArray());
            }
        }
        attacker.sendScore(asWon);
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
        return strB.equals("====");
    }
}
