package main.java.fr.mickael.model;

import main.java.fr.mickael.util.AllPossibleCode;
import main.java.fr.mickael.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.java.fr.mickael.util.Config.codeIndex;
import static main.java.fr.mickael.util.Config.codeLength;

public class ComputerMastermind extends Computer{

    private int[] computerGuessCode;
    private List<int[]> allPossibilities;
    private List<Integer> allPossibleScore;


    public ComputerMastermind() {
        super.computerSecretCode = new int[codeLength];
        this.computerGuessCode = new int[codeLength];
        this.allPossibilities = generateAllPossibilities();
        this.allPossibleScore = generateAllPossibleScore();
    }

    public List<int[]> getAllPossibilities() {
        return allPossibilities;
    }

    @Override
    public int[] guessTheCode() {
        // tous les poids
        List<Integer> allWeight = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int weight = getPropositionWeight(allPossibilities.get(i));
            allWeight.add(weight);
        }
        // poids mini
        int weightMin = 10000;
        for (int i = 0; i < allWeight.size(); i++) {
            int weight = allWeight.get(i);
            if( weight < weightMin) {
                weightMin = weight;
            }
        }
        System.out.println(weightMin);
        // premier code avec poids mini
        List<int[]> allTabMinWeight = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int weightProp = getPropositionWeight(allPossibilities.get(i));
            if (weightProp == weightMin) {
                allTabMinWeight.add(allPossibilities.get(i));
            }
        }
        computerGuessCode = allTabMinWeight.get(0);
        return computerGuessCode;
    }

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
        List<int[]> tempPossibilities = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int scoreTemp = getScoreGuessCode(allPossibilities.get(i), computerGuessCode);
            if (scoreTemp == score) {
                tempPossibilities.add(allPossibilities.get(i));
            }
        }
        allPossibilities = tempPossibilities;
    }

    private List<int[]> generateAllPossibilities() {

        List<int[]> allPossibilities = new ArrayList<>();
        AllPossibleCode allPossibleCode = new AllPossibleCode();
        allPossibleCode.generateAllCode(codeIndex);
        allPossibilities = allPossibleCode.getAllCode();
        return allPossibilities;
    }

    private List<Integer> generateAllPossibleScore() {
        List<Integer> allPossibleScore = new ArrayList<>();
        // nombre de scores possibles
        int nbScore = 0;
        // taille du groupe de score le plus petit (toujours 2). cette valeur varie jusqu'à codeLength + 1
        int nbScoreGp = 2;

        //boucle pour déterminer le nombre de scores possibles
        for (int i = 0; i < codeLength; i++) {
            nbScore += i + 2;
            //on utilise la même boucle pour trouver toutes les valeurs des scores.
            //On pourrait séparer le calcul du nombre de scores pour utiliser la valeur.
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

    private int getScoreGuessCode(int[] guessCode, int[] secretCode) {
        int scoreGuessCode = 0;
        int nbWellPlaced = 0;

        // calcul des bien placés
        for (int i = 0; i < codeLength; i++){
            if (guessCode[i] == secretCode[i]){
                nbWellPlaced++;
            }
        }

        // calcul des présents

        int nbPresent = Util.getNbPresent(guessCode, secretCode, nbWellPlaced);
        scoreGuessCode = 10 * nbWellPlaced + nbPresent;

        return scoreGuessCode;
    }



    private int getPropositionWeight (int[] array) {
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
