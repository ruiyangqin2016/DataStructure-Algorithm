import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * HashMapStudentTests
 *
 * @author Neha Deshpande
 * @version 1.0
 */
public class test {

    private HashMap<String, String> animals;
    private static final int TIMEOUT = 200;
    private MapEntry<String, String>[] table;

    /**
     *
     */
    private void printStringHashMap() {
        table = animals.getTable();
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + " --> ");
            MapEntry<String, String> current = table[i];
            while (current != null) {
                System.out.print(" (" + current.getKey() + ", " + current.getValue() + ") -> ");
                current = current.getNext();
            }
            System.out.println();
        }
    }

    @Before
    public void setUp() {
        animals = new HashMap<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testputbasic() {
        System.out.println("Testing put -> put(Chihuahua, Dog)...");
        animals.put(new String("Chihuahua"), "Dog");
        System.out.println("Checking if size is incremented...");
        assertEquals(1, animals.size());
        printStringHashMap();

        System.out.println("\nTesting put -> (Arabian, Horse)");
        animals.put(new String("Arabian"), "Horse");
        System.out.println("Checking if size is incremented...");
        assertEquals(2, animals.size());
        printStringHashMap();


        //checking for exception thrown
        System.out.print("IllegalArgumentException thrown when key is null?: -");
        try {
            animals.put(null, "test1");
        } catch (IllegalArgumentException e) {
            System.out.println("yes");
        }

        System.out.print("IllegalArgumentException thrown when value is null?: -");
        try {
            animals.put("test2", null);
        } catch (IllegalArgumentException e) {
            System.out.println("yes");
        }

        animals.put(null, null);
    }

    @Test(timeout = TIMEOUT)
    public void testPutExternalChaining() {

        putStuffNoChain();
        printStringHashMap();
        System.out.println("\nShould add chain to index 0...");
        assertEquals(6, animals.size());
        putStuffWithChain();
        printStringHashMap();
        System.out.println("Testing to make sure size gets incremented for each MapEntry added to chain...");
        assertEquals(8, animals.size());
        MapEntry<String, String>[] copyTable = animals.getTable();
        System.out.println("Checking if external chaining is implemented properly...");
        System.out.println("(values with same hash(k) should be added to the front of the chain)...");
        assertEquals(copyTable[0].getKey(), "N");
        assertEquals(copyTable[0].getNext().getKey(), "A");
        assertEquals(copyTable[0].getNext().getNext().getKey(), "Jersey");
    }

    @Test(timeout = TIMEOUT)
    public void testPutResize() {
        putLotsaStuff();
        printStringHashMap();
        MapEntry<String, String>[] copyTable = animals.getTable();
        System.out.println("Checking if table has been resized by 2n + 1....");
        assertEquals(copyTable.length, 27);
        System.out.println("Checking size after resize has occurred...");
        assertEquals(9, animals.size());
        System.out.println(table[0] == null);
        assertEquals(table[0].getKey(), "Dalmation");
        assertEquals(table[9].getKey(), "Teacup");
        try {
            assertEquals(table[9].getNext().getKey(), "Siamese");
        } catch (NullPointerException e) {
            System.out.println("YOU HAVE NOT PROPERLY IMPLEMENTED EXTERNAL CHAINING");
            System.exit(0);
        }
        assertEquals(table[11].getKey(), "A");
        assertEquals(table[13].getKey(), "Terrier");
        assertEquals(table[15].getKey(), "Asian");
        assertEquals(table[15].getNext().getKey(), "Cheshire");
        assertEquals(table[23].getKey(), "Jersey");
        assertEquals(table[24].getKey(), "N");
    }

    @Test(timeout = TIMEOUT)
    public void testPutIfKeyIsAlreadyPresent() {
        putStuffNoChain();
        printStringHashMap();
        assertEquals(6, animals.size());
        assertEquals("Cat", animals.put("Cheshire", "Cat breed"));
        System.out.println("Making sure size isn't incremented....");
        assertEquals(6, animals.size());
        MapEntry<String, String>[] copyTable = animals.getTable();
        System.out.println("Checking if value is properly updated");
        assertEquals(table[3].getValue(), "Cat breed");
        printStringHashMap();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContains() {
        putStuffNoChain();
        printStringHashMap();
        System.out.println("Testing contains(Cheshire)...");
        assertTrue(animals.containsKey(new String("Cheshire")));
        System.out.println("Testing contains(Dalmation)...");
        assertTrue(animals.containsKey(new String("Dalmation")));
        System.out.println("Testing contains(Siamese)...");
        assertTrue(animals.containsKey(new String("Siamese")));
        System.out.println("Testing contains(Teacup)");
        assertTrue(animals.containsKey(new String("Teacup")));
        System.out.println("Testing contains(Terrier)...");
        assertTrue(animals.containsKey(new String("Terrier")));
        System.out.println("Testing contains(Jersey)...");
        assertTrue(animals.containsKey(new String("Jersey")));
        assertFalse(animals.containsKey(new String("Capibara")));
        assertFalse(animals.containsKey(new String("[")));

        System.out.println("Testing external chaining: ");
        putStuffWithChain();
        printStringHashMap();
        System.out.println("Testing contains(A)...");
        assertTrue(animals.containsKey(new String("A")));
        System.out.println("Testing contains(N)...");
        assertTrue(animals.containsKey(new String("N")));

        //should throw an illegal argument exception
        animals.containsKey(null);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBasic() {
        putStuffNoChain();
        assertEquals(6, animals.size());
        System.out.println("Before removing 'Cheshire': ");
        printStringHashMap();
        assertEquals("Cat", animals.remove(new String("Cheshire")));
        assertEquals(5, animals.size());
        System.out.println("\nAfter removing 'Cheshire': ");
        printStringHashMap();
        assertFalse(animals.containsKey(new String("Cheshire")));

        System.out.print("throws NoSuchElementException when key not found?: -");
        try {
            animals.remove(new String("Capibara"));
        } catch (NoSuchElementException e) {
            System.out.println("yes");
        }

        System.out.print("throws IllegalArgumentException when key is null: -");
        try {
            animals.remove(null);
        } catch (IllegalArgumentException e) {
            System.out.println("yes");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromExternalChain() {
        putStuffNoChain();
        putStuffWithChain();
        printStringHashMap();
        MapEntry<String, String>[] copyTable = animals.getTable();

        System.out.println("\nAfter removing N...");
        assertEquals("fake", animals.remove("N"));
        printStringHashMap();

        assertEquals(7, animals.size());
        System.out.println("\nAfter adding back N & removing A...");
        assertEquals(copyTable[0].getKey(), "A");
        assertEquals(copyTable[0].getNext().getKey(), "Jersey");
        animals.put("N", "fake");
        assertEquals(8, animals.size());
        assertEquals(copyTable[0].getKey(), "N");
        assertEquals(copyTable[0].getNext().getKey(), "A");
          assertEquals("fake", animals.remove("A"));
        printStringHashMap();
        assertEquals(7, animals.size());
        assertEquals(copyTable[0].getKey(), "N");
        assertEquals(copyTable[0].getNext().getKey(), "Jersey");

        System.out.println("\nAfter removing Jersey...");
        assertEquals("Cow", animals.remove("Jersey"));
        assertEquals(6, animals.size());
        assertEquals(copyTable[0].getKey(), "N");
        assertEquals(copyTable[0].getNext(), null);
        printStringHashMap();

    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        putStuffNoChain();
        System.out.println("Testing get without external chains...");
        assertEquals("Dog", animals.get(new String("Dalmation")));
        assertEquals("Cat", animals.get(new String("Siamese")));
        assertEquals("Pig", animals.get(new String("Teacup")));
        assertEquals("Dog", animals.get(new String("Terrier")));
        assertEquals("Cat", animals.get(new String("Cheshire")));
        assertEquals("Cow", animals.get(new String("Jersey")));

        System.out.println("Testing get with an external chain...");
        putStuffWithChain();
        assertEquals("fake", animals.get(new String("A")));
        assertEquals("fake", animals.get(new String("N")));

        System.out.print("throws NoSuchElementException when key not found: -");
        try {
            animals.get(new String("Capibara"));
        } catch (NoSuchElementException e) {
            System.out.println("yes");
        }

        System.out.print("throws IllegalArgumentException when key is null: -");
        try {
            animals.get(null);
        } catch (IllegalArgumentException e) {
            System.out.println("yes");
        }

    }

    @Test (timeout = TIMEOUT)
    public void testClearWithoutResize() {
        putStuffNoChain();
        printStringHashMap();
        assertEquals(6, animals.size());
        animals.clear();
        assertEquals(0, animals.size());
        System.out.println("\nAfter clear: ");
        printStringHashMap();
        MapEntry<String, String>[] copyTable = animals.getTable();
        assertEquals(copyTable.length, HashMapInterface.INITIAL_CAPACITY);
        MapEntry<String, String>[] expected = new MapEntry[copyTable.length];
        Assert.assertArrayEquals(copyTable, expected);
    }

    @Test (timeout = TIMEOUT)
    public void testClearWithResize() {
        putLotsaStuff();
        printStringHashMap();
        assertEquals(9, animals.size());
        animals.clear();
        assertEquals(0, animals.size());
        System.out.println("\nAfter clear: ");
        printStringHashMap();
        MapEntry<String, String>[] copyTable = animals.getTable();
        System.out.println("Checking if resize sets table length back to initial capacity...");
        assertEquals(copyTable.length, HashMapInterface.INITIAL_CAPACITY);
        MapEntry<String, String>[] expected = new MapEntry[copyTable.length];
        Assert.assertArrayEquals(copyTable, expected);
    }

    @Test (timeout = TIMEOUT)
    public void testKeySetBasic() {
        putStuffNoChain();
        Set<String> keys = animals.keySet();
        Collection<String> collectionOfKeys = new ArrayList<>();
        collectionOfKeys.add("Jersey");
        collectionOfKeys.add("Cheshire");
        collectionOfKeys.add("Dalmation");
        collectionOfKeys.add("Terrier");
        collectionOfKeys.add("Teacup");
        assertTrue(keys.containsAll(collectionOfKeys));
        collectionOfKeys.add("Blah");
        assertFalse(keys.containsAll(collectionOfKeys));
    }

    @Test (timeout = TIMEOUT)
    public void testKeySetAdvanced() {
        putLotsaStuff();
        Set<String> keys = animals.keySet();
        Collection<String> collectionOfKeys = new ArrayList<>();
        collectionOfKeys.add("Jersey");
        collectionOfKeys.add("Cheshire");
        collectionOfKeys.add("Dalmation");
        collectionOfKeys.add("Terrier");
        collectionOfKeys.add("Teacup");
        collectionOfKeys.add("A");
        collectionOfKeys.add("N");
        collectionOfKeys.add("Asian");
        assertTrue(keys.containsAll(collectionOfKeys));
        collectionOfKeys.add("Blah");
        assertFalse(keys.containsAll(collectionOfKeys));
    }

    @Test (timeout = TIMEOUT)
    public void testValuesNoChains() {
        putStuffNoChain();
        printStringHashMap();
        List<String> values = animals.values();
        int i = 0;
        String[] arrayOfValues = {"Cow", "Cat", "Dog", "Dog", "Cat", "Pig"};
        for (String s: values) {
            if (i < arrayOfValues.length) {
                assertEquals(arrayOfValues[i], s);
            }
            i++;
        }

    }

    @Test (timeout = TIMEOUT)
    public void testValuesWithChains() {
        putStuffNoChain();
        putStuffWithChain();
        printStringHashMap();
        List<String> values = animals.values();
        int i = 0;
        String[] arrayOfValues = {"fake", "fake", "Cow", "Cat", "Dog", "Dog", "Cat", "Pig"};
        for (String s: values) {
            if (i < arrayOfValues.length) {
                assertEquals(arrayOfValues[i], s);
            }
            i++;
        }

    }

    @Test (timeout = TIMEOUT)
    public void testValuesForResizedTable() {
        putLotsaStuff();
        printStringHashMap();
        List<String> values = animals.values();
        int i = 0;
        String[] arrayOfValues = {"Dog", "Pig", "Cat", "fake", "Dog", "Elephant", "Cat", "Cow", "fake"};
        for (String s: values) {
            if (i < arrayOfValues.length) {
                assertEquals(arrayOfValues[i], s);
            }
            i++;
        }

    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeThrowsExceptionWhenLengthIsNegative() {
        animals.resizeBackingTable(-1);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeExceptionWhenLengthLessThanSize() {
        putStuffNoChain();
        putStuffWithChain();
        printStringHashMap();
        animals.resizeBackingTable(7);
    }

    @Test (timeout = TIMEOUT)
    public void testResize() {
        putStuffNoChain();
        putStuffWithChain();
        printStringHashMap();
        animals.resizeBackingTable(8);
        System.out.println("\nAfter resize and new hashing: ");
        printStringHashMap();
        MapEntry<String, String>[] copyTable = animals.getTable();
        assertEquals(8, animals.size());
        assertEquals("Jersey", copyTable[0].getKey());
        assertEquals("Dalmation", copyTable[1].getKey());
        assertEquals("A", copyTable[1].getNext().getKey());
        assertEquals("Teacup", copyTable[2].getKey());
        assertEquals("Siamese", copyTable[3].getKey());
        assertEquals("Terrier", copyTable[5].getKey());
        assertEquals("N", copyTable[6].getKey());
        assertEquals("Cheshire", copyTable[7].getKey());
    }

    /**
     * Put a baseline of items to the hash map.
     */
    private void putStuffNoChain() {
        animals.put(new String("Dalmation"), "Dog");
        animals.put(new String("Siamese"), "Cat");
        animals.put(new String("Teacup"), "Pig");
        animals.put(new String("Terrier"), "Dog");
        animals.put(new String("Cheshire"), "Cat");
        animals.put(new String("Jersey"), "Cow");

    }

    private void putStuffWithChain() {
        animals.put(new String("A"), "fake");
        animals.put(new String("N"), "fake");
    }

    private void putLotsaStuff() {
        animals.put(new String("Dalmation"), "Dog");
        animals.put(new String("Siamese"), "Cat");
        animals.put(new String("Teacup"), "Pig");
        animals.put(new String("Terrier"), "Dog");
        animals.put(new String("Cheshire"), "Cat");
        animals.put(new String("Jersey"), "Cow");
        animals.put(new String("A"), "fake");
        animals.put(new String("N"), "fake");
        animals.put(new String("Asian"), "Elephant");
    }
}
