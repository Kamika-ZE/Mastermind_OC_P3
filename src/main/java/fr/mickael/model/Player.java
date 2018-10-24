package main.java.fr.mickael.model;

public interface Player {

    /**
     * Method that generate a secret code.
     * @return an array of integer
     */
    int[] generateSecretCode ();

    /**
     * Method that generate a guess code.
     * @return an array of integer
     */
    int[] guessTheCode ();

    /**
     * Method that handles the logic for the computer
     * or print the clues for human player.
     */
    void getClues (char[] answer);

    /**
     * Method that tell the player if he win
     * or not.
     */
    void sendScore(boolean win);
}
