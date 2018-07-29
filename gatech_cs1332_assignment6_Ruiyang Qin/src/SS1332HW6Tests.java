import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author sergeys
 */
public class SS1332HW6Tests {
    public static final long TIMEOUT = 200L;

    @Test(timeout = TIMEOUT)
    public void testDefaultConstructor() {
        HashMap<Integer, Integer> map = new HashMap<>();
        assertEquals(HashMapInterface.INITIAL_CAPACITY, map.getTable().length);
        assertEquals(0, map.size());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        for (int i = 1; i < HashMapInterface.INITIAL_CAPACITY * 2; i++) {
            HashMap<Integer, Integer> map = new HashMap<>(i);
            assertEquals(i, map.getTable().length);
            assertEquals(0, map.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPut() {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        assertEquals(count, map.size());
        for (int i = -16; i < 16; i++) {
            assertNull(map.put(i, i * 2));
            assertEquals(++count, map.size());
        }

        for (int i = -16; i < 16; i++) {
            assertEquals(i * 2, map.put(i, 0).intValue());
            assertEquals(count, map.size());
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutErr1() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(null, 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutErr2() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutErr3() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(null, null);
    }

    @Test(timeout = TIMEOUT)
    public void testPutResize() {
        HashMap<Integer, Integer> map = new HashMap<>(1);
        assertEquals(1, map.getTable().length);
        assertEquals(0, map.size());

        map.put(0, 0);

        //3 = 2 * 1 + 1
        assertEquals(3, map.getTable().length);
        assertEquals(1, map.size());

        map.put(0, 0);

        // (1 + 1) / 3 = 0.66666666 < 0.67
        assertEquals(3, map.getTable().length);
        assertEquals(1, map.size());

        map.put(1, 0);

        // 2 / 3 = 0.66666666 < 0.67
        assertEquals(3, map.getTable().length);
        assertEquals(2, map.size());

        map.put(1, 0);

        // (2 + 1) / 3 = 1 > 0.67
        assertEquals(7, map.getTable().length);
        assertEquals(2, map.size());

        map.put(2, 0);

        // 3 / 7 = 0.4285714286 < 0.67
        assertEquals(7, map.getTable().length);
        assertEquals(3, map.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutCollisionResolutionAndReplace() {
        HashMap<Integer, Integer> map = new HashMap<>(100);

        map.put(0, 1);

        assertEquals(1, map.size());
        assertEquals(1, map.getTable()[0].getValue().intValue());

        map.put(100, 2);

        assertEquals(2, map.size());
        assertEquals(2, map.getTable()[0].getValue().intValue());
        assertEquals(1, map.getTable()[0].getNext().getValue().intValue());

        map.put(200, 3);

        assertEquals(3, map.size());
        assertEquals(3, map.getTable()[0].getValue().intValue());
        assertEquals(2, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(1,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        map.put(100, 4);

        assertEquals(3, map.size());
        assertEquals(3, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(1,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        map.put(0, 5);

        assertEquals(3, map.size());
        assertEquals(3, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        map.put(200, 6);

        assertEquals(3, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveErr1() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveErr2() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.remove(0);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveCollisionResolution() {
        HashMap<Integer, Integer> map = new HashMap<>(100);

        map.put(0, 1);
        map.put(100, 2);
        map.put(200, 3);

        map.put(100, 4);
        map.put(0, 5);
        map.put(200, 6);

        assertEquals(3, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        assertEquals(6, map.remove(200).intValue());
        assertEquals(2, map.size());
        assertEquals(4, map.getTable()[0].getValue().intValue());
        assertEquals(5, map.getTable()[0].getNext().getValue().intValue());

        map.put(200, 6); //Undoes the remove as its first

        assertEquals(3, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        assertEquals(4, map.remove(100).intValue());
        assertEquals(2, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(5, map.getTable()[0].getNext().getValue().intValue());

        map = new HashMap<>(100);

        map.put(0, 1);
        map.put(100, 2);
        map.put(200, 3);

        map.put(100, 4);
        map.put(0, 5);
        map.put(200, 6);

        assertEquals(3, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());

        assertEquals(5, map.remove(0).intValue());
        assertEquals(2, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetErr1() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetErr2() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        HashMap<Integer, Integer> map = new HashMap<>(100);

        map.put(0, 1);
        map.put(100, 2);
        map.put(200, 3);
        map.put(1, -1);

        assertEquals(4, map.size());
        assertEquals(2, map.get(100).intValue());
        assertEquals(1, map.get(0).intValue());
        assertEquals(3, map.get(200).intValue());
        assertEquals(-1, map.get(1).intValue());

        map.put(100, 4);
        map.put(0, 5);
        map.put(200, 6);

        assertEquals(4, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());
        assertEquals(-1, map.getTable()[1].getValue().intValue());

        assertEquals(4, map.get(100).intValue());
        assertEquals(5, map.get(0).intValue());
        assertEquals(6, map.get(200).intValue());
        assertEquals(-1, map.get(1).intValue());

        assertEquals(4, map.size());
        assertEquals(6, map.getTable()[0].getValue().intValue());
        assertEquals(4, map.getTable()[0].getNext().getValue().intValue());
        assertEquals(5,
                map.getTable()[0].getNext().getNext().getValue().intValue());
        assertEquals(-1, map.getTable()[1].getValue().intValue());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsKeyErr1() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        HashMap<Integer, Integer> map = new HashMap<>(100);

        map.put(0, 1);
        map.put(100, 2);
        map.put(200, 3);
        map.put(1, -1);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(100));
        assertTrue(map.containsKey(200));
        assertTrue(map.containsKey(1));

        map.put(100, 4);
        map.put(0, 5);
        map.put(200, 6);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(100));
        assertTrue(map.containsKey(200));
        assertTrue(map.containsKey(1));
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        HashMap<Integer, Integer> map = new HashMap<>(100);

        map.put(0, 1);
        map.put(100, 2);
        map.put(200, 3);
        map.put(1, -1);

        assertEquals(4, map.size());
        assertEquals(100, map.getTable().length);

        map.clear();

        assertEquals(0, map.size());
        assertEquals(HashMapInterface.INITIAL_CAPACITY, map.getTable().length);
    }


    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = -16; i < 16; i++) {
            map.put(i, 0);
            set.add(i);
            assertEquals(set, map.keySet());
        }

        for (int i = -16; i < 16; i++) {
            map.put(i, 1);
            assertEquals(set, map.keySet());
        }
    }

  @Test(timeout = TIMEOUT)
    public void testValues() {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -16; i < 16; i++) {
            map.put(i, 0);
            list.add(0);
            List<Integer> values = map.values();
            values.sort(Integer::compareTo);
            assertEquals(list, values);
        }

        for (int i = -16; i < 16; i++) {
            list.remove(Integer.valueOf(0)); //Call the one that takes an object
            map.put(i, 1);
            list.add(1);
            List<Integer> values = map.values();
            values.sort(Integer::compareTo);
            assertEquals(list, values);
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeBackingArrayErr1() {
        HashMap<Integer, Integer> map = new HashMap<>(1);

        map.resizeBackingTable(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeBackingArrayErr2() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.resizeBackingTable(-2);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeBackingArrayErr3() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);

        map.resizeBackingTable(2);
    }

    @Test(timeout = TIMEOUT)
    public void testResizeBackingArray() {
        HashMap<Integer, Integer> map = new HashMap<>(3);

        map.put(0, 1);
        map.put(6, 2);

        assertEquals(3, map.getTable().length);
        assertEquals(2, map.getTable()[0].getValue().intValue());
        assertEquals(1, map.getTable()[0].getNext().getValue().intValue());

        map.resizeBackingTable(4);

        assertEquals(4, map.getTable().length);
        assertEquals(1, map.getTable()[0].getValue().intValue());
        assertEquals(2, map.getTable()[2].getValue().intValue());

        map.resizeBackingTable(6);

        assertEquals(6, map.getTable().length);
        assertEquals(2, map.getTable()[0].getValue().intValue());
        assertEquals(1, map.getTable()[0].getNext().getValue().intValue());
    }
}