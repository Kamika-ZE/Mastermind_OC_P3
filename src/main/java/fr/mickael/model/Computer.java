package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;

import java.util.Random;

public abstract class Computer implements Player{

    protected int[] computerSecretCode;
    protected int codeLength = Config.getCodeLength();
    protected int nbDigit = Config.getNbDigit();

    public Computer() {
        this.computerSecretCode = new int[codeLength];
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
            System.out.println("YOU ARE LUCKY ! THE COMPUTER HAS LOST !");
        } else {
            System.out.println("OH MY GOD ! THE COMPUTER HAS WIN !");
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
