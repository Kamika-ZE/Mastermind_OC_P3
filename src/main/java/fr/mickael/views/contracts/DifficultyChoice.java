package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Scanner;

public class DifficultyChoice implements View {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void display() {
        logger.debug("run method display ()");
        System.out.println("\n" + String.join("*", Collections.nCopies(40, "*"))
                + "\n\nCHOOSE YOUR DIFFICULTY :\n\n"
                + "\t1/\tEASY\n"
                + "\t2/\tNORMAL\n"
                + "\t3/\tHARDCORE\n\n"
                + "Your answer : \r\n");

    }

    @Override
    public String getInput() {
        logger.debug("run method getInput()");
        Scanner scanner = new Scanner(System.in);
        String difficultyChoice = "";
        boolean isValid = false;
        while (isValid != true) {
            try {
                String response = scanner.nextLine();
                switch (response) {
                    case "1":
                        difficultyChoice = "1";
                        isValid = true;
                        break;
                    case "2":
                        difficultyChoice = "2";
                        isValid = true;
                        break;
                    case "3":
                        difficultyChoice = "3";
                        isValid = true;
                        break;
                }
            } catch (Exception e) {
                logger.warn("A exception occurred in the method getInput() of the class"
                        + getClass().getSimpleName()
                        + e.getMessage());
            }
            if(!isValid) {
                System.out.println("Please, choose between 1/, 2/ or 3/\n");
                display();
            }
        }
        return difficultyChoice;
    }

}
