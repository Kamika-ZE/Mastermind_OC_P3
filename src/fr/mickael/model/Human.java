package fr.mickael.model;

import static fr.mickael.util.Config.codeLength;
import java.util.Scanner;

public class Human implements Player{

    private Scanner sc = new Scanner(System.in);
    private int[] humanCode;

    public Human() {
        this.humanCode = new int[codeLength];
    }

    @Override
    public int[] generateSecretCode() {
        System.out.println("Tapez un code à 4 chiffres compris entre 0 et 9 puis validez par ENTREE");
        return getSecretCode();
    }

    @Override
    public int[] guessTheCode() {
        return getSecretCode();
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
        //int[] humanSecretCode = new int[codeLength];
        String str = sc.nextLine();
        for (int i = 0; i < codeLength; i++){
            humanCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanCode;
    }
}
