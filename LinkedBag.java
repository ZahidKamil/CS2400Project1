// package com.company;
/**
 /**
 A class of bags whose entries are stored in a chain of linked nodes.
 The bag is never full.
 */
public final class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;       // Reference to first node
    private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry.
     @return  True. */
    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;  // Make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode;       // New node is at beginning of chain
        numberOfEntries++;

        return true;
    } // end add

    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in this bag. */
    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while

        return result;
        // Note: The body of this method could consist of one return statement,
        // if you call Arrays.copyOf
    } // end toArray

    /** Sees whether this bag is empty.
     @return  True if the bag is empty, or false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    } // end isEmpty

    /** Gets the number of entries currently in this bag.
     @return  The integer number of entries currently in the bag. */
    public int getCurrentSize()
    {
        return numberOfEntries;
    } // end getCurrentSize

// STUBS:

    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal
     was successful, or null. */
    public T remove()
    {
        T result = null;
        if(firstNode!=null)
        {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false otherwise. */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node removeEntry = getReferenceTo(anEntry);
        if(removeEntry!=null)
        {
            removeEntry.setData(firstNode.getData()); //Switching the data of the node found with the firstnode
            firstNode = firstNode.getNextNode(); //removing the first node
            numberOfEntries--;
            result = true;
        }
        return result;
    } // end remove

    /**
     * @param anEntry The entry to be checked if it exists in the bag
     * @return A reference to the node containing the entry, if located or null otherwise
     */
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null))
        {
            if(anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }
        }
        return currentNode;
    }
    /** Removes all entries from this bag. */
    public void clear()
    {
        while(!isEmpty())
        {
            remove();
        }
    } // end clear

    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
     @return  The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry)
    {
        int counter = 0;
        int frequency = 0;
        Node currentNode = firstNode;
        while(counter < numberOfEntries && currentNode != null) //Need to check this
        {
            if(anEntry.equals(currentNode.getData()))
            {
                frequency++;
            }
            counter++;
            currentNode = currentNode.getNextNode();
        }

        return frequency;

    } // end getFrequencyOf

    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to locate.
     @return  True if the bag contains anEntry, or false otherwise. */
    public boolean contains(T anEntry)
    {
        Node currentNode = firstNode;
        while(currentNode != null)
        {
            if(anEntry.equals(currentNode.getData()))
            {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;

    } // end contains

    /**
     *
     * @param otherBag a bag implementing BagInterface<T>
     * @return a BagInterface<T> of the intersection bag containing the common items
     */
    public BagInterface<T> intersection(BagInterface<T> otherBag)
    {
        BagInterface<T> commonItemsBag = new LinkedBag<>();
        Node currentNode = firstNode;

        //Checking the intersection of the bags
        while(currentNode != null)
        {
            if(otherBag.contains(currentNode.getData()))
            {
                commonItemsBag.add(currentNode.getData());
            }
            currentNode = currentNode.getNextNode();
        }

        return commonItemsBag;
    }// end intersection


    /**
     *
     * @param otherBag a bag that implements BagInterface<T>
     * @return a bag of union of 2 bags of entries.
     */
    public BagInterface<T> union(BagInterface<T> otherBag)
    {
        BagInterface<T> unionBag = new LinkedBag<>();
        T[] otherBagArray = otherBag.toArray();
        Node currentNode = firstNode;
        //Adding entries of the referenced bag to the union bag
        for(int i = 0; i < otherBagArray.length; i++)
        {
            unionBag.add(otherBagArray[i]);
        }

        //Adding the entries of this bag to the union bag
        while(currentNode != null)
        {
            unionBag.add(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }

        return unionBag;
    }// end union


    /**
     *
     * @param otherBag
     * @return a bag containing the difference of this.bag compared to the input bag
     */
    public BagInterface<T> difference(BagInterface<T> otherBag)
    {
        BagInterface<T> differenceBag = new LinkedBag<>();
        T[] otherBagArray = otherBag.toArray();
        Node currentNode = firstNode;
        while(currentNode!= null)
        {
            differenceBag.add(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }

        currentNode = firstNode;
        int index = 0;
        while(currentNode != null && index < otherBagArray.length)
        {
            if(differenceBag.contains(otherBagArray[index]))
            {
                differenceBag.remove(otherBagArray[index]);
            }
            currentNode = currentNode.getNextNode();
            index++;
        }

        return differenceBag;
    }

    private class Node
    {
        private T    data; // Entry in bag
        private Node next; // Link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        public T getData() {
            return data;
        }

        public Node getNextNode() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextNode(Node next) {
            this.next = next;
        }
    } // end Node
} // end LinkedBag1




