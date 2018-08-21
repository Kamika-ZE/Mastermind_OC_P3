package main.java.fr.mickael.util;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleCode {

    private List<int[]> allCode;
    private int codeLength = Config.getCodeLength();
    private int nbDigit = Config.getNbDigit();
    private int[] code = new int[codeLength];


    public AllPossibleCode() {
        this.allCode = new ArrayList<>();
    }


    public void generateAllCode(int codeIndex) {
        if (codeIndex >= codeLength) {
            allCode.add(code.clone());
            return;
        }

        for(int i = 0 ; i < nbDigit; i++) {
            code[codeIndex] = i;
            generateAllCode(codeIndex + 1);
        }
    }

    public List<int[]> getAllCode () {
        return allCode;
    }
}
