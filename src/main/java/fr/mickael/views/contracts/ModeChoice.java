package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class ModeChoice implements View {
    @Override
    public void display() {
        System.out.println("CHOOSE YOUR MODE :\n\n"
                + "\t1/\tCHALLENGER\n"
                + "\t2/\tDEFENDER\n"
                + "\t3/\tDUAL\n\n");

    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String secondChoice = "";
        char reponse;
        System.out.println("Your answer : \r");
        reponse = scanner.nextLine().charAt(0);
        switch (reponse) {
            case '1':
                secondChoice = "1";
                break;
            case '2':
                secondChoice = "2";
                break;
            case '3':
                secondChoice = "3";
                break;
        }
        return secondChoice;
    }
}
