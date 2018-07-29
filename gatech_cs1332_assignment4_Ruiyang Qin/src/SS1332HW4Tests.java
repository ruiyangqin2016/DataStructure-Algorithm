import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Gatech CS1332 Spring 2018 HW4
 *
 * @author sergeys
 *
 * @version 1
 */
public class SS1332HW4Tests {
    public final static long TIMEOUT = 200L;
    
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddException() {
        BSTInterface<Integer> bst = new BST<>();
        
        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(0);

        assertEquals(1, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRootNull() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);

        assertEquals(0, bst.getRoot().getData().intValue());
        assertEquals(0, bst.remove(0).intValue());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);

        assertEquals(0, bst.getRoot().getData().intValue());

        bst.clear();

        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveException1() {
        BSTInterface<Integer> bst = new BST<>();

        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveException2() {
        BSTInterface<Integer> bst = new BST<>();

        bst.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetException1() {
        BSTInterface<Integer> bst = new BST<>();

        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetException2() {
        BSTInterface<Integer> bst = new BST<>();

        bst.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsException() {
        BSTInterface<Integer> bst = new BST<>();

        bst.contains(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDistanceBetweenException1() {
        BSTInterface<Integer> bst = new BST<>();
        
        bst.add(0);

        bst.distanceBetween(null , 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDistanceBetweenException2() {
        BSTInterface<Integer> bst = new BST<>();
        
        bst.add(0);

        bst.distanceBetween(0 , null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistanceBetweenException3() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(1);
        
        bst.distanceBetween(1 , 0);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistanceBetweenException4() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(1);
        
        bst.distanceBetween(0 , 1);
    }
    
    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        BSTInterface<Integer> bst = new BST<>();
        
        assertEquals(-1, bst.height());
    }


    /*
                0
        -4            4 
    -6      -2     2     6
  -7  -5  -3  -1  1 3   5 7
     */
    @Test(timeout = TIMEOUT)
    public void testAdd() {
        BSTInterface<Integer> bst = new BST<>();
        
        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-3);
        bst.add(-6);
        bst.add(-7);
        bst.add(-5);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        bst.add(7);
        bst.add(5);

        assertEquals(0, bst.getRoot().getData().intValue());
        
        assertEquals(-4, bst.getRoot().getLeft().getData().intValue());
        assertEquals(-6, bst.getRoot().getLeft().getLeft().getData().intValue());
        assertEquals(-7, bst.getRoot().getLeft().getLeft().getLeft().getData().intValue());
        assertEquals(-5, bst.getRoot().getLeft().getLeft().getRight().getData().intValue());
        assertEquals(-2, bst.getRoot().getLeft().getRight().getData().intValue());
        assertEquals(-3, bst.getRoot().getLeft().getRight().getLeft().getData().intValue());
        assertEquals(-1, bst.getRoot().getLeft().getRight().getRight().getData().intValue());
        
        assertEquals(4, bst.getRoot().getRight().getData().intValue());
        assertEquals(6, bst.getRoot().getRight().getRight().getData().intValue());
        assertEquals(7, bst.getRoot().getRight().getRight().getRight().getData().intValue());
        assertEquals(5, bst.getRoot().getRight().getRight().getLeft().getData().intValue());
        assertEquals(2, bst.getRoot().getRight().getLeft().getData().intValue());
        assertEquals(3, bst.getRoot().getRight().getLeft().getRight().getData().intValue());
        assertEquals(1, bst.getRoot().getRight().getLeft().getLeft().getData().intValue());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove2Children() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-3);
        bst.add(-6);
        bst.add(-7);
        bst.add(-5);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        bst.add(7);
        bst.add(5);

        assertEquals(15, bst.size());

        assertEquals(0, bst.remove(0).intValue());
        assertEquals(-1, bst.getRoot().getData().intValue());

        assertEquals(14, bst.size());

        assertEquals(-6, bst.remove(-6).intValue());
        assertEquals(-5, bst.getRoot().getLeft().getLeft().getRight().getData().intValue());

        assertEquals(13, bst.size());

        assertEquals(4, bst.remove(4).intValue());
        assertEquals(3, bst.getRoot().getRight().getData().intValue());

        assertEquals(12, bst.size());
    }

    /*
                0
        -4            4
    -6      -2     2     6
  -7          -1    3   5
     */
    @Test(timeout = TIMEOUT)
    public void testRemove1Child() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-6);
        bst.add(-7);
        bst.add(4);
        bst.add(2);
        bst.add(3);
        bst.add(6);
        bst.add(5);

        assertEquals(11, bst.size());

        assertEquals(-6, bst.remove(-6).intValue());
        assertEquals(-7, bst.getRoot().getLeft().getLeft().getData().intValue());

        assertEquals(10, bst.size());

        assertEquals(-2, bst.remove(-2).intValue());
        assertEquals(-1, bst.getRoot().getLeft().getRight().getData().intValue());

        assertEquals(9, bst.size());

        assertEquals(6, bst.remove(6).intValue());
        assertEquals(5, bst.getRoot().getRight().getRight().getData().intValue());

        assertEquals(8, bst.size());

        assertEquals(2, bst.remove(2).intValue());
        assertEquals(3, bst.getRoot().getRight().getLeft().getData().intValue());

        assertEquals(7, bst.size());
    }

    /*
                0
        -4            4
    -6      -2     2     6
     */
    @Test(timeout = TIMEOUT)
    public void testRemove0Children() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-6);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        assertEquals(7, bst.size());

        assertEquals(-6, bst.remove(-6).intValue());
        assertNull(bst.getRoot().getLeft().getLeft());

        assertEquals(6, bst.size());

        assertEquals(-2, bst.remove(-2).intValue());
        assertNull(bst.getRoot().getLeft().getRight());

        assertEquals(5, bst.size());

        assertEquals(-4, bst.remove(-4).intValue());
        assertNull(bst.getRoot().getLeft());

        assertEquals(4, bst.size());

        assertEquals(6, bst.remove(6).intValue());
        assertNull(bst.getRoot().getRight().getRight());

        assertEquals(3, bst.size());

        assertEquals(2, bst.remove(2).intValue());
        assertNull(bst.getRoot().getRight().getLeft());

        assertEquals(2, bst.size());

        assertEquals(4, bst.remove(4).intValue());
        assertNull(bst.getRoot().getRight());

        assertEquals(1, bst.size());

        assertEquals(0, bst.remove(0).intValue());
        assertNull(bst.getRoot());

        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-3);
        bst.add(-6);
        bst.add(-7);
        bst.add(-5);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        bst.add(7);
        bst.add(5);

        for (int i = -7; i <= 7; i++) {
            assertTrue(bst.contains(i));
        }

        assertFalse(bst.contains(-8));
        assertFalse(bst.contains(-9));
        assertFalse(bst.contains(-10));
        assertFalse(bst.contains(8));
        assertFalse(bst.contains(9));
        assertFalse(bst.contains(10));
    }


    @SuppressWarnings("RedundantStringConstructorCall")
    @Test(timeout = TIMEOUT)
    public void testGet() {
        BST<String> bst = new BST<>();

        String s4 = new String("4");
        String s3 = new String("3");
        String s5 = new String("5");

        bst.add(s4);
        bst.add("2");
        bst.add("1");
        bst.add(s3);
        bst.add("6");
        bst.add(s5);
        bst.add("7");


        assertSame(s4, bst.get(new String("4")));
        assertSame(s3, bst.get(new String("3")));
        assertSame(s5, bst.get(new String("5")));
    }

    /*
              0
      -4            4
  -6      -2     2     6
-7  -5  -3  -1  1 3   5 7
 */
    @Test(timeout = TIMEOUT)
    public void testDistanceBetween() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-3);
        bst.add(-6);
        bst.add(-7);
        bst.add(-5);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        bst.add(7);
        bst.add(5);

        assertEquals(0, bst.distanceBetween(-7, -7));
        assertEquals(1, bst.distanceBetween(-7, -6));
        assertEquals(2, bst.distanceBetween(-7, -5));
        assertEquals(2, bst.distanceBetween(-7, -4));
        assertEquals(3, bst.distanceBetween(-7, -2));
        assertEquals(3, bst.distanceBetween(-7, 0));
        assertEquals(4, bst.distanceBetween(-7, -3));
        assertEquals(4, bst.distanceBetween(-7, -1));
        assertEquals(4, bst.distanceBetween(-7, 4));
        assertEquals(5, bst.distanceBetween(-7, 2));
        assertEquals(5, bst.distanceBetween(-7, 6));
        assertEquals(6, bst.distanceBetween(-7, 1));
        assertEquals(6, bst.distanceBetween(-7, 3));
        assertEquals(6, bst.distanceBetween(-7, 5));
        assertEquals(6, bst.distanceBetween(-7, 7));
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-1);
        bst.add(-3);
        bst.add(-6);
        bst.add(-7);
        bst.add(-5);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        bst.add(7);
        bst.add(5);


        assertEquals(3, bst.height());
    }

    /*
                0
        -4            4
    -6      -2     2     6
     */
    @Test(timeout = TIMEOUT)
    public void testPreOrder() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-6);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        List<Integer> list = bst.preorder();

        int[] sol = new int[] {0, -4, -6, -2, 4, 2, 6};

        for (int i = 0; i < list.size(); i++) {
            assertEquals(sol[i], list.get(i).intValue());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrder() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-6);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        List<Integer> list = bst.postorder();

        int[] sol = new int[] {-6, -2, -4, 2, 6, 4, 0};

        for (int i = 0; i < list.size(); i++) {
            assertEquals(sol[i], list.get(i).intValue());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInOrder() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-6);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        List<Integer> list = bst.inorder();

        int[] sol = new int[] {-6, -4, -2, 0, 2, 4, 6};

        for (int i = 0; i < list.size(); i++) {
            assertEquals(sol[i], list.get(i).intValue());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrder() {
        BSTInterface<Integer> bst = new BST<>();

        bst.add(0);
        bst.add(-4);
        bst.add(-2);
        bst.add(-6);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        List<Integer> list = bst.levelorder();

        int[] sol = new int[] {0, -4, 4, -6, -2, 2, 6};

        for (int i = 0; i < list.size(); i++) {
            assertEquals(sol[i], list.get(i).intValue());
        }
    }
}
