import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
  * Simple test cases for heaps and priority queues.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author CS 1332 TAs
  * @version 1.0
  */
public class HeapPQStudentTests {

    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(35);
        passedIn.add(40);

        Integer[] expected = new Integer[11];
        expected[1] = 40;
        expected[2] = 35;
        expected[3] = 30;
        expected[4] = 25;
        expected[5] = 10;

        maxHeap = new MaxHeap<>(passedIn);
        assertEquals(5, maxHeap.size());
        assertArrayEquals(expected,
            ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testHeap() {
        maxHeap.add(43);
        maxHeap.add(5);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);

        Integer[] expected = new Integer[HeapInterface.INITIAL_CAPACITY];
        expected[1] = 89;
        expected[2] = 64;
        expected[3] = 43;
        expected[4] = 5;
        expected[5] = 17;
        assertArrayEquals(expected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        assertEquals(new Integer(89), maxHeap.remove());
        assertEquals(new Integer(64), maxHeap.remove());
        assertEquals(3, maxHeap.size());
        assertFalse(maxHeap.isEmpty());
        assertEquals(new Integer(43), maxHeap.remove());
        assertEquals(new Integer(17), maxHeap.remove());
        assertEquals(new Integer(5), maxHeap.remove());
        assertTrue(maxHeap.isEmpty());
        Integer[] finalExpected = new Integer[HeapInterface.INITIAL_CAPACITY];
        assertArrayEquals(finalExpected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPriorityQueue() {
        maxPriorityQueue.enqueue(43);
        maxPriorityQueue.enqueue(5);
        maxPriorityQueue.enqueue(64);
        maxPriorityQueue.enqueue(17);
        maxPriorityQueue.enqueue(89);

        assertEquals(new Integer(89), maxPriorityQueue.dequeue());
        assertEquals(new Integer(64), maxPriorityQueue.dequeue());
        assertEquals(3, maxPriorityQueue.size());
        assertFalse(maxPriorityQueue.isEmpty());
        assertEquals(new Integer(43), maxPriorityQueue.dequeue());
        assertEquals(new Integer(17), maxPriorityQueue.dequeue());
        assertEquals(new Integer(5), maxPriorityQueue.dequeue());
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
