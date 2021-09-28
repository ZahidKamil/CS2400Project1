import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedBagUnitTest {
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
      anotherBag.add("B");
      anotherBag.add("B");
      anotherBag.add("D");
      anotherBag.add("E");
      BagInterface<String> differenceBag = newBag.difference(anotherBag);
      BagInterface<String> differenceBag1 = anotherBag.difference(newBag);
      boolean test = differenceBag.contains("C") && differenceBag.contains("A") && !(differenceBag.contains("B"));
      boolean test1 = differenceBag1.contains("B") && differenceBag1.contains("D") && (differenceBag1.contains("E"));
      assertTrue(test);
      assertTrue(test1);
   }
}