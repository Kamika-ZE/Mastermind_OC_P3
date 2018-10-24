package main.java.fr.mickael.model;

import main.java.fr.mickael.exceptions.CodeInvalidException;
import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Human implements Player{

    private static Logger logger = LogManager.getLogger();
    private Scanner sc = new Scanner(System.in);
    private int[] humanSecretCode;
    private int[] humanGuessCode;
    private int codeLength = Config.getCodeLength();

    /**
     * Constructor of the class without parameter
     */
    public Human() {
        this.humanSecretCode = new int[codeLength];
        this.humanGuessCode = new int[codeLength];
    }

    /**
     * Implementation of method that generate the secret code
     * @return secretCode
     */
    @Override
    public int[] generateSecretCode() {
        int[] secretCode = null;
        while (secretCode == null){
            System.out.println("Enter a secret code of size " + codeLength + ".\n"
                    + "The numbers must be between 0 et " + (Config.getNbDigit() - 1 + "\n")
                    + "Press ENTER to validate\n");
            try {
                secretCode = getSecretCode();
            } catch (CodeInvalidException e) {
                System.out.println(e.getLocalizedMessage());
                logger.warn("CodeInvalidException : " + e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                System.out.println("\nBe careful. Use number only please.\n");
                logger.warn("NumberFormatException : " + e.getLocalizedMessage());
            }
        }
        return secretCode;
    }

    /**
     * Implementation of method that generate the secret code
     * @return guessCode
     */
    @Override
    public int[] guessTheCode() {
        int[] guessCode = null;
        while (guessCode == null){
            try {
                guessCode = getGuessCode();
            } catch (CodeInvalidException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                System.out.println("\nBe careful. Use number only please.\n");
            }
        }
        return guessCode;
    }

    /**
     * Implementation of method that print the answer
     */
    @Override
    public void getClues(char[] answer) {
        System.out.println(answer);
    }

    /**
     * Implementation of method that print the score
     */
    @Override
    public void sendScore(boolean win) {
        if (win){
            System.out.println("\nCONGRATULATIONS ! YOU WIN !\n");
        } else {
            System.out.println("\nTOO BAD ! YOU LOSE !\n");
        }
    }

    /**
     * Implementation of method that return the secret code.
     * @return humanSecretCode		the human secret code.
     */
    private int[] getSecretCode() throws CodeInvalidException {
        String str = sc.nextLine();
        if (str.length() != codeLength){
            throw new CodeInvalidException("\nBe careful ! The code size is "
                    + Config.getCodeLength() + " !\n");
        }
        for (int i = 0; i < codeLength; i++){
            humanSecretCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanSecretCode;
    }

    /**
     * Implementation of method that return the guess code.
     * @return humanGuessCode		the human guess code.
     */
    private int[] getGuessCode() throws CodeInvalidException {
        String str = sc.nextLine();
        if (str.length() != codeLength){
            throw new CodeInvalidException("\nBe careful ! The code size is "
                    + Config.getCodeLength() + " !\n");
        }
        if (str.contains(String.valueOf(Config.getNbDigit()))) {
            throw new CodeInvalidException("\nBe careful ! Please choose any number between 0 and "
                    + (Config.getNbDigit() - 1) + " !\n");
        }
        for (int i = 0; i < codeLength; i++){
            humanGuessCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanGuessCode;
    }
}
