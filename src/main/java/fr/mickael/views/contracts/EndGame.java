package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EndGame implements View {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void display() {
        System.out.println("WHAT DO YOU WANT TO DO :\n\n"
                + "\t1/\tPLAY THE SAME GAME/MODE/DIFFICULTY\n"
                + "\t2/\tCHANGE THE GAME\n"
                + "\t3/\tCHANGE THE MODE\n"
                + "\t4/\tCHANGE DIFFICULTY\n"
                + "\t5/\tQUIT THE PROGRAM\n\n"
                + "Your answer : \r");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String fourthChoice = "";
        boolean isValid = false;
        while (isValid != true) {
            try {
                String response = scanner.nextLine();
                switch (response) {
                    case "1":
                        fourthChoice = "1";
                        isValid = true;
                        break;
                    case "2":
                        fourthChoice = "2";
                        isValid = true;
                        break;
                    case "3":
                        fourthChoice = "3";
                        isValid = true;
                        break;
                    case "4":
                        fourthChoice = "4";
                        isValid = true;
                        break;
                    case "5":
                        fourthChoice = "5";
                        isValid = true;
                        break;
                }
            } catch (Exception e) {
                logger.warn("A exception occurred in the method getInput() of the class"
                        + getClass().getSimpleName()
                        + e.getMessage());
            }
            if(!isValid) {
                System.out.println("Please, choose between 1/, 2/, 3/, 4/ or 5/\n");
                display();
            }
        }
        return fourthChoice;
    }
}
