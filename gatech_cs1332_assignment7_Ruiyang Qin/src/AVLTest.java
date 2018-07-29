import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class AVLTest {
    private AVL<Integer> avl;

    @Before
    public void setup() {
        avl = new AVL<>();
    }

    @Test
    public void basicTest() {
        assertEquals(0, avl.size());
        assertNull(avl.getRoot());
        assertEquals(-1, avl.height());
        avl.add(10);
        assertEquals(1, avl.size());
        assertTrue(avl.contains(10));
        assertEquals(Integer.valueOf(10), avl.get(10));
        assertEquals(Integer.valueOf(10), avl.getRoot().getData());
        assertEquals(0, avl.height());
        assertEquals(0, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getBalanceFactor());
        avl.add(5);
        assertEquals(2, avl.size());
        assertTrue(avl.contains(5));
        assertEquals(Integer.valueOf(5), avl.get(5));
        assertEquals(Integer.valueOf(5), avl.getRoot().getLeft().getData());
        assertEquals(1, avl.height());
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(Integer.valueOf(5), avl.getSecondLargest());
        avl.add(15);
        assertEquals(3, avl.size());
        assertTrue(avl.contains(15));
        assertEquals(Integer.valueOf(15), avl.get(15));
        assertEquals(Integer.valueOf(15), avl.getRoot().getRight().getData());
        assertEquals(1, avl.height());
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getBalanceFactor());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avl.getRoot().getRight().getHeight());
        assertEquals(0, avl.getRoot().getRight().getBalanceFactor());
        assertEquals(Integer.valueOf(10), avl.getSecondLargest());
        avl.add(15);
        assertEquals(3, avl.size());
        assertEquals(Integer.valueOf(15), avl.remove(15));
        assertEquals(2, avl.size());
        assertEquals(1, avl.height());
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(1, avl.getRoot().getBalanceFactor());
        assertNull(avl.getRoot().getRight());
        avl.clear();
        assertEquals(0, avl.size());
        assertNull(avl.getRoot());
    }
    @Test
    public void exceptionTest() {
        try {
            avl = new AVL<>(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            avl.add(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            avl.remove(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            avl.remove(5);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            avl.get(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            avl.get(5);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            avl.contains(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            avl.getSecondLargest();
            fail();
        } catch (NoSuchElementException e) {
        }
        avl.add(5);
        try {
            avl.getSecondLargest();
            fail();
        } catch (NoSuchElementException e) {
        }
    }
    @Test
    public void functionalityTest() {
        avl.add(10);
        avl.add(20);
        avl.add(30);
        assertEquals(3, avl.size());
        assertEquals(Integer.valueOf(20), avl.getRoot().getData());
        assertEquals(Integer.valueOf(10), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(30), avl.getRoot().getRight().getData());
        assertEquals(1, avl.height());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(Integer.valueOf(20), avl.getSecondLargest());
        avl.add(40);
        assertEquals(Integer.valueOf(30), avl.getSecondLargest());
        avl.add(35);
        assertEquals(2, avl.height());
        assertEquals(Integer.valueOf(20), avl.getRoot().getData());
        assertEquals(Integer.valueOf(35), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(30), avl.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(40), avl.getRoot().getRight().getRight().getData());
        assertEquals(Integer.valueOf(35), avl.getSecondLargest());
        avl.add(33);
        assertEquals(Integer.valueOf(30), avl.getRoot().getData());
        assertEquals(Integer.valueOf(20), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(35), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(40), avl.getRoot().getRight().getRight().getData());
        assertEquals(Integer.valueOf(33), avl.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(10), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(35), avl.getSecondLargest());
        avl.remove(35);
        assertEquals(2, avl.height());
        assertEquals(Integer.valueOf(40), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(33), avl.getRoot().getRight().getLeft().getData());
        avl.clear();
        AVL<Integer> avl2;
        avl.add(10);
        avl.add(30);
        avl.add(5);
        avl.add(40);
        avl.add(7);
        avl.add(15);
        Integer[] array = {10, 30, 5, 40, 7, 15};
        avl2 = new AVL<>(Arrays.asList(array));
        assertTrue(avl.equals(avl2));
        assertFalse(avl.equals(null));
        assertFalse(avl.equals(new Integer(5)));
        avl2.add(50);
        assertFalse(avl.equals(avl2));
        avl.add(50);
        assertTrue(avl.equals(avl2));
    }
    @Test
    public void edgeCaseTest() {
        avl.add(50);
        avl.add(25);
        assertEquals(Integer.valueOf(25), avl.getSecondLargest());
        avl.add(75);
        avl.add(60);
        assertEquals(Integer.valueOf(60), avl.getSecondLargest());
        avl.clear();
        Integer[] array = {50, 25, 75, 10, 55, 90, 52, 60, 65};
        avl = new AVL<>(Arrays.asList(array));
        assertEquals(Integer.valueOf(50), avl.getRoot().getData());
        assertEquals(Integer.valueOf(25), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(10), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(60), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(55), avl.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(52), avl.getRoot().getRight().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(75), avl.getRoot().getRight().getRight().getData());
        assertEquals(Integer.valueOf(90), avl.getRoot().getRight().getRight().getRight().getData());
        assertEquals(Integer.valueOf(65), avl.getRoot().getRight().getRight().getLeft().getData());
        avl.remove(10);
        assertEquals(Integer.valueOf(60), avl.getRoot().getData());
        assertEquals(Integer.valueOf(50), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(25), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(55), avl.getRoot().getLeft().getRight().getData());
        assertEquals(Integer.valueOf(52), avl.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(75), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(65), avl.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(90), avl.getRoot().getRight().getRight().getData());
        assertEquals(3, avl.height());
        avl.remove(55);
        assertEquals(Integer.valueOf(60), avl.getRoot().getData());
        assertEquals(Integer.valueOf(50), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(25), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(52), avl.getRoot().getLeft().getRight().getData());
        assertEquals(Integer.valueOf(75), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(65), avl.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(90), avl.getRoot().getRight().getRight().getData());
        avl.remove(60);
        assertEquals(Integer.valueOf(65), avl.getRoot().getData());
        assertEquals(Integer.valueOf(50), avl.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(25), avl.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(52), avl.getRoot().getLeft().getRight().getData());
        assertEquals(Integer.valueOf(75), avl.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(90), avl.getRoot().getRight().getRight().getData());
        assertEquals(2, avl.height());
    }
}
