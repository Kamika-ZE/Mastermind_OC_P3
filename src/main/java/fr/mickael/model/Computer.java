package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;

import java.util.Random;

public abstract class Computer implements Player{

    protected int[] computerSecretCode;
    protected int[] computerGuessCode;
    protected int codeLength = Config.getCodeLength();
    protected int nbDigit = Config.getNbDigit();

    public Computer() {
        this.computerSecretCode = new int[codeLength];
        this.computerGuessCode = new int[codeLength];
    }


    @Override
    public int[] generateSecretCode() {
        return getSecretCode();
    }

    @Override
    public abstract int[] guessTheCode();//Ici on impl�mente l'IA

    @Override
    public abstract void getClues(char[] answer);

    @Override
    public void sendScore(boolean win) {
        if (win){
            System.out.println("\nYOU ARE LUCKY ! THE COMPUTER HAS LOST !\n");
        } else {
            System.out.println("\nOH MY GOD ! THE COMPUTER HAS WON !\n");
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
