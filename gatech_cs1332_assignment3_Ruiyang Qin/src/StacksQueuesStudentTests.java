import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */

 import java.util.LinkedList;
public class StacksQueuesStudentTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<>();
        a.add(0, "a");
        a.add(1, "b");
        a.add(2, "c");
        System.out.println(a.toString());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStack() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        LinkedNode node = ((LinkedQueue<Integer>) queue).getHead();
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }

        System.out.println(queue.dequeue());
        LinkedNode node1 = ((LinkedQueue<Integer>) queue).getHead();
        while (node1 != null) {
            System.out.print(node1.getData() + " ");
            node1 = node1.getNext();
        }


    }

    @Test (timeout = TIMEOUT)
    public void testLinkedStack1() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        stack.push(1);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);

        LinkedNode node = ((LinkedStack<Integer>) stack).getHead();
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println();

        System.out.println(stack.pop());
        LinkedNode node1 = ((LinkedStack<Integer>) stack).getHead();
        while (node1 != null) {
            System.out.print(node1.getData() + " ");
            node1 = node1.getNext();
        }
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        stack = new ArrayStack<>();


        stack.push(34);
        System.out.println(stack.pop());
        stack.push(29);
        stack.push(48);
        stack.push(59);


        System.out.println(stack.toString());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];


    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);
        stack.push(69);
        stack.push(79);
        stack.push(89);
        stack.push(99);
        stack.push(109);
        stack.push(119);
        System.out.println(stack.pop());
        stack.push(129);
        System.out.println(Arrays.toString(((ArrayStack<Integer>) stack).getBackingArray()));
        stack.push(139);
        stack.push(149);
        System.out.println(Arrays.toString(((ArrayStack<Integer>) stack).getBackingArray()));

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(Arrays.toString(((ArrayStack<Integer>) stack).getBackingArray()));
//        assertEquals((Integer) 59, stack.pop());
//
//        assertEquals(3, stack.size());
//
//        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
//
//        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
//        expected[0] = 34;
//        expected[1] = 29;
//        expected[2] = 48;
//
//        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(34);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.dequeue();
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(35);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(36);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(37);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.dequeue();
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(1);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(2);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
        queue.enqueue(3);
        System.out.println(Arrays.toString(((ArrayQueue<Integer>) queue).getBackingArray()));
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        assertEquals((Integer) 34, queue.dequeue());

        assertEquals(3, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }
}