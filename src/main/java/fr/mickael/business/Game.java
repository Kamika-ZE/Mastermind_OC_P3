package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;

/**
 * Abstract Class game.
 * Implementation in the derived class.
 * @author M. COZ
 *
 */

public abstract class Game {

    protected Player attacker;
    protected Player defender;

    /**
     * Abstract method play.
     * Method that launch the board of the current game.
     * Implementation in the derived class.
     */
    public abstract void play();

    /*
     * Abstract method to compare the secret code and the guess code
     * @param guessCode		the code of the attacker
     * @param secretCode	the code of the defender
     * @return String 		the result of the comparison
     */
    public abstract String compareCode(int[] guessCode, int[] secretCode);

    /**
     * Method to set the player for each game.
     * A player can be a computer or a human.
     * @param attacker
     * @param defender
     */
    public void setPlayers (Player attacker, Player defender){
        this.attacker = attacker;
        this.defender = defender;
    }
}
