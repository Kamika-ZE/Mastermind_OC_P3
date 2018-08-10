package fr.mickael.business;

import java.util.Arrays;

import static fr.mickael.util.Config.*;

public class Mastermind extends Game{

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
        // comme on doit enlever les pions bien placés, on initialise nbPresent à - nbWellPlaced
        int nbPresent = - nbWellPlaced;
        for (int i = 0; i < nbDigit; i++){
            int presentSecretCode = 0;
            int presentGuessCode = 0;
            for (int j = 0; j < codeLength; j++){
                if (secretCode[j] == i){
                    presentSecretCode++;
                }
                if (guessCode[j] == i){
                    presentGuessCode++;
                }
            }
            if (presentSecretCode < presentGuessCode){
                nbPresent = presentSecretCode + nbPresent;
            } else {
                nbPresent = presentGuessCode + nbPresent;
            }
        }
        strB.append(nbWellPlaced + " Bien placés " + nbPresent + " Présents");
        return strB.toString();
    }

    private static boolean isAsWon(String strB) {
        return strB.equals("4 Bien placés 0 Présents");
    }
}
