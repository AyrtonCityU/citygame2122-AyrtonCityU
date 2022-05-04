package game;

import java.util.Scanner;

public class NameInput {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter name");
        // String input
        String name = myObj.nextLine();
        // Output input by user
        System.out.println("Name: " + name);
        if (name == "Hello"){
            System.out.println("wraasdasdas");
        }
    }
}