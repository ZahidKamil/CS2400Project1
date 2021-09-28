//package com.company;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int initialCapacity = 5;
        BagInterface<String> aBag = new ResizableArrayBag<>(initialCapacity);
        BagInterface<String> bBag = new ResizableArrayBag<>(initialCapacity);
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");
        while (!quit && index < initialCapacity) {
            System.out.print("Choose an option for ResizableArrayBag aBag (0:quit, 1:Enter String): ");
            boolean hasNextInt = scanner.hasNextInt(); //returns true if the scanner's input is an integer
            if(hasNextInt)
            {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        System.out.print("Enter a string: ");
                        String stringInput = scanner.nextLine();
                        aBag.add(stringInput);
                        index++;
                        break;
                }
            }
            else {
                System.out.println("Unable to parse choice: Please enter an integer of choice");
                throw new IllegalArgumentException("Please enter an integer of choice");
            }

        }
        System.out.println(aBag);

        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");
        quit = false;
        index = 0;
        while (!quit && index < initialCapacity) {
            System.out.print("\nChoose an option for ResizableArrayBag bBag (0:quit, 1:Enter String): ");
            boolean hasNextInt = scanner.hasNextInt(); //returns true if the scanner's input is an integer
            if(hasNextInt)
            {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        System.out.print("Enter a string: ");
                        String stringInput = scanner.nextLine();
                        bBag.add(stringInput);
                        index++;
                        break;
                }
            }
            else {
                System.out.println("Unable to parse choice: Please enter an integer of choice");
                throw new IllegalArgumentException("Please enter an integer of choice");
            }

        }


        System.out.println(bBag);
        System.out.println("\nThe intersection of both bags are: " + aBag.intersection(bBag));
        System.out.println("\nThe union of both bags are: " + aBag.union(bBag));
        System.out.println("\nThe difference aBag.difference(bBag): " + aBag.difference(bBag));

        BagInterface<String> cBag = new LinkedBag<>();
        BagInterface<String> dBag = new LinkedBag<>();
        quit = false;
        index = 0;
        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");
        while (!quit) {
            System.out.print("Choose an option for LinkedBag cBag (0:quit, 1:Enter String): ");
            boolean hasNextInt = scanner.hasNextInt(); //returns true if the scanner's input is an integer
            if(hasNextInt)
            {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        System.out.print("Enter a string: ");
                        String stringInput = scanner.nextLine();
                        cBag.add(stringInput);
                        index++;
                        break;
                }
            }
            else {
                System.out.println("Unable to parse choice: Please enter an integer of choice");
                throw new IllegalArgumentException("Please enter an integer of choice");
            }

        }
        displayBag(cBag);

        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");
        quit = false;
        while (!quit) {
            System.out.print("\nChoose an option for LinkedBag dBag (0:quit, 1:Enter String): ");
            boolean hasNextInt = scanner.hasNextInt(); //returns true if the scanner's input is an integer
            if (hasNextInt) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        System.out.print("Enter a string: ");
                        String stringInput = scanner.nextLine();
                        dBag.add(stringInput);
                        index++;
                        break;
                }
            } else {
                System.out.println("Unable to parse choice: Please enter an integer of choice");
                throw new IllegalArgumentException("Please enter an integer of choice");
            }
        }
        displayBag(dBag);
        System.out.println("\nThe intersection of both bags are: ");
        displayBag(cBag.intersection(dBag));
        System.out.println("\nThe union of both bags are: ");
        displayBag(cBag.union(dBag));
        System.out.println("\nThe difference aBag.difference(bBag): ");
        displayBag(cBag.difference(dBag));


    }

    private static void displayBag(BagInterface<String> aBag)
    {
//        System.out.println("\nThe bag contains the following string(s):");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    } // end displayBag

}
