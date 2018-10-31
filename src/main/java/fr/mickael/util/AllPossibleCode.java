package main.java.fr.mickael.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to generate a list of all possible code.
 * @author M. COZ
 * @deprecated 	No longer use. Use of stream instead.
 */
public class AllPossibleCode {

    private List<int[]> allCode;
    private int codeLength = Config.getCodeLength();
    private int nbDigit = Config.getNbDigit();
    private int[] code = new int[codeLength];

    /**
     * Constructor without parameter.
     */
    public AllPossibleCode() {
        this.allCode = new ArrayList<>();
    }

    /**
     * Recursive method that generate a list of all possible code.
     * @param codeIndex		the starting index. The codeIndex should be 0.
     */
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

    /**
     * Method that return the list of all possible code.
     * @return allCode a list of all possible code.
     */
    public List<int[]> getAllCode () {
        return allCode;
    }
}
