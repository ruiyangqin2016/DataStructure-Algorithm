import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author sergeys
 */
public class SS1332HW3Tests {
    public static final long TIMEOUT = 200;

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void arrayQueueEnqueueException() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void arrayQueueDequeueException() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        queue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void arrayQueuePeek() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        assertNull(queue.peek());

        queue.enqueue(0);

        assertEquals(0, queue.peek().intValue());

        queue.enqueue(1);

        assertEquals(0, queue.peek().intValue());

        queue.dequeue();

        assertEquals(1, queue.peek().intValue());

        queue.dequeue();

        assertNull(queue.peek());
    }

    @Test(timeout = TIMEOUT)
    public void arrayQueueEnqueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Integer[] arr = new Integer[QueueInterface.INITIAL_CAPACITY];

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            queue.enqueue(i);
            linkedList.addLast(i);

            linkedList.toArray(arr);

            assertArrayEquals(arr, queue.getBackingArray());
        }
    }

    @Test(timeout = TIMEOUT)
    public void arrayQueueEnqueueRollover() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Integer[] arr = new Integer[QueueInterface.INITIAL_CAPACITY];

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            queue.enqueue(i);
            linkedList.addLast(i);

            linkedList.toArray(arr);

            assertArrayEquals(queue.getBackingArray(), arr);
        }

        assertEquals(linkedList.removeFirst(), queue.dequeue());
        assertEquals(linkedList.removeFirst(), queue.dequeue());
        assertEquals(linkedList.removeFirst(), queue.dequeue());

        queue.enqueue(100);
        queue.enqueue(101);
        queue.enqueue(102);

        arr[0] = 100;
        arr[1] = 101;
        arr[2] = 102;

        assertArrayEquals(queue.getBackingArray(), arr);
    }

    @Test(timeout = TIMEOUT)
    public void arrayQueueEnqueueExtend() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Integer[] arr = new Integer[QueueInterface.INITIAL_CAPACITY];
        Integer[] arr2 = new Integer[QueueInterface.INITIAL_CAPACITY * 2];

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY * 2; i++) {
            queue.enqueue(i);
            if (i < QueueInterface.INITIAL_CAPACITY) {
                arr[i] = i;
            }
            arr2[i] = i;

            if (i < QueueInterface.INITIAL_CAPACITY) {
                assertArrayEquals(arr, queue.getBackingArray());
            } else {
                assertArrayEquals(arr2, queue.getBackingArray());
            }
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void linkedQueueEnqueueException() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void linkedQueueDequeueException() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void linkedQueue() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY*3; i++) {
            queue.enqueue(i);

            assertEquals(i, queue.getTail().getData().intValue());
        }

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            assertEquals(i, queue.dequeue().intValue());
        }

        LinkedNode<Integer> node = queue.getHead();
        for (int i = QueueInterface.INITIAL_CAPACITY; node != null; i++) {
            assertEquals(i, node.getData().intValue());
            node = node.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void linkedQueueEmpty() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.enqueue(0);
        queue.enqueue(1);

        Assert.assertNotSame(queue.getHead(), queue.getTail());

        assertEquals(0, queue.dequeue().intValue());

        Assert.assertSame(queue.getHead(), queue.getTail());

        assertEquals(1, queue.dequeue().intValue());

        assertNull(queue.getHead());
        assertNull(queue.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void linkedQueuePeek() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        assertNull(queue.peek());

        queue.enqueue(0);

        assertEquals(0, queue.peek().intValue());

        queue.enqueue(1);

        assertEquals(0, queue.peek().intValue());

        queue.dequeue();

        assertEquals(1, queue.peek().intValue());

        queue.dequeue();

        assertNull(queue.peek());
    }

    @Test(timeout = TIMEOUT, expected =  IllegalArgumentException.class)
    public void arrayStackPushException() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.push(null);
    }

    @Test(timeout = TIMEOUT, expected =  NoSuchElementException.class)
    public void arrayStackPopException() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void arrayStackPeek() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        assertNull(stack.peek());

        stack.push(1);

        assertEquals(1, stack.peek().intValue());

        assertEquals(1, stack.pop().intValue());

        assertNull(stack.peek());
    }

    @Test(timeout = TIMEOUT)
    public void arrayStackPush() {
        ArrayStack<Integer> queue = new ArrayStack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Integer[] arr = new Integer[QueueInterface.INITIAL_CAPACITY];

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            queue.push(i);
            linkedList.addLast(i);

            linkedList.toArray(arr);

            assertArrayEquals(arr, queue.getBackingArray());
        }
    }

    @Test(timeout = TIMEOUT)
    public void arrayStackPop() {
        ArrayStack<Integer> queue = new ArrayStack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Integer[] arr = new Integer[QueueInterface.INITIAL_CAPACITY];
        Integer[] arr2 = new Integer[QueueInterface.INITIAL_CAPACITY * 2];

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY * 2; i++) {
            queue.push(i);
            linkedList.addLast(i);

            if (i < QueueInterface.INITIAL_CAPACITY) {
                linkedList.toArray(arr);
                assertArrayEquals(arr, queue.getBackingArray());
            } else {
                linkedList.toArray(arr2);
                assertArrayEquals(arr2, queue.getBackingArray());
            }
        }

        for (int i = QueueInterface.INITIAL_CAPACITY * 2 - 1; i >= 0; i--) {
            assertEquals(linkedList.removeLast(), queue.pop());
        }
    }

    @Test(timeout = TIMEOUT, expected =  IllegalArgumentException.class)
    public void linkedStackPushException() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(null);
    }

    @Test(timeout = TIMEOUT, expected =  NoSuchElementException.class)
    public void linkedStackPopException() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void linkedStackPeek() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        assertNull(stack.peek());

        stack.push(1);

        assertEquals(1, stack.peek().intValue());

        assertEquals(1, stack.pop().intValue());

        assertNull(stack.peek());
    }

    @Test(timeout = TIMEOUT)
    public void linkedStackPush() {
        LinkedStack<Integer> queue = new LinkedStack<>();

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            queue.push(i);

            assertEquals(i, queue.peek().intValue());
        }
    }

    @Test(timeout = TIMEOUT)
    public void linkedStackPop() {
        ArrayStack<Integer> queue = new ArrayStack<>();

        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY * 2; i++) {
            queue.push(i);

            assertEquals(i, queue.peek().intValue());
        }

        for (int i = QueueInterface.INITIAL_CAPACITY * 2 - 1; i >= 0; i--) {
            assertEquals(i, queue.pop().intValue());
        }
    }
}
