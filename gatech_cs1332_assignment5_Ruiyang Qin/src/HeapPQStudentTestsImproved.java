import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
  * Simple test cases for heaps and priority queues.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author CS 1332 TAs
 *  @author Neha Deshpande
  * @version 1.0
  */
public class HeapPQStudentTestsImproved {

    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    private void printHeap() {
        Comparable[] ints = maxHeap.getBackingArray();
        System.out.print("[ ");
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println("]");
    }
    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        System.out.println("\nChecking if HeapBuild works... (correct organization of heap)");
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(35);
        passedIn.add(40);
        passedIn.add(53);
        passedIn.add(20);
        passedIn.add(2);
        passedIn.add(99);
        passedIn.add(3);
        passedIn.add(232);
        passedIn.add(222);
        passedIn.add(89);

        Integer[] expected = new Integer[27];
        expected[1] = 232;
        expected[2] = 99;
        expected[3] = 222;
        expected[4] = 35;
        expected[5] = 40;
        expected[6] = 89;
        expected[7] = 20;
        expected[8] = 2;
        expected[9] = 25;
        expected[10] = 3;
        expected[11] = 10;
        expected[12] = 53;
        expected[13] = 30;

        maxHeap = new MaxHeap<>(passedIn);
        System.out.println("Initial:  [ null 25 10 30 35 40 53 20 2 99 3 232 222 89 null null null null null null null null null null null null null ]");
        System.out.println("Expected: [ null 232 99 222 35 40 89 20 2 25 3 10 53 30 null null null null null null null null null null null null null ]");
        System.out.print("Actual:   ");
        printHeap();
        assertEquals(13, maxHeap.size());
        assertArrayEquals(expected,
            ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapThrowsIllegalArgumentIfArrayListIsNull() {
        System.out.println("\nChecking if MaxHeap(ArrayList<T>) throws IllegalArgumentException when ArrayList is null...");
        ArrayList<Integer> arr = null;
        maxHeap = new MaxHeap<Integer>(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapThrowsIllegalArgumentIfDataIsNull() {
        System.out.println("\nChecking if MaxHeap(ArrayList<T> throws IllegalArgumentException when data is null...");
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(null);
        maxHeap = new MaxHeap<Integer>(arr);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapAdd() {
        System.out.println("\nTesting add() for heap...");
        System.out.println("Adding first element...");
        maxHeap.add(43);
        printHeap();
        maxHeap.add(5);
        printHeap();
        maxHeap.add(64);
        printHeap();
        maxHeap.add(17);
        printHeap();
        maxHeap.add(89);
        printHeap();

        Integer[] expected = new Integer[HeapInterface.INITIAL_CAPACITY];
        expected[1] = 89;
        expected[2] = 64;
        expected[3] = 43;
        expected[4] = 5;
        expected[5] = 17;
        assertArrayEquals(expected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        maxHeap.add(22);
        printHeap();
        maxHeap.add(100);
        printHeap();
        maxHeap.add(90);
        printHeap();
        maxHeap.add(23);
        printHeap();
        maxHeap.add(45);
        printHeap();
        maxHeap.add(50);
        printHeap();
        maxHeap.add(67);
        printHeap();
        maxHeap.add(7);
        printHeap();
        maxHeap.add(6);
        printHeap();
        maxHeap.add(4);
        printHeap();
        maxHeap.add(3);
        printHeap();
        maxHeap.add(2);
        printHeap();

        Integer[] expectedArray = new Integer[(HeapInterface.INITIAL_CAPACITY)* 2];
        expectedArray[1] = 100;
        expectedArray[2] = 90;
        expectedArray[3] = 89;
        expectedArray[4] = 64;
        expectedArray[5] = 50;
        expectedArray[6] = 67;
        expectedArray[7] = 43;
        expectedArray[8] = 5;
        expectedArray[9] = 23;
        expectedArray[10] = 17;
        expectedArray[11] = 45;
        expectedArray[12] = 22;
        expectedArray[13] = 7;
        expectedArray[14] = 6;
        expectedArray[15] = 4;
        System.out.println("Checking if backingArray resizes...");
        expectedArray[16] = 3;
        expectedArray[17] = 2;
        assertEquals(17, maxHeap.size());

        assertArrayEquals(expectedArray,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        System.out.println("Checking if IllegalArgumentException is thrown when data is null");
        maxHeap.add(null);
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testHeapRemove() {
        System.out.println("\nTesting remove() for heap...");
        maxHeap.add(43);
        maxHeap.add(5);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);
        printHeap();
        assertEquals(new Integer(89), maxHeap.remove());
        printHeap();
        assertEquals(new Integer(64), maxHeap.remove());
        printHeap();
        System.out.println("Checking if size is properly decremented...");
        assertEquals(3, maxHeap.size());
        assertFalse(maxHeap.isEmpty());
        assertEquals(new Integer(43), maxHeap.remove());
        printHeap();
        assertEquals(new Integer(17), maxHeap.remove());
        printHeap();
        assertEquals(new Integer(5), maxHeap.remove());
        printHeap();
        assertTrue(maxHeap.isEmpty());

        Integer[] finalExpected = new Integer[HeapInterface.INITIAL_CAPACITY];
        assertArrayEquals(finalExpected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        System.out.println("Checking if remove() throws NoSuchElementException when heap is empty");
        maxHeap.remove();

    }

    private void printPriorityQueue() {
        Comparable[] ints = maxPriorityQueue.getBackingHeap().getBackingArray();
        System.out.print("[ ");
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println("]");
    }

    @Test(timeout = TIMEOUT)
    public void testPriorityQueue() {
        System.out.println("\nTesting Priority Queue enqueue()...");
        maxPriorityQueue.enqueue(43);
        printPriorityQueue();
        maxPriorityQueue.enqueue(5);
        printPriorityQueue();
        maxPriorityQueue.enqueue(64);
        printPriorityQueue();
        maxPriorityQueue.enqueue(17);
        printPriorityQueue();
        maxPriorityQueue.enqueue(89);
        printPriorityQueue();

        System.out.println("Testing Priority Queue dequeue()....");
        assertEquals(new Integer(89), maxPriorityQueue.dequeue());
        printPriorityQueue();
        assertEquals(new Integer(64), maxPriorityQueue.dequeue());
        printPriorityQueue();
        assertEquals(3, maxPriorityQueue.size());
        printPriorityQueue();
        assertFalse(maxPriorityQueue.isEmpty());
        printPriorityQueue();
        assertEquals(new Integer(43), maxPriorityQueue.dequeue());
        printPriorityQueue();
        assertEquals(new Integer(17), maxPriorityQueue.dequeue());
        printPriorityQueue();
        assertEquals(new Integer(5), maxPriorityQueue.dequeue());
        printPriorityQueue();
        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        for (int i = 0; i < HeapInterface.INITIAL_CAPACITY - 1; i++) {
            maxHeap.add(i * i * i - 74);
        }

        assertEquals(HeapInterface.INITIAL_CAPACITY - 1, maxHeap.size());
        assertEquals(HeapInterface.INITIAL_CAPACITY,
                ((MaxHeap<Integer>) maxHeap).getBackingArray().length);

        maxHeap.add(9 * 9 * (HeapInterface.INITIAL_CAPACITY - 1) - 74);

        assertEquals(HeapInterface.INITIAL_CAPACITY, maxHeap.size());
        assertEquals(HeapInterface.INITIAL_CAPACITY * 2,
                ((MaxHeap<Integer>) maxHeap).getBackingArray().length);
    }
}
