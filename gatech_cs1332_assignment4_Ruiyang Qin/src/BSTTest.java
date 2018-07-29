import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTTest {

    private BST<Integer> bst;
    @Before
    public void setUp() throws Exception {
        bst = new BST<>();
    }
    @Test
    public void basicTest() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst.add(10);
        bst.add(7);
        bst.add(15);
        bst.add(2);
        bst.add(8);
        bst.add(13);
        bst.add(20);
        assertEquals(7, bst.size());
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(15));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(13));
        assertTrue(bst.contains(20));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(14));
        assertFalse(bst.contains(25));
        assertFalse(bst.contains(9));
        assertFalse(bst.contains(16));
        assertEquals(Integer.valueOf(10), bst.getRoot().getData());
        assertEquals(Integer.valueOf(7), bst.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(15), bst.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(2), bst.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(8), bst.getRoot().getLeft().getRight().getData());
        assertEquals(Integer.valueOf(13), bst.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(20), bst.getRoot().getRight().getRight().getData());
        assertEquals(Integer.valueOf(7), bst.remove(7));
        assertEquals(Integer.valueOf(15), bst.remove(15));
        assertEquals(Integer.valueOf(2), bst.remove(2));
        assertEquals(Integer.valueOf(8), bst.remove(8));
        assertEquals(Integer.valueOf(10), bst.remove(10));
        assertEquals(Integer.valueOf(13), bst.getRoot().getData());
        assertEquals(2, bst.size());
        bst.clear();
        List<Integer> testList = new ArrayList<>();
        testList.add(10);
        testList.add(7);
        testList.add(15);
        testList.add(2);
        testList.add(8);
        testList.add(13);
        testList.add(20);
        bst = new BST<>(testList);
        assertEquals(7, bst.size());
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(15));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(13));
        assertTrue(bst.contains(20));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(14));
        assertFalse(bst.contains(25));
        assertFalse(bst.contains(9));
        assertFalse(bst.contains(16));
        assertEquals(Integer.valueOf(10), bst.getRoot().getData());
        assertEquals(Integer.valueOf(7), bst.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(15), bst.getRoot().getRight().getData());
        assertEquals(Integer.valueOf(2), bst.getRoot().getLeft().getLeft().getData());
        assertEquals(Integer.valueOf(8), bst.getRoot().getLeft().getRight().getData());
        assertEquals(Integer.valueOf(13), bst.getRoot().getRight().getLeft().getData());
        assertEquals(Integer.valueOf(20), bst.getRoot().getRight().getRight().getData());
        assertEquals(Integer.valueOf(7), bst.remove(7));
        assertEquals(Integer.valueOf(15), bst.remove(15));
        assertEquals(Integer.valueOf(2), bst.remove(2));
        assertEquals(Integer.valueOf(8), bst.remove(8));
        assertEquals(Integer.valueOf(10), bst.remove(10));
        assertEquals(Integer.valueOf(13), bst.getRoot().getData());
        assertEquals(2, bst.size());
    }
    @Test
    public void traversalTest() {
        bst.add(10);
        bst.add(7);
        bst.add(15);
        bst.add(2);
        bst.add(8);
        bst.add(13);
        bst.add(20);
        List<Integer> preorderList = new ArrayList<>();
        preorderList.add(10);
        preorderList.add(7);
        preorderList.add(2);
        preorderList.add(8);
        preorderList.add(15);
        preorderList.add(13);
        preorderList.add(20);
        assertEquals(preorderList, bst.preorder());
        List<Integer> inorderList = new ArrayList<>(preorderList);
        Collections.sort(inorderList);
        assertEquals(inorderList, bst.inorder());
        List<Integer> postorderList = new ArrayList<>();
        postorderList.add(2);
        postorderList.add(8);
        postorderList.add(7);
        postorderList.add(13);
        postorderList.add(20);
        postorderList.add(15);
        postorderList.add(10);
        assertEquals(postorderList, bst.postorder());
        List<Integer> levelorderList = new ArrayList<>();
        levelorderList.add(10);
        levelorderList.add(7);
        levelorderList.add(15);
        levelorderList.add(2);
        levelorderList.add(8);
        levelorderList.add(13);
        levelorderList.add(20);
        assertEquals(levelorderList, bst.levelorder());

    }
    @Test
    public void exceptionTest() {
        try {
            bst.add(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            bst.remove(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            bst.get(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            bst.remove(0);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            bst.get(0);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            bst.contains(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        bst.add(0);
        try {
            bst.distanceBetween(null, 0);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            bst.distanceBetween(0, null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            bst.distanceBetween(0, 1);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            BST<Integer> badBST = new BST<>(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void specialMethods() {
        assertEquals(-1, bst.height());
        bst.add(10);
        assertEquals(0, bst.height());
        bst.add(7);
        assertEquals(1, bst.height());
        bst.add(15);
        assertEquals(1, bst.height());
        bst.add(2);
        assertEquals(2, bst.height());
        bst.add(8);
        assertEquals(2, bst.height());
        bst.add(13);
        bst.add(20);
        assertEquals(2, bst.height());
        assertEquals(0, bst.distanceBetween(10, 10));
        assertEquals(1, bst.distanceBetween(10, 7));
        assertEquals(1, bst.distanceBetween(10, 15));
        assertEquals(2, bst.distanceBetween(10, 2));
        assertEquals(2, bst.distanceBetween(10, 8));
        assertEquals(2, bst.distanceBetween(10, 13));
        assertEquals(2, bst.distanceBetween(10, 20));
        assertEquals(0, bst.distanceBetween(7, 7));
        assertEquals(1, bst.distanceBetween(7, 2));
        assertEquals(1, bst.distanceBetween(7, 8));
        assertEquals(1, bst.distanceBetween(7, 10));
        assertEquals(2, bst.distanceBetween(7, 15));
        assertEquals(3, bst.distanceBetween(7, 13));
        assertEquals(3, bst.distanceBetween(7, 20));
        assertEquals(0, bst.distanceBetween(2, 2));
        assertEquals(1, bst.distanceBetween(2, 7));
        assertEquals(2, bst.distanceBetween(2, 8));
        assertEquals(2, bst.distanceBetween(2, 10));
        assertEquals(3, bst.distanceBetween(2, 15));
        assertEquals(4, bst.distanceBetween(2, 13));
        assertEquals(4, bst.distanceBetween(2, 20));
        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

    }
    @Test
    public void edgeCaseTesting() {
        bst.add(10);
        assertEquals(Integer.valueOf(10), bst.get(10));
        bst.remove(10);
        assertNull(bst.getRoot());
        assertEquals(0, bst.size());
        List<Integer> blankList = new ArrayList<>();
        assertEquals(blankList, bst.preorder());
        assertEquals(blankList, bst.inorder());
        assertEquals(blankList, bst.postorder());
        assertEquals(blankList, bst.levelorder());
        bst.add(10);
        bst.add(7);
        bst.add(15);
        bst.add(2);
        bst.add(8);
        bst.add(13);
        bst.add(20);
        bst.remove(10);
        assertEquals(Integer.valueOf(8), bst.getRoot().getData());
        assertNull(bst.getRoot().getLeft().getRight());
        bst.clear();
        bst.add(10);
        bst.add(10);
        bst.add(10);
        assertEquals(1, bst.size());
        assertNull(bst.getRoot().getLeft());
        assertNull(bst.getRoot().getRight());
        bst.clear();
        bst.add(10);
        bst.add(7);
        bst.add(15);
        bst.add(9);
        bst.add(8);
        bst.remove(10);
        assertEquals(Integer.valueOf(9), bst.getRoot().getData());
        assertEquals(Integer.valueOf(7), bst.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(8), bst.getRoot().getLeft().getRight().getData());
    }
}