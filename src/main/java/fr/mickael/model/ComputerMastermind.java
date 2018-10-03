package main.java.fr.mickael.model;

import javafx.util.Pair;
import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.util.Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputerMastermind extends Computer {

    private int[] computerGuessCode;
    private List<int[]> allPossibilities;
    private List<Integer> allPossibleScore;
    private int codeLength = Config.getCodeLength();
    private int codeIndex = Config.getCodeIndex();


    public ComputerMastermind() {
        super.computerSecretCode = new int[codeLength];
        this.computerGuessCode = new int[codeLength];
        this.allPossibilities = new ArrayList<>();
        this.allPossibleScore = generateAllPossibleScore();
    }

    @Override
    public int[] guessTheCode() {
        // tous les poids
/*        List<Integer> allWeight = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int weight = getPropositionWeight(allPossibilities.get(i));
            allWeight.add(weight);
        }*/
        if (allPossibilities.isEmpty()) {
            int[] codeTemp = new int[codeLength];
            for (int i = 0; i < codeLength; i++) {
                codeTemp[i] = i % nbDigit;
            }
            computerGuessCode = codeTemp;

/*            Optional<Pair<int[], Integer>> min = generateAllPossibilities()
                    .map(code -> new Pair<>(code, getPropositionWeight(code)))
                    .sorted(Comparator.comparing(Pair::getValue))
                    .min(Comparator.comparing(Pair::getValue));
            computerGuessCode = min.get().getKey().clone();
            System.out.println(Arrays.toString(computerGuessCode));
            System.out.println(min.get().getValue());*/
        } else {
            Optional<Pair<int[], Integer>> min = allPossibilities.parallelStream()
                    .map(code -> new Pair<>(code, getPropositionWeight(code)))
                    .sorted(Comparator.comparing(Pair::getValue))
                    .min(Comparator.comparing(Pair::getValue));
            computerGuessCode = min.get().getKey();
        }

        // poids mini
        //int weightMin = Collections.min(allWeight);
/*        int weightMin = Integer.MAX_VALUE;
        for (int i = 0; i < allWeight.size(); i++) {
            int weight = allWeight.get(i);
            if( weight < weightMin) {
                weightMin = weight;
            }
        }*/
        //System.out.println(weightMin);
        // premier code avec poids mini
/*        List<int[]> allTabMinWeight = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int weightProp = getPropositionWeight(allPossibilities.get(i));
            if (weightProp == weightMin) {
                allTabMinWeight.add(allPossibilities.get(i));
            }
        }
        computerGuessCode = allTabMinWeight.get(0);*/
        //computerGuessCode = allPossibilities.get(allWeight.indexOf(weightMin));
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
/*        List<int[]> tempPossibilities = new ArrayList<>();
        for (int i = 0; i < allPossibilities.size(); i++) {
            int scoreTemp = getScoreGuessCode(allPossibilities.get(i), computerGuessCode);
            if (scoreTemp == score) {
                tempPossibilities.add(allPossibilities.get(i));
            }
        }*/
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
                })
                ;

/*        List<int[]> allPossibilities = new ArrayList<>();
        AllPossibleCode allPossibleCode = new AllPossibleCode();
        allPossibleCode.generateAllCode(codeIndex);
        allPossibilities = allPossibleCode.getAllCode();*/
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
        for (int i = 0; i < codeLength; i++) {
            if (guessCode[i] == secretCode[i]) {
                nbWellPlaced++;
            }
        }

        // calcul des présents

        int nbPresent = Util.getNbPresent(guessCode, secretCode, nbWellPlaced);
        scoreGuessCode = 10 * nbWellPlaced + nbPresent;

        return scoreGuessCode;
    }

    //a modifier
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
