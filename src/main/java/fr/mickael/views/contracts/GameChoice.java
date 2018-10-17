package main.java.fr.mickael.views.contracts;



import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class GameChoice implements View {
    @Override
    public void display() {
        System.out.println("CHOOSE YOUR GAME :\n\n"
                + "\t1/\tMORE OR LESS\n"
                + "\t2/\tMASTERMIND\n\n"
                + "Your answer : \r");

    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String firstChoice = "";
        boolean isValid = false;
        while (isValid != true) {
            try {
                char reponse = scanner.nextLine().charAt(0);
                switch (reponse) {
                    case '1':
                        firstChoice = "1";
                        isValid = true;
                        break;
                    case '2':
                        firstChoice = "2";
                        isValid = true;
                        break;
                }

            } catch (Exception e) {	}
            if(!isValid) {
                System.out.println("Please, choose between 1/ or 2/\n");
                display();
            }
        }
        //scanner.close();
        return firstChoice;
    }
}
