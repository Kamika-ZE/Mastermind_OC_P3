package main.java.fr.mickael.business;

import main.java.fr.mickael.model.ComputerMastermind;
import main.java.fr.mickael.model.ComputerMoreOrLess;
import main.java.fr.mickael.model.Human;

public class GameFactory {

    public static Game createModeDualMastermindHumanVsComputer(){
        Game game = new MastermindDual();
        game.setPlayers(new Human(), new ComputerMastermind());
        return game;
    }
    public static Game createModeDualMastermindHumanVsHuman(){
        Game game = new MastermindDual();
        game.setPlayers(new Human(), new Human());
        return game;
    }


    public static Game createMoreOrLessHumanAttackerVsComputerDefender(){
        Game game = new MoreOrLess();
        game.setPlayers(new Human(), new ComputerMoreOrLess());
        return game;
    }

    public static Game createMoreOrLessComputerAttackerVsHumanDefender(){
        Game game = new MoreOrLess();
        game.setPlayers(new ComputerMoreOrLess(), new Human());
        return game;
    }

    public static Game createMoreOrLessHumanAttackerVsHumanDefender(){
        Game game = new MoreOrLess();
        game.setPlayers(new Human(), new Human());
        return game;
    }

    public static Game createMoreOrLessComputerAttackerVsComputerDefender(){
        Game game = new MoreOrLess();
        game.setPlayers(new ComputerMoreOrLess(), new ComputerMoreOrLess());
        return game;
    }

    public static Game createMastermindHumanAttackerVsComputerDefender(){
        Game game = new Mastermind();
        game.setPlayers(new Human(), new ComputerMastermind());
        return game;
    }

    public static Game createMastermindComputerAttackerVsHumanDefender(){
        Game game = new Mastermind();
        game.setPlayers(new ComputerMastermind(), new Human());
        return game;
    }

    public static Game createMastermindHumanAttackerVsHumanDefender(){
        Game game = new Mastermind();
        game.setPlayers(new Human(), new Human());
        return game;
    }

    public static Game createMastermindComputerAttackerVsComputerDefender(){
        Game game = new Mastermind();
        game.setPlayers(new ComputerMastermind(), new ComputerMastermind());
        return game;
    }
}
