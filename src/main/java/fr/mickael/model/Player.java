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
     * @param answer the clues given by the defender
     */
    void getClues (char[] answer);

    /**
     * Method that tell the player if he win
     * or not.
     * @param win a boolean which can be true if the game is won.
     */
    void sendScore(boolean win);
}
