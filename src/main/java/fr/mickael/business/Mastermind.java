package main.java.fr.mickael.business;

import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Mastermind extends Game{

    private static Logger logger = LogManager.getLogger();

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
            System.out.println("tapez le code secret au round " + round);
            guessCode = attacker.guessTheCode();
            System.out.println("Le joueur a joué " + Arrays.toString(guessCode));
            compareCode = compareCode(guessCode, secretCode);
            asWon = isAsWon(compareCode);
            if (!asWon) {
                attacker.getClues(compareCode.toCharArray());
            }
        }
        if (asWon) {
            attacker.getClues(compareCode.toCharArray());
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
