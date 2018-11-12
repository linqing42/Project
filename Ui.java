package linqing.tojava;

import java.util.Scanner;

/**
 * Ui class that will be responsible for interacting with the user.
 */
public class Ui {
    public Scanner in = new Scanner(System.in);

    public Ui() {
    }

    public String getInput() {
        return in.nextLine();
    }

    public String readUserCommand() {
        System.out.println("Your task?");
        return getInput();
    }

    public void showToUser(String s) {
        System.out.println(s);
    }

    public void printWelcome() {
        System.out.println("Welcome to TaskManager");
    }

    public void showExit() {
        System.out.println("Bye!");
    }

    public void printError() {
        System.out.println("Unknown command! please try again");
    }

    public void printError(String ErrorMsg) {
        System.out.println(ErrorMsg);
    }

    public static void Error() {
        System.out.println("Disappear isDone element for CREATE TASK");
    }
}

