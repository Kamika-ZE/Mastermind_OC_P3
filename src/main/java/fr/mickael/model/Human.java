package main.java.fr.mickael.model;

import main.java.fr.mickael.exceptions.CodeLengthException;
import main.java.fr.mickael.util.Config;

import java.util.Scanner;

public class Human implements Player{

    private Scanner sc = new Scanner(System.in);
    private int[] humanSecretCode;
    private int[] humanGuessCode;
    private int codeLength = Config.getCodeLength();

    public Human() {
        this.humanSecretCode = new int[codeLength];
        this.humanGuessCode = new int[codeLength];
    }

    @Override
    public int[] generateSecretCode() {
        System.out.println("Tapez un code à " + codeLength +
                " chiffres compris entre 0 et " + (Config.getNbDigit() - 1) + " puis validez par ENTREE");
        return getSecretCode();
}

    @Override
    public int[] guessTheCode() {
        int[] guessCode = null;
        while (guessCode == null){
            try {
                guessCode = getGuessCode();
            } catch (CodeLengthException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                System.out.println("Attention il faut utiliser des chiffres !");
            }
        }
        return guessCode;
    }

    @Override
    public void getClues(char[] answer) {
        System.out.println(answer);
    }

    @Override
    public void sendScore(boolean win) {
        if (win){
            System.out.println("BRAVO ! Vous avez gagné !");
        } else {
            System.out.println("DOMMAGE ! Vous avez perdu !");
        }
    }

    private int[] getSecretCode() {
        String str = sc.nextLine();
        for (int i = 0; i < codeLength; i++){
            humanSecretCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanSecretCode;
    }
    private int[] getGuessCode() throws CodeLengthException {
        String str = sc.nextLine();
        if (str.length() != codeLength){
            throw new CodeLengthException("Il manque des chiffres. La taille du code est "
                    + Config.getCodeLength() + " !");
        }
        for (int i = 0; i < codeLength; i++){
            humanGuessCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanGuessCode;
    }
}
