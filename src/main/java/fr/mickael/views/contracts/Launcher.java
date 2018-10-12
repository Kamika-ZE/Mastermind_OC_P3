package main.java.fr.mickael.views.contracts;

import main.java.fr.mickael.util.Util;
import main.java.fr.mickael.views.View;

import java.util.Scanner;

public class Launcher implements View {
    @Override
    public void display() {
        System.out.println("WELCOME TO");
        System.out.println("\t________  ________   ________  ____ ___   ____");
        System.out.println("\t| ____ \\\\ | ____ \\\\  | ____ || | || | \\\\  | ||");
        System.out.println("\t| || | || | ||  \\ \\\\ | || | || | || |  \\\\ | ||");
        System.out.println("\t| ||_/ // | ||__/ // | ||_| || | || | |\\\\\\| ||");
        System.out.println("\t|  __  \\\\ |  __  //  |  __  || | || | ||\\\\| ||");
        System.out.println("\t| || \\  \\\\| || \\ \\\\  | || | || | || | || \\| ||");
        System.out.println("\t| ||_/  ||| ||  \\ \\\\ | || | || | || | ||  | ||");
        System.out.println("\t|______// |_||   \\_\\\\|_|| | || |_|| |_||  |_||");
        System.out.println();
        System.out.println("\t___    __    ___ ________ _______ _______ ___  ___ _______  ________");
        System.out.println("\t\\ \\\\  / \\\\  / // | ___  |||     ||| ____||| ||/ // |     || | ___  ||");
        System.out.println("\t \\ \\\\/   \\\\/ //  | || |_||| |[]_||| ||    | |/ //  | |[]_|| | || |_||");
        System.out.println("\t  \\   //\\   //   | ||     | ||___ | ||___ | |\\ \\\\  | ||___  | ||");
        System.out.println("\t   \\_//  \\_//    |_||     |_____|||_____|||_||\\_\\\\ |_____|| |_||");
        System.out.println("\n\n");
        System.out.println("#############################################################################");
        System.out.printf("%40s", "RULES\n");
        System.out.println("#############################################################################");

        System.out.println();
        System.out.println("Put your brain on the table. You've got the choice between two game :");
        System.out.println("More or less and Mastermind");
        System.out.println("Three modes are available for each game : CHALLENGER, DEFENDER or DUAL\n\n");

        System.out.printf(Util.SEPARATOR, "More or Less rules");
        System.out.println("Clues rules : If the clue is '+', you have to put a superior value, if it's '-', an inferior value.");
        System.out.println("If the clue is '=', it's the good number.\n");
        System.out.println("Mode CHALLENGER : You are the attacker. Find the secret code with the defender clues");
        System.out.println("Mode DEFENDER : put your code to the test. The computer will defeat you !");
        System.out.println("Mode DUAL : find the code before the computer find yours. The race is on !\n\n");

        System.out.printf(Util.SEPARATOR, "Mastermind rules");
        System.out.println("Clues rules : it's simple, you have well placed number and present number.");
        System.out.println("- well placed : one (or more) number is in the good place in the code,\n\tbut you don't know which one.");
        System.out.println("- present : one (or more) number is present in the code but not in the good position,\n\tand you don't know which one.\n");
        System.out.println("Mode CHALLENGER : You are the attacker. Find the secret code with the defender clues");
        System.out.println("Mode DEFENDER : put your code to the test. The computer will defeat you !");
        System.out.println("Mode DUAL : find the code before the computer find yours. The race is on !\n");
    }

    @Override
    public String getInput() {
        System.out.println("PRESS ENTER TO CONINUE");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //méthode de lancement. Elle demande quoi afficher.
}
