package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;
import main.java.fr.mickael.util.Config;

import java.util.Arrays;
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

        playerOneSecretCode = attacker.generateSecretCode();
        playerTwoSecretCode = defender.generateSecretCode();

        List<Player> players = new LinkedList<>();
        players.add(attacker);
        players.add(defender);

        List<int[]> playersSecretCodes = new LinkedList<>();
        playersSecretCodes.add(playerTwoSecretCode);
        playersSecretCodes.add(playerOneSecretCode);


        while (!asWon && (round < maxRound)) {
            round++;
            int test = 0;
            while( test != 2) {
                System.out.println("joueur " + (test + 1) + " tapez le code secret au round " + round);
                attackerGuessCode = players.get(test).guessTheCode();
                System.out.println("Le joueur " + (test + 1) + " a joué " + Arrays.toString(attackerGuessCode));
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

