import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    private ArrayQueue<Integer> myAQueue;
    private LinkedQueue<Integer> myLQueue;
    private ArrayStack<Integer> myAStack;
    private LinkedStack<Integer> myLStack;
    @Before
    public void setUp() throws Exception {
        myAQueue = new ArrayQueue<>();
        myLQueue = new LinkedQueue<>();
        myAStack = new ArrayStack<>();
        myLStack = new LinkedStack<>();
    }
    @Test
    public void arrayQueueTest() {
        for (int i = 0; i < 11; i++) {
            myAQueue.enqueue(i);
        }
        assertEquals(11, myAQueue.size());
        assertEquals(Integer.valueOf(0), myAQueue.dequeue());
        assertEquals(Integer.valueOf(1), myAQueue.dequeue());
        assertEquals(Integer.valueOf(2), myAQueue.dequeue());
        myAQueue.enqueue(11);
        myAQueue.enqueue(12);
        myAQueue.enqueue(13);
        System.out.println(Arrays.toString(myAQueue.getBackingArray()));
        assertEquals(11, myAQueue.size());
        for (int i = 3; i < 14; i++) {
            assertEquals(Integer.valueOf(i), myAQueue.dequeue());
        }
        for (int i = 0; i < 11; i++) {
            myAQueue.enqueue(i);
        }
        assertEquals(11, myAQueue.size());
        assertEquals(Integer.valueOf(0), myAQueue.dequeue());
        assertEquals(Integer.valueOf(1), myAQueue.dequeue());
        assertEquals(Integer.valueOf(2), myAQueue.dequeue());
        myAQueue.enqueue(11);
        myAQueue.enqueue(12);
        myAQueue.enqueue(13);
        myAQueue.enqueue(14);
        assertEquals(Integer.valueOf(3), myAQueue.getBackingArray()[0]);
        for (int i = 15; i < 100; i++) {
            myAQueue.enqueue(i);
        }
        assertEquals(97, myAQueue.size());
        for (int i = 3; i < 100; i++) {
            assertEquals(Integer.valueOf(i), myAQueue.dequeue());
        }
        assertEquals(0, myAQueue.size());
        assertTrue(myAQueue.isEmpty());
        for (int i = 0; i < myAQueue.getBackingArray().length; i++) {
            assertNull(myAQueue.getBackingArray()[i]);
        }
        try {
            myAQueue.dequeue();
            fail("No exception thrown for dequeue from empty queue");
        } catch (NoSuchElementException e) {

        }
        try {
            myAQueue.enqueue(null);
            fail("No exception thrown for null");
        } catch (IllegalArgumentException e) {

        }
    }
    @Test
    public void linkedQueueTest() {
        for (int i = 0; i < 11; i++) {
            myLQueue.enqueue(i);
        }
        assertEquals(11, myLQueue.size());
        assertEquals(Integer.valueOf(0), myLQueue.dequeue());
        assertEquals(Integer.valueOf(1), myLQueue.dequeue());
        assertEquals(Integer.valueOf(2), myLQueue.dequeue());
        myLQueue.enqueue(11);
        myLQueue.enqueue(12);
        myLQueue.enqueue(13);
        assertEquals(11, myLQueue.size());
        for (int i = 3; i < 14; i++) {
            assertEquals(Integer.valueOf(i), myLQueue.dequeue());
        }
        assertNull(myLQueue.getHead());
        assertNull(myLQueue.getTail());
        for (int i = 0; i < 11; i++) {
            myLQueue.enqueue(i);
        }
        assertEquals(11, myLQueue.size());
        assertEquals(Integer.valueOf(0), myLQueue.dequeue());
        assertEquals(Integer.valueOf(1), myLQueue.dequeue());
        assertEquals(Integer.valueOf(2), myLQueue.dequeue());
        myLQueue.enqueue(11);
        myLQueue.enqueue(12);
        myLQueue.enqueue(13);
        myLQueue.enqueue(14);
        for (int i = 15; i < 100; i++) {
            myLQueue.enqueue(i);
        }
        assertEquals(97, myLQueue.size());
        for (int i = 3; i < 100; i++) {
            assertEquals(Integer.valueOf(i), myLQueue.dequeue());
        }
        assertEquals(0, myLQueue.size());
        assertTrue(myLQueue.isEmpty());
        try {
            myLQueue.dequeue();
            fail("No exception thrown for dequeue from empty queue");
        } catch (NoSuchElementException e) {

        }
        try {
            myLQueue.enqueue(null);
            fail("No exception thrown for null");
        } catch (IllegalArgumentException e) {

        }
    }
    @Test
    public void arrayStackTest() {
        for (int i = 0; i < 11; i++) {
            myAStack.push(i);
        }
        assertEquals(11, myAStack.size());
        for (int i = 10; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), myAStack.pop());
        }
        assertEquals(0, myAStack.size());
        assertTrue(myAStack.isEmpty());
        try {
            myAStack.push(null);
            fail("No error for null push");
        } catch (IllegalArgumentException e) {

        }
        try {
            myAStack.pop();
            fail("No error for pop from empty stack");
        } catch (NoSuchElementException e) {

        }
        assertNull(myAStack.peek());
        for (int i = 0; i < 100; i++) {
            myAStack.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), myAStack.pop());
        }
        for (int i = 0; i < myAStack.getBackingArray().length; i++) {
            assertNull(myAStack.getBackingArray()[i]);
        }
    }
    @Test
    public void linkedStackTest() {
        for (int i = 0; i < 11; i++) {
            myLStack.push(i);
        }
        assertEquals(11, myLStack.size());
        for (int i = 10; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), myLStack.pop());
        }
        assertEquals(0, myLStack.size());
        assertTrue(myLStack.isEmpty());
        try {
            myLStack.push(null);
            fail("No error for null push");
        } catch (IllegalArgumentException e) {

        }
        try {
            myLStack.pop();
            fail("No error for pop from empty stack");
        } catch (NoSuchElementException e) {

        }
        assertNull(myLStack.peek());
        for (int i = 0; i < 100; i++) {
            myLStack.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), myLStack.pop());
        }
        assertNull(myLStack.getHead());
    }
}