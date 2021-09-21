// package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;

public class LinkedBagTest
{
    public static void main(String[] args)
    {
        System.out.println("Creating an empty bag.");
        BagInterface<String> aBag = new LinkedBag<>();
        testIsEmpty(aBag, true);
        displayBag(aBag);

        String[] contentsOfBag = {"A", "D", "B", "A", "C", "A", "D"};
        testAdd(aBag, contentsOfBag);
        testIsEmpty(aBag, false);

        String[] testString = {"A", "D", "Z"};
        String[] testString2 = {"B", "C", "Z"};
        testFrequency(aBag, testString);
        testFrequency(aBag, testString2);
        testContains(aBag, testString);
        testContains(aBag, testString2);

        testRemove(aBag);

        //Removing specific strings
        String[] removeString = {"", "A", "C"};
        testRemoveSpecific(aBag, removeString);

        //Testing the intersection method
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("Testing intersection bag");
        String[] bag1 = {"A", "B", "C", "B"};
        String[] bag2 = {"B", "B", "D", "E"};
        BagInterface<String> leftBag = new LinkedBag<>();
        BagInterface<String> rightBag = new LinkedBag<>();
        testAdd(leftBag, bag1);
        testAdd(rightBag, bag2);
        testIntersection(leftBag, rightBag);

        //Testing the union method
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("Testing union bag");
        String[] unionBag = {"A", "B", "C", "B"};
        String[] unionBag1 = {"Z", "B", "D", "E"};
        BagInterface<String> leftBag1 = new LinkedBag<>();
        BagInterface<String> rightBag1 = new LinkedBag<>();
        testAdd(leftBag1, unionBag);
        testAdd(rightBag1, unionBag1);
        testUnion(leftBag1, rightBag1);

        //Testing the difference method
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("Testing difference bag");
        String[] differenceBag = {"A", "B", "C", "D", "Z"};
        String[] differenceBag1 = {"B", "B", "D", "E"};
        BagInterface<String> leftBag2 = new LinkedBag<>();
        BagInterface<String> rightBag2 = new LinkedBag<>();
        testAdd(leftBag2, differenceBag);
        testAdd(rightBag2, differenceBag1);
        testDifference(leftBag2, rightBag2);
//        testDifference(rightBag2, leftBag2);

    } // end main

    // Tests the method isEmpty.
    // Precondition: If the bag is empty, the parameter empty should be true;
    // otherwise, it should be false.
    private static void testIsEmpty(BagInterface<String> bag, boolean empty)
    {
        System.out.print("\nTesting isEmpty with ");
        if (empty)
            System.out.println("an empty bag:");
        else
            System.out.println("a bag that is not empty:");

        System.out.print("isEmpty finds the bag ");
        if (empty && bag.isEmpty())
            System.out.println("empty: OK.");
        else if (empty)
            System.out.println("not empty, but it is: ERROR.");
        else if (!empty && bag.isEmpty())
            System.out.println("empty, but it is not empty: ERROR.");
        else
            System.out.println("not empty: OK.");
    } // end testIsEmpty

    // Tests the method add.
    private static void testAdd(BagInterface<String> aBag, String[] content)
    {
        System.out.print("Adding the following strings to the bag: ");
        for (int index = 0; index < content.length; index++)
        {
            if (aBag.add(content[index]))
                System.out.print(content[index] + " ");
            else
                System.out.print("\nUnable to add " + content[index] +
                        " to the bag.");
        } // end for
        System.out.println();

        displayBag(aBag);
    } // end testAdd

    // Tests the method toArray while displaying the bag.
    private static void displayBag(BagInterface<String> aBag)
    {
        System.out.println("\nThe bag contains the following string(s):");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    } // end displayBag

    //For displaying the bag without a new line character
    private static void displayBagModify(BagInterface<String> aBag)
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    } // end displayBag

    //Testing the method of getting the frequency of a certain object type.
    private static void testFrequency(BagInterface<String> aBag, String[] content)
    {
        System.out.println("\nTesting the method getFrequencyof: ");
        for(int i = 0; i< content.length; i++)
        {
            System.out.println("The count of " + content[i] + " : " + aBag.getFrequencyOf(content[i]));
        }
    }

    //Testing the method if it contains a certain entry
    private static void testContains(BagInterface<String> aBag, String[] content)
    {
        System.out.println("\nTesting the method contains: ");
        for(int i = 0; i< content.length; i++)
        {
            System.out.println("Does the bag contain " + content[i] + " ? " + aBag.contains(content[i]));
        }
    }

    //Tests the method of removing an unspecified entry
    private static void testRemove(BagInterface<String> aBag)
    {
        System.out.println("\nTesting the method remove()");
        System.out.println("The number of entries in the bag is: " + aBag.getCurrentSize());
        aBag.remove();
        System.out.println("The new number of entries is: " + aBag.getCurrentSize());
    }

    // Tests the method of removing a specific entry
    private static void testRemoveSpecific(BagInterface<String> aBag, String[] content)
    {
        displayBag(aBag);
        for(int i = 0; i < content.length; i++)
        {
            if(content[i].equals("") || content[i] == null)
            {
                System.out.println("Removing a string from the bag");
                String removedString = aBag.remove();
                System.out.println("remove() returns " + removedString);
                displayBag(aBag);
            }
            System.out.println("The bag contains " + aBag.getCurrentSize() + " strings");
            System.out.println("Removing " + content[i] + " from the bag: ");
            boolean result = aBag.remove(content[i]);
            System.out.println("The bag now contains " + aBag.getCurrentSize() + " strings");
            displayBag(aBag);
        }
    }

    //Testing the intersection method in the BagInterface for a LinkedBag
    private static void testIntersection(BagInterface<String> aBag, BagInterface<String> bBag)
    {

        BagInterface<String> intersectionBag = aBag.intersection(bBag);
        displayBagModify(intersectionBag);
        System.out.println("Displaying the same bags to check if there is any change");
        displayBagModify(aBag);
        displayBagModify(bBag);
    }

    //Testing the union method in the BagInterface for a LinkedBag
    private static void testUnion(BagInterface<String> aBag, BagInterface<String> bBag)
    {

        BagInterface<String> unionBag = aBag.union(bBag);
        displayBagModify(unionBag);
        System.out.println("Displaying the same bags to see if there are any changes made to the original bag");
        displayBagModify(aBag);
        displayBagModify(bBag);
    }

    //Testing the difference method in the BagInterface for a LinkedBag
    private static void testDifference(BagInterface<String> aBag, BagInterface<String> bBag)
    {
        System.out.println("The method: bag1.difference(bag2) is being executed ");
        BagInterface<String> differenceBag = aBag.difference(bBag);
        displayBagModify(differenceBag);

        System.out.println("\nThe method: bag2.difference(bag1) is being executed");
        BagInterface<String> differenceBag1 = bBag.difference(aBag);
        displayBagModify(differenceBag1);

        System.out.println("\nDisplaying the same bags to check if there are any changes made to the original bag");
        displayBagModify(aBag);
        displayBagModify(bBag);
    }
    /**
     * Test add and checking by comparing it to the number of items expected
     */
    @Test
    void add()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        int number = newBag.getCurrentSize();
        assertEquals(3, number);
    }

    /**
     * Test toArray by comparing it to an array with the same content
     */
    @Test
    void toArray()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("C");
        newBag.add("B");
        newBag.add("A");
        Object[] comparison = {"A", "B", "C"};
        Object[] original = newBag.toArray();
        assertArrayEquals(comparison, original);
    }

    /**
     * Testing isEmpty by adding values and comparing it to the expected outcome(false)
     */
    @Test
    void isEmpty()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        boolean test = newBag.isEmpty();
        assertFalse(test);
    }

    /**
     * Testing getCurrentSize by adding 3 values and seeing if it matches with 3
     */
    @Test
    void getCurrentSize()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        int comparison = 3;
        int original = newBag.getCurrentSize();
        assertEquals(comparison, original);
    }

    /**
     * Testing remove by checking the size of the bag before and after
     */
    @Test
    void remove()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        int original = newBag.getCurrentSize();
        newBag.remove();
        int removed = newBag.getCurrentSize();
        boolean test = original > removed;
        assertTrue(test);
    }

    /**
     * Testing remove(anEntry) by seeing if "B" is in the bag after the method is executed
     */
    @Test
    void testRemove()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        newBag.remove("B");
        boolean test = newBag.contains("B");
        assertFalse(test);
    }

    /**
     * Testing clear by checking if the final size is 0
     */
    @Test
    void clear()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        newBag.clear();
        int size = newBag.getCurrentSize();
        assertEquals(0, size);
    }

    /**
     * testing getFrequencyOf by comparing it with the expected value (2)
     */
    @Test
    void getFrequencyOf()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("C");
        newBag.add("C");
        int size = newBag.getFrequencyOf("C");
        assertEquals(2, size);
    }

    /**
     * Testing contains by seeing if it returns true
     */
    @Test
    void contains()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        boolean test = newBag.contains("C");
        assertTrue(test);
    }

    /**
     * Testing intersection by seeing if the new bag contains the intersected items
     */
    @Test
    void intersection()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        BagInterface<String> anotherBag = new LinkedBag<>();
        anotherBag.add("A");
        anotherBag.add("B");
        BagInterface<String> commonItemsBag = newBag.intersection(anotherBag);
        boolean test = commonItemsBag.contains("A") && commonItemsBag.contains("B") && !(commonItemsBag.contains("C"));
        assertTrue(test);
    }

    /**
     * Testing union by seeing if the new bag contains all items from both bags
     */
    @Test
    void union()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        BagInterface<String> anotherBag = new LinkedBag<>();
        anotherBag.add("B");
        BagInterface<String> unionBag = newBag.union(anotherBag);
        boolean test = unionBag.contains("A") && unionBag.contains("B");
        assertTrue(test);
    }

    /**
     * Testing difference by seeing if the new bag contains the difference
     */
    @Test
    void difference()
    {
        BagInterface<String> newBag = new LinkedBag<>();
        newBag.add("A");
        newBag.add("B");
        newBag.add("C");
        BagInterface<String> anotherBag = new LinkedBag<>();
        anotherBag.add("A");
        anotherBag.add("B");
        BagInterface<String> differenceBag = newBag.difference(anotherBag);
        boolean test = differenceBag.contains("C") && !(differenceBag.contains("A")) && !(differenceBag.contains("B"));
        assertTrue(test);
    }
}
 // end LinkedBagDemo1

/*
 Creating an empty bag.

 Testing isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.
 The bag contains the following string(s):

 Adding the following strings to the bag: A D B A C A D
 The bag contains the following string(s):
 D A C A B D A

 Testing isEmpty with a bag that is not empty:
 isEmpty finds the bag not empty: OK.
 */



