package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class EndGame implements View {
    @Override
    public void display() {
        System.out.println("WHAT DO YOU WANT TO DO :\n\n"
                + "\t1/\tPLAY THE SAME GAME/MODE\n"
                + "\t2/\tCHANGE THE GAME\n"
                + "\t3/\tQUIT THE PROGRAM\n\n");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String fourthChoice = "";
        char reponse;
        System.out.println("Your answer : \r");
        reponse = scanner.nextLine().charAt(0);
        switch (reponse) {
            case '1':
                fourthChoice = "1";
                break;
            case '2':
                fourthChoice = "2";
                break;
            case '3':
                fourthChoice = "3";
                break;
        }
        return fourthChoice;
    }
}
