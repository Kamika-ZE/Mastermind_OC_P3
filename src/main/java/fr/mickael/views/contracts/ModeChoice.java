package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;

import java.util.Collections;
import java.util.Scanner;

public class ModeChoice implements View {
    @Override
    public void display() {
        System.out.println("\n" + String.join("*", Collections.nCopies(40, "*"))
                + "\n\nCHOOSE YOUR MODE :\n\n"
                + "\t1/\tCHALLENGER\n"
                + "\t2/\tDEFENDER\n"
                + "\t3/\tDUAL\n\n"
                + "Your answer : \r\n");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String secondChoice = "";
        boolean isValid = false;
        while (isValid != true) {
            try {
                char reponse = scanner.nextLine().charAt(0);
                switch (reponse) {
                    case '1':
                        secondChoice = "1";
                        isValid = true;
                        break;
                    case '2':
                        secondChoice = "2";
                        isValid = true;
                        break;
                    case '3':
                        secondChoice = "3";
                        isValid = true;
                        break;
                }
            } catch (Exception e) {
                e.getMessage();
            }
            if(!isValid) {
                System.out.println("Please, choose between 1/, 2/ or 3/\n");
                display();
            }
        }
        //scanner.close();
        return secondChoice;
    }
}

