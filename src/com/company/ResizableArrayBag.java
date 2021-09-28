
import java.util.Arrays;
/**
 A class that implements a bag of objects by using an array.
 The bag is never full.
 */
public final class ResizableArrayBag<T> implements BagInterface<T>
{
    private T[] bag; // Cannot be final due to doubling
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25; // Initial capacity of bag
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag whose initial capacity is 25. */
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty bag having a given initial capacity.
     @param initialCapacity  The integer capacity desired. */
    public ResizableArrayBag(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[initialCapacity]; // Unchecked cast
        bag = tempBag;
        numberOfEntries = 0;
        integrityOK = true;
    } // end constructor

    /** Creates a bag containing given entries.
     @param contents  An array of objects. */
    public ResizableArrayBag(T[] contents)
    {
        checkCapacity(contents.length);
        bag = Arrays.copyOf(contents, contents.length);
        numberOfEntries = contents.length;
        integrityOK = true;
    } // end constructor

    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry.
     @return  True. */
    public boolean add(T newEntry)
    {
        checkintegrity();
        if (isArrayFull())
        {
            doubleCapacity();
        } // end if

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    } // end add

    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in this bag. */
    public T[] toArray()
    {
        checkintegrity();

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = bag[index];
        } // end for

        return result;
    } // end toArray

    /** Sees whether this bag is empty.
     @return  True if this bag is empty, or false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    } // end isEmpty

    /** Gets the current number of entries in this bag.
     @return  The integer number of entries currently in this bag. */
    public int getCurrentSize()
    {
        return numberOfEntries;
    } // end getCurrentSize

    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
     @return  The number of times anEntry appears in this ba. */
    public int getFrequencyOf(T anEntry)
    {
        checkintegrity();
        int counter = 0;

        for (int index = 0; index < numberOfEntries; index++)
        {
            if (anEntry.equals(bag[index]))
            {
                counter++;
            } // end if
        } // end for

        return counter;
    } // end getFrequencyOf

    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to locate.
     @return  True if this bag contains anEntry, or false otherwise. */
    public boolean contains(T anEntry)
    {
        checkintegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    } // end contains

    /** Removes all entries from this bag. */
    public void clear()
    {
        while (!isEmpty())
            remove();
    } // end clear

    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal
     was successful, or null. */
    public T remove()
    {
        checkintegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry)
    {
        checkintegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    } // end remove


    /**
     * Locates a given entry within the array bag. Precondition: checkintegrity has been called.
     * @param anEntry
     * @return Returns the index of the entry, if located, or -1 otherwise.
     */
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(bag[index]))
            {
                found = true;
                where = index;
            } // end if
            index++;
        } // end while

        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array.

        return where;
    } // end getIndexOf

    /**
     * Precondition: 0 <= givenIndex < numberOfEntries.
     * Precondition: checkintegrity has been called.
     * @param givenIndex The index for which the entry to be removed.
     * @return returns the entry at a given index within the array. If no such entry exists, returns null.
     */
    private T removeEntry(int givenIndex)
    {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0))
        {
            result = bag[givenIndex];          // Entry to remove
            int lastIndex = numberOfEntries - 1;
            bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
            bag[lastIndex] = null;             // Remove reference to last entry
            numberOfEntries--;
        } // end if

        return result;
    } // end removeEntry

    // Returns true if the array bag is full, or false if not.

    /**
     * @return Returns true if the array bag is full, or false if not.
     */
    private boolean isArrayFull()
    {
        return numberOfEntries >= bag.length;
    } // end isArrayFull

    /**
     * Doubles the size of the array bag.
     * Precondition: checkInitialization has been called.
     */
    private void doubleCapacity()
    {
    	int newLength;
    	if (bag.length == 0) {
    		newLength = 1;
    	} else {
    		newLength = 2 * bag.length;
    	}
        
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } // end doubleCapacity

    /**
     * Throws an exception if the client requests a capacity that is too large.
     * @param capacity an integer that checks if the bag can contain this capacity value
     */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    /**
     * Throws an exception if receiving object is not initialized.
     */
    private void checkintegrity()
    {
        if (!integrityOK)
            throw new SecurityException ("ArrayBag object is corrupt.");
    } // end checkintegrity

    /**
     * @return string containing the elements
     */
    public String toString() 
    {
    	String str = "[";
    	for (int i = 0; i < numberOfEntries; i++)
    		str += (String)bag[i] + ",";
    	if (numberOfEntries != 0)
    		str = str.substring(0, str.length() - 1);
    	str += "]";
    	
    	return str;
    } //end toString

    /**
     *
     * @param otherBag a bag that implements BagInterface<T>
     * @return a bag that contains entries of this bag and the bag to be passed.
     */
    public BagInterface<T> intersection(BagInterface<T> otherBag)
    {
    	int maxCap = 0;
    	if (otherBag.getCurrentSize() > getCurrentSize()) {
    		maxCap = otherBag.getCurrentSize();
    	} else {
    		getCurrentSize();
    	} //end if
    	BagInterface<T> newBag = new ResizableArrayBag(maxCap);
    	
    	int frequencyOfThisEntry = 0;
    	for (int i = 0; i < numberOfEntries; i++) {
    		if (otherBag.contains(bag[i])) {
    			if (otherBag.getFrequencyOf(bag[i]) < getFrequencyOf(bag[i])) {
    				//otherBag's frequency is used
    				frequencyOfThisEntry = otherBag.getFrequencyOf(bag[i]);
    			} else {
    				//this bag's frequency is used
    				frequencyOfThisEntry = getFrequencyOf(bag[i]);
    			}
    		} //end if
         if(!(newBag.contains(bag[i])) && otherBag.contains(bag[i]))
            for (int k = 0; k < frequencyOfThisEntry; k++) {
               newBag.add(bag[i]);
    		} //end for
    	} //end for
        return newBag;
    } //end intersection

    /**
     *
     * @param otherBag a bag that implements BagInterface<T>
     * @return a bag that contains both entries of this bag and the bag to be passed.
     */
    public BagInterface<T> union(BagInterface<T> otherBag)
    {
    	BagInterface<T> newBag = new ResizableArrayBag(otherBag.getCurrentSize() + getCurrentSize());
    	
    	for (int i = 0; i < getCurrentSize(); i++) {
    		newBag.add(bag[i]);
    	} //end for
    	
    	
    	for (int i = 0; i < otherBag.getCurrentSize(); i++) {
    		newBag.add(otherBag.toArray()[i]);
    	} //end for
    	
        return newBag;
    }//union

    /**
     *
     * @param otherBag a bag that implments BagInterface<T>
     * @return a ResizableArrayBag that removes entries from this bag in which the other bag contains
     */
    public BagInterface<T> difference(BagInterface<T> otherBag)
    {
    	BagInterface<T> newBag = new ResizableArrayBag<>();
    	ResizableArrayBag<T> anotherBag = (ResizableArrayBag<T>) otherBag;

        for(int i=0; i<getCurrentSize();i++)
        {
            newBag.add(bag[i]);
        }

    	for (int i = 0; i < otherBag.getCurrentSize(); i++) {
            if(newBag.contains(anotherBag.bag[i]))
            {
                newBag.remove(anotherBag.bag[i]);
            }
    	} //end for
    	
        return newBag;
    }
} // end ResizableArrayBag

/*
 Testing isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.

 Adding to the bag more strings than its initial capacity.
 Adding to the bag: A D B A C A D
 The bag contains 7 string(s), as follows:
 A D B A C A D
 Testing isEmpty with a bag that is not empty:
 isEmpty finds the bag not empty: OK.


 Testing the method getFrequencyOf:
 In this bag, the count of A is 3
 In this bag, the count of B is 1
 In this bag, the count of C is 1
 In this bag, the count of D is 2
 In this bag, the count of Z is 0

 Testing the method contains:
 Does this bag contain A? true
 Does this bag contain B? true
 Does this bag contain C? true
 Does this bag contain D? true
 Does this bag contain Z? false

 Removing a string from the bag:
 remove() returns D
 The bag contains 6 string(s), as follows:
 A D B A C A

 Removing "B" from the bag:
 remove("B") returns true
 The bag contains 5 string(s), as follows:
 A D A A C

 Removing "A" from the bag:
 remove("A") returns true
 The bag contains 4 string(s), as follows:
 C D A A

 Removing "C" from the bag:
 remove("C") returns true
 The bag contains 3 string(s), as follows:
 A D A

 Removing "Z" from the bag:
 remove("Z") returns false
 The bag contains 3 string(s), as follows:
 A D A

 Clearing the bag:
 Testing isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.

 The bag contains 0 string(s), as follows:
 */

