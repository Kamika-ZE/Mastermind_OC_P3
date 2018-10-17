package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class EndGame implements View {
    @Override
    public void display() {
        System.out.println("WHAT DO YOU WANT TO DO :\n\n"
                + "\t1/\tPLAY THE SAME GAME/MODE\n"
                + "\t2/\tCHANGE THE GAME\n"
                + "\t3/\tCHANGE THE MODE\n"
                + "\t4/\tQUIT THE PROGRAM\n\n"
                + "Your answer : \r");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String fourthChoice = "";
        boolean isValid = false;
        while (isValid != true) {
            try {
                char reponse = scanner.nextLine().charAt(0);
                switch (reponse) {
                    case '1':
                        fourthChoice = "1";
                        isValid = true;
                        break;
                    case '2':
                        fourthChoice = "2";
                        isValid = true;
                        break;
                    case '3':
                        fourthChoice = "3";
                        isValid = true;
                        break;
                    case '4':
                        fourthChoice = "4";
                        isValid = true;
                        break;
                }
            } catch (Exception e) {
                e.getMessage();
            }
            if(!isValid) {
                System.out.println("Please, choose between 1/, 2/, 3/ or 4/\n");
                display();
            }
        }
        return fourthChoice;
    }
}
