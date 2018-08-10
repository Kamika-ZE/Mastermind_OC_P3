package fr.mickael.model;

public interface Player {

    // generate an array of integer
    int[] generateSecretCode ();

    //guess the secret code
    int[] guessTheCode ();

    // display a char array for the clues
    void getClues (char[] answer);

    // send the score to the human player
    void sendScore(boolean win);
}
