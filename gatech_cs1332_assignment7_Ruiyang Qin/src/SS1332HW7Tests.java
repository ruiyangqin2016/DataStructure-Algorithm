import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Gatech CS1332 Spring 2018 HW7
 *
 * @author sergeys
 * @version 1
 */
public class SS1332HW7Tests {
    public static final long TIMEOUT = 200L;

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddException() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(0);

        assertEquals(1, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRootNull() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);

        assertEquals(0, avl.getRoot().getData().intValue());
        assertEquals(0, avl.remove(0).intValue());
        assertNull(avl.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);

        assertEquals(0, avl.getRoot().getData().intValue());

        avl.clear();

        assertNull(avl.getRoot());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveException1() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveException2() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetException1() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetException2() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsException() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        AVLInterface<Integer> avl = new AVL<>();

        assertEquals(-1, avl.height());
    }


    /*
                0
        -4            4 
    -6      -2     2     6
  -7  -5  -3  -1  1 3   5 7
     */
    @Test(timeout = TIMEOUT)
    public void testAddBasic() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(-4);
        avl.add(4);
        avl.add(-2);
        avl.add(2);
        avl.add(-6);
        avl.add(6);
        avl.add(-1);
        avl.add(1);
        avl.add(-3);
        avl.add(3);
        avl.add(-7);
        avl.add(7);
        avl.add(-5);
        avl.add(5);

        assertEquals(0, avl.getRoot().getData().intValue());

        assertEquals(-4, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-6,
                avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(-7, avl.getRoot().getLeft().getLeft().getLeft().getData()
                .intValue());
        assertEquals(-5, avl.getRoot().getLeft().getLeft().getRight().getData()
                .intValue());
        assertEquals(-2,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(-3, avl.getRoot().getLeft().getRight().getLeft().getData()
                .intValue());
        assertEquals(-1, avl.getRoot().getLeft().getRight().getRight().getData()
                .intValue());

        assertEquals(4, avl.getRoot().getRight().getData().intValue());
        assertEquals(6,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(7, avl.getRoot().getRight().getRight().getRight().getData()
                .intValue());
        assertEquals(5, avl.getRoot().getRight().getRight().getLeft().getData()
                .intValue());
        assertEquals(2,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(3, avl.getRoot().getRight().getLeft().getRight().getData()
                .intValue());
        assertEquals(1, avl.getRoot().getRight().getLeft().getLeft().getData()
                .intValue());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorBasic() {
        AVLInterface<Integer> avl = new AVL<>(
                Arrays.asList(0, -4, 4, -2, 2, -6, 6, -1, 1, -3, 3, -7, 7, -5,
                        5));

        assertEquals(0, avl.getRoot().getData().intValue());

        assertEquals(-4, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-6,
                avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(-7, avl.getRoot().getLeft().getLeft().getLeft().getData()
                .intValue());
        assertEquals(-5, avl.getRoot().getLeft().getLeft().getRight().getData()
                .intValue());
        assertEquals(-2,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(-3, avl.getRoot().getLeft().getRight().getLeft().getData()
                .intValue());
        assertEquals(-1, avl.getRoot().getLeft().getRight().getRight().getData()
                .intValue());

        assertEquals(4, avl.getRoot().getRight().getData().intValue());
        assertEquals(6,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(7, avl.getRoot().getRight().getRight().getRight().getData()
                .intValue());
        assertEquals(5, avl.getRoot().getRight().getRight().getLeft().getData()
                .intValue());
        assertEquals(2,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(3, avl.getRoot().getRight().getLeft().getRight().getData()
                .intValue());
        assertEquals(1, avl.getRoot().getRight().getLeft().getLeft().getData()
                .intValue());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        assertEquals(1, avl.size());
        assertEquals(0, avl.getRoot().getData().intValue());

        avl.add(1);
        assertEquals(2, avl.size());
        assertEquals(0, avl.getRoot().getData().intValue());
        assertEquals(1, avl.getRoot().getRight().getData().intValue());

        avl.add(2);
        assertEquals(3, avl.size());
        assertEquals(1, avl.getRoot().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getData().intValue());
        assertEquals(2, avl.getRoot().getRight().getData().intValue());

        avl.add(4);
        assertEquals(4, avl.size());
        assertEquals(1, avl.getRoot().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getData().intValue());
        assertEquals(2, avl.getRoot().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getRight().getData().intValue());

        avl.add(3);
        assertEquals(5, avl.size());
        assertEquals(1, avl.getRoot().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getData().intValue());
        assertEquals(3, avl.getRoot().getRight().getData().intValue());
        assertEquals(2,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getRight().getData().intValue());

        avl.add(5);
        assertEquals(6, avl.size());
        assertEquals(3, avl.getRoot().getData().intValue());
        assertEquals(1, avl.getRoot().getLeft().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(2,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(4, avl.getRoot().getRight().getData().intValue());
        assertEquals(5,
                avl.getRoot().getRight().getRight().getData().intValue());

        avl.add(6);
        assertEquals(7, avl.size());
        assertEquals(3, avl.getRoot().getData().intValue());
        assertEquals(1, avl.getRoot().getLeft().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(2,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(5, avl.getRoot().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(6,
                avl.getRoot().getRight().getRight().getData().intValue());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        AVLInterface<Integer> avl =
                new AVL<>(Arrays.asList(0, 1, 2, 4, 3, 5, 6));

        assertEquals(7, avl.size());
        assertEquals(3, avl.getRoot().getData().intValue());
        assertEquals(1, avl.getRoot().getLeft().getData().intValue());
        assertEquals(0, avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(2,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(5, avl.getRoot().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(6,
                avl.getRoot().getRight().getRight().getData().intValue());
    }

    /*
                0
        -4            4
    -6      -2     2     6
  -7  -5  -3  -1  1 3   5 7
     */
    @Test(timeout = TIMEOUT)
    public void testRemove2ChildrenBasic() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(-4);
        avl.add(4);
        avl.add(-2);
        avl.add(2);
        avl.add(-6);
        avl.add(6);
        avl.add(-1);
        avl.add(1);
        avl.add(-3);
        avl.add(3);
        avl.add(-7);
        avl.add(7);
        avl.add(-5);
        avl.add(5);

        assertEquals(15, avl.size());

        assertEquals(-6, avl.remove(-6).intValue());
        assertEquals(-5,
                avl.getRoot().getLeft().getLeft().getData().intValue());

        assertEquals(14, avl.size());

        assertEquals(-2, avl.remove(-2).intValue());
        assertEquals(-1,
                avl.getRoot().getLeft().getRight().getData().intValue());

        assertEquals(13, avl.size());

        assertEquals(2, avl.remove(2).intValue());
        assertEquals(3,
                avl.getRoot().getRight().getLeft().getData().intValue());

        assertEquals(12, avl.size());

        assertEquals(6, avl.remove(6).intValue());
        assertEquals(7,
                avl.getRoot().getRight().getRight().getData().intValue());

        assertEquals(11, avl.size());
    }

    /*
                0
        -4            4
    -6      -2     2     6
  -7  -5  -3  -1  1 3   5 7
     */
    @Test(timeout = TIMEOUT)
    public void testRemove2Children() {
        AVLInterface<Integer> avl = new AVL<>(
                Arrays.asList(0, -4, 4, -6, -2, 2, 6, -7, -5, -3, -1, 1, 3, 5,
                        7));

        assertEquals(15, avl.size());

        assertEquals(0, avl.remove(0).intValue());
        assertEquals(1, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(14, avl.size());

        assertEquals(1, avl.remove(1).intValue());
        assertEquals(2, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(13, avl.size());

        assertEquals(2, avl.remove(2).intValue());
        assertEquals(3, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getRoot().getRight().getData().intValue());
        assertEquals(7,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(5, avl.getRoot().getRight().getLeft().getRight().getData()
                .intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(12, avl.size());

        assertEquals(3, avl.remove(3).intValue());
        assertEquals(4, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getRoot().getRight().getData().intValue());
        assertEquals(5,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(7,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(11, avl.size());

        assertEquals(4, avl.remove(4).intValue());
        assertEquals(5, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getRoot().getRight().getData().intValue());
        assertEquals(7,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(10, avl.size());

        assertEquals(5, avl.remove(5).intValue());
        assertEquals(-4, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getRoot().getRight().getData().intValue());
        assertEquals(7,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(-2,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(-3, avl.getRoot().getRight().getLeft().getLeft().getData()
                .intValue());
        assertEquals(-1, avl.getRoot().getRight().getLeft().getRight().getData()
                .intValue());
        assertEquals(-6, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-7,
                avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(9, avl.size());

        assertEquals(-4, avl.remove(-4).intValue());
        assertEquals(-3, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(8, avl.size());

        assertEquals(-3, avl.remove(-3).intValue());
        assertEquals(-2, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(7, avl.size());

        assertEquals(-2, avl.remove(-2).intValue());
        assertEquals(-1, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(6, avl.size());

        assertEquals(-1, avl.remove(-1).intValue());
        assertEquals(6, avl.getRoot().getData().intValue());
        assertEquals(6, avl.getSecondLargest().intValue());
        assertEquals(5, avl.size());

        assertEquals(6, avl.remove(6).intValue());
        assertEquals(-6, avl.getRoot().getData().intValue());
        assertEquals(-7, avl.getRoot().getLeft().getData().intValue());
        assertEquals(7, avl.getRoot().getRight().getData().intValue());
        assertEquals(-5,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(-5, avl.getSecondLargest().intValue());
        assertEquals(4, avl.size());

        assertEquals(-6, avl.remove(-6).intValue());
        assertEquals(-5, avl.getRoot().getData().intValue());
        assertEquals(-5, avl.getSecondLargest().intValue());
        assertEquals(3, avl.size());

        assertEquals(-5, avl.remove(-5).intValue());
        assertEquals(7, avl.getRoot().getData().intValue());
        assertEquals(-7, avl.getSecondLargest().intValue());
        assertEquals(2, avl.size());

        assertEquals(7, avl.remove(7).intValue());
        assertEquals(-7, avl.getRoot().getData().intValue());
        assertEquals(1, avl.size());

        assertEquals(-7, avl.remove(-7).intValue());
        assertSame(null, avl.getRoot());
        assertEquals(0, avl.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testSecondLargestException() {
        new AVL<>(Collections.singletonList(0)).getSecondLargest();
    }

    /*
                0
        -4            4
    -6      -2     2     6
  -7          -1    3   5
     */
    @Test(timeout = TIMEOUT)
    public void testRemove1ChildBasic() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(-4);
        avl.add(4);
        avl.add(-2);
        avl.add(2);
        avl.add(-6);
        avl.add(6);
        avl.add(-1);
        avl.add(3);
        avl.add(-7);
        avl.add(5);

        assertEquals(11, avl.size());

        assertEquals(-6, avl.remove(-6).intValue());
        assertEquals(-7,
                avl.getRoot().getLeft().getLeft().getData().intValue());

        assertEquals(10, avl.size());

        assertEquals(-2, avl.remove(-2).intValue());
        assertEquals(-1,
                avl.getRoot().getLeft().getRight().getData().intValue());

        assertEquals(9, avl.size());

        assertEquals(6, avl.remove(6).intValue());
        assertEquals(5,
                avl.getRoot().getRight().getRight().getData().intValue());

        assertEquals(8, avl.size());

        assertEquals(2, avl.remove(2).intValue());
        assertEquals(3,
                avl.getRoot().getRight().getLeft().getData().intValue());

        assertEquals(7, avl.size());
    }

    /*
                0
        -4            4
    -6      -2     2     6
  -7          -1    3   5
     */
    @Test(timeout = TIMEOUT)
    public void testRemove1Child() {
        AVLInterface<Integer> avl =
                new AVL<>(Arrays.asList(0, -4, 4, -2, 2, -6, 6, -1, 3, -7, 5));

        assertEquals(11, avl.size());

        assertEquals(6, avl.remove(6).intValue());
        assertEquals(5,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(10, avl.size());

        assertEquals(5, avl.remove(5).intValue());
        assertEquals(4,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(9, avl.size());

        assertEquals(2, avl.remove(2).intValue());
        assertSame(null, avl.getRoot().getRight().getLeft());
        assertEquals(8, avl.size());

        /*
                    0
            -4            3
        -6      -2
      -7          -1
         */

        assertEquals(3, avl.remove(3).intValue());

        /*
                 -4
            -6       0
        -7        -2   4
                   -1
        */
        assertEquals(-4, avl.getRoot().getData().intValue());
        assertEquals(-6, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-7,
                avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(0, avl.getRoot().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(-2,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(-1, avl.getRoot().getRight().getLeft().getRight().getData()
                .intValue());
        assertEquals(7, avl.size());

        assertEquals(-6, avl.remove(-6).intValue());

        /*
                 -2
            -4       0
         -7       -1   4
        */
        assertEquals(-2, avl.getRoot().getData().intValue());
        assertEquals(-4, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-7,
                avl.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(0, avl.getRoot().getRight().getData().intValue());
        assertEquals(4,
                avl.getRoot().getRight().getRight().getData().intValue());
        assertEquals(-1,
                avl.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(6, avl.size());

        assertEquals(-4, avl.remove(-4).intValue());
        assertEquals(-7, avl.getRoot().getLeft().getData().intValue());
        assertEquals(5, avl.size());

        assertEquals(-7, avl.remove(-7).intValue());

        /*
                 0
             -2     4
               -1
        */
        assertEquals(0, avl.getRoot().getData().intValue());
        assertEquals(-2, avl.getRoot().getLeft().getData().intValue());
        assertEquals(-1,
                avl.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(4, avl.getRoot().getRight().getData().intValue());
        assertEquals(4, avl.size());
    }

    /*
                0
        -4            4
    -6      -2     2     6
     */
    @Test(timeout = TIMEOUT)
    public void testRemove0ChildrenBasic() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(-4);
        avl.add(4);
        avl.add(-2);
        avl.add(2);
        avl.add(-6);
        avl.add(6);

        assertEquals(7, avl.size());

        assertEquals(-6, avl.remove(-6).intValue());
        assertNull(avl.getRoot().getLeft().getLeft());

        assertEquals(6, avl.size());

        assertEquals(-2, avl.remove(-2).intValue());
        assertNull(avl.getRoot().getLeft().getRight());

        assertEquals(5, avl.size());

        assertEquals(2, avl.remove(2).intValue());
        assertNull(avl.getRoot().getRight().getLeft());

        assertEquals(4, avl.size());

        assertEquals(6, avl.remove(6).intValue());
        assertNull(avl.getRoot().getRight().getRight());

        assertEquals(3, avl.size());

        assertEquals(-4, avl.remove(-4).intValue());
        assertNull(avl.getRoot().getLeft());

        assertEquals(2, avl.size());

        assertEquals(4, avl.remove(4).intValue());
        assertNull(avl.getRoot().getRight());

        assertEquals(1, avl.size());

        assertEquals(0, avl.remove(0).intValue());
        assertNull(avl.getRoot());

        assertEquals(0, avl.size());
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        AVLInterface<Integer> avl = new AVL<>();

        avl.add(0);
        avl.add(-4);
        avl.add(-2);
        avl.add(-1);
        avl.add(-3);
        avl.add(-6);
        avl.add(-7);
        avl.add(-5);
        avl.add(4);
        avl.add(2);
        avl.add(1);
        avl.add(3);
        avl.add(6);
        avl.add(7);
        avl.add(5);

        for (int i = -7; i <= 7; i++) {
            assertTrue(avl.contains(i));
        }

        assertFalse(avl.contains(-8));
        assertFalse(avl.contains(-9));
        assertFalse(avl.contains(-10));
        assertFalse(avl.contains(8));
        assertFalse(avl.contains(9));
        assertFalse(avl.contains(10));
    }

    @SuppressWarnings("RedundantStringConstructorCall")
    @Test(timeout = TIMEOUT)
    public void testGet() {
        AVL<String> avl = new AVL<>();

        String s4 = new String("4");
        String s3 = new String("3");
        String s5 = new String("5");

        avl.add(s4);
        avl.add("2");
        avl.add("1");
        avl.add(s3);
        avl.add("6");
        avl.add(s5);
        avl.add("7");


        assertSame(s4, avl.get(new String("4")));
        assertSame(s3, avl.get(new String("3")));
        assertSame(s5, avl.get(new String("5")));
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test(timeout = TIMEOUT)
    public void testEquals() {
        AVLInterface<Integer> avl1 =
                new AVL<>(Arrays.asList(0, -4, 4, -2, 2, -6, 6, -1, 3, -7, 5));
        AVLInterface<Integer> avl2 = avl1;

        assertTrue(avl1.equals(avl2));

        avl2 = new AVL<>(Arrays.asList(0, -4, 4, -2, 2, -6, 6, -1, 3, -7, 5));

        assertTrue(avl1.equals(avl2));

        avl1 = new AVL<>(Arrays.asList(0, 1));
        avl2 = new AVL<>(Arrays.asList(1, 0));

        assertFalse(avl1.equals(avl2));

        assertFalse(avl1.equals(""));
        //An AVL of strings (not equal to Integers)
        assertFalse(avl1.equals(new AVL<>(Arrays.asList("0", "1"))));

        avl1 = new AVL<>();
        //An empty AVL of strings
        assertTrue(avl1.equals(new AVL<String>()));
    }
}
