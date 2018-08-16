package main.java.fr.mickael.business;

import main.java.fr.mickael.model.Player;

public abstract class Game {

    protected Player attacker;
    protected Player defender;

    public abstract void play();
    public abstract String compareCode(int[] guessCode, int[] secretCode);

    public void setPlayers (Player attacker, Player defender){
        this.attacker = attacker;
        this.defender = defender;
    }
}
