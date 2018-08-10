package fr.mickael.main;

import fr.mickael.business.Game;
import fr.mickael.business.GameFactory;

public class Main {

    public static void main(String[] args) {
        Game game = GameFactory.createMastermindHumanAttackerVsComputerDefender();
        game.play();
    }
}
