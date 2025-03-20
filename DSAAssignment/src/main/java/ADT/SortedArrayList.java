/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADT;

//@author kahfui
public class SortedArrayList<T extends Comparable<T>> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;

    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    //modify
    @Override
    public boolean add(T newEntry) {

        if (newEntry == null) {
            return false;
        }

        if (numberOfEntries == 0) {
            array[0] = newEntry;
            numberOfEntries++;
            return true;
        } else {
            if (isArrayFull()) {
                doubleArray();
            }
            int i = 0;
            while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
                i++;
            }
            makeRoom(i + 1);
            array[i] = newEntry;
            numberOfEntries++;
            return true;
        }
    }

    //remove
    @Override
    public boolean remove(T anEntry) {

        if (numberOfEntries == 0 || anEntry == null) {
            return false;
        } else {
            int i = 0;
            while (i < numberOfEntries && array[i].compareTo(anEntry) < 0) {
                i++;
            }
            if (array[i].equals(anEntry) || array[i].compareTo(anEntry) == 0) {
                removeGap(i + 1);
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean contains(T anEntry) {

        if (anEntry != null) {

            int i = 0;

            while (i < numberOfEntries && anEntry.compareTo(array[i]) > 0) {
                i++;
            }

            if (i < numberOfEntries && anEntry.compareTo(array[i]) == 0) { //(anEntry.equals(array[i]))

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean replace(T oldEntry, T newEntry) {

        if (oldEntry == null || newEntry == null) {
            return false;
        }
        if (numberOfEntries == 0) {
            return false;
        }
        if (remove(oldEntry)) {
            if (add(newEntry)) {
                return true;
            }
        }

        return false;
    }

//get the current entries
    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    //add on
    @Override
    public T get(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean removeNo(int givenPosition) {

        if (numberOfEntries == 0) {
            return false;
        } else {
            removeGap(givenPosition);
            numberOfEntries--;
            return true;
        }
    }

    @Override
    public int indexOf(T element) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (element.equals(array[index])) {
                return index; // Return the index of the found element
            }
        }
        return -1; // Return -1 if the element is not found
    }

    //-----------------------private-------------------------------------------------------------------------------
    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldList = array;
        int oldSize = oldList.length;

        array = (T[]) new Object[2 * oldSize];

        for (int index = 0; index < oldSize; index++) {
            array[index] = oldList[index];
        }
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

}
