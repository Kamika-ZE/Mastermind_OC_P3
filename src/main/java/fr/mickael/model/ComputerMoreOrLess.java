package main.java.fr.mickael.model;

import main.java.fr.mickael.util.Config;

public class ComputerMoreOrLess extends Computer {

    private int[][] ecart;
    private int codeLength = Config.getCodeLength();

    public ComputerMoreOrLess() {
        super.computerSecretCode = new int[codeLength];
        super.computerGuessCode = new int[codeLength];
        this.ecart = new int[codeLength][];
        for (int i = 0; i < ecart.length; i++){
            this.ecart[i] = new int[]{0, 10};
        }
    }


    @Override
    public int[] guessTheCode() {
        for(int i = 0; i < computerGuessCode.length; i++){
            computerGuessCode[i] = (ecart[i][0] + ecart[i][1])/2;
        }
        return computerGuessCode;
    }
    @Override
    public void getClues(char[] answer) {
        // reduire l'�cart

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
