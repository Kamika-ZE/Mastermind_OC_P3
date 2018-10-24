package main.java.fr.mickael.model;

import javafx.util.Pair;
import main.java.fr.mickael.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputerMastermind extends Computer {

    private static Logger logger = LogManager.getLogger();
    private List<int[]> allPossibilities;
    private List<Integer> allPossibleScore;
    private int codeLength = Config.getCodeLength();
    private int nbDigit = Config.getNbDigit();


    /**
     * Constructor for the mastermind computer.
     * Inherits the computer constructor
     */
    public ComputerMastermind() {
        super.computerSecretCode = new int[codeLength];
        super.computerGuessCode = new int[codeLength];
        this.allPossibilities = new ArrayList<>();
        this.allPossibleScore = generateAllPossibleScore();
    }

    /**
     * Method that return the computer guess code
     * @return computerGuessCode
     */
    @Override
    public int[] guessTheCode() {
        // tous les poids
        if (allPossibilities.isEmpty()) {
            int[] codeTemp = new int[codeLength];
            for (int i = 0; i < codeLength; i++) {
                codeTemp[i] = i % nbDigit;
            }
            computerGuessCode = codeTemp;

        } else {
            Optional<Pair<int[], Integer>> min = allPossibilities.parallelStream()
                    .map(code -> new Pair<>(code, getPropositionWeight(code)))
                    .sorted(Comparator.comparing(Pair::getValue))
                    .min(Comparator.comparing(Pair::getValue));
            computerGuessCode = min.get().getKey();
        }
        return computerGuessCode;
    }

    /**
     * Method used to reduce the list of all the possible code
     *
     */
    @Override
    public void getClues(char[] answer) {
        StringBuffer strB = new StringBuffer();
        for (int i = 0; i < answer.length; i++) {
            strB.append(answer[i]);
        }
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(strB.toString());
        String scoreString = "";
        while (matcher.find()) {
            scoreString += matcher.group(0);
        }
        int score = Integer.parseInt(scoreString);

        if (allPossibilities.isEmpty()) {
            allPossibilities = generateAllPossibilities()
                    .filter(code -> getScoreGuessCode(code, computerGuessCode) == score)
                    .collect(Collectors.toList());
        } else {
            allPossibilities = allPossibilities.parallelStream()
                    .filter(code -> getScoreGuessCode(code, computerGuessCode) == score)
                    .collect(Collectors.toList());
        }

    }

    /**
     * Method used to generate a stream of integer array
     * @return Stream<int[]>  a list of all possible code
     */
    private Stream<int[]> generateAllPossibilities() {
        int nbCombMax = (int) Math.pow((double) nbDigit, (double) codeLength);
        return Stream.iterate(0, i -> i + 1).parallel()
                .map(i -> i.toString())
                .filter(s -> {
                    for (char c : s.toCharArray()){
                        if(Character.getNumericValue(c) < nbDigit){
                            continue;
                        }
                        return false;
                    }
                    return true;
                }).limit(nbCombMax)
                .map(nb -> {
                    int[] comb = new int[codeLength];
                    for (int j = (nb.length() - 1); j >= 0; j--) {
                        comb[j + (codeLength - nb.length())] = Integer.parseInt(String.valueOf(nb.charAt(j)));
                    }
                    return comb;
                });
    }

    /**
     * Method that find all the possible score
     * @return allPossibleScore		a list of all possible score
     */
    private List<Integer> generateAllPossibleScore() {
        List<Integer> allPossibleScore = new ArrayList<>();
        // Number of possible score
        int nbScore = 0;
        // the minimal score length is always 2.
        int nbScoreGp = 2;

        //loop to determine the number of possible score.
        for (int i = 0; i < codeLength; i++) {
            nbScore += i + 2;
            //using the same loop to find all the value of the possible score.
            for (int j = 0; j < nbScoreGp; j++) {
                int scoreInd = 0;
                if (nbScore == 2) {
                    scoreInd = 10 * (((codeLength + 1) - nbScoreGp) + j);
                } else {
                    scoreInd = 10 * ((codeLength + 1) - nbScoreGp) + j;
                }
                allPossibleScore.add(scoreInd);
            }
            nbScoreGp++;
        }
        Collections.sort(allPossibleScore);
        return allPossibleScore;
    }

    /**
     * Method used to calculate the score of the current guess code.
     * @param guessCode
     * @param secretCode
     * @return scoreGuessCode		the score of the guess code.
     */
    private int getScoreGuessCode(int[] guessCode, int[] secretCode) {
        int scoreGuessCode = 0;
        int nbWellPlaced = 0;

        // number of well placed number in the code
        for (int i = 0; i < codeLength; i++) {
            if (guessCode[i] == secretCode[i]) {
                nbWellPlaced++;
            }
        }

        // number of present number in the code
        int nbPresent = - nbWellPlaced;
        for (int i = 0; i < nbDigit; i++){
            int presentSecretCode = 0;
            int presentGuessCode = 0;
            for (int j = 0; j < codeLength; j++){
                if (secretCode[j] == i){
                    presentSecretCode++;
                }
                if (guessCode[j] == i){
                    presentGuessCode++;
                }
            }
            if (presentSecretCode < presentGuessCode){
                nbPresent = presentSecretCode + nbPresent;
            } else {
                nbPresent = presentGuessCode + nbPresent;
            }
        }
        scoreGuessCode = 10 * nbWellPlaced + nbPresent;

        return scoreGuessCode;
    }

    //improve to find a generic formula
    /**
     * Method used to determine the 'weight' of a code.
     * @param array		an code
     * @return weightProp	the 'weight' of the code tested
     */
    private int getPropositionWeight(int[] array) {
        int[] scoreTab = new int[allPossibleScore.size()];
        int weightProp = 0;
        for (int i = 0; i < allPossibilities.size(); i++) {
            int scoreTemp = getScoreGuessCode(allPossibilities.get(i), array);
            for (int j = 0; j < scoreTab.length; j++) {
                if (scoreTemp == allPossibleScore.get(j)) {
                    scoreTab[j]++;
                }
                if (scoreTab[j] > weightProp) {
                    weightProp = scoreTab[j];
                }
            }
        }
        return weightProp;
    }

}
