package main.java.fr.mickael.model;

import static main.java.fr.mickael.util.Config.*;

public class ComputerMoreOrLess extends Computer {

    private int[][] ecart;

    public ComputerMoreOrLess() {
        super.computerSecretCode = new int[codeLength];
        this.ecart = new int[codeLength][];
        for (int i = 0; i < ecart.length; i++){
            this.ecart[i] = new int[]{0, 10};
        }
    }


    @Override
    public int[] guessTheCode() {
        for(int i = 0; i < computerSecretCode.length; i++){
            computerSecretCode[i] = (ecart[i][0] + ecart[i][1])/2;
        }
        return computerSecretCode;
    }
    @Override
    public void getClues(char[] answer) {
        // reduire l'�cart

        for (int i = 0; i < 4; i++) {
            if (answer[i] == '-') {
                ecart[i][1] = computerSecretCode[i];
            } else if (answer[i] == '+') {
                ecart[i][0] = computerSecretCode[i];
            }
        }
        System.out.println(answer);

    }
}
