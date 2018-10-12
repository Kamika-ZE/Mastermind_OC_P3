package main.java.fr.mickael.views.contracts;



import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class GameChoice implements View {
    @Override
    public void display() {
        System.out.println("CHOOSE YOUR GAME :\n\n"
                + "\t1/\tMORE OR LESS\n"
                + "\t2/\tMASTERMIND\n\n");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String firstChoice = "";
        char reponse;
        System.out.println("Your answer : \r");
        reponse = scanner.nextLine().charAt(0);
        switch (reponse) {
            case '1':
                firstChoice = "1";
                break;
            case '2':
                firstChoice = "2";
                break;
        }
        return firstChoice;
    }
}
