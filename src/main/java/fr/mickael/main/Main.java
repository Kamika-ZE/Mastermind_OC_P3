package main.java.fr.mickael.main;

import main.java.fr.mickael.business.Game;
import main.java.fr.mickael.business.GameFactory;

public class Main {

    public static void main(String[] args) {
        Game game = GameFactory.createMastermindComputerAttackerVsComputerDefender();
        game.play();

    }
}
