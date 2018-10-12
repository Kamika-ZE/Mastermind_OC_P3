package main.java.fr.mickael.controller;

import main.java.fr.mickael.business.Game;
import main.java.fr.mickael.business.GameFactory;
import main.java.fr.mickael.model.enumeration.State;
import main.java.fr.mickael.views.contracts.EndGame;
import main.java.fr.mickael.views.contracts.GameChoice;
import main.java.fr.mickael.views.contracts.Launcher;
import main.java.fr.mickael.views.contracts.ModeChoice;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private static MainController mainController = new MainController();
    private final Map<State, Runnable> actionByState = new HashMap<>();
    private StringBuffer gameChoiceNumber = new StringBuffer();
    private String endGameChoice;

    /**
     * Private constructor for preventing any other
     * class from instantiating
     */

    private MainController() {
        actionByState.put(State.INIT, this::initState);

        actionByState.put(State.GAME_CHOICE, this::gameChoiceState);

        actionByState.put(State.MODE_CHOICE, this::modeChoiceState);

        actionByState.put(State.IN_GAME, this::inGameState);

        actionByState.put(State.END_GAME, this::endGameState);



    }

    /**
     * static 'instance'
     */
    public static MainController getInstance() {
        return mainController;
    }


    public void run() {
        executeAction(State.INIT);
    }

    private void executeAction(State state) {
        actionByState.get(state).run();
    }

    private void initState(){
        Launcher launcher = new Launcher();
        launcher.display();
        launcher.getInput();
        executeAction(State.GAME_CHOICE);
    }

    private void gameChoiceState(){
        GameChoice gameChoice = new GameChoice();
        gameChoice.display();
        this.gameChoiceNumber = new StringBuffer();
        gameChoiceNumber.append(gameChoice.getInput());
        executeAction(State.MODE_CHOICE);
    }

    private void modeChoiceState(){
        ModeChoice modeChoice = new ModeChoice();
        modeChoice.display();
        gameChoiceNumber.append(modeChoice.getInput());
        executeAction(State.IN_GAME);
    }

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
                System.out.println("Good bye");
                break;
        }
    }

}
