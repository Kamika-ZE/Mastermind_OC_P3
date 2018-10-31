package main.java.fr.mickael.business;

import main.java.fr.mickael.model.ComputerMastermind;
import main.java.fr.mickael.model.ComputerMoreOrLess;
import main.java.fr.mickael.model.Human;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class using an implementation of factory pattern
 * this class contain all the method to launch each type of game
 * @author M. COZ
 */

public class GameFactory {
    private static Logger logger = LogManager.getLogger();

    //--------------------- MORE OR LESS ---------------------
    /**
     * method returning an instance of more or less game mode
     * mode number : 11
     * @return game an instance of the more or less Attacker mode
     */
    public static Game createMoreOrLessHumanAttackerVsComputerDefender(){
        Game game = new MoreOrLess();
        logger.info("Create an instance of More or Less with a human attacker and computer defender");
        game.setPlayers(new Human(), new ComputerMoreOrLess());
        return game;
    }

    /**
     * method returning an instance of more or less game mode
     * mode number 12
     * @return game an instance of the more or less Defender mode
     */
    public static Game createMoreOrLessComputerAttackerVsHumanDefender(){
        Game game = new MoreOrLess();
        logger.info("Create an instance of More or Less with a computer attacker and human defender.");
        game.setPlayers(new ComputerMoreOrLess(), new Human());
        return game;
    }

    /**
     * method returning an instance of more or less game mode
     * mode number : 13
     * each player enter a secret code.
     * each player try to find the opposite player's secret code
     * @return game an instance of the more or less Dual mode Human versus Computer
     */
    public static Game createModeDualMoreOrLessHumanVsComputer(){
        Game game = new MoreOrLessDual();
        logger.info("Create an instance of More or Less dual mode.");
        game.setPlayers(new Human(), new ComputerMoreOrLess());
        return game;
    }

    /**
     * method returning an instance of more or less game mode
     * @return game an instance of the more or less Attacker mode Computer versus Computer
     */
    public static Game createMoreOrLessComputerAttackerVsComputerDefender(){
        Game game = new MoreOrLess();
        game.setPlayers(new ComputerMoreOrLess(), new ComputerMoreOrLess());
        return game;
    }

    //--------------------- MASTERMIND ---------------------

    /**
     * method returning an instance of mastermind game mode
     * mode number : 21
     * @return game an instance of the mastermind Attacker mode
     */
    public static Game createMastermindHumanAttackerVsComputerDefender(){
        Game game = new Mastermind();
        logger.info("Create an instance of Mastermind with a human attacker and computer defender");
        game.setPlayers(new Human(), new ComputerMastermind());
        return game;
    }

    /**
     * method returning an instance of mastermind game mode
     * mode number : 22
     * @return game an instance of the mastermind Defender mode
     */
    public static Game createMastermindComputerAttackerVsHumanDefender(){
        Game game = new Mastermind();
        logger.info("Create an instance of Mastermind with a computer attacker and human defender");
        game.setPlayers(new ComputerMastermind(), new Human());
        return game;
    }

    /**
     * method returning an instance of mastermind game mode
     * mode number : 23
     * each player enter a secret code.
     * each player try to find the opposite player's secret code
     * @return game an instance of the mastermind Dual mode Human versus Computer
     */
    public static Game createModeDualMastermindHumanVsComputer(){
        Game game = new MastermindDual();
        logger.info("Create an instance of Mastermind dual mode.");
        game.setPlayers(new Human(), new ComputerMastermind());
        return game;
    }

    /**
     * method returning an instance of mastermind game mode
     * @return game an instance of the mastermind Attacker mode Computer versus Computer
     */
    public static Game createMastermindComputerAttackerVsComputerDefender(){
        Game game = new Mastermind();
        game.setPlayers(new ComputerMastermind(), new ComputerMastermind());
        return game;
    }
}
