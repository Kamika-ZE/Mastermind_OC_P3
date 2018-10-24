package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerMoreOrLess extends Computer {

    private static Logger logger = LogManager.getLogger();
    private int[][] ecart;
    private int codeLength = Config.getCodeLength();

    /**
     * Constructor for the more or less computer.
     * Inherits the computer constructor
     */
    public ComputerMoreOrLess() {
        super.computerSecretCode = new int[codeLength];
        super.computerGuessCode = new int[codeLength];
        this.ecart = new int[codeLength][];
        for (int i = 0; i < ecart.length; i++){
            this.ecart[i] = new int[]{0, 10};
        }
    }


    /**
     * Method that return the computer guess code
     * @return computerGuessCode
     */
    @Override
    public int[] guessTheCode() {
        for(int i = 0; i < computerGuessCode.length; i++){
            computerGuessCode[i] = (ecart[i][0] + ecart[i][1])/2;
        }
        return computerGuessCode;
    }

    /**
     * Method used to filter the result by dichotomy
     * and allow the computer to reduce the gaps.
     */
    @Override
    public void getClues(char[] answer) {

        for (int i = 0; i < 4; i++) {
            if (answer[i] == '-') {
                ecart[i][1] = computerGuessCode[i];
            } else if (answer[i] == '+') {
                ecart[i][0] = computerGuessCode[i];
            }
        }
        System.out.println(answer);
    }
}
