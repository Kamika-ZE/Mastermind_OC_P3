package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Class Computer
 * This class implements the Player interface.
 * @author M. COZ
 *
 */
public abstract class Computer implements Player{

    private static Logger logger = LogManager.getLogger();
    protected int[] computerSecretCode;
    protected int[] computerGuessCode;
    protected int codeLength = Config.getCodeLength();
    protected int nbDigit = Config.getNbDigit();

    /**
     * Constructor of the class without parameter.
     */
    public Computer() {
        this.computerSecretCode = new int[codeLength];
        this.computerGuessCode = new int[codeLength];
    }



    @Override
    public int[] generateSecretCode() {
        logger.debug("running generateSecreteCode(). Return an int[] generate by the private method getSecretCode().");
        return getSecretCode();
    }

    @Override
    public abstract int[] guessTheCode();

    @Override
    public abstract void getClues(char[] answer);

    /**
     * Implementation of method that print the score
     */
    @Override
    public void sendScore(boolean win) {
        if (win){
            System.out.println("\nYOU ARE LUCKY ! THE COMPUTER HAS LOST !\n");
        } else {
            System.out.println("\nOH MY GOD ! THE COMPUTER HAS WON !\n");
        }
    }

    /**
     * Private method that return the computer secret code.
     * @return computerSecretCode
     */
    private int[] getSecretCode() {
        Random rand = new Random();
        for (int i =0; i < codeLength; i++){
            computerSecretCode[i] = rand.nextInt(nbDigit);
        }
        return computerSecretCode;
    }
}
