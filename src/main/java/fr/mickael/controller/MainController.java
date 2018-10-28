package main.java.fr.mickael.controller;

import main.java.fr.mickael.business.Game;
import main.java.fr.mickael.business.GameFactory;
import main.java.fr.mickael.model.enumeration.State;
import main.java.fr.mickael.util.Config;
import main.java.fr.mickael.views.contracts.DifficultyChoice;
import main.java.fr.mickael.views.contracts.EndGame;
import main.java.fr.mickael.views.contracts.GameChoice;
import main.java.fr.mickael.views.contracts.Launcher;
import main.java.fr.mickael.views.contracts.ModeChoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private static Logger logger = LogManager.getLogger();
    private static MainController mainController = new MainController();
    private final Map<State, Runnable> actionByState = new HashMap<>();
    private StringBuffer gameChoiceNumber = new StringBuffer();
    private String endGameChoice;
    private String difficultyChoiceNumber;

    /**
     * Private constructor for preventing any other
     * class from instantiating
     */
    private MainController() {

        actionByState.put(State.INIT, this::initState);

        actionByState.put(State.GAME_CHOICE, this::gameChoiceState);

        actionByState.put(State.MODE_CHOICE, this::modeChoiceState);

        actionByState.put(State.DIFFICULTY_CHOICE, this::difficultyChoiceState);

        actionByState.put(State.IN_GAME, this::inGameState);

        actionByState.put(State.END_GAME, this::endGameState);
    }

    /**
     * Static 'instance'
     * @return mainController an 'instance' of the main controller
     */
    public static MainController getInstance() {
        logger.debug("Main controller constructor 'initialisation'.");
        return mainController;
    }

    /**
     * Method that launch the first state of the program.
     */
    public void run() {
        executeAction(State.INIT);
    }

    /*
     * Private method that execute an action.
     * @param state		a state of the game.
     */
    private void executeAction(State state) {
        actionByState.get(state).run();
    }

    /**
     * Private method that execute the first view
     * And get the player input for executing the next action.
     */
    private void initState(){
        Launcher launcher = new Launcher();
        launcher.display();
        launcher.getInput();
        executeAction(State.GAME_CHOICE);
    }

    /**
     * Private method that execute the GAME CHOICE's view
     * And get the player input for executing the next action.
     */
    private void gameChoiceState(){
        GameChoice gameChoice = new GameChoice();
        gameChoice.display();
        this.gameChoiceNumber = new StringBuffer();
        gameChoiceNumber.append(gameChoice.getInput());
        executeAction(State.MODE_CHOICE);
    }

    /**
     * Private method that execute the MODE CHOICE's view
     * And get the player input for executing the next action.
     */
    private void modeChoiceState(){
        ModeChoice modeChoice = new ModeChoice();
        modeChoice.display();
        gameChoiceNumber.append(modeChoice.getInput());
        executeAction(State.DIFFICULTY_CHOICE);
    }

    /**
     * Private method that execute the DIFFICULTY CHOICE's view
     * And get the player input for executing the next action.
     */
    private void difficultyChoiceState() {
        DifficultyChoice difficultyChoice = new DifficultyChoice();
        difficultyChoice.display();
        difficultyChoiceNumber = difficultyChoice.getInput();
        switch (difficultyChoiceNumber) {
            case "1":
                Config.setCodeLength(4);
                Config.setMaxRound(8);
                Config.setNbDigit(6);
                break;

            case "2":
                Config.getDefaultCodeLength();
                Config.getDefaultMaxRound();
                Config.getDefaultNbDigit();
                break;

            case "3":
                Config.setCodeLength(6);
                Config.setMaxRound(7);
                Config.setNbDigit(8);
                break;
        }
        executeAction(State.IN_GAME);
    }

    /**
     * Private method that execute the IN GAME's view
     * And get the player input for executing the next action.
     */
    private void inGameState(){
        Game game;
        switch (gameChoiceNumber.toString()){
            case "11":
                game = GameFactory.createMoreOrLessHumanAttackerVsComputerDefender();
                game.play();
                break;

            case "12":
                game = GameFactory.createMoreOrLessComputerAttackerVsHumanDefender();
                game.play();
                break;

            case "13":
                game = GameFactory.createModeDualMoreOrLessHumanVsComputer();
                game.play();
                break;

            case "21":
                game = GameFactory.createMastermindHumanAttackerVsComputerDefender();
                game.play();
                break;

            case "22":
                game = GameFactory.createMastermindComputerAttackerVsHumanDefender();
                game.play();
                break;

            case "23":
                game = GameFactory.createModeDualMastermindHumanVsComputer();
                game.play();
                break;
        }
        executeAction(State.END_GAME);
    }

    /**
     * Private method that execute the END GAME's view
     * And get the player input for executing the next action.
     */
    private void endGameState(){
        EndGame endGame = new EndGame();
        endGame.display();
        this.endGameChoice = endGame.getInput();
        switch (endGameChoice){
            case "1":
                executeAction(State.IN_GAME);
                break;

            case "2":
                executeAction(State.GAME_CHOICE);
                break;

            case "3":
                gameChoiceNumber.delete(1, 2);
                executeAction(State.MODE_CHOICE);
                break;

            case "4":
                executeAction(State.DIFFICULTY_CHOICE);
                break;

            case "5":
                System.out.println("Good bye\n\n"
                    + "This window will close automatically.");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    logger.warn("Exception : " + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
    }
}
