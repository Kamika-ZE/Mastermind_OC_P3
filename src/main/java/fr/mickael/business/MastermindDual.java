package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class MastermindDual extends Game {

    private static Logger logger = LogManager.getLogger();
    private int codeLength = Config.getCodeLength();
    private int maxRound = Config.getMaxRound();

    @Override
    public void play() {

        logger.debug("initialisation des variables");
        int round = 0;
        boolean asWon = false;
        int[] attackerSecretCode;
        int[] defenderSecretCode;
        int[] attackerGuessCode;
        int[] defenderGuessCode;
        String compareCodeAttacker = "";
        String compareCodeDefender = "";

        attackerSecretCode = attacker.generateSecretCode();
        defenderSecretCode = defender.generateSecretCode();

        boolean playerOne = true;

        while((round < maxRound)) {
            round++;

            while (!asWon) {
                if (playerOne) {
                    System.out.println("joueur 1 tapez le code secret au round " + round);
                    attackerGuessCode = attacker.guessTheCode();
                    System.out.println("Le joueur 1 a joué " + Arrays.toString(attackerGuessCode));
                    compareCodeAttacker = compareCode(attackerGuessCode, defenderSecretCode);
                    asWon = isAsWon(compareCodeAttacker);
                    playerOne = false;
                    if (!asWon) {
                        attacker.getClues(compareCodeAttacker.toCharArray());
                    }
                } else {
                    System.out.println("joueur 2 tapez le code secret au round " + round);
                    defenderGuessCode = defender.guessTheCode();
                    System.out.println("Le joueur 2 a joué " + Arrays.toString(defenderGuessCode));
                    compareCodeDefender = compareCode(defenderGuessCode, attackerSecretCode);
                    asWon = isAsWon(compareCodeDefender);
                    playerOne = true;
                    if (!asWon) {
                        defender.getClues(compareCodeDefender.toCharArray());
                    }
                }
            }

        }
        if (asWon) {
            attacker.sendScore(true);
        } else {
            attacker.sendScore(false);
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
        int nbPresent = Util.getNbPresent(guessCode, secretCode, nbWellPlaced);
        strB.append(nbWellPlaced + " Bien placés " + nbPresent + " Présents");
        return strB.toString();
    }

    private static boolean isAsWon(String strB) {
        return strB.equals("4 Bien placés 0 Présents");
    }
}
