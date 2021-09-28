import static org.junit.jupiter.api.Assertions.*;
//Test the output of the method against the assertion that we made about the expected output.
class ResizableArrayBagUnitTest {
    /**
     * Testing the add bag to ensure that the bag is adding the entries we want
     */
    @org.junit.jupiter.api.Test
    void add() {
        BagInterface<String> addBag = new ResizableArrayBag<>();
        String[] contents = {"A","B", "C"};
        //Initializing the bag with the given entries.
        ResizableArrayBag<String> checkBag = new ResizableArrayBag<>(contents);

        //Adding the entries of the bag to the string
        for(int i = 0; i < contents.length;i++)
        {
            addBag.add(contents[i]);
        }
        assertArrayEquals(checkBag.toArray(), addBag.toArray());
    }


    /**
     * Tests the toArray() method implemented in ResizableArrayBag
     */
    @org.junit.jupiter.api.Test
    void toArray() {
        String[] contents = {"A","B", "C"};
        ResizableArrayBag<String> checkBag = new ResizableArrayBag<>(contents);
        Object[] checkBagArray = checkBag.toArray();
        Object[] testBagArray = {"A", "B", "C"};
        assertArrayEquals(testBagArray, checkBagArray);
    }

    /**
     * Checks if the ResizableArrayBag is empty or has no entries
     */
    @org.junit.jupiter.api.Test
    void isEmpty() {
        BagInterface<String> isEmptyBag = new ResizableArrayBag<>();
        boolean test = isEmptyBag.isEmpty();
        assertTrue(test);

    }

    /**
     * Checks the implementation of the number of entries in the bag
     */
    @org.junit.jupiter.api.Test
    void getCurrentSize() {
        String[] contents = {"A","B", "C", "D", "E", "F"};
        BagInterface<String> currentSizeBag = new ResizableArrayBag<>(contents);
        assertEquals(contents.length, currentSizeBag.getCurrentSize());
    }

    /**
     * Checks if thecount of the number of entries match as planned.
     */
    @org.junit.jupiter.api.Test
    void getFrequencyOf() {
        String[] contents = {"A","E", "C", "E", "E", "F"};
        String[] contents1 = {"F","E", "F", "F", "E", "F"};
        BagInterface<String> frequencyBag = new ResizableArrayBag<>(contents);
        BagInterface<String> frequencyBag1 = new ResizableArrayBag<>(contents1);

        assertEquals(3,frequencyBag.getFrequencyOf("E"));
        assertEquals(4, frequencyBag1.getFrequencyOf("F"));
    }

    /**
     * Checks if the bag contains a certain entry.
     */
    @org.junit.jupiter.api.Test
    void contains() {
        String[] contents = {"A","E", "C", "D", "E", "F"};
        BagInterface<String> containsBag = new ResizableArrayBag<>(contents);
        boolean containsTrue = containsBag.contains("E");
        boolean containsFalse = containsBag.contains("Z");

        assertTrue(containsTrue);
        assertFalse(containsFalse);
    }

    /**
     * Checks if the bag cna be cleared in which there exists no entries
     */
    @org.junit.jupiter.api.Test
    void clear() {
        String[] contents = {"A","E", "C", "E", "E", "F"};
        BagInterface<String> clearBag = new ResizableArrayBag<>(contents);
        clearBag.clear();
        boolean isClear = clearBag.isEmpty();
        assertTrue(isClear);
    }

    /**
     * Checks if the bag can remove any entry
     */
    @org.junit.jupiter.api.Test
    void remove() {
        String[] contents = {"A","E", "C", "E", "E", "F"};
        BagInterface<String> removeBag = new ResizableArrayBag<>(contents);
        int beforeRemove = removeBag.getCurrentSize();
        String removed = removeBag.remove();
        int afterRemove = removeBag.getCurrentSize();

        assertEquals("java.lang.String", removed.getClass().getName());
        assertEquals(beforeRemove, afterRemove+1);

    }

    /**
     * Checks if the bag can remove a specific entry
     */
    @org.junit.jupiter.api.Test
    void testRemove() {
        String[] contents = {"A","E", "C", "E", "E", "F"};
        BagInterface<String> removeBag = new ResizableArrayBag<>(contents);
        boolean removed = removeBag.remove("E");
        assertTrue(removed);
    }

    /**
     * Checks if the returned bag can contain the common entries in both bags
     */
    @org.junit.jupiter.api.Test
    void intersection() {
        String[] contents1 = {"A", "B", "C", "Z"};
        String[] contents2 = {"C", "D", "E", "Z"};
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        for(int i=0; i < contents1.length; i++)
        {
            bag1.add(contents1[i]);
            bag2.add(contents2[i]);
        }

        String[] checkBag = {"C", "Z"};
        BagInterface<String> intersectionBag = bag1.intersection(bag2);
        assertArrayEquals(checkBag, intersectionBag.toArray());
    }

    /**
     * Checks if the returned bag can contain entries from both bags
     */
    @org.junit.jupiter.api.Test
    void union() {
        String[] contents1 = {"A", "B", "C"};
        String[] contents2 = {"C", "D", "Z"};
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        for(int i=0; i < contents1.length; i++)
        {
            bag1.add(contents1[i]);
            bag2.add(contents2[i]);
        }

        String[] checkBag = {"A", "B", "C", "C", "D", "Z"};
        BagInterface<String> unionBag = bag1.union(bag2);
        assertArrayEquals(checkBag, unionBag.toArray());

    }

    /**
     * Checks if the returned bag contains the difference from the other bag.
     */
    @org.junit.jupiter.api.Test
    void difference() {
        String[] contents1 = {"A", "B", "C", "D", "E"};
        String[] contents2 = {"A", "A", "D", "D", "Z"};
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        for(int i=0; i < contents1.length; i++)
        {
            bag1.add(contents1[i]);
            bag2.add(contents2[i]);
        }

        BagInterface<String> differenceBag = bag1.difference(bag2);
        boolean check = differenceBag.contains("B") && differenceBag.contains("C") && differenceBag.contains("E");
        assertTrue(check);
    }

}