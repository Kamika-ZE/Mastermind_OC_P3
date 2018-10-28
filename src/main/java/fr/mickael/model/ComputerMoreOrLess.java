package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ComputerMoreOrLess extends Computer {

    private static Logger logger = LogManager.getLogger();
    private int[][] gap;
    private int codeLength = Config.getCodeLength();

    /**
     * Constructor for the more or less computer.
     * Inherits the computer constructor
     */
    public ComputerMoreOrLess() {
        super.computerSecretCode = new int[codeLength];
        super.computerGuessCode = new int[codeLength];
        this.gap = new int[codeLength][];
        for (int i = 0; i < gap.length; i++){
            this.gap[i] = new int[]{0, 10};
        }
    }


    /**
     * Method that return the computer guess code
     * @return computerGuessCode
     */
    @Override
    public int[] guessTheCode() {
        for(int i = 0; i < computerGuessCode.length; i++){
            computerGuessCode[i] = (gap[i][0] + gap[i][1])/2;
        }
        return computerGuessCode;
    }

    /**
     * Method used to filter the result by dichotomy
     * and allow the computer to reduce the gap.
     */
    @Override
    public void getClues(char[] answer) {
        logger.debug("running getClues(). Take a parameter char[] answer : " + Arrays.toString(answer));
        for (int i = 0; i < 4; i++) {
            if (answer[i] == '-') {
                gap[i][1] = computerGuessCode[i];
            } else if (answer[i] == '+') {
                gap[i][0] = computerGuessCode[i];
            }
        }
        System.out.println(answer);
    }
}
