package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;
import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
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
        int nbWellPlaced = 0;
        // calcul des bien placés
        for (int i = 0; i < codeLength; i++){
            if (guessCode[i] == secretCode[i]){
                nbWellPlaced++;
            }
        }
        // calcul des présents
        int nbPresent = Util.getNbPresent(guessCode, secretCode, nbWellPlaced);
        strB.append(nbWellPlaced);
        if (nbWellPlaced > 1) {
            strB.append(" bien placés ");
        } else {
            strB.append(" bien placé ");
        }
        strB.append(nbPresent);
        if (nbPresent > 1) {
            strB.append(" présents");
        } else {
            strB.append(" présent");
        }
        return strB.toString();
    }

    private static boolean isAsWon(String strB) {
        return strB.equals(Config.getCodeLength() + " bien placés| 0 présent");
    }
}
