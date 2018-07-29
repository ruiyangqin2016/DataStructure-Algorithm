import java.util.ArrayList;
/**
 * Your implementation of a max heap.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("the data is null");
        }

        backingArray = (T[]) new Comparable[data.size() * 2 + 1];
        size = data.size();

        for (int i = 1; i <= size; i++) {
            T a = data.get(i - 1);
            if (a == null) {
                throw new IllegalArgumentException(
                        "the element in data is null");
            }
            backingArray[i] = a;
        }
        for (int i = size / 2; i > 0; i--) {
            swap(i, backingArray);
        }
    }

    /**
     *
     * @param pos Position start from index 1 to check if the heap is work
     * @param arr pass in backingArray
     */
    private void swap(int pos, T[] arr) {
        if (pos * 2 + 1 < arr.length && arr[pos * 2 + 1] != null) {
            if (arr[pos].compareTo(arr[pos * 2]) < 0
                    || arr[pos].compareTo(arr[pos * 2 + 1]) < 0) {
                if (arr[pos * 2].compareTo(arr[pos * 2 + 1]) > 0) {
                    T holdEle = arr[pos * 2];
                    arr[pos * 2] = arr[pos];
                    arr[pos] = holdEle;
                    swap(pos * 2, arr);
                } else {
                    T holdEle = arr[pos * 2 + 1];
                    arr[pos * 2 + 1] = arr[pos];
                    arr[pos] = holdEle;
                    swap(pos * 2 + 1, arr);
                }
            }
        } else if (pos * 2 < arr.length && arr[pos * 2] != null) {
            if (arr[pos * 2].compareTo(arr[pos]) > 0) {
                T holdEle = arr[pos * 2];
                arr[pos * 2] = arr[pos];
                arr[pos] = holdEle;
            }
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("the item is null");
        }

        if (size < backingArray.length - 1) {
            if (size == 0) { // size is 1
                backingArray[size + 1] = item;
            } else { // size is greater or equal to 2
                backingArray[size + 1] = item;
                addSwap(size + 1, backingArray);
            }
        } else {
            Comparable[] newArr = new Comparable[backingArray.length * 2];
            for (int i = 1; i <= size; i++) {
                newArr[i] = backingArray[i];
            }
            newArr[size + 1] = item;
            addSwap(size + 1, (T[]) newArr);
            backingArray = (T[]) newArr;
        }
        size++;
    }

    /**
     * helper method for add(), swap element based on heap  rule
     * @param pos start from last, move towards frong
     * @param arr backingArray
     */
    private void addSwap(int pos, T[] arr) {
        if (pos > 1) {
            if (arr[pos].compareTo(arr[pos / 2]) > 0) {
                T holdData = arr[pos];
                arr[pos] = arr[pos / 2];
                arr[pos / 2] = holdData;
                addSwap(pos / 2, arr);
            }
        }
    }

    @Override
    public T remove() {
        if (backingArray[1] == null) {
            throw new java.util.NoSuchElementException("the heap is empty");
        }
        T holdData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        removeSwap(1, backingArray);
        return holdData;
    }

    /**
     * Helper method for remove()
     * @param pos start from 1
     * @param arr backingArray
     */
    private void removeSwap(int pos, T[]arr) {
        if (pos * 2 + 1 <= size) {
            if (arr[pos].compareTo(arr[pos * 2]) < 0
                    || arr[pos].compareTo(arr[pos * 2 + 1]) < 0) {
                if (arr[pos * 2].compareTo(arr[pos * 2 + 1]) > 0) {
                    T holdEle = arr[pos * 2];
                    arr[pos * 2] = arr[pos];
                    arr[pos] = holdEle;
                    removeSwap(pos * 2, arr);
                } else {
                    T holdEle = arr[pos * 2 + 1];
                    arr[pos * 2 + 1] = arr[pos];
                    arr[pos] = holdEle;
                    removeSwap(pos * 2 + 1, arr);
                }
            }
        }  else if (pos * 2 <= size) { // without right child
            if (arr[pos].compareTo(arr[pos * 2]) < 0) {
                T holdEle = arr[pos * 2];
                arr[pos * 2] = arr[pos];
                arr[pos] = holdEle;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    
    @Override
    public void clear() {
        Comparable[] arr = new Comparable[INITIAL_CAPACITY];
        backingArray = (T[]) arr;
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
