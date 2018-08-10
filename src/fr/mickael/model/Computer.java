package fr.mickael.model;

import java.util.Random;
import static fr.mickael.util.Config.*;

public abstract class Computer implements Player{

    protected int[] computerSecretCode;

    public Computer() {
        this.computerSecretCode = new int[codeLength];
    }


    @Override
    public int[] generateSecretCode() {
        System.out.println("Le code secret a été défini !");
        return getSecretCode();
    }

    @Override
    public abstract int[] guessTheCode();//Ici on impl�mente l'IA

    @Override
    public abstract void getClues(char[] answer);

    @Override
    public void sendScore(boolean win) {
        if (win){
            System.out.println("BRAVO ! Vous avez gagné !");
        } else {
            System.out.println("DOMMAGE ! Vous avez perdu !");
        }
    }


    private int[] getSecretCode() {
        Random rand = new Random();
        for (int i =0; i < codeLength; i++){
            computerSecretCode[i] = rand.nextInt(nbDigit);
        }
        return computerSecretCode;
    }
}
