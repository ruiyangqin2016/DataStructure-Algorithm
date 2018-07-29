import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author sergeys
 */
public class SS1332HW5Tests {
    public final static long TIMEOUT = 200L;

    @Test(timeout = TIMEOUT)
    public void testHeapConstructor1() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        assertTrue(heap.isEmpty());
        assertEquals(HeapInterface.INITIAL_CAPACITY, heap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapConstructor2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        MaxHeap<Integer> heap = new MaxHeap<>(list);

        assertFalse(heap.isEmpty());
        assertEquals(3, heap.getBackingArray().length);
        assertEquals(null, heap.getBackingArray()[0]);
        assertEquals(1, heap.getBackingArray()[1]);

        heap.clear();

        assertEquals(HeapInterface.INITIAL_CAPACITY, heap.getBackingArray().length);
    }

    @Test(timeout = 10000)
    public void testHeapConstructor2Extensive() {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random(1337);

        for (int a = 0; a < 1000; a++) {
            list.clear();
            System.out.println("a is " + a);
            for (int i = 0; i < a; i++) {
                int q = random.nextInt();
                while (list.contains(q)) {
                    q = random.nextInt();
                }
                list.add(q);
            }
//            System.out.println(a + ": List: " + list.toString());
            MaxHeap<Integer> heap = new MaxHeap<>(list);
//            System.out.println(a + ": Heap: " + Arrays.toString(heap.getBackingArray()));

            for (int i = 1; i < a / 2 + 1; i++) {
                if (i * 2 <= a) {
                    assertTrue("Children must be less than the parent", (Integer)heap.getBackingArray()[i] > (Integer)heap.getBackingArray()[i * 2]);
                }
                if (i * 2 + 1 <= a) {
                    assertTrue("Children must be less than the parent", (Integer)heap.getBackingArray()[i] > (Integer)heap.getBackingArray()[i * 2 + 1]);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapConstructor2Exception1() {
        new MaxHeap<>(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapConstructor2Exception2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);
        new MaxHeap<Integer>(list);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapAddException() {
        new MaxHeap<Integer>().add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAddExtensive() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random(1337);

        for (int i = 0; i < 1000; i++) {
            heap.add(random.nextInt());
        }

        for (int i = 1; i < 501; i++) {
            if (i * 2 <= 1000) {
                assertTrue("Children must be less than the parent", (Integer)heap.getBackingArray()[i] > (Integer)heap.getBackingArray()[i * 2]);
            }
            if (i * 2 + 1 <= 1000) {
                assertTrue("Children must be less than the parent", (Integer)heap.getBackingArray()[i] > (Integer)heap.getBackingArray()[i * 2 + 1]);
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAddResize() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1000); //N 1000 N
        MaxHeap<Integer> heap = new MaxHeap<>(list);

        heap.add(999); // N 1000 999
        assertEquals(3, heap.getBackingArray().length);

        heap.add(998); //N 1000 999 998 N N
        assertEquals(6, heap.getBackingArray().length);
        heap.add(997);
        heap.add(996);
        assertEquals(6, heap.getBackingArray().length);

        heap.add(1001);
        assertEquals(12, heap.getBackingArray().length);
        assertEquals(1001, heap.getBackingArray()[1]);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testHeapRemoveException() {
        new MaxHeap<Integer>().remove();
    }

    @Test(timeout = TIMEOUT)
    public void testHeapRemoveExtensive() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random(1337);

        for (int i = 0; i < 1000; i++) {
            heap.add(random.nextInt());
        }

        int length = heap.getBackingArray().length;

        Integer old = heap.remove();

        while (!heap.isEmpty()) {
            Integer remed = heap.remove();
            assertTrue("Remove should remove the largest value", old > remed);
            assertNull(heap.getBackingArray()[heap.size() + 1]);
            old = remed;
        }

        assertEquals(length, heap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testQueueConstructor() {
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>();

        assertTrue(queue.isEmpty());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQueueAddException() {
        new MaxPriorityQueue<Integer>().enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testQueueRemoveException() {
        new MaxPriorityQueue<Integer>().dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testQueueExtensive() {
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>();
        Random random = new Random(1337);

        for (int i = 0; i < 1000; i++) {
            queue.enqueue(random.nextInt());
        }

        Integer old = queue.dequeue();

        while (!queue.isEmpty()) {
            Integer remed = queue.dequeue();
            assertTrue("Remove should remove the largest value", old > remed);
            old = remed;
        }
    }
}
