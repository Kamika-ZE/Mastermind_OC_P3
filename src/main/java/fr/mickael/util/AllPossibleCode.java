package main.java.fr.mickael.util;

import java.util.ArrayList;
import java.util.List;
import static main.java.fr.mickael.util.Config.*;

public class AllPossibleCode {

    private List<int[]> allCode;
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
