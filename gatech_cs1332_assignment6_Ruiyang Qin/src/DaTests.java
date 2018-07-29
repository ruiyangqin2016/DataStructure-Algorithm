import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class DaTests {
    public final static long TIMEOUT = 200L;
    private HashMap<String, Integer> map;

    @Before
    public void setup() {
        map = new HashMap<String, Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testPut() {

        for (char c = '!'; c <= 126; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }
        for (char c = '!'; c <= 126; c++) {
            int hash = String.valueOf(c).hashCode() % map.getTable().length;
            assertEquals(String.valueOf(c), map.getTable()[hash].getKey());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPutResize() {
        for (char c = 'a'; c < 'i'; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }
        assertEquals('i' - 'a', map.size());
        int current = map.getTable().length;
        map.put(String.valueOf('j'), Integer.valueOf('j'));
        assertEquals(current * 2 + 1, map.getTable().length);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutExceptionKey() {
        map.put(null, 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutExceptionVal() {
        map.put("hello", null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {

        map.put("c", 1337);
        assertEquals(1, map.size());
        assertEquals(1337, (int)map.remove("c"));
        assertEquals(0, map.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        map.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testRemoveNotHere() {
        map.put("efqefqe", 30131);
        map.remove("AAAAA");
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        for (char c = 1; c < 128; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }

        for (char c = 127; c >= 1; c--) {
            assertTrue(map.containsKey(String.valueOf(c)));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsKeyException() {
        map.put("hello", 1337);
        map.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsValueEq() {
        map.put("hello", 1337);
        assertTrue(map.containsKey(new String("hello")));
    }
    
    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, map.size());
        for (char c = 'a'; c < 'z'; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }
        assertEquals('z' - 'a', map.size());
        map.clear();
        assertEquals(0, map.size());
        assertEquals(HashMapInterface.INITIAL_CAPACITY, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        for (char c = 'a'; c < 'e'; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }
        assertEquals(HashMapInterface.INITIAL_CAPACITY, map.getTable().length);
        map.resizeBackingTable(99);
        assertEquals(99, map.getTable().length);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeExNeg() {
        
        map.resizeBackingTable(-1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeExSmall() {
    
        for (char c = 'a'; c < 'z'; c++) {
            map.put(String.valueOf(c), Integer.valueOf(c));
        }
        map.resizeBackingTable(13);
    }
    private void printMap() {

        MapEntry<String, Integer>[] table = map.getTable();
        for (int i = 0; i < table.length; i++) {
            MapEntry<String, Integer> current = table[i];
            if (current == null) {
                System.out.println("null");
                continue;
            }
            String line = current.toString();

           while (current.getNext() != null) {
                current = current.getNext();

                line += "  |  ";
                line += current.toString();
            } 
           System.out.println(line);
        }
    }

}