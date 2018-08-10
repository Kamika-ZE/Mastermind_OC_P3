package fr.mickael.business;

import java.util.Arrays;

import static fr.mickael.util.Config.codeLength;
import static fr.mickael.util.Config.maxRound;

public class MoreOrLess extends Game{

    @Override
    public void play() {
        int round = 0;
        boolean asWon = false;
        int[] secretCode = new int[codeLength];
        int[] guessCode = new int[codeLength];
        String compareCode = "";

        secretCode = defender.generateSecretCode();
        while(!asWon && round < maxRound) {
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
        for (int i = 0; i < 4; i++) {
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
