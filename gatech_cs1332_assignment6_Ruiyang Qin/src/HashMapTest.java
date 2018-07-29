import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap<Integer, Integer> map;
    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
    }
    @Test
    public void basicTest() {
        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
        assertNull(map.put(1, 1));
        assertEquals(1, map.size());
        assertTrue(map.containsKey(1));
        assertEquals(Integer.valueOf(1), map.get(1));
        assertEquals(Integer.valueOf(1), map.getTable()[1].getValue());
        assertNull(map.put(2, 2));
        assertEquals(2, map.size());
        assertTrue(map.containsKey(2));
        assertEquals(Integer.valueOf(2), map.get(2));
        assertEquals(Integer.valueOf(2), map.getTable()[2].getValue());
        assertEquals(Integer.valueOf(1), map.put(1, 3));
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(3), map.get(1));
        assertEquals(Integer.valueOf(3), map.remove(1));
        assertFalse(map.containsKey(1));
        assertEquals(1, map.size());
        assertNull(map.getTable()[1]);
        map.clear();
        assertFalse(map.containsKey(2));
        assertEquals(0, map.size());
        assertEquals(13, map.getTable().length);
        map = new HashMap<>(20);
        assertEquals(20, map.getTable().length);
    }
    @Test
    public void externalChainTest() {
        assertNull(map.put(0, 0));
        assertNull(map.put(13, 13));
        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(13));
        for (int i = 1; i < 13; i++) {
            assertNull(map.getTable()[i]);
        }
        assertEquals(Integer.valueOf(13), map.getTable()[0].getValue());
        assertEquals(Integer.valueOf(0), map.getTable()[0].getNext().getValue());
        map.put(26, 26);
        assertEquals(Integer.valueOf(26), map.getTable()[0].getValue());
        assertEquals(Integer.valueOf(13), map.getTable()[0].getNext().getValue());
        assertEquals(Integer.valueOf(0), map.getTable()[0].getNext().getNext().getValue());
    }
    @Test
    public void resizeTest() {
        map.put(0, 0);
        map.put(13, 13);
        map.put(1, 1);
        map.put(27, 27);
        map.put(2, 2);
        map.put(40, 40);
        map.put(14, 14);
        map.put(28, 28);
        assertEquals(13, map.getTable().length);
        map.put(0, 1);
        assertEquals(27, map.getTable().length);
        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(13));
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(27));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(40));
        assertTrue(map.containsKey(14));
        assertTrue(map.containsKey(28));
        assertEquals(8, map.size());
        assertEquals(Integer.valueOf(27), map.getTable()[0].getValue());
        assertEquals(Integer.valueOf(1), map.getTable()[0].getNext().getValue());
        assertEquals(Integer.valueOf(28), map.getTable()[1].getValue());
        assertEquals(Integer.valueOf(1), map.getTable()[1].getNext().getValue());
        assertEquals(Integer.valueOf(2), map.getTable()[2].getValue());
        assertEquals(Integer.valueOf(40), map.getTable()[13].getValue());
        assertEquals(Integer.valueOf(13), map.getTable()[13].getNext().getValue());
        assertEquals(Integer.valueOf(14), map.getTable()[14].getValue());
    }
    @Test
    public void keysAndValues() {
        map.put(0, 0);
        map.put(13, 13);
        map.put(1, 1);
        map.put(27, 27);
        map.put(2, 2);
        map.put(40, 40);
        map.put(14, 14);
        map.put(28, 28);
        Integer[] valArray = {13, 0, 14, 40, 27, 1, 28, 2};
        ArrayList<Integer> valList = new ArrayList<>(Arrays.asList(valArray));
        HashSet<Integer> keySet = new HashSet<>(valList);
        assertEquals(valList, map.values());
        assertEquals(keySet, map.keySet());
    }
    @Test
    public void exceptionTest() {
        try {
            map.put(null, 1);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            map.put(1, null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            map.remove(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            map.remove(1);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            map.get(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            map.get(1);
            fail();
        } catch (NoSuchElementException e) {
        }
        try {
            map.containsKey(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            map.resizeBackingTable(0);
            fail();
        } catch (IllegalArgumentException e) {
        }
        map.put(1, 1);
        map.put(2, 2);
        try {
            map.resizeBackingTable(1);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void edgeCaseTesting() {
        map.put(Integer.MIN_VALUE, 0);
        assertEquals(Integer.valueOf(0), map.get((Integer.MIN_VALUE)));
        assertEquals(Integer.valueOf(0), map.getTable()[(Math.abs(Integer.MIN_VALUE % 13))].getValue());
        map.clear();
        map.put(0, 0);
        map.put(13, 13);
        map.put(26, 26);
        assertTrue(map.containsKey(0));
        assertEquals(Integer.valueOf(0), map.get(0));
        assertEquals(Integer.valueOf(0), map.getTable()[0].getNext().getNext().getValue());
        map.put(0, 5);
        assertTrue(map.containsKey(0));
        assertEquals(Integer.valueOf(5), map.get(0));
        assertEquals(Integer.valueOf(5), map.getTable()[0].getNext().getNext().getValue());
        assertEquals(Integer.valueOf(13), map.remove(13));
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(26), map.getTable()[0].getValue());
        assertEquals(Integer.valueOf(5), map.getTable()[0].getNext().getValue());
        assertNull(map.getTable()[0].getNext().getNext());
        List<Integer> emptyList = new ArrayList<>();
        HashSet<Integer> emptySet = new HashSet<>();
        map.clear();
        assertEquals(emptyList, map.values());
        assertEquals(emptySet, map.keySet());
    }
}