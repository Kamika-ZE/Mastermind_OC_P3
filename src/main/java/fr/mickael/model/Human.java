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
        int[] secretCode = null;
        while (secretCode == null){
            System.out.println("Enter a secret code of size " + codeLength + ".\n"
                    + "The numbers must be between 0 et " + (Config.getNbDigit() - 1 + "\n")
                    + "Press ENTER to validate\n");
            try {
                secretCode = getSecretCode();
            } catch (CodeLengthException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                System.out.println("Be careful. Use number only please.\n");
            }
        }
        return secretCode;
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
                System.out.println("Be careful. Use number only please.\n");
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
            System.out.println("\nCONGRATULATIONS ! YOU WIN !\n");
        } else {
            System.out.println("\nTOO BAD ! YOU LOSE !\n");
        }
    }

    private int[] getSecretCode() throws CodeLengthException {
        String str = sc.nextLine();
        if (str.length() != codeLength){
            throw new CodeLengthException("Be careful ! The code size is "
                    + Config.getCodeLength() + " !\n");
        }
        for (int i = 0; i < codeLength; i++){
            humanSecretCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanSecretCode;
    }
    private int[] getGuessCode() throws CodeLengthException {
        String str = sc.nextLine();
        if (str.length() != codeLength){
            throw new CodeLengthException("Be careful ! The code size is "
                    + Config.getCodeLength() + " !\n");
        }
        for (int i = 0; i < codeLength; i++){
            humanGuessCode[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return humanGuessCode;
    }
}
