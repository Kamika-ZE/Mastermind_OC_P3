package main.java.fr.mickael.model;

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
        System.out.println("Tapez un code à 4 chiffres compris entre 0 et 9 puis validez par ENTREE");
        return getSecretCode();
    }

    @Override
    public int[] guessTheCode() {
        return getGuessCode();
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
    private int[] getGuessCode() {
        String str = sc.nextLine();
        for (int i = 0; i < codeLength; i++){
            humanGuessCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanGuessCode;
    }
}
